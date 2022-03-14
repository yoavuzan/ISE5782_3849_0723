package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * This class will reperesent sphere
 *
 * @author Yoav uzan and yaniv bartov
 */
public class Sphere implements Geometry {

    final private Point center;
    final private Double radius;

    /**
     * constractor
     *
     * @param center center
     * @param radius radius
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        if (radius <= 0)
            throw new IllegalArgumentException("radius must be bigger then zero");
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
    public Vector getNormal(Point point){
        Vector normal=point.subtract(center).normalize();
        return normal;}
}
