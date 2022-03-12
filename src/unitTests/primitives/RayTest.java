package unitTests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Ray;
import primitives.Vector;
import primitives.Point;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Point class
 * @author Yoav Uzan and Yaniv Bartov
 */

class RayTest {
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Ray(new Point(1,1,1),new Vector(1,3,4));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Double3");
        }
    }

}