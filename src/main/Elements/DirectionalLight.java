package Elements;

import Primitives.Point3D;
import Primitives.Vector;

import java.awt.*;
import java.util.Objects;

public class DirectionalLight extends Light {
  Vector _direction;



    // ***************** Constructors ********************** //
    public DirectionalLight(Vector _direction) {
        this._direction = _direction;
    }

    public DirectionalLight(Color color, Vector _direction) {
        super(color);
        this._direction = _direction;
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
        DirectionalLight that = (DirectionalLight) o;
        return Objects.equals(_direction, that._direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_direction);
    }

    @Override
    public String toString() {
        return "DirectionalLight{" +
                "_direction=" + _direction +
                ", _color=" + _color +
                '}';
    }

    // ***************** Operations ******************** //

    @Override
    public Color getIntensity(Point3D point) {
        return new Color(this._color.getRed(),this._color.getGreen(),this._color.getBlue());
    }

    @Override
    public Vector getL(Point3D point) {
        Vector v=new Vector(this._direction);
        v.normalize();
        return new Vector(v);
    }

}
