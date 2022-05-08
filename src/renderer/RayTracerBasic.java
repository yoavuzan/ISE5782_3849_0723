package renderer;


import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.Color;
import primitives.Double3;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * RayTracerBasic class inheritance from RayTracerBase class
 *
 * @author Yoav and Yaniv
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * @param sc Constructor using super class constructor
     */
    public RayTracerBasic(Scene sc) {
        super(sc);
    }

    /**
     * Implementation for the abstract method traceRay
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersectionsPoints = scene.geometries.findGeoIntersections(ray);
        if (intersectionsPoints == null) return scene.background;
        else return calcColor(ray.findClosestGeoPoint(intersectionsPoints), ray);
    }

    /**
     * Calculate the effects of lights
     *
     * @param intersection- the point the ray intersect the object
     * @param ray- the ray that intersect the object
     * @return The color resulted by local effects calculation
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDirection();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().getShininess();

        var kd = intersection.geometry.getMaterial().getkD();
        var ks = intersection.geometry.getMaterial().getkS();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // checks if nl == nv
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity), calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    /**
     * Calculates diffusive light
     *
     * @param kd              -Double3-factor of diffusive in the material
     * @param l-              vector
     * @param n-              vector
     * @param lightIntensity- Intensity of the light source
     * @return The color of diffusive effects
     */
    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
        double ln = alignZero(l.dotProduct(n));
        if (ln < 0) ln = ln * -1;
        return lightIntensity.scale(kd).scale(ln);
    }

    /**
     * Calculate specular light
     *
     * @param ks-Double3-              factor of specular in the material
     * @param l-                       vector
     * @param n-                       vector
     * @param v-                       vector
     * @param nShininess-              Shininess of the material
     * @param lightIntensity-Intensity of the light source
     * @return The color of specular reflection
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        double vr = alignZero(v.scale(-1).dotProduct(r));
        if (vr < 0) vr = 0;
        vr = Math.pow(vr, nShininess);
        return lightIntensity.scale(ks).scale(vr);
    }

    /**
     * Calculate the color of a certain point
     *
     * @param point-specific point
     * @return The ambient light of the scene
     */
    public Color calcColor(GeoPoint point, Ray ray) {
        return scene.ambientLight.getIntensity().add(point.geometry.getEmission()).add(calcLocalEffects(point, ray));
    }
}
