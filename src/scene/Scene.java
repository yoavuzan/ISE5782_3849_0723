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

    public Scene(String name1) {
        name = name;
    }

    public Scene setBackground(Color background1) {
        background = background1;
        return this;
    }

    public Scene setAmbientLight( AmbientLight color) {
        ambientLight = color;
        return this;
    }

    public Scene setGeometries(Geometries geometries1) {
        geometries = geometries1;
        return this;
    }

}
