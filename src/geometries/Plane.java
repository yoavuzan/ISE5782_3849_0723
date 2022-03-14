package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * This class will reperesent plane
 *
 * @author Yoav uzan and yaniv bartov
 */
public class Plane implements Geometry {
    final Point q0;
    final Vector normal;

    /**
     * TODO explanations here
     *
     * @param q0
     * @param normal vector for the normal (will bwe normalized automatically)
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * @param p1
     * @param p2
     * @param p3
     * @throws IllegalArgumentException when all three points are co-lined
     */
    public Plane(Point p1, Point p2, Point p3) {
        q0 = p1;

        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);

        Vector N = U.crossProduct(V);
        //right hand rule
        normal = N.normalize();
    }

    public Point getQ0() {
        return q0;
    }

    /**
     * getter for normal vector
     *
     * @return the normal
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

}

