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

    public void TestConstuctorPlane() {
        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct plane");
        }
    }

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
     * Test  Find Intersection
     */
    @Test
    void testFindIntersection() {
        Plane plane = new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));


    }


}
