package geometries;

import primitives.*;
/**
 * This class will reperesent tube
 *
 * @author Yoav uzan and Yaniv Bartov
 */

public class Clyinder extends Tube {
    double height;

    /**
     * constractor of clyinder
     *
     * @param axisRay Ray that goes through the height of tube
     * @param radius  Radius of tube
     * @param height  height of clyinder
     */
    public Clyinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }


    /**
     * Returns the height of the Cylinder
     *
     * @return The height of the Cylinder.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the normal vector at the given point
     *
     * @param point The point to evaluate the normal at.
     * @return The normal vector of the plane.
     */

    @Override
    public Vector getNormal(Point point) {
        //checks if point is on base of cylinder (bottom circle)
        if (point.distance(axisRay.getPoint()) <= radius)
            return axisRay.getVector();

        //if point is not on bottom, find the center of the top base.
        Point topCenter = axisRay.getPoint().add(axisRay.getVector().scale(height));
        //checks if point is on the other base of cylinder (top circle)
        if (point.distance(topCenter) <= radius)
            return axisRay.getVector();

        //if point is not on either base, call parent getNormal() to find normal of point on side of cylinder
        return super.getNormal(point);
    }
}
