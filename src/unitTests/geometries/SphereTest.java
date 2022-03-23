package unitTests.geometries;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for Geometries.Sphere class
 * @author Yoav Uzan and Yaniv Bartov
 */
class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     */
    @Test
    void getNormalTest() {
        //============ Equivalence Partitions Tests ==============//
        //T01: Testing that getNormal returns the right vector
        Sphere sp = new Sphere(new Point(1, 1, 0), 1);
        assertEquals( sp.getNormal(new Point(2, 1, 0)), new Vector(1, 0, 0),"getNormal() is bad");
    }
    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere( new Point (1, 0, 0),1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(
                sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),"Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(),"Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere",List.of(p1, p2), result);

        // TC03: Ray starts inside the sphere (1 point)
        result = sphere.findIntersections(new Ray( new Point(0.5, 0.5, 0),new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere once", p2, result.get(0));

        // TC04: Ray starts after the sphere (0 points)
        assertNull("Ray's line out of sphere",
                sphere.findIntersections(new Ray(new Point(2, 1, 0),new Vector(3, 1, 0))));

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray( new Point(1, -1, 0),new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere once", new Point(1.6, -0.8, 0), result.get(0));

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull("Ray's line out of sphere",
                sphere.findIntersections(new Ray(new Point(1.6, -0.8, 0),new Vector(3, 1, 0))));

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        p1 = new Point(0.4, 0.8, 0);
        p2 = new Point(1.6, -0.8, 0);
        result = sphere.findIntersections(new Ray(new Point(4, -4, 0),new Vector(-3, 4, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(p1, p2), result);

        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray (new Point(1.6, -0.8, 0),(new Vector(-3, 4, 0))));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere once", new Point(0.4, 0.8, 0), result.get(0));

        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(new Ray( new Point(1.3, -0.4, 0),new Vector(-3, 4, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere once", new Point(0.4, 0.8, 0), result.get(0));

        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntersections(new Ray( new Point(1, 0, 0),new Vector(-3, 4, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere once", new Point(0.4, 0.8, 0), result.get(0));

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull("Ray's line out of sphere",
                sphere.findIntersections(new Ray( new Point(0.4, 0.8, 0),new Vector(-3, 4, 0))));

        // TC18: Ray starts after sphere (0 points)
        assertNull("Ray's line out of sphere",
                sphere.findIntersections(new Ray( new Point(-0.2, 1.6, 0),new Vector(-3, 4, 0))));

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull("Ray's line out of sphere",
                sphere.findIntersections(new Ray( new Point(0.8, 1.1, 0),new Vector(-4, -3, 0))));

        // TC20: Ray starts at the tangent point
        assertNull("Ray's line out of sphere",
                sphere.findIntersections(new Ray( new Point(0.4, 0.8, 0),new Vector(4, 3, 0))));

        // TC21: Ray starts after the tangent point
        assertNull("Ray's line out of sphere",
                sphere.findIntersections(new Ray( new Point(0.8, 1.1, 0),new Vector(4, 3, 0))));

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(
                sphere.findIntersections(new Ray( new Point(0, 1, 0),new Vector(4, 3, 0))),"Ray's line out of sphere");

    }

}