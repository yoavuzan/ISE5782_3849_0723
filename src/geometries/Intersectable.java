package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This abstract class used to calculate intersections with different geometries
 *
 * @author Yoav Uzan and Yaniv Bartov
 */
public abstract class Intersectable {
    /**
     * static class represent geometry and its point
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * constructor of geoPoint
         * @param  geometry view like sphere plane tube
         * @param point in geometry
         */
        public GeoPoint(Geometry geometry,Point point) {
            this.geometry = geometry;
            this.point=point;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof GeoPoint))
                return false;
            GeoPoint other = (GeoPoint) obj;
            return other.point.equals(this.point) && other.geometry == this.geometry; // Checking geometry by reference
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
         * find the  geoPoints which intersections of this ray with the geometry
         *
         * @param ray to intersect with the geometry
         * @return list of Geo points from func findGeoIntersectionsHelper
         */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }
    protected abstract List<GeoPoint> findGeoIntersectionsHelper (Ray ray);
}
