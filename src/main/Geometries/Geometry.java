package Geometries;
import Primitives.*;
import java.awt.Color;
import java.util.ArrayList;


public abstract class Geometry
{
   private Color _color;
   private Color _emmission=new Color(0,0,0);
   private Material _material=new Material();
   private double _nShininess=1;

    // ***************** Constructors ********************** //
    public Geometry()
    {
        this._color=new Color(255, 255, 255);
    }

    public Geometry(Color _color)
    {
        this._nShininess=1;
        this._material=new Material();
        this._color = _color;
        this._emmission=_color;
    }


    // ***************** Getters/Setters ********************** //

    public Color get_color()
    {
        return _color;
    }

    public void set_color(Color _color)
    {
        this._color = _color;
    }

    public Color get_emmission() {
        return new Color(this._emmission.getRed(),this._emmission.getGreen(),this._emmission.getBlue());
    }

    public void set_emmission(Color _emmission) {
        this._emmission = new Color(_emmission.getRed(),_emmission.getGreen(),_emmission.getBlue());

    }

    public Material get_material() {
        return new Material(this._material);
    }

    public void set_material(Material _material) {
        this._material = new Material(_material);
    }

    public double get_nShininess() {
        return _nShininess;
    }

    public void set_nShininess(double _nShininess) {
        this._nShininess = _nShininess;
    }

    // ***************** Administration  ******************** //
public abstract Vector getNormal(Point3D p);
    public abstract ArrayList<Point3D> findIntersections(Ray R);

}


