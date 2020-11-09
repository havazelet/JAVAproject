package Scene;

import Elements.AmbientLight;
import Elements.Light;
import Geometries.Geometry;
//import javafx.scene.AmbientLight;
import Elements.Camera;

import Primitives.Coordinate;
import Primitives.Point3D;
import Primitives.Vector;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Scene
{
    private String _sceneName;
    private Color _background;
    private List <Geometry> _geometries= new ArrayList();
    private Camera _camera;
    private double _screenDistance;
    private AmbientLight _ambientLight;
    List <Light> _lights=new ArrayList();
    // ***************** Constructors ********************** //

    public Scene()
    {
        _ambientLight = new AmbientLight();
        _background = new Color(0, 0, 0);
        _ambientLight = new AmbientLight();
        _geometries = new ArrayList();
        _camera = new Camera();
        _screenDistance = 150;
        _lights=new ArrayList();

    }

    public Scene(Scene nscene) {
        _ambientLight = new AmbientLight(nscene.get_ambientLight());
        this._sceneName = nscene._sceneName;
        this._background = nscene._background;
        this._geometries = nscene._geometries;
        this._camera = nscene._camera;
        this._screenDistance = nscene._screenDistance;
        for(Light item:nscene._lights)
            this._lights.add(item);
    }

    public Scene(String _sceneName, Color _background, List<Geometry> _geometries, Camera _camera, double _screenDistance) {
        this._sceneName = _sceneName;
        this._background = _background;
        this._geometries = _geometries;
        this._camera = _camera;
        this._screenDistance = _screenDistance;
    }

    // ***************** Getters/Setters ********************** //

    public String get_sceneName() {
        return _sceneName;
    }

    public void set_sceneName(String _sceneName) {
        this._sceneName = _sceneName;
    }

    public Color get_background() {
        return _background;
    }

    public void set_background(Color _background) {
        this._background = _background;
    }

    public List<Geometry> get_geometries() {
        return _geometries;
    }

    public void set_geometries(List<Geometry> _geometries) {
        this._geometries = _geometries;
    }

    public Camera get_camera() {return new Camera(_camera); }

    public void set_camera(Camera _camera) {
        this._camera = new Camera(_camera);
    }

    public double get_screenDistance() {
        return _screenDistance;
    }

    public void set_screenDistance(double _screenDistance) {
        this._screenDistance = _screenDistance;
    }

    public AmbientLight get_ambientLight() { return _ambientLight; }

    public void set_ambientLight(AmbientLight _ambientLight) { this._ambientLight = _ambientLight; }

    public List<Light> get_lights() { return _lights; }

    public void set_lights(List<Light> _lights) { this._lights = _lights; }

    // ***************** Administration  ******************** //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scene scene = (Scene) o;
        return Double.compare(scene._screenDistance, _screenDistance) == 0 &&
                Objects.equals(_sceneName, scene._sceneName) &&
                Objects.equals(_background, scene._background) &&
                Objects.equals(_geometries, scene._geometries) &&
                Objects.equals(_camera, scene._camera);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_sceneName, _background, _geometries, _camera, _screenDistance);
    }

    @Override
    public String toString() {
        return "Scene{" +
                "_sceneName='" + _sceneName + '\'' +
                ", _background=" + _background +
                ", _geometries=" + _geometries +
                ", _camera=" + _camera +
                ", _screenDistance=" + _screenDistance +
                '}';
    }

   public void addGeometry(Geometry g)
    {
        this._geometries.add(g);
    }


    public Iterator<Geometry> getGeometriesIterator() { return _geometries.iterator(); }

    public void addLight(Light light){this._lights.add(light); }

    public  Iterator<Light> getLightsIterator(){return _lights.iterator();}
}
