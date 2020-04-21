package geometries;

import primitives.*;

/**
 * interface of all geomtry. all geomtry inherit him
 */
public interface Geometry extends Intersectable
{
   /**
    * return the normal in the point
    * @param p
    * @return
    */
   Vector getNormal (Point3D p);
}
