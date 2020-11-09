package Primitives;

import java.util.Objects;

public class Coordinate {

    private double _coordinate;

    // ***************** Constructors ********************** //
    public Coordinate() {_coordinate=0.0;}
    public Coordinate(double _coordinate) {
        this._coordinate = _coordinate;
    }
    public Coordinate(Coordinate coordinate) {_coordinate= coordinate._coordinate;}



    // ***************** Getters/Setters ********************** //
    public double get_coordinate() {
        return _coordinate;
    }
    public void set_coordinate(double _coordinate) {
        this._coordinate = _coordinate;
    }


    // ***************** Administration  ******************** //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Double.compare(that._coordinate, _coordinate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_coordinate);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "_coordinate=" + _coordinate +
                '}';
    }

    // ***************** Operations ******************** //
    public Coordinate add(Coordinate other) {
        double temp=_coordinate+other.get_coordinate();
        return new Coordinate(temp);
    }
    public Coordinate Substrct(Coordinate other) {
        double temp=_coordinate-other.get_coordinate();
        return new Coordinate(temp);
    }
    public Coordinate sub_coord(Coordinate coordinate)
    {
        Coordinate d=new Coordinate();
         d.set_coordinate(this.get_coordinate()-coordinate.get_coordinate());
         return  d;
    }


    public double sub_double(Coordinate coordinate)
    {

        return _coordinate= _coordinate-coordinate.get_coordinate();

    }





}
