package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * This class will represent sphere
 *
 * @author Yoav uzan and yaniv bartov
 */
public class Sphere extends Geometry {

    final private Point center;
    final private double radius;
    final private double radiusSqr;

    /**
     * constructor- build new Sphere with center and radius
     *
     * @param center center
     * @param radius radius
     */
    public Sphere(Point center, double radius) {
        if (alignZero(radius) <= 0)
            throw new IllegalArgumentException("radius must be bigger then zero");
        this.center = center;
        this.radius = radius;
        this.radiusSqr = radius * radius;
    }

    /**
     * Returns the center of the circle
     *
     * @return The center of the circle.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Returns the radius of the circle
     *
     * @return The radius of the circle.
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        // We used "alignZero" in this function to make the calculation accurate
        // Special case: if point p0 == center, that mean that all we need to calculate
        // is the radios multi scalar with the direction, and add p0
        Vector u;
        try {
            u = center.subtract(ray.getPoint0()); // u= center-p0
        } catch (IllegalArgumentException ignore) {
            return List.of(new GeoPoint(this, ray.getPoint(radius)));
        }

        double tm = u.dotProduct(ray.getDirection());  // tm=u*v
        double dSqr = u.lengthSquared() - tm * tm; // d^2=u^2-dm^2
        double thSqr = radiusSqr - dSqr;
        if (alignZero(thSqr) <= 0) // if (d ≥ r) there are no intersections
            return null;
        double th = Math.sqrt(thSqr);// th=sqrt{r^2-d^2}

        //𝑃𝑖 = 𝑃0 + 𝑡𝑖 * 𝑣  ->  only if 𝑡𝑖 > 0
        double t2 = alignZero(tm + th);
        if (t2 <= 0) return null;
        double t1 = alignZero(tm - th);
        if (alignZero(t1 - maxDistance) > 0) return null;

        if (alignZero(t2 - maxDistance) > 0)
            return t1 <= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t1)));
        else {
            GeoPoint gp2 = new GeoPoint(this, ray.getPoint(t2));
            return t1 <= 0 ? List.of(gp2) : List.of(new GeoPoint(this, ray.getPoint(t1)), gp2);
        }
    }
}
