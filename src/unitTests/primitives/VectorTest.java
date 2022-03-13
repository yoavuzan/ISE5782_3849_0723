package unitTests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

public class VectorTest {

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    public void testNormalize() {
        Vector v = new Vector(1, 2, 3);
        Vector v2 = new Vector(1, 2, 3);
        v.normalize();
        v2.normalize();

        assertEquals(v, v2);

        v = new Vector(1, 1, 1);
        try {
            v.normalize();
            // fail("Didn't throw divide by zero exception!");
        } catch (ArithmeticException e) {
            assertTrue(true);
        }


    }

    @Test
    public void test_add() {

        Vector vector1 = new Vector(1, 1, 1);
        Vector vector2 = new Vector(1, 1, 1);
        Vector vector3 = new Vector(2, 2, 2);
        vector2 = vector1.add(vector2);

        assertEquals(vector2, vector3);

    }

    @Test
    public void test_subtract() {

        Vector vector1 = new Vector(3, 3, 3);
        Vector vector2 = new Vector(1, 1, 1);
        Vector vector3 = new Vector(2, 2, 2);
        vector1 = vector1.subtract(vector2);

        assertEquals(vector1, vector3);

    }

    @Test
    public void test_dorProduct() {

        Vector vector1 = new Vector(1, 0, 0);
        Vector vector2 = new Vector(0, 1, 0);
        Vector vector3 = new Vector(0, 1, 0);

        if (vector1.dotProduct(vector2) != 0)
            fail("problem whith calc");
        assertTrue(true);

    }

    @Test
    public void test_length() {

        Vector vector1 = new Vector(2, 2, 1);

        if (vector1.length() != 3)
            fail("problem whith calc");
        assertTrue(true);

    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals( v1.length() * v2.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue( isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue( isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }

    @Test
    public void null_test() {

        Vector vector1 = new Vector(1, 1, 1);
        Vector vector2 = null;
        assertNull(vector2);
        // assertNotNull(vector2);
        // fail("is null");

    }

    @Test
    public void pointer_test() {

        Vector vector1 = new Vector(1, 1, 1);
        Vector vector2 = vector1;

        assertSame(vector1, vector2);
        // fail("is same");

    }

    @Test
    public void array_test() {

        Vector vector1 = new Vector(1, 1, 1);
        Vector vector2 = new Vector(1, 1, 1);
        Vector vector3 = new Vector(1, 0, 0);

        Vector[] vector11 = new Vector[3];
        vector11[0] = vector1;
        vector11[1] = vector2;
        vector11[2] = vector3;
        Vector[] vector12 = new Vector[3];
        vector12[0] = vector1;
        vector12[1] = vector2;
        vector12[2] = vector3;
        Vector[] vector13 = new Vector[3];
        vector13[0] = vector3;
        vector13[1] = vector2;
        vector13[2] = vector1;

        assertArrayEquals(vector11, vector12);
        // assertArrayEquals(vector11, vector13);
        // fail("is same");

    }

}
