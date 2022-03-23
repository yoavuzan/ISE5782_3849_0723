package geometries;

import primitives.*;
import static primitives.Util.*;

/**
 * This class will represent tube
 *
 * @author Yoav uzan and Yaniv Bar-tov
 */

public class Cylinder extends Tube {

    final private double height;

    /**
     * constaractor
     * @param axisRay //Ray that goes through the height of tube
     * @param radius  /Radius of tube
     * @param height  /height of tube
     */
    public Cylinder(Ray axisRay, double radius, double height) {
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
        Vector v = axisRay.getDirection();
        try {
            // t=v*(P-P0)
            double t = v.dotProduct(point.subtract(axisRay.getPoint()));
            //checks if point is on a base of cylinder (bottom or top circle)
            if (isZero(t) || isZero(t - height))
                return v;
        } catch(IllegalArgumentException ignore) {
            return v;
        }

        //if point is not on either base, call parent getNormal() to find normal of point on side of cylinder
        return super.getNormal(point);
    }
}
