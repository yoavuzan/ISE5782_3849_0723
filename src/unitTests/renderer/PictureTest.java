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

public class PictureTest {
    private Scene scene = new Scene("Test scene");

    /**
     * produces a picture we created, looks like bubble-gum
     */
    @Test
    public void ourPicture() {
        Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, -1, 0)) //
                .setVPSize(300, 300).setVPDistance(400);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));
        scene.background = new Color(40, 40, 80);
        scene.geometries.add( //
                new Sphere(new Point(100, -100, -600), 50).setEmission(new Color(250, 100, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(1000).setKt(0).setKr(1)),

                new Polygon(new Point(20, 15, 10), new Point(-30, 15, 10), new Point(-30, 15, -70), new Point(20, 15, -70))
                        .setEmission(new Color(15, 100, 15)) //
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(1).setKr(0)),

                new Polygon(new Point(-5, 5, -50), new Point(-5, 15, -50), new Point(-15, 15, -50), new Point(-15, 5, -50))
                        .setEmission(new Color(100, 100, 100)) //(A,B,C,D)
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(1000).setKt(0).setKr(0.1)),
                new Polygon(new Point(-5, 5, -60), new Point(-5, 15, -60), new Point(-15, 15, -60), new Point(-15, 5, -60))
                        .setEmission(new Color(100, 100, 100)) //(E,F,G,H)
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(1000).setKt(0).setKr(0.1)),
                new Polygon(new Point(-5, 5, -50), new Point(-5, 5, -60), new Point(-15, 5, -60), new Point(-15, 5, -50))
                        .setEmission(new Color(100, 100, 100)) //(A,E,H,D)
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(1000).setKt(0).setKr(0.1)),
                new Polygon(new Point(-15, 5, -50), new Point(-15, 5, -60), new Point(-15, 15, -60), new Point(-15, 15, -50))
                        .setEmission(new Color(100, 100, 100)) //(D,H,G,C)
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(1000).setKt(0).setKr(0.1)),

                new Polygon(new Point(-8.5, 15, -49.9), new Point(-11.5, 15, -49.9), new Point(-11.5, 10, -49.9), new Point(-8.5, 10, -49.9))
                        .setEmission(new Color(50, 25, 0)) //dor
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(100).setKt(0).setKr(0.1)),

                new Polygon(new Point(-5, 5, -50), new Point(-5, 5, -60), new Point(-5, 15, -60), new Point(-5, 15, -50))
                        .setEmission(new Color(100, 100, 100)) //A,E,F,B
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(1000).setKt(0).setKr(0.1)),


                //bc
                new Triangle(new Point(-5, 5, -50), new Point(-15, 5, -50), new Point(-10, -5, -55))
                        .setEmission(new Color(200, 0, 0)) //E,H
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(1000).setKt(0).setKr(0)),
                //cg
                new Triangle(new Point(-15, 5, -50), new Point(-15, 5, -60), new Point(-10, -5, -55))
                        .setEmission(new Color(200, 0, 0)) //G,H
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(1000).setKt(0).setKr(0)),
                //gf
                new Triangle(new Point(-15, 5, -60), new Point(-5, 5, -60), new Point(-10, -5, -55))
                        .setEmission(new Color(200, 0, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(1000).setKt(0).setKr(0)),
                //fb
                new Triangle(new Point(-5, 5, -60), new Point(-5, 5, -50), new Point(-10, -5, -55))
                        .setEmission(new Color(200, 0, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(1000).setKt(0).setKr(0)));

        for (int x = -16; x <= 16; x=x+4) {
            scene.geometries.add(new Cylinder(new Ray(new Point(x, 15, -62), new Vector(0, -1, 0)), 0.4, 7)
                    .setEmission(new Color(200, 100, 0))
                    .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(1000).setKt(0).setKr(0)));
        }
        scene.geometries.add(new Cylinder(new Ray(new Point(-17, 12, -62), new Vector(1, 0, 0)), 0.4, 34)
                .setEmission(new Color(200, 100, 0))
                .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(1000).setKt(0).setKr(0)),
                new Cylinder(new Ray(new Point(-17, 9, -62), new Vector(1, 0, 0)), 0.4, 34)
                        .setEmission(new Color(200, 100, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(1000).setKt(0).setKr(0)));

        scene.lights.add(new PointLight(new Color(500, 400, 200), new Point(100, -100, -500)) //
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
