package unitTests.geometries;

import geometries.Clyinder;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for Geometries.cyinder class
 * @author Yoav Uzan and Yaniv Bartov
 */

class CyinderTest {

    /**
     * Test method for {@link geometries.Clyinder#getNormal(primitives.Point)}.
     */
    @Test
    void getNormalTest() {
        // ============ Equivalence Partitions Tests ==============
        //test point not on base
        Clyinder cyl = new Clyinder(
                new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)), 1d,
                2d);
        Vector temp = cyl.getNormal(new Point(2, 1, 0));
        assertEquals(temp, new Vector(1, 0, 0), "getNormal() for side is incorrect");

        //test center of base bottom
        Vector temp1 = cyl.getNormal(new Point(1.5, 0, 0));
        assertEquals(temp1, new Vector(0, 1, 0), "getNormal() for side is incorrect");

        //test center of base top
        Vector temp2 = cyl.getNormal(new Point(1.5, 2, 0));
        assertEquals(temp2, new Vector(0, 1, 0), "getNormal() for side is incorrect");

        // =============== Boundary Values Tests ==================
        //test point on bottom
        try {
            temp = cyl.getNormal(new Point(1, 0, 0));
            assertEquals(temp, new Vector(0, 0, 0), "getNormal() for bottom base is incorrect");
        }
        catch(Exception xsption ){


        }
        //test point on top
        temp = cyl.getNormal(new Point(1, 2, 0));
        assertEquals(temp, new Vector(0, 1, 0), "getNormal() for top base is incorrect");
    }
}