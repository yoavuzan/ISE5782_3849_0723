package lighting;


import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * @author Yaniv and Yoav
 * interface for light source
 */
public interface LightSource {

    /**
     * Calculate the intensity of each light
     * @param p
     * @return The intensity
     */
    public Color getIntensity(Point p);

    /**
     *
     * @param p
     * @return The Vector between point p to point position
     */
    public Vector getL(Point p);

}
