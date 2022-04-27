package geometries;

import primitives.*;

/**
 * This interface will represent Geometry
 *
 * @author Yoav uzan and yaniv bartov
 */
public abstract class Geometry extends Intersectable {
    protected Color emission=Color.BLACK;

    /**
     * getter for emission color
     * @return emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * setter of emission color
     * @param emission1 -color
     * @return
     */
    public Geometry setEmission(Color emission1){
        emission=emission1;
        return this;
    }

    /**
     * get normal at a point on the surface of the geometry
     *
     * @param point on the geometry
     * @return normal vector
     */
    public abstract Vector getNormal(Point point);
}
