package geometries;

import primitives.*;
import java.util.List;

/**
 * This class will represent plane
 *
 * @author Yoav uzan and yaniv bartov
 */
public class Plane implements Geometry {
    private final Point point;
    private final Vector normal;

    /**
     * constructor of plane by point and normal
     *
     * @param point
     * @param normal vector for the normal (will be normalized automatically)
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }

    /**
     * constructor of plane by  three points
     *
     * @param point1
     * @param point2
     * @param point3
     * @throws IllegalArgumentException when all three points are co-lined
     */
    public Plane(Point point1, Point point2, Point point3) {
        point = point1;

        Vector u = point2.subtract(point1);
        Vector v = point3.subtract(point1);
        normal = u.crossProduct(v).normalize();
    }

    /**
     * getter for point
     * @return the point
     */
    public Point getPoint() {
        return point;
    }

    /**
     * getter for normal vector
     * @return the normal
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}

