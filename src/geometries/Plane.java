package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * This class will represent plane
 *
 * @author Yoav uzan and yaniv bartov
 */
public class Plane extends Geometry {
    private final Point planePoint;
    private final Vector normal;

    /**
     * constructor of plane by a point and the normal
     *
     * @param point  plane reference point
     * @param normal vector for the normal (will be normalized automatically)
     */
    public Plane(Point point, Vector normal) {
        this.planePoint = point;
        this.normal = normal.normalize();
    }

    /**
     * constructor of plane by three points
     *
     * @param point1-    one of three points to represent the plane
     * @param point2-one of three points to represent the plane
     * @param point3-one of three points to represent the plane
     * @throws IllegalArgumentException when all three points are co-lined
     */
    public Plane(Point point1, Point point2, Point point3) {
        planePoint = point1;
        Vector u = point2.subtract(point1);
        Vector v = point3.subtract(point1);
        normal = u.crossProduct(v).normalize();
    }

    /**
     * getter for plane's reference point
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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point rayPoint = ray.getPoint0();
        Vector v = ray.getDirection();

        double nv = normal.dotProduct(v);// nv= normal* rayDirection
        //t= np / nv
        // In case there are zeroes in denominator and numerator
        if (isZero(nv))
            return null;

        double np;
        try {
            np = normal.dotProduct(planePoint.subtract(rayPoint));// np=normal* (planePoint-rayPoint)
        } catch (IllegalArgumentException ignore) {
            return null;
        }

        double t = alignZero(np / nv);
        return t <= 0 && alignZero(t - maxDistance) <0 ? null
                : List.of(new GeoPoint(this, ray.getPoint(t)));
    }
}

