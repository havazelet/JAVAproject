package Test;

import Elements.SpotLight;
import Geometries.Square;
import Primitives.Material;
import Primitives.Point3D;
import Primitives.Vector;
import Renderer.ImageWriter;
import Renderer.Render;
import Scene.Scene;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class SquareTest {

        @Test
        public void Square() throws Exception {

            Scene scene = new Scene();
            scene.set_screenDistance(300);

            Square square =new Square(new Point3D(-50, -100, -350),
                    new Point3D(-100, -50, -350),
                    new Point3D(-100, -300, -350), new Point3D(-250,-100,-350));

            Material m=new Material();
            m.set_n(20);
            square.set_material(m);
            scene.addGeometry(square);
            square.set_emmission(new Color(255, 29, 93));


            scene.addLight(new SpotLight(new Color(78, 255, 134), new Point3D(-200, -200, -150),
                    new Vector(2, 6, -2), 1.4, 0.00001, 0.000003));

            ImageWriter imageWriter = new ImageWriter("SquareTest", 500, 500, 500, 500);
            Render render = new Render(scene,imageWriter);
            render.renderImage();
            render.get_imageWriter().writeToimage();
        }
}