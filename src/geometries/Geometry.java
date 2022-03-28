package geometries;

import primitives.*;

/**
 * This interface will represent Geometry
 *
 * @author Yoav uzan and yaniv bartov
 */
public interface Geometry extends Intersectable {
    /**
     * get normal of all geometry
     *
     * @param point
     * @return normal
     */
    Vector getNormal(Point point);
}
