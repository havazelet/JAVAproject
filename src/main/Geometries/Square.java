package Geometries;

import Primitives.*;
import Primitives.Ray;
import Primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Square extends Geometry implements FlatGeometry {
   private Triangle t1;
   private Triangle t2;


    // ***************** Constructors ********************** //
    public Square(Point3D _p1, Point3D _p2, Point3D _p3, Point3D _p4) {
      t1=new Triangle(new Point3D(_p1),new Point3D(_p2),new Point3D(_p3));
      t2=new Triangle(new Point3D(_p2),new Point3D(_p3),new Point3D(_p4));
    }

    // ***************** Getters/Setters ********************** //

    public Triangle getT1() {
        return t1;
    }

    public void setT1(Triangle t1) {
        this.t1 = t1;
    }

    public Triangle getT2() {
        return t2;
    }

    public void setT2(Triangle t2) {
        this.t2 = t2;
    }

    // ***************** Operations ******************** //


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Objects.equals(t1, square.t1) &&
                Objects.equals(t2, square.t2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t1, t2);
    }

    @Override
    public String toString() {
        return "Square{" +
                "t1=" + t1 +
                ", t2=" + t2 +
                '}';
    }



    @Override
    public Vector getNormal(Point3D p) {
        return new Vector(t1.getNormal(p));
    }

    @Override
    public ArrayList<Point3D> findIntersections(Ray R) {
        ArrayList<Point3D> IntersectionsPoint = new ArrayList<Point3D>();
        IntersectionsPoint= t1.findIntersections(R);
        if (IntersectionsPoint.isEmpty())
            return (t2.findIntersections(R));
        return IntersectionsPoint;
    }
}
