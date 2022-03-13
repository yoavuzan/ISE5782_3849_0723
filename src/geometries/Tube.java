package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry {

     Ray  axisRay;
     Double radius;
    /**
     * A constructor
     *
     * @param axisRay //Ray that goes through the height of tube
     * @param radius  /Radius of tube
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        if (radius <= 0)
            throw new IllegalArgumentException("radius must be bigger then zero");
        this.radius = radius;
    }

    /**
     * Returns the axis ray
     *
     * @return The axisRay is being returned.
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
        Vector p0_p = point.subtract(axisRay.getPoint());
        Vector v = axisRay.getVector();
        double t = v.dotProduct(p0_p);

        //if point is on rim then dot product will return 0, in which case the normal is p0_p
        if (t == 0) {
            return p0_p.normalize();
        }

        v = v.scale(t);
        Point o = axisRay.getPoint().add(v);
        Vector o_p = point.subtract(o);
        return o_p.normalize();
    }
}
