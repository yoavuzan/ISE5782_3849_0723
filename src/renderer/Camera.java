package renderer;

import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.*;

/**
 * Testing Camera Class
 *
 * @author Yoav Uzan and Yaniv Bar-Tov
 */

public class Camera {

    private Point startPoint; //start of camera
    private Vector up;
    private Vector to;
    private Vector right;
    private double height;
    private double distance;
    private double width;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracerBase;

    /**
     * constructor of camera with throw
     *
     * @param start
     * @param to1
     * @param up1
     */
    public Camera(Point start, Vector to1, Vector up1) {
        if (!isZero(up1.dotProduct(to1)))
            throw new IllegalArgumentException("The vectors is not vertical");

        up = up1.normalize();
        to = to1.normalize();
        right = up1.crossProduct(to1).normalize().scale(-1);
        startPoint = start;


    }

    /**
     * update the width and height
     *
     * @param width1
     * @param height1
     * @return the camera with the new width and height
     */

    public Camera setVPSize(double width1, double height1) {
        width = width1;
        height = height1;
        return this;
    }

    /**
     * update the distance
     *
     * @param distance1
     * @return the camera with the new distance
     */

    public Camera setVPDistance(double distance1) {
        distance = distance1;
        return this;
    }

    /**
     * update the imageWriter1
     *
     * @param imageWriter1
     * @return the camera with the new distance
     */
    public Camera setImageWriter(ImageWriter imageWriter1) {
        imageWriter = imageWriter1;
        return this;
    }

    /**
     * update the RayTracerbase
     *
     * @param rayTracerBase1
     * @return the camera with the new distance
     */
    public Camera setRayTracer(RayTracerBase rayTracerBase1) {
        rayTracerBase = rayTracerBase1;
        return this;
    }

    /**
     * create new ray for use
     *
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return new ray that come from veiw plane
     */

    public Ray constructRay(int nX, int nY, int j, int i) {

        Point pC = startPoint.add(to.scale(distance));

        double rY = height / nY;
        double rX = width / nX;

        double Yi = -(i - (nY - 1d) / 2) * rY;
        double Xj = (j - (nX - 1d) / 2) * rX;
        Point Pij = pC;

        if (Yi != 0) Pij = Pij.add(up.scale(Yi));
        if (Xj != 0) Pij = Pij.add(right.scale(Xj));


        try {
            return new Ray(startPoint, Pij.subtract(startPoint));
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @return vector up
     */

    Vector GetUpVector() {
        return up;
    }

    /**
     * @return vector to
     */
    Vector GetToVector() {
        return to;
    }

    /**
     * @return vector right
     */
    Vector GetRightVector() {
        return right;
    }

    /**
     * @return start point (position)
     */
    Point GetStartPoint() {
        return startPoint;
    }

    /**
     * @return height
     */

    double GetHeight() {
        return height;
    }

    /**
     * @return width
     */
    double GetWidth() {
        return width;
    }

    /**
     * @return distance
     */
    double GetDistance() {
        return distance;
    }




    /**
     * Print Grid of the image
     * @param interval of the grid's line
     * @param intervalColor color of grid's line
     */
    public void printGrid(int interval, Color intervalColor) {
        if(imageWriter == null)
            throw new MissingResourceException("Missing Resource", imageWriter.getClass().getName(), "");
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


    public void writeToImage() {
        if(imageWriter == null)
            throw new MissingResourceException("Missing Resource", imageWriter.getClass().getName(), "");
        imageWriter.writeToImage();
    }

    /**
     * Render image function that throws exception if not all arguments are passed
     */
    public void renderImage(){
        try
        {
            if(imageWriter == null)
                throw new MissingResourceException("Missing Resource", ImageWriter.class.getName(),"");
            if(rayTracerBase == null)
                throw new MissingResourceException("Missing Resource",RayTracerBase.class.getName(),"");
            if(this.startPoint == null || this.to == null || this.right == null || this.up == null || this.width == 0 || this.height == 0)
                throw new MissingResourceException("Missing Resource", Camera.class.getName(), "");

            //rendering the image
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    Ray ray = constructRay(nX, nY, j, i);
                    imageWriter.writePixel(j, i, rayTracerBase.traceRay(ray));
                }
            }
        }
        catch (MissingResourceException e){
            throw new UnsupportedOperationException("Render didn't receive " + e.getClassName());
        }
    }






}
