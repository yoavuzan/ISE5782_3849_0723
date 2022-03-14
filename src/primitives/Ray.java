package primitives;

/**
 * This class will reperesent Ray
 *
 * @author Yoav uzan and yaniv bartov
 */

public class Ray {

    private final Point point;
    private final Vector direction;

    /**
     * constractor new  Ray
     *
     * @param point    of beganing
     * @param direction of vector
     */
    public Ray(Point point, Vector direction) {
        this.point = point;
        this.direction = direction.normalize();
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
    public Vector getDirection() {
        return direction;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray) obj;
        return point.equals(other.point) && direction.equals(other.direction);
    }

    @Override
    public String toString() {
        return point.toString() + " " + direction.toString();
    }
}
