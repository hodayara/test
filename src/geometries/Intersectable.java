package geometries;

import primitives.*;

import java.util.List;

public interface Intersectable {
    /**
     *Ray-Geometry intersections
     * @param ray
     * @return list of Point3D
     */
    List<Point3D> findIntsersections(Ray ray);
}
