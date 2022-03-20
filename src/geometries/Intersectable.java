package geometries;

import primitives.*;
import java.util.List;

public interface Intersectable {
    /**
     * find the intsersections of this ray with the geometry
     * @param ray
     * @return list of intsersections
     */
    public List<Point> findIntsersections(Ray ray);
}
