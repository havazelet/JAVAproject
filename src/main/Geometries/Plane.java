package Geometries;

import Primitives.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Plane extends Geometry implements FlatGeometry
{
  private Point3D _Q;
   private Vector N;

    public Plane()
    {
    }

    public Plane(Point3D _Q, Vector n)
    {
        this._Q = _Q;
        N = n;
    }

    public Plane(Color c, Point3D p, Vector v)
    {
        super(c);
        this._Q =new Point3D(p);
        this.N = new Vector(v);
    }


    public Point3D get_Q()
    {
        return _Q;
    }

    public void set_Q(Point3D _Q) {
        this._Q = _Q;
    }

    public Vector getN() {
        return N;
    }

    public void setN(Vector n) {
        N = n;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return Objects.equals(_Q, plane._Q) &&
                Objects.equals(N, plane.N);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_Q, N);
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_Q=" + _Q +
                ", N=" + N +
                '}';
    }

    @Override
    public Vector getNormal(Point3D p)
    {
        return N;
    }

    @Override
    public ArrayList<Point3D> findIntersections(Ray R) {
       //Q0-P0
        Point3D P0=new Point3D(R.get_POO());
        Point3D Q0=new Point3D(_Q);
        Vector vec=new Vector(Q0,P0);
      //N*V
        Vector V= new Vector(R.get_direction());
        V.normalize();
        Vector N=new Vector(this.getNormal(Q0));
        if(N.dotProduct(V) < 0)
            N.scale(-1);
        N.normalize();
        double NV=N.dotProduct(V);

        double t=N.dotProduct(vec)/NV;
        V.scale(t);
        P0.add(V);
        ArrayList<Point3D> IntersectionsPoint=new ArrayList<Point3D>();
        if (t<=0)
            return IntersectionsPoint;

       IntersectionsPoint.add(P0);
       return IntersectionsPoint;
    }





}
