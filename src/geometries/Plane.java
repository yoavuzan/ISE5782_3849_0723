package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{
    final Point q0;
    final Vector normal;

    /**
     * TODO explanations here
     * @param q0
     * @param normal vector for the normal (will bwe normalized automatically)
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    public Plane(Point p1, Point p2, Point p3) {
        q0 =p1;

        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);

        Vector N = U.crossProduct(V);
        //right hand rule
        normal = N.normalize();;
    }

    public Point getQ0() {
        return q0;
    }

    /**
     * getter for _normal field
     * @return
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * implementation of getNormal from Geometry
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }


}

