package unitTests.renderer;

import geometries.Cylinder;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

public class fastTest {
    private Scene scene = new Scene("Test scene");
    @Test
    public void ourPicture() {
        Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, -1, 0)) //
                .setVPSize(300, 300).setVPDistance(400);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));
        scene.background = new Color(40, 40, 80);
        scene.geometries.add( //
                new Sphere(new Point(110, -110, -600), 50).setEmission(new Color(250, 100, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(1000).setKt(0).setKr(1))

              );

        scene.lights.add(new PointLight(new Color(500, 400, 200), new Point(100, -100, -500)) //
                .setKl(4E-5).setKq(2E-7));


        ImageWriter imageWriter = new ImageWriter("fastPicture", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}
