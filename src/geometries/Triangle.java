package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;


/**
 * This class will reperesent Polygon
 *
 * @author Yoav uzan and yaniv bartov
 */
public class Triangle extends Polygon {

    /**
     *
     * @param vertices
     */
    public  Triangle(Point... vertices){
        super(vertices);
    }


    List<Point> getTrungle()
    {
        //need to check
        return this.vertices;
    }

    /**
     *
     * @return string with all parm of Triangle
     */
    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }
}