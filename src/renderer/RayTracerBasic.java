package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
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
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    public Color traceRays(List<Ray> rays) {
        Color color = scene.background;
        for (Ray ray : rays) {
            GeoPoint closestPoint = findClosestIntersection(ray);
        }
        return color.scale(1 / 64);
    }

    /**
     * Calculate the effects of lights
     *
     * @param intersection- the point the ray intersect the object
     * @param ray-          the ray that intersect the object
     * @param k             for calcColor
     * @return The color resulted by local effects calculation
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, Double3 k) {
        Vector v = ray.getDirection();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Color color = intersection.geometry.getEmission();
        int nShininess = intersection.geometry.getMaterial().nShininess;
        var kd = intersection.geometry.getMaterial().kD;
        var ks = intersection.geometry.getMaterial().kS;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // checks if nl == nv
                Double3 ktr = transparency(intersection, lightSource, l, n);
                if (!(ktr.product(k).lowerThan(MIN_CALC_COLOR_K))) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
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
     * @param l-                       normalized from light source
     * @param n-                       normal to the intersected geometry surface at the point
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
     * @param point point of intersection
     * @param ray   of intersection
     * @param level stop condition
     * @param k     factor of color
     * @return The final color of the point by Phong model
     */
    public Color calcColor(GeoPoint point, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(point, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(point, ray.getDirection(), level, k));
    }

    /***
     * Construct reflected ray from given point. Ray will be moved across normal line in direction of ray
     * @param p represent geo point
     * @param v direction of light ray
     * @param n normal
     * @return new reflected ray
     */
    private Ray constructReflectedRay(Point p, Vector v, Vector n) {
        Vector r = v.subtract(n.scale(2 * v.dotProduct(n)));
        return new Ray(p, r, n);
    }

    /**
     * Construct a transparency ray from point. Ray will be moved across normal line in direction of ray
     *
     * @param p-point on the geometry
     * @param v-      direction of the light ray from the geometry
     * @param n-      normal vector of the point on geometry
     * @return new refracted ray
     */
    private Ray constructRefractedRay(Point p, Vector v, Vector n) {
        return new Ray(p, v, n);
    }

    /**
     * Method that checks if there is an object shading light source from point
     *
     * @param geoPoint point and its geometry
     * @param ls       light source
     * @param l        direction from light to point
     * @param n        normal from the point
     * @return accumulated transparency factor
     */
    private Double3 transparency(GeoPoint geoPoint, LightSource ls, Vector l, Vector n) {
        Ray lightRay = new Ray(geoPoint.point, l.scale(-1), n); // from point to light source
        double lightDistance = ls.getDistance(geoPoint.point);

        var intersections = scene.geometries.findGeoIntersections(lightRay, lightDistance);
        if (intersections == null) return Double3.ONE;
        Double3 ktr = Double3.ONE;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0) {
                ktr = ktr.product(gp.geometry.getMaterial().kT);
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
            }
        }
        return ktr;
    }

    /**
     * method that calculate the intersections and consider the closest intersection to the beginning of the fund with the following signature
     *
     * @param ray- ray which intersect
     * @return the point of the intersection(return null if there were no cut points to the fund)
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        return ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
    }

    /**
     * calculate the  light global effects on the point (reflected and refracted rays )
     *
     * @param gp-    the point to calculate its color
     * @param v-     direction of light ray
     * @param level- stop conditions
     * @param k-     factor of color
     * @return color the influence of reflection and refraction
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, Double3 k) {
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        return calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, k, material.kR)
                .add(calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, k, material.kT));
    }

    /**
     * recursive method that calculate global effect of reflected/refracted ray on the color
     *
     * @param ray-   the reflected/refracted ray
     * @param level- stop conditions
     * @param k      -    aggregated global effect factor
     * @param kx-    factor of reflection/refraction
     * @return the effect of this reflected/refracted ray on the color
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;

        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }

    /**
     * calculate the color of a geoPoint
     *
     * @param gp-  point
     * @param ray-
     * @return the color of this point
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }
}

