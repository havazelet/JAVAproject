package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sphere extends Geometry {
    private Point3D _center;
    private double _radius;

    // ***************** Constructors ********************** //
    public Sphere()
    {
    super();
    this._radius=1;
    this._center=new Point3D();
    }

    public Sphere(Color c, double radius, Point3D p) {
        super(c);
        //this.set_color(c);
        this._radius = radius;
        this._center = new Point3D(p);

    }

    public Sphere(Point3D _center, double _radius) {
        super();
        this._center = new Point3D(_center);
        this._radius = _radius;
    }
    public Sphere(Sphere sphere)
    {
        super();
        this._radius = sphere._radius;
        this._center = new Point3D(sphere.get_center());

    }

    // ***************** Getters/Setters ********************** //

    public Point3D get_center() { return new Point3D(_center); }

    public void set_center(Point3D _center) { this._center = new Point3D(_center); }

    public double get_radius() {
        return _radius;
    }

    public void set_radius(double _radius) {
        this._radius = _radius;
    }

    // ***************** Operations ******************** //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere Sphere = (Sphere) o;
        return Double.compare(Sphere._radius, _radius) == 0 &&
                Objects.equals(_center, Sphere._center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_center, _radius);
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public Vector getNormal(Point3D p) {

        Point3D P0= new Point3D(p);
        Vector v = new Vector(P0.sub(_center));
        v.normalize();
        return v;
    }

    @Override
    public ArrayList<Point3D> findIntersections(Ray R) {
        Vector u=new Vector(_center,R.get_POO());
        //double tm=R.get_direction().dotProduct(u);
        double tm=u.dotProduct(R.get_direction());
        double d= Math.sqrt(Math.pow(u.length(),2)-Math.pow(tm,2));
        ArrayList<Point3D> IntersectionsPoint=new ArrayList<Point3D>();

        if(d>this.get_radius())
            return IntersectionsPoint;

        double th=Math.sqrt(Math.pow(this.get_radius(),2)-Math.pow(d,2));
        double t1=tm-th;
        double t2=tm+th;
        if(t1>0)
        {
            Point3D P1=new Point3D(R.get_POO());
            Vector v=new Vector(R.get_direction());
            v.normalize();
            v.scale(t1);
            P1.add(new Vector(v));
            IntersectionsPoint.add(P1);
        }
        if(t2>0)
        {
            Point3D P2=new Point3D(R.get_POO());
            Vector v=new Vector(R.get_direction());
            v.normalize();
            v.scale(t2);
            P2.add(new Vector(v));
            IntersectionsPoint.add(P2);
        }
        return IntersectionsPoint;
    }



}