package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * This abstract class will represent Geometry
 *
 * @author Yoav Uzan and Yaniv Bartov
 */
public abstract class Geometry extends Intersectable {
    private Color emission = Color.BLACK;
    private Material material = new Material();

    /**
     * getter for emission color
     *
     * @return emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * getter for material
     *
     * @return material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * setter of emission color
     * @param emission1 color
     * @return The new geometry with new emission
     */
    public Geometry setEmission(Color emission1) {
        emission = emission1;
        return this;
    }
    /**
     * setter of material
     * @param material -material
     * @return Geometry with new material
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
