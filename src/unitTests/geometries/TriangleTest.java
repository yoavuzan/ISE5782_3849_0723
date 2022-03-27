package unitTests.geometries;

import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for Geometries.Triangle class
 * @author Yoav Uzan and Yaniv Bartov
 */
class TriangleTest {

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
         Triangle triangle = new Triangle(
                new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0));

        double sqrt3 = Math.sqrt(1d / 3);
        Vector checkResult= triangle.getNormal(new Point(0, 0, 1));//normal from point in the triangle
        assertEquals("Bad normal to triangle",new Vector(sqrt3, sqrt3, sqrt3), checkResult);
    }

    /**
     * Test method for {@link geometries.Polygon#findIntersections}.
     */
    @org.junit.Test
    public void testFindIntersections() {
        Triangle tr = new Triangle(new Point(1,0,0), new Point(0, 1, 0), new Point(0, 0, 1));
        // ============ Equivalence Partitions Tests ==============

        // TC11: Inside polygon/triangle
        List<Point> result = tr.findIntersections(new Ray( new Point(-1, -2, -1),new Vector(1, 2, 1)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses triangle once", new Point(0.25, 0.5, 0.25), result.get(0));

        // TC12: Outside against edge
        assertNull("Ray's crosses outside the triangle",
                tr.findIntersections(new Ray(new Point(-1, -2, -1),new Vector(1, 3, 3))));

        // TC13: Outside against vertex
        assertNull("Ray's crosses outside the triangle",
                tr.findIntersections(new Ray( new Point(-1, -2, -1),new Vector(1, 2, 4))));

        // =============== Boundary Values Tests ==================

        // ***** (the ray begins "before" the plane)

        // TC21: In vertex
        assertNull("Ray's crosses the triangle's vertices",
                tr.findIntersections(new Ray(new Point(-1, -2, -1),new Vector(1, 2, 2))));

        // TC22: On edge
        assertNull("Ray's crosses the triangle's edge",
                tr.findIntersections(new Ray( new Point(-1, -2, -1),new Vector(1.5, 2, 1.5))));

        // TC23: On edge's continuation
        assertNull("Ray's crosses the triangle's edge",
                tr.findIntersections(new Ray( new Point(-1, -2, -1),new Vector(0, 2, 3))));
    }
}