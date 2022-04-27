package unitTests.geometries;

import geometries.Cylinder;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for Geometries.cylinder class
 *
 * @author Yoav Uzan and Yaniv Bartov
 */

class CylinderTest {

    /**
     * Test method for {@link Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    void getNormalTest() {
        Cylinder cyl = new Cylinder(
                new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)), 1d,
                2d);

        // ============ Equivalence Partitions Tests ==============
        Vector temp;
        //TC1: test center of base bottom
         temp = cyl.getNormal(new Point(1.5, 0, 0));
        assertEquals(temp, new Vector(0, 1, 0), "getNormal() for side is incorrect");

        //TC2: test center of base top
         temp = cyl.getNormal(new Point(1.5, 2, 0));
        assertEquals(temp, new Vector(0, 1, 0), "getNormal() for side is incorrect");

        // =============== Boundary Values Tests ==================
        // BV01: test point at bottom center
        temp = cyl.getNormal(new Point(1, 0, 0));
        assertEquals(new Vector(0, 1, 0), temp, "getNormal() for bottom base is incorrect");

        // BV02: test point at top center
        temp = cyl.getNormal(new Point(1, 2, 0));
        assertEquals(temp, new Vector(0, 1, 0), "getNormal() for top base is incorrect");

        //BV03: test point on the right side
         temp = cyl.getNormal(new Point(2, 1, 0));
        assertEquals(temp, new Vector(1, 0, 0), "getNormal() for right side right is incorrect");

        //BV04: test point on the left side
        temp = cyl.getNormal(new Point(0, 1, 0));
        assertEquals(temp, new Vector(-1, 0, 0), "getNormal() for side is incorrect");
    }

    @Test
    void findIntersections() {
        Cylinder c1 = new Cylinder(new Ray(new Point(0,0,-2), new Vector(0,0,4)), 4d, 4.0);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the tube (0 points)
        assertNull(c1.findIntersections(new Ray(new Point(6, 4, 5), new Vector(2,-6,-5))),
                "Ray's line out of tube");

        // TC02: Ray starts before and crosses the tube (2 points)
        Point p1 = new Point(-3.8971531066245366, 0.9012200971609129, 0.45061004858045645);
        Point p2 = new Point(2.035084141107294, 3.4436074890459833, 1.7218037445229917);
        List<Point> result = c1.findIntersections(new Ray(new Point(-6, 0, 0), new Vector(14, 6, 3)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses tube");

        // TC03: Ray starts inside the tube (1 point)
        p1 = new Point(2.9883515824043925, 2.6589010549362615, 1.3294505274681307);
        result = c1.findIntersections(new Ray(new Point(-1,0,0), new Vector(9,6,3)));
        assertEquals(result.size(), 1, "Wrong number of points");
        assertEquals(List.of(p1), result, "Ray crosses sphere");

        // TC04: Ray starts after the tube (0 points)
        assertNull(c1.findIntersections(new Ray(new Point(4, 0, 0), new Vector(4,-2,0))),
                "Ray's line out of tube");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the tube (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        p1 = new Point(3.7169390722305886, 1.477959381487059, 0.0);
        result = c1.findIntersections(new Ray(new Point(3,1,0), new Vector(3,2,0)));
        assertEquals(result.size(), 1, "Wrong number of points");
        assertEquals(List.of(p1), result, "Ray crosses tube");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(c1.findIntersections(new Ray(new Point(6,3,0), new Vector(3,3,1))),
                "Ray's line out of tube");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the tube (2 points)
        p1 = new Point(-4, 0, 0);
        p2 = new Point(4, 0, 0);
        result = c1.findIntersections(new Ray(new Point(-6,0,0), new Vector(14,0,0)));
        assertEquals(result.size(), 2, "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses tube");

        // TC14: Ray starts at tube and goes inside (1 points)
        p1 = new Point(4, 0.0, 0.0);
        result = c1.findIntersections(new Ray(new Point(-2,0,0), new Vector(8,0,0)));
        assertEquals(result.size(), 1, "Wrong number of points");
        assertEquals(List.of(p1), result, "Ray crosses tube");

        // TC15: Ray starts inside (1 points)
        p1 = new Point(4, 0, 0.0);
        result = c1.findIntersections(new Ray(new Point(2,0,0), new Vector(6,0,0)));
        assertEquals(result.size(), 1, "Wrong number of points");
        assertEquals(List.of(p1), result, "Ray crosses tube");

        // TC16: Ray starts at the center (1 points)
        p1 = new Point(4, 0.0, 0.0);
        result = c1.findIntersections(new Ray(new Point(0,0,0), new Vector(8,0,0)));
        assertEquals(result.size(), 1, "Wrong number of points");
        assertEquals(List.of(p1), result, "Ray crosses sphere");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(c1.findIntersections(new Ray(new Point(4,0,0), new Vector(4,0,0))),
                "Ray's line out of tube");

        // TC18: Ray starts after sphere (0 points)
        assertNull(c1.findIntersections(new Ray(new Point(6,0,0), new Vector(2,0,0))),
                "Ray's line out of tube");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(c1.findIntersections(new Ray(new Point(4,-5,0), new Vector(0,3,0))),
                "Ray's line out of tube");

        // TC20: Ray starts at the tangent point
        assertNull(c1.findIntersections(new Ray(new Point(4,0,0), new Vector(0,3,0))),
                "Ray's line out of tube");

        // TC21: Ray starts after the tangent point
        assertNull(c1.findIntersections(new Ray(new Point(4,1,0), new Vector(0,3,0))),
                "Ray's line out of tube");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(c1.findIntersections(new Ray(new Point(6,1,0), new Vector(0,2,0))),
                "Ray's line out of tube");
    }
}