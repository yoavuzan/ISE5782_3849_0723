package unitTests.primitives;
import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for primitives.Point class
 * @author Yoav Uzan and Yaniv Bartov
 */
class PointTest {

    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Point(2,3,3);
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Point");
        }
    }

    @org.junit.jupiter.api.Test
    void add()
    {
        Vector vector=new Vector(1,2,3);
        Point point=new Point(6,5,7);
        assertNotEquals( point.add(vector),  new Point(0,0,0),"Error result ! add function equals to zero ! ");

        Point p1 = new Point(1,2,3);
        // ============ Equivalence Partitions Tests ==============
        Vector v=new Vector(1,2,3);
        Point newP=new Point(2,4,6);
        //TC01: Test that the result of add is proper
        assertEquals(newP,p1.add(v),"add() got wrong result ");

        // =============== Boundary Values Tests ==================

    }

    @org.junit.jupiter.api.Test
    void subtract() {
    }

    @org.junit.jupiter.api.Test
    void distanceSquared() {
    }

    @org.junit.jupiter.api.Test
    void distance() {
    }
}