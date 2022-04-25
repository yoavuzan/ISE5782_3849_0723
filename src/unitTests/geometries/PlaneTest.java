package unitTests.geometries;

import geometries.Plane;
import geometries.Polygon;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Geometries.Plane class
 *
 * @author Yoav Uzan and Yaniv Bartov
 */
class PlaneTest {

    @Test
    public void TestConstructorPlane() {
        // TC01: Correct concave quadrangular with vertices in correct order
        assertDoesNotThrow(() ->  new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0)),
                "Failed constructing a correct plane");
    }

    @Test
    void TestPlane() {
        //============ Boundary Values Test=============
        // Tow point converge

        assertThrows(IllegalArgumentException.class, () -> new Plane(
                        new Point(0, 0, 1),
                        new Point(0, 0, 1),
                        new Point(0, 1, 0)),
                "constructor for 3 points does not throw an exception in case of 2 same points");

        //test all points on same line
        assertThrows(IllegalArgumentException.class, () -> new Plane(
                        new Point(1, 1, 1),
                        new Point(2, 2, 2),
                        new Point(3, 3, 3)),
                "constructor for 3 points does not throw an exception in case of all points on same line");
    }


    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        Plane plane = new Plane(
                new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0));

        double sqrt = Math.sqrt(1d / 3);//point for check
        Vector normalCheck = plane.getNormal(new Point(0, 0, 1));
        assertEquals(new Vector(sqrt, sqrt, sqrt), normalCheck, "Bad normal to plane");
    }

    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {

        Plane plane = new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));
        // ============ Equivalence Partitions Tests ==============
        // **** Group:The Ray must be neither orthogonal nor parallel to the plane

        // TC01: Ray intersects the plane(1 point)
        List<Point> result = plane.findIntersections(new Ray(new Point(2, 4, 0), new Vector(-3, -1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(-1.75, 2.75, 0), result.get(0), "Ray crosses plane once");
        // TC02: Ray does not intersect the plane(0 point)
        assertNull(
                plane.findIntersections(new Ray(new Point(2, 4, 0), new Vector(3, 4, 0)))
                , "Ray's crosses the plane");
        // my calcuate= (-1/7,8/7,0)
        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to the plane

        // TC10: The ray is not included in the plane
        assertNull(
                plane.findIntersections(new Ray(new Point(-1, -1, 0), new Vector(-1, 1, 0)))
                , "Ray's parallel the plane");

        // TC11: The ray included in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1, -1, 1), new Vector(-1, 1, 0)))
                , "Ray's included the plane");

        // **** Group: Ray is orthogonal to the plane

        // TC12: The ray start is before the plane
        result = plane.findIntersections(new Ray(new Point(-1, -1, 0), new Vector(1, 1, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(0, 0, 1), result.get(0), "Ray crosses plane once");

        // TC13: The ray start is in the plane
        assertNull(
                plane.findIntersections(new Ray(new Point(1, -1, 1), new Vector(1, 1, 1)))
                , "Ray's crosses the plane");

        // TC14: The ray start is after the plane
        assertNull(
                plane.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 1, 1))),
                "Ray's crosses the plane");

        // ***********
        // TC15: Ray is neither orthogonal nor parallel and begins at the plane
        assertNull(
                plane.findIntersections(new Ray(new Point(1, -1, 1), new Vector(-3, -1, 0))),
                "Ray's crosses the plane");

        // TC16: Ray is neither orthogonal nor parallel to the plane and begins in the
        // same point
        assertNull(
                plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-3, -1, 0)))
                , "Ray's crosses the plane");
    }
}