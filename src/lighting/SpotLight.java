package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * this class represent models point light source with direction (such as a luxo lamp)
 * include-  Intensity  • Position  •Direction dir (Vector) - normalized • Attenuation factors
 * @author Yaniv and Yoav
 */
public class SpotLight extends PointLight {

    private Vector direction;

    /**
     * constructor for pointLight
     * @param intensity- color intensity
     * @param position- the position of the spot light
     * @param dir- the direction of the spot light
     * @param kC-attenuation Factor
     * @param kL-attenuation Factor
     * @param kQ-attenuation Factor
     */
    public SpotLight(Color intensity, Point position, Vector dir, double kC, double kL, double kQ) {
        super(intensity, position, kC, kL, kQ);
        direction = dir.normalize();
    }

    /**
     * constructor for pointLight
     * @param intensity- color intensity
     * @param position- the position of the spot light
     * @param dir- the direction of the spot light
     */
    public SpotLight(Color intensity, Point position, Vector dir) {
        super(intensity, position);
        direction = dir.normalize();
    }

    @Override
    public Color getIntensity(Point p) {

        Vector l = super.getL(p);

        if (alignZero(direction.dotProduct(l)) <= 0) //In case the dir * l return zero or negative number
            return Color.BLACK;

        return super.getIntensity(p).scale(direction.dotProduct(l));
    }

    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }

}