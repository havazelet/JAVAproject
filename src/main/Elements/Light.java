package Elements;
import Primitives.Point3D;
import Primitives.Vector;
import java.awt.Color;


public abstract class Light {
    protected Color _color;

    // ***************** Constructors ********************** //
    public Light()
    {
        this._color=new Color(255,255,255);
    }

    public Light(Color color) {
        this._color=new Color(color.getRed(),color.getGreen(),color.getBlue());
    }


//    public Light(Color color)
//    {
//
//        if(color!=null)
//            this._color = color;
//        else
//            color=new Color(255, 255, 255);
//    }

    public Light(int r, int g, int b) { this._color=new Color(r, g, b); }

    public Light(Light l) { this._color=l._color; }

    // ***************** Getters/Setters ********************** //

    public Color get_color() {return _color;}
    public void set_color(Color _color) {this._color = _color;}
//public Color getColor()
//{
//    if(_color!=null)
//        return  new Color(_color.getRGB());
//    else
//        return this._color=new Color(255, 255, 255);
//}
//
//    public void setColor(Color color)
//    {
//        this._color = color;
//    }


    //........................................................//
    public abstract Color getIntensity(Point3D point);
    public abstract Vector getL(Point3D point);
}
