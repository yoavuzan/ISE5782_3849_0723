package geometries;

import primitives.*;
import java.util.LinkedList;
import java.util.List;

/**
 * This class will represent collection of some geometries
 *
 * @author Yoav uzan and Yaniv Bar-tov
 */
public class Geometries implements Intersectable {
    private List<Intersectable> shapes;

    /**
     * Empty constructor
     */
    public Geometries() {
        shapes = new LinkedList<>();
    }

    /**
     * constructor with list of geometries
     *
     * @param geometries
     */
    public Geometries(Intersectable... geometries) {
        shapes = List.of(geometries);
    }

    /**
     * Adds list of geometries to the current list
     *
     * @param list of geometries
     */
    public void add(Intersectable... geometries) {
        shapes.addAll(List.of(geometries));
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        if (shapes.isEmpty())        // In case the collection is empty
            return null;
        List<Point> result = null, points;

        for (Intersectable geom : shapes)    // The loop find Intersections for each shape
        {
            points = geom.findIntersections(ray);

            if (points != null) {
                if (result == null) {
                    result = points;
                } else
                    result.addAll(points);
            }
        }
        return result;
    }
}
