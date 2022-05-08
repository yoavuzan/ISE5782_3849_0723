package renderer;


import primitives.Color;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.List;

/**
 * RayTracerBasic class inheritance from RayTracerBase class
 * @author Yoav and Yaniv
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * @param sc Ctor using super class constructor
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
        if (intersectionsPoints == null)
            return scene.background;
        else
            return calcColor(ray.findClosestGeoPoint(intersectionsPoints));
    }

    /**
     * Calculate the color of a certain point
     *
     * @param point of intersections
     * @return The ambient light of the scene
     */
    public Color calcColor(GeoPoint point)
    {
        return scene.ambientLight.getIntensity().add(point.geometry.getEmission());
    }
}
