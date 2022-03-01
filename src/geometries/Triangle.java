package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

public class Triangle implements Geometry {


    public  Triangle(List<Point> vertices){
        if (vertices.length > 3)
            throw new IllegalArgumentException("A triangle can't have more than 3 vertices");
        super(vertices);
    }

    public Point getQ1() {
        return q1;
    }

    public Point getQ2() {
        return q2;
    }

    public Point getQ3() {
        return q3;
    }
}