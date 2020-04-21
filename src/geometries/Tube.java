package geometries;

import primitives.*;
import primitives.Util;

import java.util.List;

/**
 *class to define a tube
 */
public class Tube extends RadialGeometry implements Geometry
{
    /**
     * tube value
     */
    protected Ray _axisRay;

    /**
     *
     * @return the ray in the tube
     */
    public Ray get_axisRay()
    {
        return _axisRay;
    }

    /**
     * A constractor that gets ray and radius
     * @param r
     * @param radius
     */
    public Tube(Ray r,double radius)
    {
        super(radius);
        _axisRay =new Ray(r);
    }

    /**
     * a function to find normal in a given point
     * @param p
     * @return
     */
  //  @java.lang.Override
//    public Vector getNormal(Point3D p)
//    {
////        double t = _axisRay.get_dir().dotProduct(p.subtract(_axisRay.get_p0()));
////        Point3D O = _axisRay.get_p0().add(_axisRay.get_dir().scale(t));
////        Vector n = (p.subtract(O)).normalized();
//
//       // return n;
//    }


    //n = normalize(P - O)

    // O is projection of P on cylinder's ray:

    // t = v (P – P0)

    // O = P0 + tv



    @Override

    public Vector getNormal(Point3D p) {

        Point3D p0 = _axisRay.get_p0();
        Vector v = _axisRay.get_dir();
        //t = v (P – P0)
        double t = p0.subtract(p).dotProduct(v);
        //System.out.println(t);
        // O = P0 + tv
        Point3D o=null;
        if (!Util.isZero(t))// if it's close to 0, we'll get ZERO vector exception
            o = p0.add(v.scale(t));
        //System.out.println(o);
        Vector n = o.subtract(p).normalize();
        //System.out.println(n);
        return n;

    }
    public List<Point3D> findIntsersections(Ray ray)
    {
        return null;
    }
}
