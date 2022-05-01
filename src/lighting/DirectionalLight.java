package lighting;


import primitives.*;

/**
 *	Class for the directional light
 * * @author Yaniv and Yoav
 */
public class DirectionalLight extends Light implements LightSource{

    private Vector direction;

    /**
     * constructor
     * @param intensity-
     * @param dir-
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

