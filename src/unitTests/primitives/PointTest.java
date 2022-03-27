package unitTests.primitives;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for primitives.Point class
 * @author Yoav Uzan and Yaniv Bartov
 */
class PointTest {
/**
 * test constructor of point
 */
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

    /**
     * test add of tow point
     */
    @org.junit.jupiter.api.Test
    void addTest()
    {
        Point p1 = new Point(1,2,3);
        // ============ Equivalence Partitions Tests ==============
        Vector v=new Vector(1,2,3);
        Point newP=new Point(2,4,6);
        //TC01: Test that the result of add is proper
        assertEquals(newP,p1.add(v),"add() got wrong result ");

    }

    /**
     * test subtract of tow point
     */
    @Test
    void subtractTest() {
        Point p1 = new Point(2,3,4);
        Point p2=new Point(1,2,3);
        // ============ Equivalence Partitions Tests ==============
        Vector v=new Vector(1,1,1);
        //TC01: Test that the result of subtract is proper
        assertEquals(v,p1.subtract(p2),"subtract() got wrong result ");
        // TC02: Test to check if throw exception- subtract to a (0,0,0) vector
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1), "subtract for 0 does not throw an exception");
    }

    /**
     * test distance of squared
     */
    @org.junit.jupiter.api.Test
    void distanceSquared() {
        Point p1 = new Point(1,2,3);
        Point p2 = new Point(-1,-2,-3);

        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the result of distance is proper
        assertEquals(p1.distanceSquared(new Point(1, 1, 1)),5d,"distanceSquared got wrong result ");
        //TC02: Test that the result of distance is proper
        assertEquals(p1.distanceSquared(new Point(1, 1, 1)),25d,"distanceSquared got wrong result ");
        //TC03: Test that the result of distance is proper
        assertEquals(p1.distanceSquared(new Point(0,0,0)),14d,"distanceSquared got wrong result ");
        //TC04: Test that the result of distance is proper
        assertEquals(p1.distanceSquared(new Point(0,0,0)),14d,"distanceSquared got wrong result ");

        // =============== Boundary Values Tests ==================
        // TC5: the same distance
        // expected 0
        assertEquals(p1.distanceSquared(p1), 0);
    }

    /**
     * test distance with tow point
     */
    @org.junit.jupiter.api.Test
    void distance() {
        Point p1 = new Point(1,1,2);
        Point p2 = new Point(1,4,6);
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the result of substract is proper
        assertEquals(p1.distance(p2),5d,"distance() got wrong result ");

    }
}