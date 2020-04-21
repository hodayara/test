package geometries;

/**
 * abstract class to define radial geometry' contains a radius
 */
public abstract class RadialGeometry
{
    /**
     * radial geometry value
     */
    protected double _radius;

    /**
     *A constractor that gets radius
     * @param r
     */
    RadialGeometry(double r)
    {
        _radius= r;
    }

    /**
     *A constractor that gets Radial Geometry
     * @param radGeo
     */
    RadialGeometry(RadialGeometry radGeo)
    {
        _radius= radGeo.getRadius();
    }

    /**
     *
     * @return the radius
     */
    public double getRadius() {
        return _radius;
    }
}
