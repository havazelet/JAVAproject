package Primitives;

import java.util.Objects;

public class Point2D
{
   protected Coordinate _x;
   protected Coordinate _y;

    // ***************** Constructors ********************** //

    public Point2D() {
        this._x=new Coordinate();
        this._y=new Coordinate();
    }

    public Point2D(Coordinate _x, Coordinate _y) {
        this._x = _x;
        this._y = _y;
    }

    // ***************** Getters/Setters ********************** //
    public Coordinate get_x() {
        return _x;
    }

    public void set_x(Coordinate _x) {
        this._x = _x;
    }

    public Coordinate get_y() {
        return _y;
    }

    public void set_y(Coordinate _y) {
        this._y = _y;
    }

    // ***************** Administration  ******************** //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point2D = (Point2D) o;
        return Objects.equals(_x, point2D._x) &&
                Objects.equals(_y, point2D._y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_x, _y);
    }

    @Override
    public String toString() {
        return "Point2D{" +
                "_x=" + _x +
                ", _y=" + _y +
                '}';
    }
}



