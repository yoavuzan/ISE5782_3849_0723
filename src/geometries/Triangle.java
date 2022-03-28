package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
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
     * @param vertices
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
    public List<Point> findIntersections(Ray ray) {
        List<Point> resultPoint = plane.findIntersections(ray);
        if (resultPoint == null) // In case there is no intersection with the plane return null
            return null;
        Point rayPoint = ray.getPoint0();
        Vector rayDirection = ray.getDirection();
        Vector v1 = vertices.get(0).subtract(rayPoint);
        Vector v2 = vertices.get(1).subtract(rayPoint);
        Vector v3 = vertices.get(2).subtract(rayPoint);
        Vector n1 = (v1.crossProduct(v2)).normalize();
        Vector n2 = (v2.crossProduct(v3)).normalize();
        Vector n3 = (v3.crossProduct(v1)).normalize();
        double t1 = alignZero(n1.dotProduct(rayDirection));
        double t2 = alignZero(n2.dotProduct(rayDirection));
        double t3 = alignZero(n3.dotProduct(rayDirection));

        if (t1 == 0 || t2 == 0 || t3 == 0) // In case one or more of the scalars equals zero
            return null; // that mean the point is not inside the triangle

        LinkedList<Point> result = new LinkedList<Point>();
        if ((t1 > 0 && t2 > 0 && t3 > 0) || (t1 < 0 && t2 < 0 && t3 < 0)) { // In case the all scalars are in the same sign, the point is in the triangle
            result.add(resultPoint.get(0));
            return result;
        } else
            return null;    //If the scalars are in a different sign
    }
}