package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public abstract class RayTracerBase {

    protected Scene scene;

    /**
     * Constructor with scene parameter
     * @param sc
     */
    public RayTracerBase(Scene sc) {
        scene = sc;
    }

    /**
     * Traces the ray and returns the Color
     * @param ray
     * @return Color
     */
    public abstract Color traceRay(Ray ray);	//abstract method for trace ray
}
