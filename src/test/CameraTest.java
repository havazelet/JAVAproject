package Test;
import Elements.Camera;
import Primitives.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CameraTest {

//    @Before
//    public void setUp() throws Exception {
//    }

//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void constructRayThroughPixel() throws Exception {
//        int Nx=3;
//        int Ny=3;
//        double i=1;
//        double j=1;
//        double Dist=1;
//        double width=9;
//        double height=9;
//        Point3D p=new Point3D(0,0,0);
//        Vector Vup=new Vector(0,1,0);
//        Vector Vto=new Vector(0,0,-1);
//     //   Vector Vr=new Vector(1,0,0);
//        Camera Cmr= new Camera(p,Vup,Vto);
//        Ray RayResalt=new Ray(p,Vto);
//        Ray RayFanction=Cmr.constructRayThroughPixel(Nx,Ny,i,j,Dist,width,height);
//        assertEquals(RayResalt.toString(),RayFanction.toString());
//
//    }
//@Test
//
//public void testRaysConstruction() throws Exception {
//
//    final int WIDTH = 3;
//
//    final int HEIGHT = 3;
//
//    Point3D[][] screen = new Point3D[HEIGHT][WIDTH];
//
//    Camera camera = new Camera(new Point3D(new Coordinate(0.0), new Coordinate(0.0),new Coordinate(0.0)),
//            new Vector(0.0, 1.0, 0.0),
//            new Vector(0.0, 0.0, -1.0));
//
//    System.out.println("Camera:\n" + camera);
//
//    for (int i = 0; i < HEIGHT; i++) {
//
//        for (int j = 0; j < WIDTH; j++) {
//
//            Ray ray = camera.constructRayThroughPixel(
//                    WIDTH, HEIGHT, j-1, i-1, 1, 3 * WIDTH, 3 * HEIGHT);
//
//            screen[i][j] = ray.get_POO();
//
//            System.out.print(screen[i][j]);
//
//            System.out.println(ray.get_direction());
//
//// Checking z-coordinate
//            assertTrue(Double.compare(screen[i][j].get_z().get_coordinate(), -1.0) == 0);
//
//// Checking all options
//            double x = screen[i][j].get_x().get_coordinate();
//
//            double y = screen[i][j].get_x().get_coordinate();
//
//            if (Double.compare(x, 3) == 0
//                    || Double.compare(x, 0) == 0
//                    || Double.compare(x, -3) == 0) {
//
//                if (Double.compare(y, 3) == 0
//                        || Double.compare(y, 0) == 0
//                        || Double.compare(y, -3) == 0) {
//
//                    assertTrue(true);
//
//                } else {
//                    fail("Wrong y coordinate");
//                }
//
//            } else {
//                fail("Wrong x coordinate");
//            }
//
//        }
//
//        System.out.println("---");
//
//    }

}

