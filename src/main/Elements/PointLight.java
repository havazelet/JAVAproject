package Elements;

import Primitives.Point3D;
import Primitives.Vector;

import java.awt.*;
import java.util.Objects;

public class PointLight extends Light {

 Point3D _position;
 double _Kc,_K1,Kq;

// ***************** Constructors ********************** //
    public PointLight(Color color, Point3D _position, double _Kc, double _K1, double kq) {
        super(color);
        this._position = new Point3D(_position);
        this._Kc = _Kc;
        this._K1 = _K1;
        Kq = kq;
    }

    // ***************** Getters/Setters ********************** //

    public Point3D get_position() {
        return _position;
    }

    public void set_position(Point3D _position) {
        this._position = _position;
    }

    public double get_Kc() {
        return _Kc;
    }

    public void set_Kc(double _Kc) {
        this._Kc = _Kc;
    }

    public double get_K1() {
        return _K1;
    }

    public void set_K1(double _K1) {
        this._K1 = _K1;
    }

    public double getKq() {
        return Kq;
    }

    public void setKq(double kq) {
        Kq = kq;
    }
// ***************** Administration  ******************** //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointLight that = (PointLight) o;
        return Double.compare(that._Kc, _Kc) == 0 &&
                Double.compare(that._K1, _K1) == 0 &&
                Double.compare(that.Kq, Kq) == 0 &&
                _position.equals(that._position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_position, _Kc, _K1, Kq);
    }

    @Override
    public String toString() {
        return "PointLight{" +
                "_position=" + _position +
                ", _Kc=" + _Kc +
                ", _K1=" + _K1 +
                ", Kq=" + Kq +
                ", _color=" + _color +
                '}';
    }


// ***************** Operations ******************** //
//    @Override
//    public Color getIntensity(Point3D point) {
//        double d = this._position.distance(point);
//        int light = (int) (_Kc + (_K1 * d) + (Kq * d * d));
//        return new Color(this._color.getRed() / light, this._color.getRed() / light, this._color.getRed() / light);
//    }
//
//    @Override
//    public Vector getL(Point3D point) {
//        Vector a=new Vector(point,_position);
//        a.normalize();
//        return a;
//    }


    @Override
    public Color getIntensity(Point3D point)  {
        double d = point.distance(this.get_position());
        double light = get_Kc() + (get_K1() * d) + (getKq() * d * d);
        int a = (int) (this._color.getRed() / light);
        int b = (int) (this._color.getGreen()/ light);
        int c = (int) (this._color.getBlue()/ light);
        if(a>255)
            a=255;
        if(b>255)
            b=255;
        if(c>255)
            c=255;
        /*  if (a < 0) {
            a = 0;
        }
        if (b < 0) {
            b = 0;
        }
        if (c < 0) {
            c = 0;
        }*/
        return new Color(a, b, c);
    }


    @Override
    public Vector getL(Point3D point) {
        Vector v= new Vector(point, this.get_position());
        v.normalize();
        return v;
    }
}
