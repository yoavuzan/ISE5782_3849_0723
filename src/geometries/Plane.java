package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * This class will represent plane
 *
 * @author Yoav uzan and yaniv bartov
 */
public class Plane implements Geometry {
    private final Point planePoint;
    private final Vector normal;

    /**
     * constructor of plane by point and normal
     *
     * @param point
     * @param normal vector for the normal (will be normalized automatically)
     */
    public Plane(Point point, Vector normal) {
        this.planePoint = point;
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
        planePoint = point1;

        Vector u = point2.subtract(point1);
        Vector v = point3.subtract(point1);
        normal = u.crossProduct(v).normalize();
    }

    /**
     * getter for point
     *
     * @return the point
     */
    public Point getPlanePoint() {
        return planePoint;
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
    public List<Point> findIntersections(Ray ray) {
        {
            Point rayPoint = ray.getPoint();
            if (rayPoint.equals(planePoint))
                return null;
            double x = normal.dotProduct(planePoint.subtract(rayPoint));// x=normal* (planePoint-rayPoint)
            double y = normal.dotProduct(ray.getDirection());// y= normal* rayDirection
            //t= x / y
            // In case there are zeroes in denominator and numerator
            if (isZero(x) || isZero(y))
                return null;
            double t = (x / y);
            if (t < 0) // In case there is no intersection with the plane return null
                return null;
            List<Point> result = new LinkedList<Point>();
            result.add(rayPoint.add(ray.getDirection().scale(t)));//𝑃 = rayPoint + 𝑡 ∙ rayDirection
            return result;
        }
    }
}

