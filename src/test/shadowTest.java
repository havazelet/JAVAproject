package Test;

import static org.junit.Assert.*;

import java.awt.Color;
import org.junit.Test;
import Elements.SpotLight;
import Geometries.Sphere;
import Geometries.Triangle;
import Primitives.Material;
import Primitives.Point3D;
import Primitives.Vector;
import Renderer.ImageWriter;
import Renderer.Render;
import Scene.Scene;


public class shadowTest {

    @Test
    public void sadowTest1() throws Exception {

        Scene scene = new Scene();
        scene.set_screenDistance(100);
        Triangle triangle1 = new Triangle(new Color (0, 0, 100),new Point3D(-300, 100, -300),
                new Point3D(300, 100, -300),
                new Point3D(0, 700, -300) );

        Triangle triangle2 = new Triangle(   new Color (0, 100, 0),new Point3D(-100, -100, -150),
                new Point3D(100, -100, -150),
                new Point3D(0, 100, -150)
             );

        Material m1=new Material();
        m1.set_n(4);
        triangle1.set_material(m1);
        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(0, -200, 0),
                new Vector(0,100,-150), 1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("shadow Test 1", 500, 500, 500, 500);

        Render render = new Render(scene,imageWriter);

        render.renderImage();
        render.get_imageWriter().writeToimage();
    }

    @Test
    public void spotLightTest2() throws Exception {

        Scene scene = new Scene();
        scene.set_screenDistance(200);
        Sphere sphere = new Sphere(new Color(0, 0, 100),500, new Point3D(0.0, 0.0, -1000));
        Material m=new Material();
        m.set_n(20);
        sphere.set_material(m);
        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Color (0, 0, 100),new Point3D(-125, -225, -260),
                new Point3D(-225, -125, -260),
                new Point3D(-225, -225, -270)
                );

        Material m1=new Material();
        m1.set_n(4);
        triangle.set_material(m);
        scene.addGeometry(triangle);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                new Vector(2, 2, -3), 1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("shadow Spot test 2", 500, 500, 500, 500);

        Render render = new Render(scene,imageWriter);

        render.renderImage();
        render.get_imageWriter().writeToimage();

    }


    @Test
    public void Shadow() throws Exception {
        Scene scene = new Scene();
        scene.set_screenDistance(200);
        Sphere sphere = new Sphere(new Color(255, 100, 100),500, new Point3D(0.0, 0.0, -1000));

        Material m=new Material();
        m.set_n(20);
        sphere.set_material(m);
        scene.addGeometry(sphere);
        sphere.set_emmission(new Color(0, 0, 100));


        Triangle triangle = new Triangle(new Point3D(-125, -225, -285),
                new Point3D(-225, -125, -285),
                new Point3D(-225, -225, -295));

        triangle.set_emmission(new Color(0, 0, 100));
        triangle.set_material(m);
        scene.addGeometry(triangle);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                new Vector(2, 2, -2), 0.6, 0.00001, 0.000003));

        ImageWriter imageWriter = new ImageWriter("Shadow", 500, 500, 500, 500);

        Render render = new Render(scene,imageWriter);
        render.renderImage();
        render.get_imageWriter().writeToimage();
    }

    @Test
    public void ShadowLightPosition1() throws Exception {

        Scene scene = new Scene();
        scene.set_screenDistance(200);
        Sphere sphere = new Sphere(new Color(0, 0, 100),500, new Point3D(0.0, 0.0, -1000));
        Material m=new Material();
        m.set_n(20);
        sphere.set_material(m);
        //sphere.set_emmission(new Color(0, 0, 100));
        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Point3D(-125, -225, -285),
                new Point3D(-225, -125, -285),
                new Point3D(-225, -225, -295));

        triangle.set_emmission(new Color(0, 0, 100));
        triangle.set_material(m);
        scene.addGeometry(triangle);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -175),
                new Vector(2, 2, -2), 0.6, 0.00001, 0.000003));

        ImageWriter imageWriter = new ImageWriter("ShadowLightPosition1", 500, 500, 500, 500);

        Render render = new Render( scene,imageWriter);
        render.renderImage();
        render.get_imageWriter().writeToimage();
    }

    @Test
    public void ShadowLightPosition2() throws Exception {

        Scene scene = new Scene();
        scene.set_screenDistance(200);
        Sphere sphere = new Sphere(new Color(0, 0, 100),500, new Point3D(0.0, 0.0, -1000));
        Material m=new Material();
        m.set_n(20);
        sphere.set_material(m);
        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Point3D(-125, -225, -285),
                new Point3D(-225, -125, -285),
                new Point3D(-225, -225, -295));

        triangle.set_emmission(new Color(0, 0, 100));
        triangle.set_material(m);
        scene.addGeometry(triangle);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -220),
                new Vector(2, 2, -2), 0.6, 0.00001, 0.000003));

        ImageWriter imageWriter = new ImageWriter("ShadowLightPosition2", 500, 500, 500, 500);

        Render render = new Render( scene,imageWriter);
        render.renderImage();
        render.get_imageWriter().writeToimage();
    }

    @Test
    public void ShadowTrianglePosition1() throws Exception {

        Scene scene = new Scene();
        scene.set_screenDistance(200);
        Sphere sphere = new Sphere(new Color(0, 0, 100),500, new Point3D(0.0, 0.0, -1000));
        Material m=new Material();
        m.set_n(20);
        sphere.set_material(m);
        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Point3D(-100, -200, -285),
                new Point3D(-200, -100, -285),
                new Point3D(-200, -200, -295));

        triangle.set_emmission(new Color(0, 0, 100));
        triangle.set_material(m);
        scene.addGeometry(triangle);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                new Vector(2, 2, -2), 0.6, 0.00001, 0.000003));

        ImageWriter imageWriter = new ImageWriter("ShadowTrianglePosition1", 500, 500, 500, 500);

        Render render = new Render( scene,imageWriter);
        render.renderImage();
        render.get_imageWriter().writeToimage();

    }

    @Test
    public void ShadowTrianglePosition2() throws Exception {

        Scene scene = new Scene();
        scene.set_screenDistance(200);
        Sphere sphere = new Sphere(new Color(0, 0, 100),500, new Point3D(0.0, 0.0, -1000));
        Material m=new Material();
        m.set_n(20);
        sphere.set_material(m);
        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Point3D(-70, -155, -280),
                new Point3D(-155, -70, -280),
                new Point3D(-155, -155, -290));

        triangle.set_emmission(new Color(0, 0, 100));
        triangle.set_material(m);
        scene.addGeometry(triangle);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                new Vector(2, 2, -2), 0.6, 0.00001, 0.000003));

        ImageWriter imageWriter = new ImageWriter("ShadowTrianglePosition2", 500, 500, 500, 500);

        Render render = new Render( scene,imageWriter);
        render.renderImage();
        render.get_imageWriter().writeToimage();

    }

}