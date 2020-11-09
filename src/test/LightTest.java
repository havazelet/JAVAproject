package Test;
import java.awt.Color;


import org.junit.Test;

import Elements.PointLight;
import Elements.SpotLight;
import Geometries.Sphere;
import Geometries.Triangle;
import Primitives.Material;
import Primitives.Point3D;
import Primitives.Vector;
import Renderer.ImageWriter;
import Renderer.Render;
import Scene.Scene;

import static org.junit.Assert.*;

public class LightTest {

    @Test
    public void emmissionTest() throws Exception {
        Scene scene = new Scene();

        scene.addGeometry(new Sphere(new Color(255,0,0),50, new Point3D(0.0, 0.0, -149) ));

        Triangle triangle = new Triangle(new Color(0,255,0),new Point3D( 100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D( 100, 100, -149) );

        Triangle triangle2 = new Triangle(new Color(0,0,255),new Point3D( 100, 0, -149),
                new Point3D(  0, -100, -149),
                new Point3D( 100,-100, -149)  );

        Triangle triangle3 = new Triangle(new Color(255,255,0),new Point3D(-100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D(-100, 100, -149) );

        Triangle triangle4 = new Triangle(new Color(255,0,255),new Point3D(-100, 0, -149),
                new Point3D(  0,  -100, -149),
                new Point3D(-100, -100, -149) );

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        ImageWriter imageWriter = new ImageWriter("Emmition test", 500, 500, 500, 500);

        Render render = new Render(scene,imageWriter);

        render.renderImage();
        render.printGrid(50);
        render.get_imageWriter().writeToimage();
    }

    @Test
    public void pointLightTest1() throws Exception {

        Scene scene = new Scene();
        scene.set_screenDistance(100);
        Sphere sphere = new Sphere (new Color(0,0,100),800, new Point3D(0,0,-1000));
        Material m=new Material();
        m.set_Kd(1);
        m.set_n(19);

        sphere.set_material(m);
        scene.addGeometry(sphere);
        scene.addLight(new PointLight(new Color(255,100,100), new Point3D(-200, -200,-100),//-200, -200, -100),
                1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Point Test1", 500, 500, 500, 500);

        Render render = new Render(scene, imageWriter);
        render.renderImage();
        //render.printGrid(50);
        render.get_imageWriter().writeToimage();

    }


    @Test
    public void pointLightTest2() throws Exception {

        Scene scene = new Scene();
        Sphere sphere = new Sphere( new Color(0, 0, 100),800,new Point3D(0.0, 0.0, -1000));
        Material m=new Material();
        m.set_n(20);
        sphere.set_material(m);

        Triangle triangle = new Triangle(new Color(0,0,0),new Point3D(  3500,  3500, -2000),
                new Point3D( -3500, -3500, -1000),
                new Point3D(  3500, -3500, -2000) );

        Triangle triangle2 = new Triangle( new Color(0,0,0),new Point3D(  3500,  3500, -2000),
                new Point3D( -3500,  3500, -1000),
                new Point3D( -3500, -3500, -1000) );

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(200, 200, -100),
                0, 0.000001, 0.0000005));


        ImageWriter imageWriter = new ImageWriter("Point test 2", 500, 500, 500, 500);

        Render render = new Render(scene,imageWriter);

        render.renderImage();
        render.get_imageWriter().writeToimage();
    }



    @Test
    public void spotLightTest1() throws Exception {

        Scene scene = new Scene();
        scene.set_screenDistance(100);
        Sphere sphere = new Sphere (new Color(0,0,100),800, new Point3D(0,0, -1000));


        Material m=new Material();
        m.set_n(20);
        sphere.set_material(m);

        scene.addGeometry(sphere);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -100),
                new Vector(2, 2, -3), 0, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Spot Test1", 500, 500, 500, 500);

        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.get_imageWriter().writeToimage();
    }

    @Test
    public void spotLightTest2() throws Exception {

        Scene scene = new Scene();
        scene.set_screenDistance(200);

        Sphere sphere = new Sphere (new Color(0,0,100),500, new Point3D(0,0,-1000));

        Material m=new Material();
        m.set_n(20);
        sphere.set_material(m);

        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Color (0, 0, 100),
                new Point3D(-125, -225, -260),
                new Point3D(-225, -125, -260),
                new Point3D(-225, -225, -270)
        );

        Material m1=new Material();
       m1.set_n(4);
       triangle.set_material(m);

        scene.addGeometry(triangle);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                new Vector(2, 2, -3),1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("Spot Test2", 500, 500, 500, 500);

        Render render = new Render(scene, imageWriter);

        render.renderImage();
        //render.printGrid(50);
        render.get_imageWriter().writeToimage();
    }

    @Test
    public void spotLightTest3() throws Exception {


        Scene scene = new Scene();
        scene.set_screenDistance(100);

        Triangle triangle = new Triangle(new Color(0,0,0),
                new Point3D(  3500,  3500, -2000),
                new Point3D( -3500, -3500, -1000),
                new Point3D(  3500, -3500, -2000)
        );



        Triangle triangle2 = new Triangle(new Color(0,0,0),
                new Point3D(  3500,  3500, -2000),
                new Point3D( -3500,  3500, -1000),
                new Point3D( -3500, -3500, -1000)
        );

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100),
                new Vector(-2, -2, -3),0, 0.000001, 0.0000005));


        ImageWriter imageWriter = new ImageWriter("Spot Test3", 500, 500, 500, 500);

        Render render = new Render(scene, imageWriter);

        render.renderImage();
        //	render.printGrid(50);
        render.get_imageWriter().writeToimage();
    }
}