package Test;

import Elements.SpotLight;
import Geometries.Plane;
import Geometries.Rhombus;
import Geometries.Square;
import Geometries.Triangle;
import Primitives.Material;
import Primitives.Point3D;
import Primitives.Vector;
import Renderer.ImageWriter;
import Renderer.Render;
import Scene.Scene;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class RhombusTest {

    @Test
    public void Rhombus() throws Exception {

        Scene scene = new Scene();
        scene.set_screenDistance(200);

        Rhombus rhombus = new Rhombus(new Vector(-100, -30, -30), new Vector(10, 90, -30), new Point3D(-25, -20, -100));
        Rhombus rhombus2 = new Rhombus(new Vector(-100, -30, -30), new Vector(10, 90, -30), new Point3D(100, -20, -100));
        Rhombus rhombus3 = new Rhombus(new Vector(10, 90, -30), new Vector(-100, -30, -30), new Point3D(100, -20, -100));


        Material m = new Material();
        m.set_n(20);
        rhombus.set_material(m);
        rhombus2.set_material(m);
        rhombus3.set_material(m);
        scene.addGeometry(rhombus);
        scene.addGeometry(rhombus2);
        scene.addGeometry(rhombus3);

        rhombus.set_emmission(new Color(250, 100, 9));
        rhombus2.set_emmission(new Color(250, 255, 10));
        rhombus3.set_emmission(new Color(255, 200, 150));


        scene.addLight(new SpotLight(new Color(78, 255, 134), new Point3D(-200, -200, -150),
                new Vector(2, 6, -2), 1.4, 0.00001, 0.000003));

        ImageWriter imageWriter = new ImageWriter("Rhombus Test", 500, 500, 500, 500);
        Render render = new Render(scene, imageWriter);
        render.renderImage();
        render.get_imageWriter().writeToimage();
    }


    @Test
    public void flag() throws Exception {

        Scene scene = new Scene();

        scene.set_screenDistance(100);

        Triangle triangle1 = new Triangle(Color.blue, new Point3D(125, -100, -149), new Point3D(-125, -100, -149), new Point3D(0, 150, -149));
        Triangle triangle2 = new Triangle(Color.blue, new Point3D(125, 100, -149), new Point3D(-125, 100, -149), new Point3D(0, -150, -149));

        ImageWriter imageWriter = new ImageWriter("flag Test", 500, 500, 500, 500);

            scene.addGeometry(triangle1);
            scene.addGeometry(triangle2);

            Render render = new Render(scene, imageWriter);
            render.renderImage();
            render.get_imageWriter().writeToimage();

        }


    }



