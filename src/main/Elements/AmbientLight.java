package Elements;

import Primitives.Point3D;
import Primitives.Vector;
import java.awt.Color;
import java.awt.*;
import java.util.Objects;


public class AmbientLight extends Light
{
    private final double Ka=0.1;

    // ***************** Constructors ********************** //
    public AmbientLight() {
        _color = new Color(255,255,255);
        //Ka = 1;
    }

    public AmbientLight(AmbientLight l)
    {
        super(l._color);
        //   this.Ka=l.getKa();
        // TODO Auto-generated constructor stub
    }

    public AmbientLight(java.awt.Color color, double _Ka) {
        _color = new Color(color.getRed(), color.getGreen(), color.getBlue());
        //Ka = _Ka;
    }


    // ***************** Getters/Setters ********************** //
    public double getKa() { return Ka; }


    // ***************** Administration  ******************** //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmbientLight that = (AmbientLight) o;
        return Double.compare(that.Ka, Ka) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Ka);
    }


    @Override
    public String toString() {
        return "AmbientLight{" +
                "Ka=" + Ka +
                ", _color=" + _color +
                '}';
    }



    // ***************** Operations ******************** //
    @Override
    public Color getIntensity(Point3D point) {
        return new Color((int) (Ka * _color.getRed()), (int) (Ka * _color.getGreen()), (int) (Ka * _color.getBlue()));
    }

    @Override
    public Vector getL(Point3D point) {
        return new Vector();
    } //can be a problem
}
