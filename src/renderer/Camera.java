package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

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
    private int antiAliasing = 4;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

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
     * setter for antiAnanlize
     *
     * @param antiAnanlize- the number of pixels in row/col of every pixel
     * @return camera
     */
    public Camera setAntiAnanlize(int antiAnanlize) {
        this.antiAliasing = antiAnanlize;
        return this;
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
     * setter the imageWriter1
     *
     * @param imageWriter1 -
     * @return the camera
     */
    public Camera setImageWriter(ImageWriter imageWriter1) {
        imageWriter = imageWriter1;
        return this;
    }

    /**
     * setter the RayTracerbase
     *
     * @param rayTracerBase1 -
     * @return the camera
     */
    public Camera setRayTracer(RayTracerBase rayTracerBase1) {
        rayTracer = rayTracerBase1;
        return this;
    }

    /**
     * create new ray from camera through view plane to geometries
     *
     * @param nX-Resolution of view plane x-axis
     * @param nY-Resolution of view plane y-axis
     * @param j-number      of columns
     * @param i-            number of rows
     * @return new ray that come from view plane
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        double rY = height / nY;
        double rX = width / nX;

        Point pIJ = startPoint.add(to.scale(distance)); // The center of the View Plane

        double yI = -(i - (nY - 1d) / 2) * rY;
        double xJ = (j - (nX - 1d) / 2) * rX;
        if (yI != 0) pIJ = pIJ.add(up.scale(yI));
        if (xJ != 0) pIJ = pIJ.add(right.scale(xJ));

        return new Ray(startPoint, pIJ.subtract(startPoint));
    }

    /**
     * calauate the point the ray intersect on the view plan
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return
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
     * @param nX-Resolution of view plane x-axis
     * @param nY-Resolution of view plane y-axis
     * @param j-            number of columns of the pixel to intersect
     * @param i-            number of rows of the pixel to intersect
     * @return new ray that come from view plane
     */
    public List<Ray> constructRays(int nX, int nY, int j, int i) {
        List<Ray> rays = new LinkedList<>();

        Point centerPixel = getPixelLocation(nX,nY,j,i);

        double  rY = height/ nY/ antiAliasing;
        double rX = width / nX/ antiAliasing;
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
     * @param nX-Resolution of view plane x-axis
     * @param nY-Resolution of view plane y-axis
     * @param j-            number of columns
     * @param i-            number of rows
     * @return the color of the ray to that point
     */
    private Color castRay(int nX, int nY, int i, int j) {
        return rayTracer.traceRays(constructRays(nX, nY, j, i));
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
            throw new MissingResourceException("Missing Resource", imageWriter.getClass().getName(), "");
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
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                imageWriter.writePixel(j, i, this.castRay(nX, nY, i, j));
            }
        }
        return this;
    }
}
