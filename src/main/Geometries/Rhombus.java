package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.util.ArrayList;
import java.util.Objects;

public class Rhombus extends Geometry implements FlatGeometry{

    private Triangle t1;
    private Triangle t2;


    // ***************** Constructors ********************** //

    public Rhombus(Vector V1, Vector V2,Point3D P1) {
        Point3D a=new Point3D(P1);
        Point3D p1ezer=new Point3D(P1);
        p1ezer.add(V1);
        Point3D b=new Point3D(p1ezer);
        P1.add(V2);
        Point3D c=new Point3D(P1);
        Point3D p2ezer=new Point3D(b);
        p2ezer.add(V2);
        Point3D d=new Point3D(p2ezer);

        t1=new Triangle(a,b,c);
        t2=new Triangle(b,c,d);
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
        Rhombus rhombus = (Rhombus) o;
        return Objects.equals(t1, rhombus.t1) &&
                Objects.equals(t2, rhombus.t2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t1, t2);
    }

    @Override
    public String toString() {
        return "Rhombus{" +
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
