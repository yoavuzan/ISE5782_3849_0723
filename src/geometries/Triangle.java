package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

public class Triangle extends Polygon {


    public  Triangle(Point... vertices){
        super(vertices);
    }
    List<Point> getTrungle()
    {
        //need to check
        return this.vertices;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }
}