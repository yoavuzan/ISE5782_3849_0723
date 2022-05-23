package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * This class will represent Polygon
 *
 * @author Yoav Uzan and Yaniv Bar-tov
 */
public class Triangle extends Polygon {

    /**
     * constructor new triangle with vertices
     *
     * @param vertices (three points)
     */
    public Triangle(Point... vertices) {
        super(vertices);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        var result = plane.findGeoIntersections(ray,maxDistance);
        if (result == null) // In case there is no intersection with the plane return null
            return null;

        Point rayPoint = ray.getPoint0();
        Vector rayDirection = ray.getDirection();
        Vector v1 = vertices.get(0).subtract(rayPoint);
        Vector v2 = vertices.get(1).subtract(rayPoint);
        Vector n1 = (v1.crossProduct(v2)).normalize();
        double t1 = alignZero(n1.dotProduct(rayDirection));
        if (t1 == 0) return null;

        Vector v3 = vertices.get(2).subtract(rayPoint);
        Vector n2 = (v2.crossProduct(v3)).normalize();
        double t2 = alignZero(n2.dotProduct(rayDirection));
        if (t1 * t2 <= 0) return null;

        Vector n3 = (v3.crossProduct(v1)).normalize();
        double t3 = alignZero(n3.dotProduct(rayDirection));
        if (t1 * t3 <= 0) return null;

        result.get(0).geometry = this;
        return result;
    }
}