package unitTests.primitives;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

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