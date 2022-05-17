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
     * Max level recursion for global effects
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     * min level attenuation factor in recursion
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * Initial K for calcColor recursion
     */
    private static final Double3 INITIAL_K = Double3.ONE;


    /**
     * @param sc Constructor using super class constructor
     */
    public RayTracerBasic(Scene sc) {
        super(sc);
    }

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
     * @param ray-          the ray that intersect the object
     * @return The color resulted by local effects calculation
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDirection();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        Color color = intersection.geometry.getEmission();
        if (nv == 0) return color;
        int nShininess = intersection.geometry.getMaterial().getShininess();

        var kd = intersection.geometry.getMaterial().kD;
        var ks = intersection.geometry.getMaterial().kS;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // checks if nl == nv
                if (unshaded(lightSource, l, n, intersection)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * Calculates diffusive light
     *
     * @param kd              -Double3-factor of diffusive in the material
     * @param l-              vector for use dot Product(l*n)
     * @param n-              vector for use dot Product(l*n)
     * @param lightIntensity- Intensity of the light source
     * @return The color of diffusive effects
     */
    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
        double ln = alignZero(l.dotProduct(n));
        return lightIntensity.scale(kd).scale(ln < 0 ? -ln : ln);
    }

    /**
     * Calculate specular light
     *
     * @param ks-Double3-              factor of specular in the material
     * @param l-                        normalized from light source
     * @param n-                        normal to the intersected geometry surface at the point
     * @param v-                       direction of the ray
     * @param nShininess-              Shininess of the material
     * @param lightIntensity-Intensity of the light source
     * @return The color of specular reflection
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        double minusVR = alignZero(-v.dotProduct(r));
        return minusVR <= 0 ? Color.BLACK : lightIntensity.scale(ks).scale(Math.pow(minusVR, nShininess));
    }

    /**
     * Calculate the color of a certain point
     *
     * @param point we go to paint
     * @return The ambient light of the scene
     */
    public Color calcColor(GeoPoint point, Ray ray) {
        return scene.ambientLight.getIntensity().add(calcLocalEffects(point, ray));
    }


    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geoPoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries
                .findGeoIntersections(lightRay, light.getDistance(geoPoint.point));
        return intersections == null || intersections.isEmpty() || geoPoint.geometry.getMaterial().kT != Double3.ZERO;
    }
}

