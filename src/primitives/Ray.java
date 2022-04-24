package primitives;

import java.util.List;

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

    /**
     * the closet point to the began of the ray
     *
     * @param listPoint
     * @return the closet point to the began of the ray
     */
    public Point findClosestPoint(List<Point> listPoint) {
        if (listPoint == null) return null;
        Point closePoint = listPoint.get(0);
        for (Point p : listPoint) {
            if (closePoint.distance(point0) > p.distance(point0))
                closePoint = p;
        }
        return closePoint;
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
