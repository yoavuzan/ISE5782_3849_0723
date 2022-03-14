package primitives;

/**
 * This class will reperesent Ray
 *
 * @author Yoav uzan and yaniv bartov
 */

public class Ray {

    private final Point point;
    private final Vector derction;

    /**
     * constractor new  Ray
     *
     * @param point    of beganing
     * @param derction of vector
     */
    public Ray(Point point, Vector derction) {
        this.point = point;
        this.derction = derction.normalize();
    }

    /**
     * @return get the point of ray
     */
    public Point getPoint() {
        return point;
    }

    /**
     * @return get the vector of ray
     */
    public Vector getDerction() {
        return derction;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray) obj;
        return point.equals(other.point) && derction.equals(other.derction);
    }

    @Override
    public String toString() {
        return point.toString() + " " + derction.toString();
    }
}
