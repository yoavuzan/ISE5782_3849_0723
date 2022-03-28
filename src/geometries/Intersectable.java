package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * This interface used to calculate intersections with different geometries
 *
 * @author Yoav Uzan and Yaniv Bartov
 */
public interface Intersectable {
    /**
     * find the intersections of this ray with the geometry
     *
     * @param ray to intersect with the geometry
     * @return list of intersections
     */
    List<Point> findIntersections(Ray ray);
}
