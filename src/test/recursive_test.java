package Test;
import Elements.Camera;
import Elements.SpotLight;
import Geometries.Sphere;
import Geometries.Triangle;
import Primitives.Material;
import Primitives.Point3D;
import Primitives.Vector;
import Renderer.ImageWriter;
import Renderer.Render;
import Scene.Scene;
import java.awt.Color;
import org.junit.Test;


public class recursive_test {

@Test
public void recursiveTest() throws Exception {

    Scene scene = new Scene();
    scene.set_screenDistance(300);

    Sphere sphere = new Sphere( new Color(0, 0, 100),500,new Point3D(0.0, 0.0, -1000));
    Material m=new Material();
    m.set_n(20);
    m.set_Kt(0.5);
    sphere.set_material(m);
    scene.addGeometry(sphere);

    Sphere sphere2 = new Sphere(new Color(100, 20, 20),250,new Point3D(0.0, 0.0, -1000));
    Material m2=new Material();
    m2.set_n(20);
    m2.set_Kt(0);
    sphere2.set_material(m2);
    scene.addGeometry(sphere2);

    scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
            new Vector(2, 2, -3), 0, 0.00001, 0.000005));

    ImageWriter imageWriter = new ImageWriter("Recursive Test 1", 500, 500, 500, 500);

    Render render = new Render(scene,imageWriter);
        render.renderImage();
        render.get_imageWriter().writeToimage();
}



    @Test
    public void recursiveTest2() throws Exception {

        Scene scene = new Scene();
        scene.set_camera(new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1)));

        scene.set_screenDistance(300);

        Sphere sphere = new Sphere(new Color(0, 0, 100),300, new Point3D(-550, -500, -1000));
        Material m=new Material();
        m.set_n(20);
        m.set_Kt(0.5);
        sphere.set_material(m);
        scene.addGeometry(sphere);
//
        Sphere sphere2 = new Sphere(new Color(100, 20, 20),150, new Point3D(-550, -500, -1000));
        m.set_Kt(0);
        sphere2.set_material(m);
        scene.addGeometry(sphere2);

        Triangle triangle = new Triangle(new Point3D(1500, -1500, -1500),
                new Point3D(-1500, 1500, -1500),
                new Point3D(200, 200, -375));

        Triangle triangle2 = new Triangle(new Point3D(1500, -1500, -1500),
                new Point3D(-1500, 1500, -1500),
                new Point3D(-1500, -1500, -1500));

        triangle.set_emmission(new Color(20, 20, 20));
        triangle2.set_emmission(new Color(20, 20, 20));
        m.set_Kr(1);
        triangle.set_material(m);
        m.set_Kr(0.5);
        triangle2.set_material(m);
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -150),
                new Vector(-2, -2, -3), 0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Recursive Test 2", 500, 500, 500, 500);

        Render render = new Render(scene,imageWriter);
        render.renderImage();
        render.get_imageWriter().writeToimage();

    }

    @Test
    public void recursiveTes() throws Exception {

        Scene scene = new Scene();
        scene.set_camera(new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1)));

        scene.set_screenDistance(300);

        Sphere sphere = new Sphere(new Color(0, 0, 100),300, new Point3D(-550, -500, -1000));
        Material m=new Material();
        m.set_n(20);
        m.set_Kt(0.5);
        sphere.set_material(m);
        scene.addGeometry(sphere);
//
        Sphere sphere2 = new Sphere(new Color(100, 20, 20),150, new Point3D(-550, -500, -1000));
        m.set_Kt(0);
        sphere2.set_material(m);
        scene.addGeometry(sphere2);

        Triangle triangle = new Triangle(new Point3D(1500, -1500, -1500),
                new Point3D(-1500, 1500, -1500),
                new Point3D(200, 200, -375));

        Triangle triangle2 = new Triangle(new Point3D(1500, -1500, -1500),
                new Point3D(-1500, 1500, -1500),
                new Point3D(-1500, -1500, -1500));

        triangle.set_emmission(new Color(20, 20, 20));
        triangle2.set_emmission(new Color(20, 20, 20));
        m.set_Kr(1);
        triangle.set_material(m);
        m.set_Kr(0.5);
        triangle2.set_material(m);
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -150),
                new Vector(-2, -2, -3), 0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Recursive Tes", 500, 500, 500, 500);

        Render render = new Render(scene,imageWriter);
        render.renderImage();
        render.get_imageWriter().writeToimage();

    }

    @Test
    public void recursiveTest3() throws Exception {

        Scene scene = new Scene();
        scene.set_screenDistance(300);

        Sphere sphere = new Sphere(new Color(0, 0, 100),300, new Point3D(0, 0, -1000));
        Material m=new Material();
        m.set_n(20);
        m.set_Kt(0.5);
        sphere.set_material(m);
        scene.addGeometry(sphere);

        Sphere sphere2 = new Sphere(new Color(100, 20, 20),150, new Point3D(0, 0, -1000));
        m.set_Kt(0);
        sphere2.set_material(m);

        scene.addGeometry(sphere2);

        Triangle triangle = new Triangle(new Point3D(2000, -1000, -1500),
                new Point3D(-1000, 2000, -1500),
                new Point3D(700, 700, -375));

        Triangle triangle2 = new Triangle(new Point3D(2000, -1000, -1500),
                new Point3D(-1000, 2000, -1500),
                new Point3D(-1000, -1000, -1500));

        triangle.set_emmission(new Color(20, 20, 20));
        triangle2.set_emmission(new Color(20, 20, 20));
        m.set_Kr(1);
        triangle.set_material(m);
        m.set_Kr(0.5);
        triangle2.set_material(m);
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -150),
                new Vector(-2, -2, -3), 0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Recursive Test 3", 500, 500, 500, 500);

        Render render = new Render(scene,imageWriter);
        render.renderImage();
        render.get_imageWriter().writeToimage();
    }

}
