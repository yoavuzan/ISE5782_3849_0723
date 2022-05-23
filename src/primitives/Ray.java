package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * This class will represent Ray
 *
 * @author Yoav uzan and yaniv bartov
 */

public class Ray {
    private static final double DELTA = 0.1;
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
     * constructor of ray with a shift point.  (According to an original point, the direction of the ray, the normal vector)
     *
     * @param p0      point of beginning
     * @param dir     direction of vector
     * @param normal- the normal on which the straight one defines should be moved the point of the head of the ray
     */
    public Ray(Point p0, Vector dir, Vector normal) {
        Vector delta = normal.scale(normal.dotProduct(dir) > 0 ? DELTA : -DELTA);
        this.point0 = p0.add(delta);
        this.direction = dir;
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
        GeoPoint closePoint = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (GeoPoint p : geoPoints) {
            double dist = p.point.distance(point0);
            if (dist < minDistance) { //In case the distance of closes point is bigger than the p point
                closePoint = p;
                minDistance = dist;
            }
        }
        return closePoint;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ray other)) return false;
        return point0.equals(other.point0) && direction.equals(other.direction);
    }

    @Override
    public String toString() {
        return point0 + " " + direction;
    }
}
