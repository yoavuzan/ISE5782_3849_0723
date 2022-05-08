package lighting;

import primitives.*;

/**
 * This class represent the ambient light.
 * An ambient light source represents a fixed-intensity and fixed-color light source
 * that affects all objects in the scene equally.
 *
 * @author Yoav Uzan and Yaniv Bar-tov
 */
public class AmbientLight extends Light{

    /**
     * default constructor
     * initialize the ambient light intensity to black
     */
    public AmbientLight() {
        super(Color.BLACK);
    }

    /**
     * constructor build the ambient light intensity
     *
     * @param iA-    color
     * @param kA-    discount factor
     */
    public AmbientLight(Color iA, Double3 kA) {
        super(iA.scale(kA));
    }

}
