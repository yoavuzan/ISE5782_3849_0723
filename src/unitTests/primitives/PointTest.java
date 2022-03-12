package unitTests.primitives;
import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

import java.util.function.Supplier;

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