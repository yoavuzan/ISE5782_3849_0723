package unitTests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Vector class
 *
 * @author Yoav Uzan and Yaniv Bartov
 */
public class VectorTest {

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    public void normalizeTest() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        // TC01: Test to check if the result of vector is legal
        assertEquals(1, u.length(), 0.0000001, "normalize() result is not a unit vector");
        assertEquals(v.length(), v.dotProduct(u), 0.0000001, "");
    }

    /**
     * Test add of two vectors
     */
    @Test
    public void addTest() {
        // ============ Equivalence Partitions Tests ==============
        Vector vector1 = new Vector(1, 1, 1);
        Vector vector2 = new Vector(1, 2, 3);

        //TC01: Test that the result of add is proper
        assertEquals(vector1.add(vector1), new Vector(2, 2, 2), "add() got wrong result ");

        // TC02: Test to check if throw exception for  ZERO vector
        assertThrows(IllegalArgumentException.class, () -> vector2.add(new Vector(-1, -2, -3)), "add() for ZERO vector does not throw an exception");
    }

    /**
     * Test subtract of two vectors
     */
    @Test
    public void subtractTest() {
        // ============ Equivalence Partitions Tests ==============
        Vector vector1 = new Vector(3, 3, 3);
        Vector vector2 = new Vector(1, 1, 1);
        Vector vector3 = new Vector(2, 2, 2);

        //TC01: Test that the result of add is proper
        vector1 = vector1.subtract(vector2);
        assertEquals(vector1, vector3, "substruct() got wrong result ");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}
     * Test scalar multi vector
     */
    @Test
    void testScale() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============

        //TC01: Test that the result of scale is proper
        assertEquals(v1.scale(2), new Vector(2, 4, 6), "scale() got wrong result ");

        // TC02: Test to check if throw exception
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0), "scale() for 0 does not throw an exception");
    }

    /**
     * Dot Product of two vectors
     */
    @Test
    public void dotProductTest() {
        Vector vector1 = new Vector(1, 0, 0);
        Vector vector2 = new Vector(0, 1, 0);
        // ============ Equivalence Partitions Tests ==============

        //TC01: Test that the result of dot product is proper
        assertEquals(0, vector1.dotProduct(vector2), 0.0000001, "dotProduct() wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void crossProductTest() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertEquals(0, vr.dotProduct(v1), 0.000001, "crossProduct() result is not orthogonal to 1st operand");
        assertEquals(0, vr.dotProduct(v2), 0.000001, "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test length of vector
     */
    @Test
    public void lengthTest() {
        Vector vector1 = new Vector(2, 2, 1);
        //TC01: Test to check if  the result of length is proper
        assertEquals(vector1.length(), 3d, "length() is not what it should be");
    }
}
