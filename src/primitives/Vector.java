package primitives;

/**
 * This class represent vector
 *
 * @author Yoav uzan and Yaniv Bartov
 */
public class Vector extends Point {

    /**
     * primary  constructor for Vector class
     *
     * @param xyz head of vector starting from origin Point(0.0.0)
     */
    Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector(0,0,0) is not allowed");
        }
    }

    /**
     * constructor of vector with 3 kord
     *
     * @param x kord
     * @param y kord
     * @param z kord
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
    }

    /**
     * calculate the length squared of the vector
     *
     * @return length of Squared
     */
    public double lengthSquared() {
        return dotProduct(this);
    }

    /**
     * calculate vector plus vector
     *
     * @param other vector
     * @return the result of add tow vector
     */
    public Vector add(Vector other) {
        return new Vector(xyz.add(other.xyz));
    }

    /**
     * Scale (multiply) floating point triad by a number into a new triad where each
     * number is multiplied by the number
     *
     * @param rhs right handle side operand for scaling
     * @return result of scale
     */
    public Vector scale(double rhs) {
        return new Vector(xyz.scale(rhs));
    }

    /**
     * calculate the length of vector
     *
     * @return length of vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * dot product between two vectors (scalar product)
     *
     * @param other the right vector of U.V
     * @return scalar value of the dot product
     * @link https://www.mathsisfun.com/algebra/vectors-dot-product.html
     */
    public double dotProduct(Vector other) {
        return xyz.d1 * other.xyz.d1
                + xyz.d2 * other.xyz.d2
                + xyz.d3 * other.xyz.d3;
    }

    /**
     * cross product between two vectors (vectorial product)
     *
     * @param other other the right vector of U.V
     * @return the vector resulting from the cross product (Right-hand rule)
     */
    public Vector crossProduct(Vector other) {
        //return the normal vector

        double ax = xyz.d1;
        double ay = xyz.d2;
        double az = xyz.d3;

        double bx = other.xyz.d1;
        double by = other.xyz.d2;
        double bz = other.xyz.d3;

        double cx = ay * bz - az * by;
        double cy = az * bx - ax * bz;
        double cz = ax * by - ay * bx;

        return new Vector(cx, cy, cz);//normal vector
    }

    /**
     * calculate normalized vector
     *
     * @return the normalized vector
     */
    public Vector normalize() {
        return new Vector(xyz.reduce(length()));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point other = (Point) obj;
        return super.equals(other);
    }

    @Override
    public String toString() {
        return "->" + super.toString();
    }

}
