package geometries;
import primitives.*;

/**
 * This interface will reperesent Geometry
 *
 * @author Yoav uzan and yaniv bartov
 */
public interface Geometry extends Intersectable {
    /**
     * get normal of all geometry
     * @param point
     * @return normal
     */
    Vector getNormal(Point point);
}
