package Renderer;
import Elements.Camera;
import Elements.Light;
import Geometries.FlatGeometry;
import Geometries.Geometry;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import Scene.*;

import java.util.*;
import java.util.Map.Entry;
import java.awt.Color;
import java.util.Iterator;


public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;
    private final int RECURSION_LEVEL = 3;//stop when the recursion level reach this parameter.

    // ***************** Constructors ********************** //

    public Render(Scene _scene, ImageWriter _imageWriter) {
        this._scene = new Scene(_scene);
        this._imageWriter = new ImageWriter(_imageWriter);
    }

    public Render(Render render) {
        _scene = new Scene(render._scene);
        _imageWriter = new ImageWriter(render._imageWriter);

    }
    // ***************** Getters/Setters ********************** //

    public Scene get_scene() {
        return _scene;
    }

    public void set_scene(Scene _scene) {
        this._scene = _scene;
    }

    public ImageWriter get_imageWriter() {
        return _imageWriter;
    }

    public void set_imageWriter(ImageWriter _imageWriter) {
        this._imageWriter = _imageWriter;
    }

    // ***************** Operations ******************** //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Render render = (Render) o;
        return Objects.equals(_scene, render._scene) &&
                Objects.equals(_imageWriter, render._imageWriter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_scene, _imageWriter);
    }

    @Override
    public String toString() {
        return "Render{" +
                "_scene=" + _scene +
                ", _imageWriter=" + _imageWriter +
                '}';
    }


    public void printGrid(int interval) {
        for (int i = 0; i < _imageWriter.getHeight(); i++) {

            for (int j = interval; j < _imageWriter.getWidth(); ) {
                _imageWriter.writePixel(i, j, 255, 255, 255);
                _imageWriter.writePixel(j, i, 255, 255, 255);
                j = j + interval;
            }
        }
        for (int i = 0; i < _imageWriter.getHeight(); ) {

            for (int j = 0; j < _imageWriter.getWidth(); j++) {
                _imageWriter.writePixel(i, j, 255, 255, 255);
                _imageWriter.writePixel(j, i, 255, 255, 255);
            }
            i = i + _imageWriter.getWidth() - 1;
        }
    }

    public void renderImage() throws Exception {
        for (int i = 0; i < _imageWriter.getHeight(); i++)
            for (int j = 0; j < _imageWriter.getWidth(); j++) {
                Ray ray = _scene.get_camera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(), j, i, _scene.get_screenDistance(),
                        _imageWriter.getWidth(), _imageWriter.getHeight());
                Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);
                if (intersectionPoints.isEmpty())
                    _imageWriter.writePixel(j, i, _scene.get_background());
                else {
                    Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
                    Map.Entry<Geometry, Point3D> entry = closestPoint.entrySet().iterator().next();
                    _imageWriter.writePixel(j, i, calcColor(entry.getKey(), entry.getValue(), ray));
                }
            }

    }



    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints) {
        double distance = Double.MAX_VALUE;
        Point3D P0 = _scene.get_camera().get_p0();
        Map<Geometry, Point3D> minDistancePoint = new HashMap();
        for (Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet()) {
            for (Point3D point : entry.getValue()) {
                if (P0.distance(point) < distance) {
                    minDistancePoint.clear();
                    minDistancePoint.put(entry.getKey(), new Point3D(point));
                    distance = P0.distance(point);
                }
            }
        }
        return minDistancePoint;
    }



    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray) {

        Iterator<Geometry> geometries = _scene.getGeometriesIterator();
        Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();

        while (geometries.hasNext()) {
            Geometry geometry = geometries.next();
            List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);
         //   intersectionPoints.addAll(geometryIntersectionPoints);
            if(!geometryIntersectionPoints.isEmpty())
            intersectionPoints.put(geometry, geometryIntersectionPoints);
        }
        return intersectionPoints;
    }



    private Color calcColor(Geometry geometry, Point3D point,Ray inRay)
    {
        return calcColor(geometry, point,inRay,0);
    }



    private Color calcColor(Geometry geometry, Point3D point,Ray inRay, int level) {
        if(level==RECURSION_LEVEL)
           return new Color(0,0,0);

        Color ambientLight = _scene.get_ambientLight().getIntensity(point);
        Color emissionLight = geometry.get_emmission();
        int a=ambientLight.getRed() + emissionLight.getRed();
        int b=ambientLight.getGreen() + emissionLight.getGreen();
        int c=ambientLight.getBlue() + emissionLight.getBlue();
        if(a>255) a=255;
        if(b>255) b=255;
        if(c>255) c=255;
        Color I0 = new Color(a,b,c);

        Iterator<Light>lights=_scene.getLightsIterator();
        Color diffuseLight=new Color(0,0,0);
        Color specularLight=new Color(0,0,0);

        while(lights.hasNext())
        {
            Light light = lights.next();
            if(!occluded(light,point,geometry)) {
                Color tempDiffuseLight = calcDiffusiveComp(geometry.get_material().get_Kd(), geometry.getNormal(point), light.getL(point), light.getIntensity(point));
                a = tempDiffuseLight.getRed() + diffuseLight.getRed();
                if (a > 255) a = 255;
                b = tempDiffuseLight.getGreen() + diffuseLight.getGreen();
                if (b > 255) b = 255;
                c = tempDiffuseLight.getBlue() + diffuseLight.getBlue();
                if (c > 255) c = 255;
                diffuseLight = new Color(a, b, c);


                Color tempSpecularLight = calcSpecularComp(geometry.get_material().get_Ks(), new Vector(point, _scene.get_camera().get_p0()), geometry.getNormal(point), light.getL(point), geometry.get_material().get_n(), light.getIntensity(point));
                a = tempSpecularLight.getRed() + specularLight.getRed();
                if (a > 255) a = 255;
                b = tempSpecularLight.getGreen() + specularLight.getGreen();
                if (b > 255) b = 255;
                c = tempSpecularLight.getBlue() + specularLight.getBlue();
                if (c > 255) c = 255;
                specularLight = new Color(a, b, c);
            }
        }

        Color reflectedLight = new Color(0, 0, 0);
        Color refractedLight = new Color(0, 0, 0);

        // Recursive call for a reflected ray
        Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
        List<Ray> reflectedList = splitRay(reflectedRay, 0, 1);
        for(Ray ray: reflectedList)
        {
            Entry<Geometry, Point3D> reflectedEntry = findClosesntIntersection(ray);
            if (reflectedEntry != null && geometry.get_material().get_Kr() > 0) {
                Color reflectedColor = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), ray, level + 1);
                double kr = geometry.get_material().get_Kr();
                double red, green, blue;
                red = reflectedLight.getRed() + reflectedColor.getRed()*kr/1;
                green = reflectedLight.getGreen() + reflectedColor.getGreen()*kr/1;
                blue = reflectedLight.getBlue() + reflectedColor.getBlue()*kr/1;
                reflectedLight = CheckBoundaryOfColor((int)red, (int)green, (int)blue);
            }
        }
//        Entry<Geometry, Point3D> reflectedEntry = findClosesntIntersection(reflectedRay);
//        if (reflectedEntry != null && geometry.get_material().get_Kr() > 0)
//        {
//            Color reflectedColor = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), reflectedRay, level + 1);
//            double kr = geometry.get_material().get_Kr();
//            reflectedLight = new Color((int)(reflectedColor.getRed() * kr),(int)( reflectedColor.getGreen() * kr),(int) (reflectedColor.getBlue() * kr));
//        }


        // Recursive call for a refracted ray
        Ray refractedRay = constructRefractedRay(geometry, point, inRay);
        List<Ray> refractedList = splitRay(refractedRay, 0, 1);
        for(Ray ray: refractedList)
        {
            Entry<Geometry, Point3D> refractedEntry = findClosesntIntersection(ray);
            if (refractedEntry != null && geometry.get_material().get_Kt() > 0) {
                Color refractedColor = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), ray, level + 1);
                double kt = geometry.get_material().get_Kt();
                double red, green, blue;
                red = refractedLight.getRed() + refractedColor.getRed()*kt/1;
                green = refractedLight.getGreen() + refractedColor.getGreen()*kt/1;
                blue = refractedLight.getBlue() + refractedColor.getBlue()*kt/1;
                refractedLight = CheckBoundaryOfColor((int)red, (int)green, (int)blue);
            }
        }
//        Entry<Geometry, Point3D> refractedEntry = findClosesntIntersection(refractedRay);
//        if (refractedEntry != null && geometry.get_material().get_Kt() > 0) {
//            Color refractedColor = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);
//            double kt = geometry.get_material().get_Kt();
//            refractedLight = new Color((int)(refractedColor.getRed() * kt),(int)( refractedColor.getGreen() * kt),(int)(refractedColor.getBlue() * kt));
//        }


        double A=I0.getRed()+diffuseLight.getRed()+specularLight.getRed()+reflectedLight.getRed()+refractedLight.getRed();
        if (A>255)A=255;
        double B=I0.getGreen()+diffuseLight.getGreen()+specularLight.getGreen()+reflectedLight.getGreen()+refractedLight.getGreen();
        if (B>255)B=255;
        double C=I0.getBlue()+diffuseLight.getBlue()+specularLight.getBlue()+reflectedLight.getBlue()+refractedLight.getBlue();
        if (C>255)C=255;

        return new Color((int)A,(int)B,(int)C);
    }



    private Entry<Geometry, Point3D> findClosesntIntersection(Ray reflectedRay) {

        Point3D p = new Point3D(reflectedRay.get_POO());
        Vector d = new Vector(reflectedRay.get_direction());
        d.scale(2);
        p.add(d);
        Ray r = new Ray(p, new Vector(reflectedRay.get_direction()));

        Map<Geometry, Point3D> point = getClosestPoint(getSceneRayIntersections(r));
        for (Entry<Geometry, Point3D> x : point.entrySet()) {
            return x;
        }
        return null;
    }


    private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay) {
        return new Ray(point, new Vector(inRay.get_direction()));
    }


    private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay) {
        Vector D = new Vector(inRay.get_direction());
        Vector N = new Vector(normal);
        double DN = -2 * D.dotProduct(N);
        N.scale(DN);
        D.add(N);
        D.normalize();
        Point3D p=new Point3D(point);
        p.add(D);
        Ray R = new Ray(new Point3D(p), D);
        return R;
    }



    private boolean occluded(Light light, Point3D point, Geometry geometry)
    {
        Vector lightDirection = new Vector(light.getL(point));
        lightDirection.scale(-1);

        Point3D geometryPoint = new Point3D(point);
        Vector epsVector = new Vector(geometry.getNormal(point));
        epsVector.scale(2);
        geometryPoint.add(epsVector);
        Ray lightRay = new Ray(geometryPoint, lightDirection);
        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(lightRay);

        // Flat geometry cannot self intersect
        if (geometry instanceof FlatGeometry){
            intersectionPoints.remove(geometry);

        }
        for (Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet()) {
            if (entry.getKey().get_material().get_Kt() == 0) {
                return true;
            }
        }
        return false;
    }


    private Color calcSpecularComp(double ks, Vector V, Vector N, Vector L, double n, Color intensity) {

      L.normalize();
      N.normalize();
      double x =-2*(L.dotProduct(N));
      Vector normal=new Vector(N);
      normal.scale(x);
      Vector R=new Vector(L);
      R.add(normal);
      R.normalize();
      V.normalize();
      double V_R=-R.dotProduct(V);
        if (V.dotProduct(R) > 0) {
            return Color.BLACK;
        }
      //V_R=Math.abs(V_R);
        if(V_R < 0)
            V_R = 0;
      V_R=Math.pow(V_R,n);
      V_R*=ks;

      double a=V_R*intensity.getRed();
      if(a>255) a=255;
      double b=V_R*intensity.getGreen();
      if(b>255) b=255;
      double c=V_R*intensity.getBlue();
      if(c>255) c=255;

      return  new Color((int)a,(int)b,(int)c);
    }


    private Color calcDiffusiveComp(double kd, Vector normal, Vector l, Color intensity)
    {
        normal.normalize();
        l.normalize();
        double Y=Math.abs(kd*(normal.dotProduct(l)));
        if(Y<0)
            Y=0;
        double a=Y*intensity.getRed();
        if(a>255) a=255;
        double b=Y*intensity.getGreen();
        if(b>255) b=255;
        double c=Y*intensity.getBlue();
        if(c>255) c=255;

        return  new Color((int)a,(int)b,(int)c);
    }



    private Color CheckBoundaryOfColor(double r, double g, double b) {
        if (r > 255) {
            r = 255;
        }
        if (g > 255) {
            g = 255;
        }
        if (b > 255) {
            b = 255;
        }
        if (r < 0) {
            r = 0;
        }
        if (g < 0) {
            g = 0;
        }
        if (b < 0) {
            b = 0;
        }
        return new Color((int) r, (int) g, (int) b);
    }

    List<Ray> splitRay(Ray ray, double tangens, int numOfRays)
    {
        List<Ray> rayList = new ArrayList<Ray>();
        Vector originalDirection = ray.get_direction();
        originalDirection.normalize();
        //(x,y,z)*(-y,x,0) = 0
        Vector normal = new Vector(-originalDirection.get_head().get_y().get_coordinate(),originalDirection.get_head().get_x().get_coordinate(), 0);
        if (normal.get_head().get_x().get_coordinate() == 0 && normal.get_head().get_y().get_coordinate() == 0)
            normal = new Vector(1,1,0);

        normal.normalize();
        Vector normal2 = normal.crossProduct(originalDirection);
        normal2.normalize();
        normal.scale(tangens);
        normal2.scale(tangens);

        Random rand = new Random();

        for(int i = 0; i < numOfRays; i++)
        {
            double randNum1 = rand.nextDouble()*2 - 1;
            double randNum2 = rand.nextDouble()*2 - 1;
            Vector newDirection = new Vector(originalDirection),
                    n1 = new Vector(normal),
                    n2 = new Vector(normal2);
            n1.scale(randNum1);
            n2.scale(randNum2);
            newDirection.add(n1);
            newDirection.add(n2);
            rayList.add(new Ray(ray.get_POO(), newDirection));
        }
        return rayList;
    }
}