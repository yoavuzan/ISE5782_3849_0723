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

    /**
     *
     * @param other
     * @return  d = ((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) + (z2 - z1)*(z2 - z1))
     */
    public double distanceSquared(Point other){
        double x1 = xyz.d1;
        double y1 = xyz.d2;
        double z1 = xyz.d3;

        double x2 = other.xyz.d1;
        double y2 = other.xyz.d2;
        double z2 = other.xyz.d3;

        return ((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) + (z2 - z1)*(z2 - z1));
    }

    /**
     *
     * @param other
     * @return d = Sqrt (lengthSquare)
     * @link https://www.engineeringtoolbox.com/distance-relationship-between-two-points-d_1854.html
     */
    public  double distance (Point other){
        return Math.sqrt(distanceSquared(other));
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
