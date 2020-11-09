package Geometries;
import java.awt.Color;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Triangle extends Geometry implements FlatGeometry {
    private Point3D _p1;
    private Point3D _p2;
    private Point3D _p3;

//    public Triangle()
//    {
//    }



    public Triangle(Point3D _p1, Point3D _p2, Point3D _p3) {
        this._p1 = _p1;
        this._p2 = _p2;
        this._p3 = _p3;
    }

    public Triangle(Color c, Point3D _p1, Point3D _p2, Point3D _p3) {
        super(c);
        this._p1 = _p1;
        this._p2 = _p2;
        this._p3 = _p3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(_p1, triangle._p1) &&
                Objects.equals(_p2, triangle._p2) &&
                Objects.equals(_p3, triangle._p3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_p1, _p2, _p3);
    }


    @Override
    public String toString() {
        return "Triangle{" +
                "_p1=" + _p1 +
                ", _p2=" + _p2 +
                ", _p3=" + _p3 +
                '}';
    }


    @Override
    public Vector getNormal(Point3D p) {
        Vector v1 = p.sub(_p1);
        Vector v2 = p.sub(_p2);
        Vector v3 = v1.crossProduct(v2);
        v3.normalize();
        return v3;
    }


    @Override
    public ArrayList<Point3D> findIntersections(Ray R) {

        Vector N = new Vector(this.getNormal(_p3));
        Plane plane = new Plane(_p3, N);
        ArrayList<Point3D> IntersectionsPoint = new ArrayList<Point3D>();
        IntersectionsPoint = (ArrayList<Point3D>) plane.findIntersections(R);
        Point3D p=new Point3D();
        if (!IntersectionsPoint.isEmpty())
            p= IntersectionsPoint.get(0);
        else return IntersectionsPoint;
        Vector v1=new Vector(_p1,R.get_POO());
        Vector v2=new Vector(_p2,R.get_POO());
        Vector v3=new Vector(_p3,R.get_POO());

        Vector N1=new Vector(v1.crossProduct(v2));
        Vector N2=new Vector(v2.crossProduct(v3));
        Vector N3=new Vector(v3.crossProduct(v1));

        N1.normalize();
        N2.normalize();
        N3.normalize();

        Vector V=new Vector(p,R.get_POO());
        double d1=V.dotProduct(N1);
        double d2=V.dotProduct(N2);
        double d3=V.dotProduct(N3);

        ArrayList<Point3D> list = new ArrayList();
        if((d1>=0&&d2>=0&&d3>=0)||(d1<=0&&d2<=0&&d3<=0)) {
            list.add(p);
            //return IntersectionsPoint;
        }
       //else return list;
        return list;
    }


}
