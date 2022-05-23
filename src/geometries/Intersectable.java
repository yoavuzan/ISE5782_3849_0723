package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * This abstract class used to calculate intersections with different geometries
 *
 * @author Yoav Uzan and Yaniv Bartov
 */
public abstract class Intersectable {
    /**
     * static class represent geometry and  point of intersection
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * constructor of geoPoint
         *
         * @param geometry- the geometry
         * @param point-    point of ray of intersection
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)  return true;
            if (!(obj instanceof GeoPoint other)) return false;
            return other.geometry == this.geometry && other.point.equals(this.point); // Checking geometry by reference
        }

    }

    /**
     * find the points which intersections of this ray with the geometry
     *
     * @param ray to intersect with the geometry
     * @return list of points
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * find the geoPoints which intersections of this ray with the geometry
     *
     * @param ray to intersect with the geometry
     * @return list of Geo points
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * find the geoPoints which intersections of this ray with the geometry
     *
     * @param ray to intersect with the geometry
     * @param maxDistance- the max distance of the ray of light source to light
     * @return list of Geo points
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    /**
     * find the geoPoints which intersections of this ray with the geometry
     *
     * @param ray to intersect with the geometry
     * @param maxDistance- the max distance of the ray of light source to light
     * @return list of Geo points
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);
}
