package geometries;

import java.util.List;

import primitives.*;

import static primitives.Util.*;


/**
 * This class will represent Polygon
 *
 * @author Yoav Uzan and Yaniv Bar-tov
 */
public class Triangle extends Polygon {

    /**
     * constructor new triangle with vertices
     * @param vertices
     */
    public Triangle(Point... vertices) {
        super(vertices);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}