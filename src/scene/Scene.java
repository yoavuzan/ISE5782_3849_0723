package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Scene represent view of scene. It combines Collection of geometries, the background color
 * ,the Ambient Light and the light sources.
 *
 * @author Yoav and Yaniv
 */
public class Scene {
    public final String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = new Geometries();
    public List<LightSource> lights = new LinkedList<>();

    /**
     * Scene constructor accepting Scene name,
     * the background default is black
     *
     * @param name1 the name of jpeg file
     */
    public Scene(String name1) {
        name = name1;
    }

    /**
     * setter for the background
     *
     * @param background1- color
     * @return the scene with the update background
     */
    public Scene setBackground(Color background1) {
        background = background1;
        return this;
    }

    /**
     * setter for the Ambient Light
     *
     * @param color- AmbientLight
     * @return the scene with the update Ambient Light
     */
    public Scene setAmbientLight(AmbientLight color) {
        ambientLight = color;
        return this;
    }

    /**
     * setter for the geometries
     *
     * @param geometries -collection of geometries
     * @return the scene with the update geometries collection
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * setter for the lights
     *
     * @param lights -list of light sources
     * @return the scene with the update lights collection
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
