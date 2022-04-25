package lighting;

import primitives.*;

/**
 * This class represent the ambient light
 *
 * @author Yoav Uzan and Yaniv Bar-tov
 */
public class AmbientLight {
    public Color intensity;

    /**
     * default constructor
     * initialize the ambient light intensity to black
     */
    public AmbientLight() {
        intensity = Color.BLACK;
    }

    /**
     * constructor build the ambient light intensity
     *
     * @param color-
     * @param ka-    discount factor
     */
    public AmbientLight(Color color, Double3 ka) {
        intensity = color.scale(ka);
    }

    /**
     * @return The ambient light intensity
     */
    Color GetIntensity() {
        return intensity;
    }
}
