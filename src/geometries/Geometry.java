package geometries;

import primitives.*;

/**
 * This interface will represent Geometry
 *
 * @author Yoav uzan and yaniv bartov
 */
public interface Geometry extends Intersectable {
    /**
     * get normal at a point on the surface of the geometry
     *
     * @param point on the geometry
     * @return normal vector
     */
    Vector getNormal(Point point);
}
