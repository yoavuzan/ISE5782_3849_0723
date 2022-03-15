package geometries;

import primitives.*;
import static primitives.Util.*;

/**
 * This class will represent tube
 *
 * @author Yoav uzan and yaniv bartov
 */

public class Clyinder extends Tube {
    final private double height;

    /**
     * constaractor
     * @param axisRay //Ray that goes through the height of tube
     * @param radius  /Radius of tube
     */
    public Clyinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * Returns the height of the Cylinder
     *
     * @return the height of the Cylinder.
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
        // t=v*(P-P0)
        double t = axisRay.getDirection().dotProduct(point.subtract(axisRay.getPoint()));
        //checks if point is on base of cylinder (bottom circle)
        if(isZero(t))
            return axisRay.getDirection();
        //checks if point is on the other base of cylinder (top circle)
        if(isZero(t-height))
            return axisRay.getDirection();
        //if point is not on either base, call parent getNormal() to find normal of point on side of cylinder
        return super.getNormal(point);
    }
}
