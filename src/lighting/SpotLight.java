package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * this class represent models point light source with direction (such as a luxo lamp)
 * include-  Intensity  • Position  •Direction dir (Vector) - normalized • Attenuation factors
 *
 * @author Yaniv and Yoav
 */
public class SpotLight extends PointLight {

    private final Vector direction;

    /**
     * constructor for pointLight
     *
     * @param intensity- color intensity
     * @param position-  the position of the spotlight
     * @param dir-       the direction of the spotlight
     */
    public SpotLight(Color intensity, Point position, Vector dir) {
        super(intensity, position);
        direction = dir.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        double factor = alignZero(direction.dotProduct(getL(p)));
        return factor <= 0 ? Color.BLACK : super.getIntensity(p).scale(factor);
    }
}