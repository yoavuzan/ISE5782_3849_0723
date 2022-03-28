package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * This class will represent collection of some geometries
 *
 * @author Yoav uzan and Yaniv Bar-tov
 */
public class Geometries implements Intersectable {
    private final List<Intersectable> shapes = new LinkedList<>();

    /**
     * Empty default constructor
     */
    public Geometries() {
    }

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
        shapes.addAll(List.of(geometries));
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null;

        for (Intersectable geom : shapes)    // The loop find Intersections for each shape
        {
            List<Point> points = geom.findIntersections(ray);

            if (points != null) {
                if (result == null)
                    result = new LinkedList<>();
                result.addAll(points);
            }
        }
        return result;
    }
}
