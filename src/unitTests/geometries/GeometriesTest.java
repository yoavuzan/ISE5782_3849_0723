package unitTests.geometries;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    /**
     * Test method for {@link geometries.Geometries#add(geometries.Intersectable[])}.
     */
    void addTest() {
    }

    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     */
    void findIntersectionsTest() {
        Sphere sph = new Sphere(new Point(1, 1, 1), 1);
        Plane plane = new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 2));
        Triangle tr = new Triangle(new Point(1,0,0), new Point(0, 1, 0), new Point(0, 0, 1));
        Geometries collection = new Geometries(sph, plane, tr);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Some of the Geometries are intersected
        Ray ray = new Ray(new Point(-1, 0, 0),new Vector(1, 1, 1));
        assertEquals("Wrong number of intersection points", 3, collection.findIntersections(ray).size()); // Intersects only plane and sphere

        // =============== Boundary Values Tests ==================
        // TC11: All the Geometries are intersected
        ray = new Ray( new Point(2, 2, 2.5),new Vector(-1, -1, -1));
        assertEquals("Wrong number of intersection points", 4, collection.findIntersections(ray).size());

        // TC12: No Geometries are intersected
        ray = new Ray( new Point(-1, 0, 0),new Vector(-1, -1, -1));
        assertNull("No intersection points", collection.findIntersections(ray));

        // TC13: Only one Geometry shape is intersected
        ray = new Ray( new Point(2, 0, 2),new Vector(-1, -1, -1));
        assertEquals("Wrong number of intersection points", 1, collection.findIntersections(ray).size());  // Intersects only plane

        // TC14: Empty Geometries collection
        collection = new Geometries();
        assertNull("No geometry shapes in the collection",
                collection.findIntersections(new Ray( new Point(-1, 0, 0),new Vector(1, 1, 0))));
    }
}