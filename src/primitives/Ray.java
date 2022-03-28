package primitives;

/**
 * This class will reperesent Ray
 *
 * @author Yoav uzan and yaniv bartov
 */

public class Ray {

    private final Point point0;
    private final Vector direction;

    /**
     * constructor new Ray
     *
     * @param point     of beginning
     * @param direction of vector
     */
    public Ray(Point point, Vector direction) {
        this.point0 = point;
        this.direction = direction.normalize();
    }

    /**
     * Getter for the direction of the ray
     *
     * @return the direction vector
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * Getter for the head point of the ray
     *
     * @return the head point
     */
    public Point getPoint0() {
        return point0;
    }

    /**
     * calculate point on the ray
     *
     * @param t -is number
     * @return P= P0+t∙rayDirection
     */
    public Point getPoint(double t) {
        return point0.add(direction.scale(t));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray) obj;
        return point0.equals(other.point0) && direction.equals(other.direction);
    }

    @Override
    public String toString() {
        return point0.toString() + " " + direction.toString();
    }
}
