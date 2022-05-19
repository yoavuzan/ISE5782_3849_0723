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
                new Sphere(new Point(0,50,-800),120) //the turquise sphere
                        .setEmission(new Color(0,100,100)) //
                        .setMaterial(new Material().setKd(0.6).setKs(0.9).setShininess(1000).setKt(0).setKr(1)),
                new Sphere(new Point(-210,110,-900),300) //the large purple sphere
                        .setEmission(new Color(30,0,50)) //
                        .setMaterial(new Material().setKd(0).setKs(0.2).setShininess(1000).setKt(0.6).setKr(0.2)),
                new Sphere(new Point(300,50,-200),80) //the blue sphere (not seen boldly)
                        .setEmission(new Color(0,0,100)) //
                        .setMaterial(new Material().setKd(0.6).setKs(0.9).setShininess(1000).setKt(0).setKr(0.2)),
                new Sphere(new Point(10, 30, -100),10) //the red sphere
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0).setKr(0.8)),
                new Sphere(new Point(-80, 80, -400),20) //the magenta sphere
                        .setEmission(new Color(java.awt.Color.MAGENTA)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.7).setKr(0)),
                new Sphere(new Point(-100, 60, -300),30) //the yellow sphere
                        .setEmission(new Color(java.awt.Color.YELLOW)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.7).setKr(0)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),//the triangle
                        new Point(-1500, -1500, -2000)).setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setKd(0.25).setKs(0.9).setShininess(20).setKt(0).setKr(0.8)));


        scene.lights.add(new SpotLight(new Color(200,200,200), new Point(1000,-550,1000), new Vector(0,-1,0)) //
                .setKl(4E-5).setKq(2E-7));
        scene.lights.add(new PointLight(new Color(300, 500, 500), new Point(-50, -50, 50))//
                .setKl(0.00001).setKq(0.000001));

        ImageWriter imageWriter = new ImageWriter("ourPicture", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}
