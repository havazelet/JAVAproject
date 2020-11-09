package Primitives;

import java.util.ArrayList;
import java.util.Objects;

public class Point3D extends Point2D {
   private   Coordinate _z;
    // ***************** Constructors ********************** //
    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
        super(_x, _y);
        this._z = _z;
    }

    public Point3D(double x, double y,double z)
    {
        super(new Coordinate(x),new Coordinate(y));
        this._z=new Coordinate(z);

    }

    public Point3D()
    {
        super();
        this._z=new Coordinate();
    }

    public Point3D(Point3D p3) {
        super(p3.get_x(), p3.get_y());
        this._z = new Coordinate(p3.get_z());
    }




        // ***************** Getters/Setters ********************** //
    public Coordinate get_z() {
        return _z;
    }

    public void set_z(Coordinate _z) {
        this._z = _z;
    }


    // ***************** Administration  ******************** //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Point3D point3D = (Point3D) o;
        return Objects.equals(_z, point3D._z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), _z);
    }

    @Override
    public String toString() {
        return "Point3D{" +
                ", _x=" + _x +
                ", _y=" + _y +
                "_z=" + _z +
                '}';
    }

    // ***************** Operations ******************** //

    public double distance(Point3D p)
    {
        return Math.sqrt((Math.pow(get_x().get_coordinate()-p.get_x().get_coordinate(),2))+
                (Math.pow(get_y().get_coordinate()-p.get_y().get_coordinate(),2))+
                (Math.pow(_z.get_coordinate()-p._z.get_coordinate(),2)));

    }

    public void add(Vector vector)
    {

        this.set_x(this.get_x().add(vector.get_head().get_x()));
        this.set_y(this.get_y().add(vector.get_head().get_y()));
        this.set_z(this.get_z().add(vector.get_head().get_z()));

    }

    public Vector sub(Point3D p2)
    {
        Point3D p3=new Point3D();
        p3.set_x(this.get_x().sub_coord(p2.get_x()));
        p3.set_y(this.get_y().sub_coord(p2.get_y()));
        p3.set_z(this.get_z().sub_coord(p2.get_z()));

        Vector v=new Vector(p3);
        return v;
    }


    public void sub(Vector vector)
    {
        this._x.sub_double(vector.get_head().get_x());
        this._y.sub_double(vector.get_head().get_y());
        this._z.sub_double(vector.get_head().get_z());
    }




}

