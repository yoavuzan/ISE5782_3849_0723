package lighting;


import primitives.*;

/**
 * class that represent Models of omni directional point light source (such as a bulb)
 * include-• Intensity (color) • Position (Point) • Factors (kc, kl, kq) for attenuation with distance (d)
 * @author Yaniv and Yoav
 */
public class PointLight extends Light implements LightSource {
    private Point position;
    private double kC = 1;
    private double kL=0;
    private double kQ=0;

    /**
     * constructor for pointLight
     * @param intensity- color intensity
     * @param position- the position of the light point
     * @param c-attenuation Factor
     * @param l-attenuation Factor
     * @param q-attenuation Factor
     */
    public PointLight(Color intensity, Point position, double c, double l, double q) {
        super(intensity);
        this.position = position;
        kC = c;
        kL = l;
        kQ = q;
    }

    /**
     * constructor for pointLight
     * @param intensity- color intensity
     * @param position- the position of the light point
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * default constructor- initialize attenuation factors to: kC = 1, kL=0, kQ=0;
     * @param intensity- intensity color
     */
    public PointLight(Color intensity) {
        super(intensity);
    }
    /**
     * setter attenuation factor kC
     * @param kC
     * @return  the object pointLight
     */

    public PointLight setKc(double kC){
        this.kC=kC;
        return this;
    }
    /**
     * setter attenuation factor kL
     * @param kL
     * @return  the object pointLight
     */
    public PointLight setKl(double kL){
        this.kL=kL;
        return this;
    }
    /**
     * setter attenuation factor kQ
     * @param kQ
     * @return the object pointLight
     */
    public PointLight setKq(double kQ){
        this.kQ=kQ;
        return this;
    }

    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }

    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        Color iL = getIntensity().scale((1 / (kC + kL * d + kQ * d * d)));
        return iL;
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

}