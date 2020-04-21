package primitives;

/**
 * A Ray class is a basic class and it contains a 3D point and vector.
 *  * The class is based on point3D class and vector class.
 */
public class Ray
{
    /**
     * Ray values
     */
    Vector _dir;
    Point3D _p0;

    /**
     * Constructor that gets a vector and a point
     * @param v
     * @param p
     */
    public Ray(Vector v, Point3D p)
    {
        _dir =new Vector(v.normalized());
        _p0 =new Point3D(p);
    }

    /**
     * Constructor that gets a Ray
     * @param r
     */
    public Ray(Ray r)
    {
        _dir =new Vector(r.get_dir().normalized());
        _p0 =new Point3D(r.get_p0());
    }

    /**
     *
     * @return the vector in the ray
     */
    public Vector get_dir()
    {
        return _dir;
    }

    /**
     *
     * @return the point in the ray
     */
    public Point3D get_p0()
    {
        return _p0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray oth = (Ray)obj;
        return _dir.equals(oth.get_dir()) && _p0.equals(oth.get_p0());
    }

    public @Override String toString ()
    {
        return String.format("point:%s\nvector:%s", _p0, _dir);
    }

    /**
     * P=P_0+t∙v
     * @param t
     * @return
     */
    public  Point3D getPoint(double t)
    {
        Point3D p= this.get_p0().add(this.get_dir().scale(t));
        return p;
    }
}
