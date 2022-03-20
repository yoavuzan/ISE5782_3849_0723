package unitTests.geometries;

import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), checkResult,"Bad normal to triangle");

    }
}