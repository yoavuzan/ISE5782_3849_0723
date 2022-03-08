package unitTests;
import primitives.Point;
import primitives.Vector;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

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