package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * This class will represent tube
 *
 * @author Yoav uzan and yaniv bartov
 */
public class Tube implements Geometry {

    final protected Ray axisRay;
    final protected Double radius;

    /**
     * constructor new tube with axis ray and radius
     *
     * @param axisRay //Ray that goes through the height of tube
     * @param radius  /Radius of tube
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        if (alignZero(radius) <= 0)
            throw new IllegalArgumentException("radius must be bigger then zero");
        this.radius = radius;
    }

    /**
     * Returns the axis ray
     *
     * @return The axis ray is being returned.
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * Returns the radius of the circle
     *
     * @return The radius of the circle.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * get normal of point on tube
     *
     * @param point Point on tube
     * @return Normal in point
     */
    @Override
    public Vector getNormal(Point point) {
        //t = v∙(p − p0)
        //o = p0 + t∙v
        //n = normalize(P - o)
        Vector v = axisRay.getDirection();
        Point p0 = axisRay.getPoint0();
        double t = v.dotProduct(point.subtract(p0));
        Point o = isZero(t) ? p0 : p0.add(v.scale(t));
        return point.subtract(o).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
