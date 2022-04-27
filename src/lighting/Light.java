package lighting;


import primitives.Color;

/**
 * @author Yaniv and Yoav
 * Abstract class for light
 */
abstract class Light {

    private Color intensity;

    /**
     * Protected constructor for intensity
     * @param intensity
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter for intensity of the light
     * @return The intensity of the light
     */
    public Color getIntensity() {
        return intensity;
    }
}

