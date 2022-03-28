package unitTests.geometries;

import geometries.Cylinder;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}