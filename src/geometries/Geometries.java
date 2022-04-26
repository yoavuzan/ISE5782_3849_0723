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
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null;

        for (Intersectable geom : shapes)    // The loop find Intersections for each shape
        {
            List<Point> points = geom.findIntersections(ray);

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
