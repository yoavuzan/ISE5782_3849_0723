package primitives;

/**
 * This class represent point
 *
 * @author Yoav uzan and yaniv bartov
 */
public class Point {
    public static final Point ZERO = new Point(Double3.ZERO);
    protected final Double3 xyz;

    /**
     * primary constructor for Point
     * @param xyz Double3 value for x,z,z axis
     */
    Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * secondary constructor for Point
     * @param x coordinate value for X axis
     * @param y coordinate value for Y axis
     * @param z coordinate value for Z axis
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * add vector to point
     * @param vector
     * @return the new point
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * subtract point from vector
     * @param point
     * @return the new vector
     */
    public Vector subtract(Point point) {
        return new Vector(xyz.subtract(point.xyz));
    }

    /**
     * calculate the distance squared between two points
     * @param other point
     * @return The distance squared from point to point
     */
    public double distanceSquared(Point other) {
        double dx = xyz.d1 - other.xyz.d1;
        double dy = xyz.d2 - other.xyz.d2;
        double dz = xyz.d3 - other.xyz.d3;

        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * calculate the distance between two points
     * @param other point
     * @return distance = Sqrt of lengthSquare method
     */
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point other)) return false;
        return xyz.equals(other.xyz);
    }

    @Override
    public String toString() {
        return xyz.toString();
    }

    /**
     * get the value of x coordinate of the point
     * @return  x
     */
    public double getX() {
        return xyz.d1;
    }
}