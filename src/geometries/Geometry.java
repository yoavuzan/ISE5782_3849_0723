package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * This interface will reperesent Geometry
 *
 * @author Yoav uzan and yaniv bartov
 */
public interface Geometry {
    Vector getNormal(Point point);
}
