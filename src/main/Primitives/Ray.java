package Primitives;

import java.util.Objects;

public class Ray {
    private Point3D _POO;
    private Vector _direction;

    // ***************** Constructors ********************** //

    public Ray() {
    }

    public Ray(Vector _direction) {
        this._direction = _direction;
    }

    public Ray(Point3D _POO, Vector _direction) {
        this._POO = _POO;
        this._direction = _direction;
    }
    // ***************** Getters/Setters ********************** //

    public Point3D get_POO() {
        return _POO;
    }

    public void set_POO(Point3D _POO) {
        this._POO = _POO;
    }

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
        Ray ray = (Ray) o;
        return Objects.equals(_POO, ray._POO) &&
                Objects.equals(_direction, ray._direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_POO, _direction);
    }


    @Override
    public String toString() {
        return "Ray{" +
                "_POO=" + _POO +
                ", _direction=" + _direction +
                '}';
    }
}
