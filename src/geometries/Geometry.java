package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * This abstract class will represent Geometry
 *
 * @author Yoav uzan and yaniv bartov
 */
public abstract class Geometry extends Intersectable {
    protected Color emission = Color.BLACK;
    private Material material = new Material();

    /**
     * getter for material
     *
     * @return material
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * getter for emission color
     *
     * @return emission
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * setter of emission color
     *
     * @param emission1 -color
     * @return
     */
    public Geometry setEmission(Color emission1) {
        emission = emission1;
        return this;
    }
    /**
     * setter of material
     *
     * @param material -material
     * @return
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
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
