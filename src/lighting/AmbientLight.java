package lighting;

import primitives.*;

/**
 * This class represent the ambient light
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
     * @param iA-
     * @param kA-    discount factor
     */
    public AmbientLight(Color iA, Double3 kA) {
        super(iA.scale(kA));
    }

}
