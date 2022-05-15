package lighting;


import primitives.*;

/**
 *	Class for the directional light
 * * @author Yaniv and Yoav
 */
public class DirectionalLight extends Light implements LightSource{

    private Vector direction;

    /**
     * constructor for the directional light
     * @param intensity- color intensity
     * @param dir- direction of the light (vector)
     */
    public DirectionalLight(Color intensity, Vector dir) {
        super(intensity);
        direction =dir;
    }

    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction.normalize();
    }
}
