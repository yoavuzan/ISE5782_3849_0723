package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Double3;

public class Scene {
    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = new Geometries();

    Scene(String name1) {
        name = name;
    }

    public Scene SetBackGround(Color background1) {
        background = background1;
        return this;
    }

    public Scene SetAmbientLight(Color color, Double3 ka) {
        ambientLight = new AmbientLight(color, ka);
        return this;
    }

    public Scene SetGeometries(Geometries geometries1) {
        geometries = geometries1;
        return this;
    }

}
