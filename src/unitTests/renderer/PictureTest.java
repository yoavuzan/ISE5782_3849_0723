package unitTests.renderer;

import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.*;
import scene.Scene;
import geometries.*;

public class PictureTest {
    private Scene scene = new Scene("Test scene");
    /**
     *
     * produces a picture we created, looks like bubble-gum
     */
    @Test
    public void ourPicture() {
        Camera camera = new Camera(new Point(0, 0,0), new Vector(0, 0, -1), new Vector(0, -1, 0)) //
                .setVPSize(300, 300).setVPDistance(400);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));

        scene.geometries.add( //
         //       new Plane(new Point(-10, 10, -600), new Point(0, 10, -600), new Point(-5, -3, -600))
           //             .setEmission(new Color(0,0,100)) //
             //           .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(100).setKt(1).setKr(0)),
                new Sphere(new Point(100,-100,-600),50)
                        .setEmission(new Color(150,100,0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(1000).setKt(0).setKr(1)),
        new Polygon(new Point(-10, 10, -60), new Point(-10, 20, -60), new Point(0, 20, -60), new Point(0, 10, -60))
                .setEmission(new Color(100,100,100)) //
                .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(1000).setKt(0.5).setKr(0.1)),
        new Triangle(new Point(-10, 10, -60), new Point(0, 10, -60), new Point(-5, -3, -60))
                .setEmission(new Color(200,0,0)) //
                .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(1000).setKt(0).setKr(1)));


        scene.lights.add(new SpotLight(new Color(200,200,200), new Point(10,50,-100), new Vector(0,-1,0)) //
                .setKl(4E-5).setKq(2E-7));
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
                .setKl(4E-4).setKq(2E-5));
        scene.lights.add(new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                .setKl(0.0004).setKq(0.0000006));

        ImageWriter imageWriter = new ImageWriter("ourPicture", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}
