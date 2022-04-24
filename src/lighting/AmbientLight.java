package lighting;

import primitives.*;

public class AmbientLight {
    Color intensity;

    public AmbientLight() {
        intensity = Color.BLACK;
    }

    public AmbientLight(Color color, Double3 ka) {
        intensity = color.scale(ka);
    }

    Color GetIntensity(){return  intensity;}
}
