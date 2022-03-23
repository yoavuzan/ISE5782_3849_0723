package unitTests.geometries;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.*;

/**
 * Unit tests for Geometries.Plane class
 * @author Yoav Uzan and Yaniv Bartov
 */
class PlaneTest {

    public void TestConstructorPlane()
    {
        // TC01: Correct concave quadrangular with vertices in correct order
        try
        { new Plane(new Point(0, 0, 1), new Point(1, 0, 0),new Point(0,1,0));}
         catch(IllegalArgumentException e)
        {fail("Failed constructing a correct plane");}
    }
void TestPlane(){
        //============ Boundary Values Test=============
    // Tow point converge

    assertThrows(IllegalArgumentException.class,()->new Plane(
                    new Point(0, 0, 1),
                    new Point(0, 0, 1),
                    new Point(0, 1, 0)),
            "constructor for 3 points does not throw an exception in case of 2 same points");

    //test all points on same line
    assertThrows(IllegalArgumentException.class,()->new Plane(
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
    Plane plane=new Plane(
            new Point(0,0,1),
            new Point(1,0,0),
            new Point(0,1,0));

    double sqrt=Math.sqrt(1d/3);//point for check
    Vector normalCheck=plane.getNormal(new Point(0,0,1));
        assertEquals("Bad normal to plane",new Vector(sqrt, sqrt, sqrt), normalCheck);
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
        List<Point> result = plane.findIntersections(new Ray(new Point(2, 4, 0),new Vector(-3, -1, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses plane once", new Point(-1.75, 2.75, 0), result.get(0));
        // my calcuate=(-0.4,3.2,0)
        // TC02: Ray does not intersect the plane(0 point)
        assertNull("Ray's crosses the plane",
                plane.findIntersections(new Ray( new Point(2, 4, 0),new Vector(3, 4, 0))));
        // my calcuate=(-1/7,8/7,0)
        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to the plane

        // TC10: The ray is not included in the plane
        assertNull("Ray's parallel the plane",
                plane.findIntersections(new Ray( new Point(-1, -1, 0),new Vector(-1, 1, 0))));

        // TC11: The ray included in the plane
        assertNull("Ray's included the plane",
                plane.findIntersections(new Ray( new Point(1, -1, 1),new Vector(-1, 1, 0))));

        // **** Group: Ray is orthogonal to the plane

        // TC12: The ray start is before the plane
        result = plane.findIntersections(new Ray(new Point(-1, -1, 0),new Vector(1, 1, 1)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses plane once", new Point(0, 0, 1), result.get(0));

        // TC13: The ray start is in the plane
        assertNull("Ray's crosses the plane",
                plane.findIntersections(new Ray( new Point(1, -1, 1),new Vector(1, 1, 1))));

        // TC14: The ray start is after the plane
        assertNull("Ray's crosses the plane",
                plane.findIntersections(new Ray(new Point(2, 0, 0),new Vector(1, 1, 1) )));

        // ***********

        // TC15: Ray is neither orthogonal nor parallel and begins at the plane
        assertNull("Ray's crosses the plane",
                plane.findIntersections(new Ray( new Point(1, -1, 1),new Vector(-3, -1, 0))));

        // TC16: Ray is neither orthogonal nor parallel to the plane and begins in the
        // same point
        assertNull("Ray's crosses the plane",
                plane.findIntersections(new Ray( new Point(1, 0, 0),new Vector(-3, -1, 0))));
    }

}