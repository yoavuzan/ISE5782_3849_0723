package lighting;


import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * interface for light source
 * @author Yaniv and Yoav
 */
public interface LightSource {

    /**
     * Calculate the intensity of each light
     * @param p- point
     * @return The intensity
     */
    Color getIntensity(Point p);

    /**
     *find the Vector between point p to point position
     * @param p- a point
     * @return The Vector between point p to point position
     */
     Vector getL(Point p);

}
