package Primitives;

import java.util.Objects;

public class Material
{
    private double _Kd; // Diffusion attenuation coefficient – פיזור האור
    private double _Ks; // Specular attenuation coefficient – החזרת האור
    private double _n;  // shininess index
    private double _Kr;// reflect
    private double _Kt;// refract

    // ***************** Constructors ********************** //

    public Material()
    {
        _Kd=1;
        _Ks=1;
        _n=1;
        _Kr=0;
        _Kt=0;

    }

    public Material(double _Kd, double _Ks, double _n) {
        this._Kd = _Kd;
        this._Ks = _Ks;
        this._n = _n;

    }

    public Material(Material met) {
        this._Kd = met._Kd;
        this._Ks = met._Ks;
        this._n = met._n;
        this._Kr=met._Kr;
        this._Kt=met._Kt;
    }

    public Material(double _Kd, double _Ks, double _n, double _Kr, double _Kt) {
        this._Kd = _Kd;
        this._Ks = _Ks;
        this._n = _n;
        this._Kr = _Kr;
        this._Kt = _Kt;
    }
    // ***************** Getters/Setters ********************** //

    public double get_Kd() {return _Kd;}

    public void set_Kd(double _Kd) { this._Kd = _Kd; }

    public double get_Ks() { return _Ks; }

    public void set_Ks(double _Ks) { this._Ks = _Ks; }

    public double get_n() { return _n; }

    public void set_n(double _n) { this._n = _n; }

    public double get_Kr() { return _Kr; }

    public void set_Kr(double _Kr) { this._Kr = _Kr; }

    public double get_Kt() { return _Kt; }

    public void set_Kt(double _Kt) { this._Kt = _Kt; }

    // ***************** Administration  ******************** //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Double.compare(material._Kd, _Kd) == 0 &&
                Double.compare(material._Ks, _Ks) == 0 &&
                Double.compare(material._n, _n) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_Kd, _Ks, _n);
    }

    @Override
    public String toString() {
        return "Material{" +
                "_Kd=" + _Kd +
                ", _Ks=" + _Ks +
                ", _n=" + _n +
                '}';
    }

}
