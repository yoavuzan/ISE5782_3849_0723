package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * Scene represent view of scene. it combines Collection of geometries and the background color
 * and the Ambient Light
 *
 * @author Yoav and Yaniv
 */
public class Scene {
    public final String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = new Geometries();

    /**
     * Scene constructor accepting Scene name,
     * the background default is black
     * @param name1 the name of jpeg file
     */
    public Scene(String name1) {
        name = name1;
    }

    /**
     * setter for the background
     * @param background1- color
     * @return the scene with the update background
     */
    public Scene setBackground(Color background1) {
        background = background1;
        return this;
    }

    /**
     * setter for the Ambient Light
     * @param color- AmbientLight
     * @return the scene with the update Ambient Light
     */
    public Scene setAmbientLight( AmbientLight color) {
        ambientLight = color;
        return this;
    }

    /**
     * setter for the geometries
     * @param geometries1 -collection of geometries
     * @return the scene with the update geometries collection
     */
    public Scene setGeometries(Geometries geometries1) {
        geometries = geometries1;
        return this;
    }
}
