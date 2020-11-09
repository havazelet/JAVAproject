package Test;
import Elements.*;

import Geometries.*;
import java.awt.Color;
import Primitives.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class SphereTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getNormal()
    {
        Point3D p=new Point3D(0,0,0);
        Sphere S= new Sphere(p,1.0);
        Vector v=new Vector(1,0,0);
        Point3D p1=new Point3D(1,0,0);

        if(S.getNormal(p1).equals(v)==true)
            assertTrue(true);
        else fail("ERROR"+S.getNormal(p1));
    }

    @Test
    public void testIntersectionPoints() throws Exception {

        final int WIDTH = 3;

        final int HEIGHT = 3;

        Ray[][] rays = new Ray[HEIGHT][WIDTH];

        Camera camera = new Camera(new Point3D(new Coordinate(0.0), new Coordinate(0.0), new Coordinate(0.0)),
                new Vector(0.0, 1.0, 0.0),
                new Vector(0.0, 0.0, -1.0));

        Sphere sphere = new Sphere( new Point3D(new Coordinate(0.0), new Coordinate(0.0), new Coordinate(-3.0)),1);

        Sphere sphere2 = new Sphere( new Point3D(new Coordinate(0.0), new Coordinate(0.0), new Coordinate(-3.0)),10);

// Only the center ray intersect the sphere in two locations
        List<Point3D> intersectionPointsSphere = new ArrayList();

// The sphere encapsulates the view plane - all rays intersect with the sphere once
        List<Point3D> intersectionPointsSphere2 = new ArrayList();

        System.out.println("Camera:\n" + camera);

        for (int i = 0; i < HEIGHT; i++) {

            for (int j = 0; j < WIDTH; j++) {

                rays[i][j] = camera.constructRayThroughPixel(
                        WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);

                List<Point3D> rayIntersectionPoints = sphere.findIntersections(rays[i][j]);

                List<Point3D> rayIntersectionPoints2 = sphere2.findIntersections(rays[i][j]);

                for (Point3D iPoint : rayIntersectionPoints) {
                    intersectionPointsSphere.add(iPoint);
                }

                for (Point3D iPoint : rayIntersectionPoints2) {
                    intersectionPointsSphere2.add(iPoint);
                }

            }
        }

        assertTrue(intersectionPointsSphere.size() == 2);
        System.out.println(intersectionPointsSphere);
        assertTrue(intersectionPointsSphere2.size() == 9);

        System.out.println("\nIntersection Points:");

        for (Point3D iPoint : intersectionPointsSphere) {

            assertTrue(iPoint.equals(new Point3D(new Coordinate(0.0), new Coordinate(0.0), new Coordinate(-2.0)))
                    || iPoint.equals(new Point3D(new Coordinate(0.0), new Coordinate(0.0), new Coordinate(-4.0))));

            System.out.println(iPoint);

        }
    }

//    @Test
//    public void testSphereIntersections() {
//
//
//        // creating the expected values
//        List<Point3D> answerList1 = new ArrayList<Point3D>();
//        List<Point3D> answerList2 = new ArrayList<Point3D>();
//
//        Point3D answerPoint1 = new Point3D(0, 0, -200);
//        Point3D answerPoint2 = new Point3D(0, 0, -600);
//
//        answerList2.add(answerPoint1);
//        answerList2.add(answerPoint2);
//
//
//        // building the spheres
//
//        Point3D p1 = new Point3D(0, 0, -400);
//        Point3D p2 = new Point3D(p1);
//        Point3D centerPoint = new Point3D(0,0,0);
//
//        Vector direction1 = new Vector(50, -50, -50);
//        Vector direction2 = new Vector(0, 0, -5);
//        Sphere sphere1 = new Sphere(Color.white, 200, p1);
//        Sphere sphere2 = new Sphere(Color.white, 200, p2);
//
//        // building the ray that will intersect the spheres
//
//        Ray ray1 = new Ray(centerPoint, direction1);
//        Ray ray2 = new Ray(centerPoint, direction2);
//
//        // testing the findIntersection functions
//        List<Point3D> list1 = new ArrayList<Point3D>();
//        list1 = sphere1.findIntersections(ray1);
//        assertEquals(answerList1, list1);
//
//        List<Point3D> list2 = new ArrayList<Point3D>();
//        list2 = sphere2.findIntersections(ray2);
//        assertEquals(answerList2, list2);
//    }
//
//
//
//        @Test
//        public void testFindIntersections() throws Exception
//        {
//            Sphere sphere=new Sphere(null,2.0,new Point3D(0,0,-3));
//            ArrayList<Point3D> myintersectionPoints=(ArrayList<Point3D>) sphere.findIntersections(new Ray(new Point3D(0,0,0), new Vector(0, 0,-1)));
//            ArrayList<Point3D> intersectionPoints=new ArrayList<Point3D>();
//            intersectionPoints.add(new Point3D(0,0,-1));
//            intersectionPoints.add(new Point3D(0,0,-5));
//            assertTrue(myintersectionPoints.containsAll(intersectionPoints));
//
//            Sphere sphere1=new Sphere(null,2.0, new Point3D(0.0,0.0,-3.0));
//            ArrayList<Point3D> myintersectionPoints1=(ArrayList<Point3D>) sphere1.findIntersections(new Ray (new Point3D(0,0,-3), new Vector(0,0,1)));
//            ArrayList<Point3D> intersectionPoints1=new ArrayList<Point3D>();
//            intersectionPoints1.add(new Point3D(0.0,0.0,-1.0));
//            assertEquals(intersectionPoints1,myintersectionPoints1);
//
//            Sphere sphere2=new Sphere(null,1.0,new Point3D(0.0,0.0,3.0));
//            ArrayList<Point3D> myintersectionPoints2=(ArrayList<Point3D>) sphere2.findIntersections(new Ray(new Point3D(), new Vector(0,1,0)));
//            ArrayList<Point3D> intersectionPoints2=new ArrayList<Point3D>();
//            assertEquals(intersectionPoints2,myintersectionPoints2);
//
//            Sphere sphere3=new Sphere  (null,50.0,new Point3D(0.0,0.0,-150));
//            ArrayList<Point3D> myResult3=(ArrayList<Point3D>) sphere3.findIntersections(new Ray(new Point3D(0,0,0), new Vector(250, 250,-150)));
//            ArrayList<Point3D> result3=new ArrayList<Point3D>();
//            assertEquals(result3,myResult3);
//
//            Sphere sphere4=new Sphere(null,2,new Point3D(0,0,-3));
//            ArrayList<Point3D> myintersectionPoints4=(ArrayList<Point3D>) sphere4.findIntersections(new Ray(new Point3D(0,0,-3), new Vector(0, 0,-1)));
//            ArrayList<Point3D> intersectionPoints4=new ArrayList<Point3D>();
//            intersectionPoints4.add(new Point3D(0,0,-5));
//            assertTrue(myintersectionPoints4.containsAll(intersectionPoints4));
//
//
//        }

}