package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * This class will represent sphere
 *
 * @author Yoav uzan and yaniv bartov
 */
public class Sphere implements Geometry {

    final private Point center;
    final private Double radius;

    /**
     * build new Sphere with center and radius
     *
     * @param center center
     * @param radius radius
     */
    public Sphere(Point center, double radius) {
        if (alignZero(radius) <= 0)
            throw new IllegalArgumentException("radius must be bigger then zero");
        this.center = center;
        this.radius = radius;
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
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = new LinkedList<Point>();
        // We used "alignZero" in this function to make the calculation accurate
        Vector v = ray.getDirection();
        Point p0 = ray.getPoint();
        // Special case: if point p0 == center, that mean that all we need to calculate
        // is the radios mult scalar with the direction, and add p0
        if (center.equals(p0)) {
            result.add(p0.add(v.scale(radius)));
            return result;
        }
        Vector u = center.subtract(p0); // u= center-p0
        double tm = u.dotProduct(v);  // tm=u*v
        double d = Math.sqrt(alignZero(u.lengthSquared() - tm * tm)); // d=sqrt{u^2-dm^2}
        if (d >= radius) // if (d ≥ r) there are no intersections
            return null;
        double th = Math.sqrt(radius * radius - d * d);// th=sqrt{r^2-d^2}
        double t1 = tm + th;
        double t2 = tm - th;
        //𝑃𝑖 = 𝑃0 + 𝑡𝑖 * 𝑣  ->  only if 𝑡𝑖 > 0
        if (alignZero(t1) > 0) { // add p1 intersection point to the list
            result.add(p0.add(v.scale(t1)));
        }
        if (alignZero(t2) > 0) { // add p2 intersection point to the list
            result.add(p0.add(v.scale(t2)));
        }
        if (result.isEmpty())
            return null; // In case there are no intersections points
        return result;
    }
}

