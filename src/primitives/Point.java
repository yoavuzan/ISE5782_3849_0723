package primitives;
<<<<<<< HEAD
/**
 * This class will reperesent point
 *
 * @author Yoav uzan and yaniv bartov
 */
public class Point {

    final Double3 xyz;

    /**
     * primary constructor for Point
     * @param xyz Double3 value for x,z,z axis
     */
    public Point(Double3 xyz){
        this.xyz=xyz;
    }

    /**
     * secondary constructor for Point
     *
     * @param x coordinate value for X axis
     * @param y coordinate value for Y axis
     * @param z coordinate value for Z axis
     */
    public Point(double x,double y,double z){
        xyz=new Double3(x,y,z)
    }

    public Double3 getXyz() {
        return xyz;
    }

    /**
     * add vetctor to point
     * @param vector
     * @return the new point
     */
    public Point add(Vector vector){
        return  new Point(xyz.add(vector.xyz));
    }

    /**
     * subtract point from vector
     * @param point
     * @return the new vector
     */
    public Vector subtract(Point point){
        return new Vector(xyz.subtract(point.xyz));
    }


    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj==null) return false;
        if(!(obj instanceof Point))return false;
        Point other=(Point) obj;
        return xyz.equals(other.xyz);
    }

    @Override
    public  Sring toString(){ return xyz.toString()}
=======

public class Point {
>>>>>>> initial commit
}
