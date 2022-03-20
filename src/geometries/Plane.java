package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * This class will reperesent plane
 *
 * @author Yoav uzan and yaniv bartov
 */
public class Plane implements Geometry {
    final Point point;
    final Vector normal;

    /**
     * TODO explanations here
     *
     * @param point
     * @param normal vector for the normal (will bwe normalized automatically)
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }

    /**
     * @param point1
     * @param point2
     * @param point3
     * @throws IllegalArgumentException when all three points are co-lined
     */
    public Plane(Point point1, Point point2, Point point3) {
        point = point1;

        Vector U = point2.subtract(point1);
        Vector V = point3.subtract(point1);
        normal = U.crossProduct(V).normalize();
    }

    public Point getPoint() {
        return point;
    }

    /**
     * getter for normal vector
     *
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
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}

