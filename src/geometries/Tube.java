package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * This class will reperesent tube
 * @author Yoav uzan and yaniv bartov
 */
public class Tube implements Geometry {

    final protected Ray axisRay;
    final protected Double radius;

    /**
     *  constructor new tube with axis ray and radius
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
        Vector p0p = point.subtract(axisRay.getPoint());
        Vector v = axisRay.getDirection();
        double t = v.dotProduct(p0p);

        //if point is on rim then dot product will return 0, in which case the normal is p0_p
        if (t == 0) {
            return p0p.normalize();
        }
        v = v.scale(t);
        return point.subtract(axisRay.getPoint().add(v)).normalize();//op= point-(P0+v)
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}
