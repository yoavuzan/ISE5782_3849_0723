package unitTests.primitives;


import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class RayTest {

@Test
    public void testFindClosestPoint() {
        Ray ray = new Ray( new Point(1, 0, 0),new Vector(-1, 1, 0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: The point that was found is in the middle of the list

        assertEquals( new Point(2, 0, 0),
                ray.findClosestPoint(List.of(new Point(5, 0, 0), new Point(2, 0, 0), new Point(8, 0, 0))),
                "The point is not the closeset to the ray (middle test)");

        // =============== Boundary Values Tests ==================

        // TC11: No point was found (empty list)
        assertNull(ray.findClosestPoint(null),"The list should return null");

        // TC12: The point that was found is in the head of the list
        assertEquals( new Point(2, 0, 0),
                ray.findClosestPoint(List.of(new Point(2, 0, 0), new Point(5, 0, 0), new Point(8, 0, 0))),
                "The point is not close to the ray (head test)");

        // TC13: The point that was found is in the tail of the list
        assertEquals( new Point(2, 0, 0),
                ray.findClosestPoint(List.of(new Point(8, 0, 0), new Point(5, 0, 0), new Point(2, 0, 0)))
        ,"The point is not close to the ray (tail test)");
    }

}