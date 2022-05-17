package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * This class will represent collection of some geometries
 *
 * @author Yoav uzan and Yaniv Bar-tov
 */
public class Geometries extends Intersectable {
    private final List<Intersectable> shapes = new LinkedList<>();

    /**
     * constructor with list of geometries
     *
     * @param geometries list of geometries
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Adds list of geometries to the current list
     *
     * @param geometries list of geometries
     */
    public void add(Intersectable... geometries) {
        if (geometries.length != 0) shapes.addAll(List.of(geometries));
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> result = null;
        for (Intersectable geom : shapes)    // The loop find Intersections for each shape
        {
            List<GeoPoint> points = geom.findGeoIntersections(ray, maxDistance);
            if (points != null) {
                if (result == null)
                    result = new LinkedList<>(points);
                else
                    result.addAll(points);
            }
        }
        return result;
    }
}
