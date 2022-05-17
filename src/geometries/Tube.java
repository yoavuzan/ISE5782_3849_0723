package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * This class will represent tube
 *
 * @author Yoav uzan and yaniv bartov
 */
public class Tube extends Geometry {

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
    protected  List<GeoPoint> findGeoIntersectionsHelper (Ray ray,double maxDistance){
        Vector dir = ray.getDirection();
        Vector v = axisRay.getDirection();
        double dirV = dir.dotProduct(v);

        if (ray.getPoint0().equals(axisRay.getPoint0())) { // In case the ray starts on the p0.
            if (isZero(dirV))
                return List.of(new GeoPoint(this,ray.getPoint(radius)));

            if (dir.equals(v.scale(dir.dotProduct(v))))
                return null;

            return List.of(new GeoPoint(this,ray.getPoint(
                    Math.sqrt(radius * radius / dir.subtract(v.scale(dir.dotProduct(v)))
                            .lengthSquared()))));
        }

        Vector deltaP = ray.getPoint0().subtract(axisRay.getPoint0());
        double dpV = deltaP.dotProduct(v);

        double a = 1 - dirV * dirV;
        double b = 2 * (dir.dotProduct(deltaP) - dirV * dpV);
        double c = deltaP.lengthSquared() - dpV * dpV - radius * radius;

        if (isZero(a)) {
            if (isZero(b)) { // If a constant equation.
                return null;
            }
            return List.of(new GeoPoint(this,ray.getPoint(-c / b))); // if it's linear, there's a solution.
        }

        double discriminant = alignZero(b * b - 4 * a * c);

        if (discriminant < 0) // No real solutions.
            return null;

        double t1 = alignZero(-(b + Math.sqrt(discriminant)) / (2 * a)); // Positive solution.
        double t2 = alignZero(-(b - Math.sqrt(discriminant)) / (2 * a)); // Negative solution.

        if (discriminant <= 0) // No real solutions.
            return null;

        if (t1 > 0 && t2 > 0 && alignZero(t1 - maxDistance) <= 0 && alignZero(t2 - maxDistance) <= 0) {
            List<GeoPoint> _points = new ArrayList<>(2);
            _points.add(new GeoPoint(this,ray.getPoint(t1)));
            _points.add(new GeoPoint(this,ray.getPoint(t2)));
            return _points;
        }
        else if (t1 > 0 && alignZero(t1 - maxDistance) <= 0) {
            List<GeoPoint> _points = new ArrayList<>(1);
            _points.add(new GeoPoint(this,ray.getPoint(t1)));
            return  _points;
        }
        else if (t2 > 0 && alignZero(t1 - maxDistance)<=0) {
            List<GeoPoint> _points = new ArrayList<>(1);
            _points.add(new GeoPoint(this,ray.getPoint(t2)));
            return _points;
        }
        return null;
    }

}
