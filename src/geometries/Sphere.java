package geometries;
import static java.lang.StrictMath.sqrt;
import static primitives.Util.*;

import primitives.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *class to define a sphere
 */
public class Sphere extends RadialGeometry implements Geometry
{
    /**
     *sphere value
     */
    protected Point3D _center;

    /**
     *
     * @return point3D of the sphere
     */
    public Point3D get_center()

    {
        return _center;
    }
    public @Override String toString()
    {
        return String.format("sphere: point center: {}", _center.toString());
    }

    /**
     *A constractor that gets point3D
     * @param p
     */
    public Sphere(Point3D p, double radus)
    {
        super(radus);
        _center = new Point3D(p);
    }

    /**
     * a function to find normal in a given point
     * @param p
     * @return
     */
    @java.lang.Override
    public Vector getNormal(Point3D p)
    {
        return new Vector(_center.subtract(p).normalize());
    }
    @java.lang.Override
    public List<Point3D> findIntsersections(Ray ray)
    {
        //𝑢 = 𝑂 − 𝑃0
        Vector u;
        try {

         u = new Vector(ray.get_p0().subtract(this.get_center()));
        }
        catch (IllegalArgumentException e)
        {
            double t=this.getRadius();
            Point3D p= ray.get_p0().add(ray.get_dir().scale(t));
            List<Point3D> l = new ArrayList<Point3D>();
            l.add(p);
            return l;

        }
        //𝑡𝑚 = 𝑣 ∙ 𝑢
        double tm =ray.get_dir().dotProduct(u);
        //d=sqrt(|u|^2-tm^2)
        double d =sqrt( u.lengthSquared()-(tm*tm));
        //if (d>r) there are no intersections
        if(d>this.getRadius())
            return null;
        //th=aqrt(r^2-d^2)
        double th = sqrt((this.getRadius()*this.getRadius())-(d*d));
        //𝑡1,2 = 𝑡𝑚 ± 𝑡ℎ,
        double t1 =tm+th;
        double t2 = tm-th;
        //Ray points: 𝑃 = 𝑃0 + 𝑡 ∙ 𝑣, 𝑡 ≥ 0
        Point3D p1=null;
        if(t1>0)
            p1 = ray.getPoint(t1);
        Point3D p2=null;
        if(t2>0)
            p2 = ray.getPoint(t2);
        List<Point3D> l = new ArrayList<Point3D>();
        if(p1==null&& p2==null|| p1.equals(p2))
            return null;
        if(p1!=null&& !p1.equals(ray.get_p0()))
            l.add(p1);
        if(p2!=null&& !p2.equals(ray.get_p0()))
            l.add(p2);

        return l;
    }
}
