package Test;

import Geometries.Triangle;
import Primitives.Point3D;
import Primitives.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import Geometries.*;
import java.awt.Color;
import Primitives.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import Elements.*;
public class TriangleTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getNormal()
    {
        Point3D p1=new Point3D(0,1,0);
        Point3D p2=new Point3D(1,1,1);
        Point3D p3=new Point3D(2,0,2);
        Triangle t=new Triangle(p1,p2,p3);
        Vector v=new Vector(1,0,-1);
        v.normalize();
        System.out.println(""+v.toString());
        if(t.getNormal(p3).equals(v)==true)
            assertTrue(true);
        else fail("ERROR"+t.getNormal(p3));
    }

//    @Test
//    public void TriangleIntersectionPointsTest() throws Exception {
//
//        final int WIDTH = 3;
//
//        final int HEIGHT = 3;
//
//        Ray[][] rays = new Ray[HEIGHT][WIDTH];
//
//        Camera camera = new Camera(new Point3D(new Coordinate(0.0), new Coordinate(0.0),new Coordinate(0.0)),
//                new Vector(0.0, 1.0, 0.0),
//                new Vector(0.0, 0.0, -1.0));
//
//        Triangle triangle = new Triangle(new Point3D(new Coordinate(0),new Coordinate(1),new Coordinate(-2)),
//                new Point3D(new Coordinate(1),new Coordinate(-1),new Coordinate(-2)),
//                new Point3D(new Coordinate(-1),new Coordinate(-1),new Coordinate(-2)));
//
//        Triangle triangle2 = new Triangle(new Point3D(new Coordinate(0),new Coordinate(10),new Coordinate(-2)),
//                new Point3D(new Coordinate(1),new Coordinate(-1),new Coordinate(-2)),
//                new Point3D(new Coordinate(-1),new Coordinate(-1),new Coordinate(-2)));
//
//        List<Point3D> intersectionPointsTriangle = new ArrayList();
//
//        List<Point3D> intersectionPointsTriangle2 = new ArrayList();
//
//        System.out.println("Camera:\n" + camera);
//
//        for (int i = 0; i < HEIGHT; i++) {
//
//            for (int j = 0; j < WIDTH; j++) {
//
//                rays[i][j] = camera.constructRayThrowAPixel(WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);
//
//                List<Point3D> rayIntersectionPoints = triangle.findIntersections(rays[i][j]);
//
//                List<Point3D> rayIntersectionPoints2 = triangle2.findIntersections(rays[i][j]);
//
//                for (Point3D iPoint : rayIntersectionPoints) {
//                    intersectionPointsTriangle.add(iPoint);
//                }
//
//                for (Point3D iPoint : rayIntersectionPoints2) {
//                    intersectionPointsTriangle2.add(iPoint);
//                }
//
//            }
//
//        }
//
//        assertTrue(intersectionPointsTriangle.size() == 1);
//
//        assertTrue(intersectionPointsTriangle2.size() == 2);
//
//        System.out.println("Intersection Points:");
//
//        for (Point3D iPoint : intersectionPointsTriangle) {
//            System.out.println(iPoint);
//        }
//
//        System.out.println("--");
//
//        for (Point3D iPoint : intersectionPointsTriangle2) {
//            System.out.println(iPoint);
//        }
//
//    }
@Test
public void testTriangleIntersections() {

    // creating the expected values

    List<Point3D> answerList = new ArrayList<Point3D>();
    Point3D answerPoint = new Point3D(0, 0, -200);
    answerList.add(answerPoint);

    // building the triangle

    Point3D p1 = new Point3D(0, 100, -200);
    Point3D p2 = new Point3D(100, -100, -200);
    Point3D p3 = new Point3D(-100, -100, -200);

    Triangle t1 = new Triangle(Color.white, p1, p2, p3);
    Triangle t2 = new Triangle(Color.white, p1, p2, p3);

    // building the ray that will intersect the triangle

    Point3D centerPoint = new Point3D(0,0,0);
    Vector vector = new Vector(0, 0, -5);
    Ray ray = new Ray(centerPoint, vector);

    // testing the findIntersection function

    List<Point3D> list = new ArrayList<Point3D>();
    list = t2.findIntersections(ray);
    assertEquals(answerList, list);
}

}
