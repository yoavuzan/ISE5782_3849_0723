package primitives;

/**
 * This class will reperesent Ray
 *
 * @author Yoav uzan and yaniv bartov
 */
public class Ray {

    final Point point;
    final Vector vector;

    /**
     *
     * @param point
     * @param vector
     */
    public Ray(Point point, Vector vector){
        this.point=point;
        this.vector=vector.normalize();
    }

    /**
     * @return Point
     */
    public Point getPoint() {
        return point;
    }

    /**
     *
     * @return vector
     */
    public Vector getVector() {
        return vector;
    }

    /**
     *
     * @param obj
     * @return if the obj equals to parm.
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray)obj;
        return point.equals(other.point)&&vector.equals(other.vector);
    }

    /**
     *
     * @return string of parm
     */
    @Override
    public String toString() { return point.toString()+" "+vector.toString(); }
}
