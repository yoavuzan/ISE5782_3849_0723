package unitTests.renderer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import renderer.Camera;
import primitives.*;
import geometries.*;

import java.util.List;

/**
 * Testing Camera Class
 *
 * @author Yoav Uzan and Yaniv Bar-Tov
 */
class CameraTest {
    static final Point ZERO_POINT = new Point(0, 0, 0);

    /**
     * Test method for work
     */

    @Test
    void testConstructRay() {
        Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0)).setVPDistance(10);
        String badRay = "Bad ray";

        // ============ Equivalence Partitions Tests ==============

        // EP01: 4X4 Inside (1,1)
        assertEquals(new Ray(ZERO_POINT, new Vector(1, -1, -10)),
                camera.setVPSize(8, 8).constructRay(4, 4, 1, 1), badRay);

        // =============== Boundary Values Tests ==================
        // BV01: 3X3 Center (1,1)

        assertEquals(new Ray(ZERO_POINT, new Vector(0, 0, -10)),
                camera.setVPSize(6, 6).constructRay(3, 3, 1, 1), badRay);

        // BV02: 3X3 Center of Upper Side (0,1)
        assertEquals(new Ray(ZERO_POINT, new Vector(0, -2, -10)),
                camera.setVPSize(6, 6).constructRay(3, 3, 1, 0), badRay);

        // BV03: 3X3 Center of Left Side (1,0)
        assertEquals(new Ray(ZERO_POINT, new Vector(2, 0, -10)),
                camera.setVPSize(6, 6).constructRay(3, 3, 0, 1), badRay);

        // BV04: 3X3 Corner (0,0)
        assertEquals(new Ray(ZERO_POINT, new Vector(2, -2, -10)),
                camera.setVPSize(6, 6).constructRay(3, 3, 0, 0), badRay);

        // BV05: 4X4 Corner (0,0)
        assertEquals(new Ray(ZERO_POINT, new Vector(3, -3, -10)),
                camera.setVPSize(8, 8).constructRay(4, 4, 0, 0), badRay);

        // BV06: 4X4 Side (0,1)
        assertEquals(new Ray(ZERO_POINT, new Vector(1, -3, -10)),
                camera.setVPSize(8, 8).constructRay(4, 4, 1, 0), badRay);

        String wrongInter = "wrong intersection";
        //  3X3 with tow instruction
        camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1).setVPSize(3, 3);

        assertEquals(2, numberOfIntersections(3, 3, new Geometries(new Sphere(new Point(0, 0, -3), 1)), camera)
                , wrongInter);

        camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, -1, 0)).setVPDistance(1).setVPSize(3, 3);

        //  3X3 with 18 instruction
        assertEquals(18, numberOfIntersections(3, 3, new Geometries(new Sphere(new Point(0, 0, -2.5), 2.5)), camera)
                , wrongInter);
        //  3X3 with 10 instruction
        assertEquals(10, numberOfIntersections(3, 3, new Geometries(new Sphere(new Point(0, 0, -2), 2)), camera)
                , wrongInter);

        //  3X3 with 9 instruction
        assertEquals(9, numberOfIntersections(3, 3, new Geometries(new Sphere(new Point(0, 0, 2), 4)), camera)
                , wrongInter);

        //  3X3 with 0 instruction
        assertEquals(0, numberOfIntersections(3, 3, new Geometries(new Sphere(new Point(0, 0, 1), 0.5)), camera)
                , wrongInter);

        //**********Plane Tests******************

        //nine intersection with parallel Plane
        assertEquals(9,
                numberOfIntersections(3, 3, new Geometries(new Plane(new Point(0, 0, 0), new Vector(0, 0, 1))), camera), wrongInter);

        //nine intersection with sloping Plane
        assertEquals(9,
                numberOfIntersections(3, 3, new Geometries(new Plane(new Point(0, 0, 0), new Vector(0, 1, 5))), camera), wrongInter);

        //six intersection with sharp sloping Plane
        assertEquals(6,
                numberOfIntersections(3, 3, new Geometries(new Plane(new Point(0, 0, 0), new Vector(0, 4, 1))), camera), wrongInter);

        //*********** triangle test***********

        //one intersection with triangle

        assertEquals(1,
                numberOfIntersections(3, 3, new Geometries(new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2))), camera), wrongInter);

        //tow intersection with triangle

        assertEquals(2,
                numberOfIntersections(3, 3, new Geometries(new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2))), camera), wrongInter);

    }

    /**
     * Calculates the number of intersections
     *
     * @param nx
     * @param ny
     * @param geometries
     * @param camera
     * @return the number of intersections
     */
    private int numberOfIntersections(int nx, int ny, Geometries geometries, Camera camera) {
        int numOfIntersections = 0;
        for (int i = 0; i < nx; i++)
            for (int j = 0; j < ny; j++) {
                Ray ray = camera.constructRay(nx, ny, j, i);
                List<Point> intersections = geometries.findIntersections(ray);
                if (intersections != null)
                    numOfIntersections += geometries.findIntersections(ray).size();
            }
        return numOfIntersections;
    }

}

