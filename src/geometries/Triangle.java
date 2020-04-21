package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.isZero;

/**
 *class to define a triangle
 */
public class Triangle extends Polygon implements Geometry
{
    /**
     *A constractor that gets 3 point3D
     * @param a
     * @param b
     * @param c
     */
    public Triangle(Point3D a,Point3D b,Point3D c)
    {
        super(a,b,c);
    }
    public @Override String toString ()
    {
        return String.format("point 1: {}\npoint2: {}\npoint3: {})", _vertices.get(0),_vertices.get(1),_vertices.get(2));
    }

    /**
     * a function to find normal in a given point
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point3D point)
    {
        return super.getNormal(point);
        //return null;
    }
    @java.lang.Override
    public List<Point3D> findIntsersections(Ray ray)
    {
        List<Point3D> intersections = _plane.findIntsersections(ray);
        if (intersections == null)
            return null;

        Point3D p0 = ray.get_p0();
        Vector v = ray.get_dir();

        Vector v1 = p0.subtract(_vertices.get(0));
        Vector v2 = p0.subtract(_vertices.get(1));
        Vector v3 = p0.subtract(_vertices.get(2));

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3)) return null;

        return ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) ? intersections : null;
    }
}
