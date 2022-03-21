package geometries;

import primitives.*;
import java.util.List;

public interface Intersectable {
    /**
     * find the intersections of this ray with the geometry
     * @param ray
     * @return list of intersections
     */
    public List<Point> findIntersections(Ray ray);
}
