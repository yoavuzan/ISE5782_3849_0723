package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * This class will reperesent tube
 *
 * @author Yoav uzan and yaniv bartov
 */
public class Tube implements Geometry {

     Ray  axisRay;
     Double radius;

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
