package unittests;

import org.junit.Test;
import geometries.*;
import primitives.*;


import java.util.List;

import static org.junit.Assert.*;

public class SphereTest {

    @Test
    public void getNormal()
    {
        Sphere s = new Sphere(new Point3D(0,0,0),1);
        Point3D p =new Point3D(1,0,0);
        // ============ Equivalence Partitions Tests ==============
        assertEquals("getNormal() result is not good",s.getNormal(p),new Vector(1,0,0));
    }

    @Test
    public void findIntsersections()
    {
        Sphere sphere = new Sphere( new Point3D(1, 0, 0),1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals("Ray's line out of sphere", null, sphere.findIntsersections(new Ray( new Vector(1, 1, 0),new Point3D(-1, 0, 0))));
        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntsersections(new Ray( new Vector(3, 1, 0),new Point3D(-1, 0, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        
        if (result.get(0).get_x().get() > result.get(1).get_x().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(p1, p2), result);
        

        // TC03: Ray starts inside the sphere (1 point)
        result = sphere.findIntsersections(new Ray( new Vector(1, 0, 0),new Point3D(1, 0.6, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        
        assertEquals("Ray crosses sphere", List.of(new Point3D(1.8, 0.6, 0)), result);


        // TC04: Ray starts after the sphere (0 points)
        result = sphere.findIntsersections(new Ray( new Vector(1, 0, 0),new Point3D(3, 1.5, 0)));
        assertEquals("Wrong number of points", null, result);


        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntsersections(new Ray( new Vector(1, 0, 0),new Point3D(0.4, 0.8, 0)));
        assertEquals("Wrong number of points", 1, result.size());

        assertEquals("Ray crosses sphere", List.of(new Point3D(1.6, 0.8, 0)), result);


        // TC12: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntsersections(new Ray( new Vector(1, 0, 0),new Point3D(0.4, 0.8, 0)));
        assertEquals("Wrong number of points", 1, result.size());

        assertEquals("Ray crosses sphere", List.of(new Point3D(1.6, 0.8, 0)), result);


        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntsersections(new Ray( new Vector(1, 0, 0),new Point3D(-2, 0, 0)));
        assertEquals("Wrong number of points", 2, result.size());

        if (result.get(0).get_x().get() > result.get(1).get_x().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(new Point3D(0, 0, 0),new Point3D(2,0,0)), result);

        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntsersections(new Ray( new Vector(0, -1, 0),new Point3D(1, 1, 0)));
        assertEquals("Wrong number of points", 1, result.size());

        assertEquals("Ray crosses sphere", List.of(new Point3D(1, -1, 0)), result);

        // TC15: Ray starts inside (1 points)
        result = sphere.findIntsersections(new Ray( new Vector(1, 0, 0),new Point3D(0.4, 0, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(new Point3D(2, 0, 0)), result);

        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntsersections(new Ray( new Vector(0, 1, 0),new Point3D(1, 0, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", List.of(new Point3D(1, 1, 0)), result);

        // TC17: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntsersections(new Ray( new Vector(1, 0, 0),new Point3D(0.4, 0.8, 0)));
        assertEquals("Wrong number of points", 1, result.size());

        assertEquals("Ray crosses sphere", List.of(new Point3D(1.6, 0.8, 0)), result);

        // TC18: Ray starts after sphere (0 points)
        result = sphere.findIntsersections(new Ray( new Vector(1, 0, 0),new Point3D(88, 0, 0)));
        assertEquals("Wrong number of points", null, result);

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        result = sphere.findIntsersections(new Ray( new Vector(1, 0, 0),new Point3D(0, -1, 0)));
        assertEquals("Wrong number of points", null, result);

        // TC20: Ray starts at the tangent point
        result = sphere.findIntsersections(new Ray( new Vector(1, 0, 0),new Point3D(1, 1, 0)));
        assertEquals("Wrong number of points", null, result);

        // TC21: Ray starts after the tangent point
        result = sphere.findIntsersections(new Ray( new Vector(1, 0, 0),new Point3D(84, 0, 0)));
        assertEquals("Wrong number of points", null, result);
        
                // **** Group: Special cases
                // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
                result = sphere.findIntsersections(new Ray( new Vector(1, 0, 0),new Point3D(0.4, 3, 0)));
        assertEquals("Wrong number of points", null, result);
        
    }
}