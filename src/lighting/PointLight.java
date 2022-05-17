package lighting;


import primitives.*;

/**
 * class that represent Models of omni directional point light source (such as a bulb)
 * include-• Intensity (color) • Position (Point) • Factors (kc, kl, kq) for attenuation with distance (d)
 * @author Yaniv and Yoav
 */
public class PointLight extends Light implements LightSource {
    private final Point position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     * constructor for pointLight
     *
     * @param intensity- color intensity
     * @param position-  the position of the light point
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * setter attenuation factor kC
     *
     * @param kC Factor light
     * @return the object pointLight
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * setter attenuation factor kL
     *
     * @param kL Factor light
     * @return the object pointLight
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * setter attenuation factor kQ
     *
     * @param kQ Factor light
     * @return the object pointLight
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        return intensity.scale((1 / (kC + kL * d + kQ * d * d)));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }
}