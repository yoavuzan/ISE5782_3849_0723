package primitives;

public class Ray {
    final Point point;
    final Vector vector;

    public Ray(Point point, Vector vector){
        this.point=point;
        this.vector=vector.normalize;
    }

    public Point getPoint() {
        return point;
    }

    public Vector getVector() {
        return vector;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point other = (Ray)obj;
        return point.equals(other.point)&&vector.equals(other.vector);
    }

    @Override
    public String toString() { return point.toString()+" "+vector.toString(); }
}
