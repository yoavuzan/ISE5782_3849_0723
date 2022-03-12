package unitTests.geometries;

import geometries.Plane;
import geometries.Polygon;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for Geometries.Plane class
 * @author Yoav Uzan and Yaniv Bartov
 */
class PlaneTest {

    public void TestConstuctorPlane()
    {
        // TC01: Correct concave quadrangular with vertices in correct order
        try
        { new Plane(new Point(0, 0, 1), new Point(1, 0, 0),new Point(0,1,0));}
         catch(IllegalArgumentException e)
        {fail("Failed constructing a correct plane");}
    }

    @Test
    void getQ0() {
    }


    @Test
    void testGetNormal() {
    Plane plane=new Plane(new Point(2,5,7),new Point(4,7,11),new Point(3,10,9));
    assertEquals(plane.getNormal(),1,"there is no normaliza");

    }
}