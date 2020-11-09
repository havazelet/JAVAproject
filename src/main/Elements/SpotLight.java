package Elements;

import Primitives.Point3D;
import Primitives.Vector;

import java.awt.*;
import java.util.Objects;

public class SpotLight extends PointLight {

    private Vector _direction;

// ***************** Constructors ********************** //
    public SpotLight(Color color, Point3D _position,Vector direction, double _Kc, double _K1, double kq)
    {
        super(color, _position, _Kc, _K1, kq);
        this._direction=new Vector(direction);
    }

// ***************** Getters/Setters ********************** //

    public Vector get_direction() {
        return _direction;
    }

    public void set_direction(Vector _direction) {
        this._direction = _direction;
    }


// ***************** Administration  ******************** //


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SpotLight spotLight = (SpotLight) o;
        return Objects.equals(_direction, spotLight._direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), _direction);
    }

    @Override
    public String toString() {
        return "SpotLight{" +
                "_direction=" + _direction +
                ", _position=" + _position +
                ", _Kc=" + _Kc +
                ", _K1=" + _K1 +
                ", Kq=" + Kq +
                ", _color=" + _color +
                '}';
    }

//     ***************** Operations ******************** //
@Override
public Color getIntensity(Point3D point) {
    Vector D = new Vector(point, get_position());
    D.normalize();
    _direction.normalize();
    double dist = point.distance(get_position());
    double dotP = Math.abs(D.dotProduct(_direction));
    double z = get_Kc() + (get_K1() * dist) + (getKq() * dist * dist);
    int r = (int) ((_color.getRed()*dotP) / z);
    int g = (int) ((_color.getGreen()*dotP) / z);
    int b = (int) ((_color.getBlue()*dotP) / z);
    //execptions of the range: 0-255.
    if (r > 255)
        r = 255;
    if (g > 255)
        g = 255;
    if (b > 255)
        b = 255;
       /* if (r < 0)
            r = 0;
        if (g < 0)
            g = 0;
        if (b < 0)
            b = 0;*/

    return new Color(r, g, b);
}
}
