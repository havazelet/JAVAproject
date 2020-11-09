package Primitives;
import java.util.Objects;


public class Vector {
    private Point3D _head;

    // ***************** Constructors ********************** //
    public Vector(Point3D head)
    {
        super();
        this._head = new Point3D(head);
    }
    public Vector(Point3D P1,Point3D P2)
    {
        Point3D NP1=new Point3D(P1);
        Point3D NP2=new Point3D(P2);
        Vector v = NP1.sub(NP2);
        this._head=new Point3D(v._head);

    }


    public Vector(double x,double y,double z)

    {
        if(x==-0)x=0;
        if(y==-0)y=0;
        if(z==-0)z=0;
        this._head=new Point3D(x, y, z);
    }

    public Vector()
    {
        super();
        this._head=new Point3D();
    }

    public Vector(Vector v)
    {
        super();
        this._head =new Point3D(v.get_head());
    }



    // ***************** Getters/Setters ********************** //
    public Point3D get_head() {
        return _head;
    }

    public void set_head(Point3D _head) {
        this._head = _head;
    }



    // ***************** Administration  ******************** //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Objects.equals(_head, vector._head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_head);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "_head=" + _head +
                '}';
    }


    // ***************** Operations ******************** //
    public void add(Vector vector) {
        this._head.add(vector);
    }

    public void subtract(Vector vector) {
        this._head.sub(vector);
    }


    public void scale(double scalingFacor) {
        double x = this._head.get_x().get_coordinate() * scalingFacor;
        double y = this._head.get_y().get_coordinate() * scalingFacor;
        double z = this._head.get_z().get_coordinate() * scalingFacor;
        this.set_head(new Point3D(x, y, z));
    }


    public double length() {
        double d= Math.sqrt(Math.pow(this._head.get_x().get_coordinate(), 2) +
                Math.pow(this._head.get_y().get_coordinate(), 2) +
                Math.pow(this._head.get_z().get_coordinate(), 2));
        return d;
    }


    public void normalize() {
        if (this.length() == 0)
            return;
        double x = 1 / this.length();
        this.scale(x);
    }


    public Vector crossProduct(Vector vector)//vector cross
    {

        double i = this._head.get_y().get_coordinate() * vector._head.get_z().get_coordinate() - this._head.get_z().get_coordinate() * vector._head.get_y().get_coordinate();
        double j = -(this._head.get_x().get_coordinate() * vector._head.get_z().get_coordinate() - this._head.get_z().get_coordinate() * vector._head.get_x().get_coordinate());
        double k = this._head.get_x().get_coordinate() * vector._head.get_y().get_coordinate() - this._head.get_y().get_coordinate() * vector._head.get_x().get_coordinate();
        Vector newV = new Vector(i, j, k);
        return newV;
    }

    public double dotProduct(Vector vector)//Skalar cross
    {
        Point3D p= new Point3D(_head);
        double dot=p.get_x().get_coordinate()*vector._head.get_x().get_coordinate()+
                p.get_y().get_coordinate()*vector._head.get_y().get_coordinate()+
                p.get_z().get_coordinate()*vector._head.get_z().get_coordinate();
        return dot;
    }

}
