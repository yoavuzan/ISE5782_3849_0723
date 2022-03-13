package unitTests.primitives;
import primitives.Double3;
import org.junit.jupiter.api.Test;
import primitives.Point;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for primitives.Point class
 * @author Yoav Uzan and Yaniv Bartov
 */
class Double3Test {

    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Double3(2,3,3);
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Double3");
        }
    }
    @org.junit.jupiter.api.Test
    void add() {
    }

    @org.junit.jupiter.api.Test
    void subtract() {
    }

    @org.junit.jupiter.api.Test
    void scale() {
    }

    @org.junit.jupiter.api.Test
    void reduce() {
    }

    @org.junit.jupiter.api.Test
    void product() {
    }
}