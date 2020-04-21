package unittests;
import geometries.*;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

public class PlaneTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void getNormal()
    {
        Plane plane = new Plane(new Point3D(1,2,3),new Point3D(1,1,1), new Point3D(2,2,2));
        // ============ Equivalence Partitions Tests ==============
        assertEquals(plane.getNormal(new Point3D(1,2,3)),new Vector(1,-2,1).normalize());
    }

    @Test
    public void findIntsersections()
    {
        Plane plane = new Plane(new Point3D(0,0,1),new Point3D(2,1,1), new Point3D(2,2,1));
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the plane (0 points)
        assertEquals("Ray's line out of plane", null,
                plane.findIntsersections(new Ray(new Vector(1, 1, 1),new Point3D(-1, 0, 5))));

        // TC02: Ray starts before and crosses the plane (1 points)
        List<Point3D> result = plane.findIntsersections(new Ray(new Vector(-1, -1, -1),new Point3D(1, 1, 2)));
        assertEquals("Wrong number of points", 1, result.size());

        assertEquals("Ray crosses plane", List.of(new Point3D(0, 0, 1)), result);

        // TC04: Ray starts after the plane (0 points)
         result = plane.findIntsersections(new Ray(new Vector(-1, -1, -1),new Point3D(-1, -1, -2)));
        assertEquals("Wrong number of points", null, result);

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the plane
        // TC11: Ray starts at plane and goes inside (0 points)
         result = plane.findIntsersections(new Ray(new Vector(-1, -1, -1),new Point3D(1, 1, 1)));
        assertEquals("Wrong number of points", null, result);


        // **** Group: Ray's line orthogonal
        // TC13: Ray starts before the plane (1 points)
        result = plane.findIntsersections(new Ray(new Vector(0, 0, -1),new Point3D(1, 1, 2)));
        assertEquals("Wrong number of points", 1, result.size());

        assertEquals("Ray crosses plane", List.of(new Point3D(1, 1, 1)), result);


        // TC14: Ray starts at plane (0 points)
        result = plane.findIntsersections(new Ray(new Vector(0, 0, -1),new Point3D(1, 1, 1)));
        assertEquals("Wrong number of points", null, result);

        // TC15: Ray starts after plane (0 points)
        result = plane.findIntsersections(new Ray(new Vector(0, 0, -1),new Point3D(1, 1, -7)));
        assertEquals("Wrong number of points", null, result);

        // **** Group: Ray's line is paralel to the plane (all tests 0 points)
        // TC19: Ray included in plane
        result = plane.findIntsersections(new Ray(new Vector(1, 0, 0),new Point3D(1, 1, 1)));
        assertEquals("Wrong number of points", null, result);

        // TC20: Ray paralel and not included in plane
        result = plane.findIntsersections(new Ray(new Vector(1, 0, 0),new Point3D(1, 1, 8)));
        assertEquals("Wrong number of points", null, result);

        // **** Group: Special cases
        // TC19: Ray is neither orthogonal nor parallel to and begins at the plane (𝑃0 is in the plane, but not the ray)
        result = plane.findIntsersections(new Ray(new Vector(1, 0, 1),new Point3D(1, 1, 1)));
        assertEquals("Wrong number of points", null, result);

        //TC19.5 Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane
        result = plane.findIntsersections(new Ray(new Vector(1, 0, 1),new Point3D(0, 0, 1)));
        assertEquals("Wrong number of points", null, result);

    }
}