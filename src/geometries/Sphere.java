package geometries;

import primitives.*;

import static primitives.Util.*;

/**
 * This class will represent sphere
 *
 * @author Yoav uzan and yaniv bartov
 */
public class Sphere implements Geometry {

    final private Point center;
    final private Double radius;

    /**
     * build new Sphere with center and radius
     *
     * @param center center
     * @param radius radius
     */
    public Sphere(Point center, double radius) {
        if (alignZero(radius) <= 0)
            throw new IllegalArgumentException("radius must be bigger then zero");
        this.center = center;
        this.radius = radius;
    }

    /**
     * Returns the center of the circle
     *
     * @return The center of the circle.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Returns the radius of the circle
     *
     * @return The radius of the circle.
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }
}
