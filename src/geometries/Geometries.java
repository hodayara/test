package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable
{
    protected ArrayList<Intersectable> interList;

    public List<Intersectable> getInterList() {
        return interList;
    }

    public Geometries() {
        this.interList = new ArrayList<Intersectable>();//we chose array list because we need to go throgh the list easyly and not add and delete objects frequently
    }
    public Geometries(Intersectable... geometries)
    {
        this.add(geometries);
    }

    public void add(Intersectable... geometries)
    {
        for (int i=0;i<geometries.length;i++)
        {
            interList.add(geometries[i]);
        }
    }

    /**
     * intsersections for all the geometries in the list
     * @param ray
     * @return list of point3D, for all intsersections
     */
    @Override
    public List<Point3D> findIntsersections(Ray ray)
    {

        List<Point3D> l = new ArrayList<Point3D>();
        List<Point3D> l1 = new ArrayList<Point3D>();
        for(int i=0;i<this.interList.size();i++)
        {
            l1 =  this.interList.get(i).findIntsersections(ray);
            if(l1!=null)
                for(int j=0;j<l1.size();j++)//copy the list that get
                    l.add(l1.get(j));
        }
        if(l==null)
            return null;
        return l;

    }
}
