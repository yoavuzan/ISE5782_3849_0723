package lighting;


import primitives.Color;

/**
 * Abstract class for all source light
 *
 * @author Yaniv and Yoav
 */
abstract class Light {

    protected final Color intensity;

    /**
     * Protected constructor for intensity
     *
     * @param intensity- color intensity
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter for intensity of the light
     *
     * @return The intensity of the light
     */
    public Color getIntensity() {
        return intensity;
    }
}

