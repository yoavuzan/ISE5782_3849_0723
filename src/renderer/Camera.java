package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.stream.IntStream;

import static primitives.Util.isZero;

/**
 * Camera class represents a camera viewing objects through a view plane
 *
 * @author Yoav Uzan and Yaniv BarTov
 */

public class Camera {

    private final Point startPoint; //start of camera
    private final Vector up;
    private final Vector to;
    private final Vector right;
    private double height;
    private double distance;
    private double width;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    private int antiAliasing = 1;
    private int maxAdaptiveLevel = 3;
    private boolean useAdaptive = false;

    /**
     * constructor of camera
     *
     * @param start- the Point of the camera position
     * @param to1    -vector to view plane
     * @param up1-   vector up from camera
     */
    public Camera(Point start, Vector to1, Vector up1) {
        if (!isZero(up1.dotProduct(to1)))
            throw new IllegalArgumentException("The vectors is not vertical");
        up = up1.normalize();
        to = to1.normalize();
        right = to1.crossProduct(up1).normalize();
        startPoint = start;
    }

    /**
     * setter for antiAliasing
     *
     * @param antiAliasing- the number of pixels in row/col of every pixel
     */
    public Camera setAntiAliasing(int antiAliasing) {
        this.antiAliasing = antiAliasing;
        return this;
    }

    /**
     * setter for UseAdaptive
     *
     * @param useAdaptive- the number of pixels in row/col of every pixel
     */
    public Camera setUseAdaptive(boolean useAdaptive) {
        this.useAdaptive = useAdaptive;
        return this;
    }

    /**
     * setter for maxAdaptiveLevel
     *
     * @param maxAdaptiveLevel- The depth of the recursion
     */
    public void setMaxAdaptiveLevel(int maxAdaptiveLevel) {
        this.maxAdaptiveLevel = maxAdaptiveLevel;
    }

    /**
     * setter for width height of view plane
     *
     * @param width1  of plane
     * @param height1 of plane
     * @return camera
     */
    public Camera setVPSize(double width1, double height1) {
        width = width1;
        height = height1;
        return this;
    }

    /**
     * setter for distance of view plane
     *
     * @param distance1 of plane
     * @return camera
     */
    public Camera setVPDistance(double distance1) {
        distance = distance1;
        return this;
    }

    /**
     * setter the imageWriter
     *
     * @param imageWriter1 - imageWriter
     * @return the camera
     */
    public Camera setImageWriter(ImageWriter imageWriter1) {
        imageWriter = imageWriter1;
        return this;
    }

    /**
     * setter the RayTracerbase
     *
     * @param rayTracerBase1 - rayTracerBase
     * @return the camera
     */
    public Camera setRayTracer(RayTracerBase rayTracerBase1) {
        rayTracer = rayTracerBase1;
        return this;
    }

    /**
     * calculate the point the ray intersect on the view plan- the center of pixel
     *
     * @param nX- Resolution of view plane x-axis
     * @param nY- Resolution of view plane y-axis
     * @param j-  number of columns of the pixel
     * @param i-  number of rows of the pixel
     * @return the point the ray intersect on the view plan
     */
    private Point getPixelLocation(int nX, int nY, int j, int i) {
        double rY = height / nY;
        double rX = width / nX;

        Point pIJ = startPoint.add(to.scale(distance)); // The center of the View Plane

        double yI = -(i - (nY - 1d) / 2) * rY;
        double xJ = (j - (nX - 1d) / 2) * rX;
        if (yI != 0) pIJ = pIJ.add(up.scale(yI));
        if (xJ != 0) pIJ = pIJ.add(right.scale(xJ));
        return pIJ;
    }

    /**
     * create new ray from camera through view plane to geometries
     *
     * @param nX- Resolution of view plane x-axis
     * @param nY- Resolution of view plane y-axis
     * @param j-  number of columns of the pixel to intersect
     * @param i-  number of rows of the pixel to intersect
     * @return new ray that come from view plane
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        return new Ray(startPoint, getPixelLocation(nX, nY, j, i).subtract(startPoint));
    }

    /**
     * create new ray from camera through view plane to geometries
     *
     * @param nX- Resolution of view plane x-axis
     * @param nY- Resolution of view plane y-axis
     * @param j-  number of columns of the pixel to intersect
     * @param i-  number of rows of the pixel to intersect
     * @return new ray that come from view plane
     */
    public List<Ray> constructRays(int nX, int nY, int j, int i) {
        List<Ray> rays = new LinkedList<>();
        Point centerPixel = getPixelLocation(nX, nY, j, i);
        double rY = height / nY / antiAliasing;
        double rX = width / nX / antiAliasing;
        double x, y;

        for (int row = 0; row < antiAliasing; row++) {
            for (int col = 0; col < antiAliasing; col++) {
                y = -(row - (antiAliasing - 1d) / 2) * rY;
                x = (col - (antiAliasing - 1d) / 2) * rX;
                Point pIJ = centerPixel;
                if (y != 0) pIJ = pIJ.add(up.scale(y));
                if (x != 0) pIJ = pIJ.add(right.scale(x));
                rays.add(new Ray(startPoint, pIJ.subtract(startPoint)));
            }
        }
        return rays;
    }

    /**
     * create the Ray and return the color of the ray
     *
     * @param nX- Resolution of view plane x-axis
     * @param nY- Resolution of view plane y-axis
     * @param j-  number of columns
     * @param i-  number of rows
     * @return the color of the ray to that point
     */
    private Color castRay(int nX, int nY, int i, int j) {
        if (useAdaptive)
            return adaptiveHelper(getPixelLocation(nX, nY, j, i), nX, nY);
        else if (antiAliasing == 1)
            return rayTracer.traceRay(constructRay(nX, nY, j, i));
        else
            return rayTracer.traceRays(constructRays(nX, nY, j, i));
    }

    /**
     * get the point and return the color of the ray to this point
     *
     * @param p- point on the view plane
     * @return color of this point
     */
    private Color calcPointColor(Point p) {
        return rayTracer.traceRay(new Ray(startPoint, p.subtract(startPoint)));
    }

    /**
     * calculate average color of the pixel by using adaptive Super-sampling
     *
     * @param center- the center of the pixel
     * @param nY-     number of pixels to width
     * @param nX-     number of pixels to length
     * @return- the average color of the pixel
     */
    private Color adaptiveHelper(Point center, double nY, double nX) {
        Hashtable<Point, Color> pointColorTable = new Hashtable<Point, Color>();
        double rY = height / nY / 2;
        double rX = width / nX / 2;
        Color upRight = calcPointColor(center.add(up.scale(rY)).add(right.scale(rX)));
        Color upLeft = calcPointColor(center.add(up.scale(rY)).add(right.scale(-rX)));
        Color downRight = calcPointColor(center.add(up.scale(-rY)).add(right.scale(rX)));
        Color downLeft = calcPointColor(center.add(up.scale(-rY)).add(right.scale(-rX)));

        return adaptive(1, center, rX, rY, pointColorTable, upLeft, upRight, downLeft, downRight);
    }

    /**
     * recursive method that return the average color of the pixel- by checking the color of the four corners
     *
     * @param max-         the depth of the recursion
     * @param center-      the center of the pixel
     * @param rX-          the width of the pixel
     * @param rY-          the height of the pixel
     * @param upLeftCol-   the color of the up left corner
     * @param upRightCol-  the color of the up right corner
     * @param downLeftCol- the color of the down left corner
     * @param downRightCol - the color of the down right corner
     * @return the average color of the pixel
     */
    private Color adaptive(int max, Point center, double rX, double rY, Hashtable<Point, Color> pointColorTable,
                           Color upLeftCol, Color upRightCol, Color downLeftCol, Color downRightCol) {
        if (max == maxAdaptiveLevel) {
            return downRightCol.add(upLeftCol).add(upRightCol).add(downLeftCol).reduce(4);
        }
        if (upRightCol.equals(upLeftCol) && downRightCol.equals(downLeftCol) && downLeftCol.equals(upLeftCol))
            return upRightCol;
        else {
            Color rightPCol = getPointColorFromTable(center.add(right.scale(rX)), pointColorTable);
            Color leftPCol = getPointColorFromTable(center.add(right.scale(-rX)), pointColorTable);
            Color upPCol = getPointColorFromTable(center.add(up.scale(rY)), pointColorTable);
            Color downPCol = getPointColorFromTable(center.add(up.scale(-rY)), pointColorTable);
            Color centerCol = calcPointColor(center);

            rX = rX / 2;
            rY = rY / 2;
            upLeftCol = adaptive(max + 1, center.add(up.scale(rY / 2)).add(right.scale(-rX / 2)), rX, rY, pointColorTable,
                    upLeftCol, upPCol, leftPCol, centerCol);
            upRightCol = adaptive(max + 1, center.add(up.scale(rY / 2)).add(right.scale(rX / 2)), rX, rY, pointColorTable,
                    upPCol, upRightCol, centerCol, leftPCol);
            downLeftCol = adaptive(max + 1, center.add(up.scale(-rY / 2)).add(right.scale(-rX / 2)), rX, rY, pointColorTable,
                    leftPCol, centerCol, downLeftCol, downPCol);
            downRightCol = adaptive(max + 1, center.add(up.scale(-rY / 2)).add(right.scale(rX / 2)), rX, rY, pointColorTable,
                    centerCol, rightPCol, downPCol, downRightCol);
            return downRightCol.add(upLeftCol).add(upRightCol).add(downLeftCol).reduce(4);
        }
    }

    /**
     * check if this point exist in the HashTable return his color otherwise calculate the color and save it
     *
     * @param point-           certain point in the pixel
     * @param pointColorTable- dictionary that save points and their color
     * @return the color of the point
     */
    private Color getPointColorFromTable(Point point, Hashtable<Point, Color> pointColorTable) {
        if (!(pointColorTable.containsKey(point))) {
            Color color = calcPointColor(point);
            pointColorTable.put(point, color);
            return color;
        }
        return pointColorTable.get(point);
    }

    /**
     * getter for the vector of the
     *
     * @return vector up
     */
    public Vector getUpVector() {
        return up;
    }

    /**
     * getter for the vector of the
     *
     * @return vector to
     */
    public Vector getToVector() {
        return to;
    }

    /**
     * getter for the vector of the
     *
     * @return vector right
     */
    public Vector getRightVector() {
        return right;
    }

    /**
     * getter for the point of the camera position
     *
     * @return start point (position)
     */
    public Point getStartPoint() {
        return startPoint;
    }

    /**
     * getter of the height
     *
     * @return height
     */
    public double getHeight() {
        return height;
    }

    /**
     * getter of the width
     *
     * @return width
     */
    public double getWidth() {
        return width;
    }

    /**
     * getter of the distance
     *
     * @return distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Print Grid of the image
     *
     * @param interval      of the grid's line
     * @param intervalColor color of grid's line
     */
    public void printGrid(int interval, Color intervalColor) {
        if (imageWriter == null)
            throw new MissingResourceException("Missing Resource", "imageWriter equal to null", "");
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, intervalColor);
                }
            }
        }
    }

    /**
     * function that activates the method that writes the image
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("Missing Resource-", "imageWriter=null", "");
        imageWriter.writeToImage();
    }

    /**
     * create a new image with rays
     *
     * @return new camera after render
     */
    public Camera renderImage() {
        if (imageWriter == null)
            throw new MissingResourceException("Missing Resource", "ImageWriter equal to null", "");
        if (rayTracer == null)
            throw new MissingResourceException("Missing Resource", "RayTracerBase equal to null", "");
        if (this.startPoint == null || this.to == null || this.right == null || this.up == null || this.width == 0 || this.height == 0)
            throw new MissingResourceException("Missing Resource", "Camera equal to null", "");
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        IntStream.range(0, nY).parallel().forEach(i -> {
            IntStream.range(0, nX).parallel().forEach(j -> {
                imageWriter.writePixel(j, i, this.castRay(nX, nY, i, j));
            });
        });
        return this;
    }

}
