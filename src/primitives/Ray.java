package primitives;
import geometries.Intersectable.GeoPoint;
import java.util.List;

/**
 * This class will reperesent Ray
 *
 * @author Yoav uzan and yaniv bartov
 */

public class Ray {

    private final Point point0;
    private final Vector direction;

    /**
     * constructor new Ray
     *
     * @param point     of beginning
     * @param direction of vector
     */
    public Ray(Point point, Vector direction) {
        this.point0 = point;
        this.direction = direction.normalize();
    }

    /**
     * Getter for the direction of the ray
     *
     * @return the direction vector
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * Getter for the head point of the ray
     *
     * @return the head point
     */
    public Point getPoint0() {
        return point0;
    }

    /**
     * calculate point on the ray
     *
     * @param t -is number
     * @return P= P0+t∙rayDirection
     */
    public Point getPoint(double t) {
        return point0.add(direction.scale(t));
    }

    /**
     * the closet point to the began of the ray
     *
     * @param points of all ray
     * @return the closet point to the began of the ray
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * the closet geoPoint to the began of the ray
     *
     * @param geoPoints-of all ray
     * @return the closet point to the began of the ray
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints) {

        if (geoPoints == null) //In case of an empty list
            return null;
        GeoPoint closePoint = geoPoints.get(0);	//Save the first point in the list
        for (GeoPoint p : geoPoints) {
            if (closePoint.point.distance(point0) > p.point.distance(point0))	//In case the distance of closes point is bigger than the p point
                closePoint = p;
        }
        return closePoint;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray) obj;
        return point0.equals(other.point0) && direction.equals(other.direction);
    }

    @Override
    public String toString() {
        return point0.toString() + " " + direction.toString();
    }
}
