package Test;

import Geometries.Plane;
import Primitives.Point3D;
import Primitives.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Geometries.*;
import java.awt.Color;
import Primitives.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import Elements.*;

import java.awt.*;

import static org.junit.Assert.*;

public class PlaneTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getNormal()
    {
        Point3D point=new Point3D(2,2,3);
        Vector V=new Vector(1,2,3);
        Plane p= new Plane (point,V);

        if(p.getNormal(point)==V)
            assertTrue(true);
        else fail("ERROR"+p.getNormal(point));


    }
    @Test
    public void testIntersectionPoints() throws Exception {

        final int WIDTH = 3;

        final int HEIGHT = 3;

        Ray[][] rays = new Ray[HEIGHT][WIDTH];

        Camera camera = new Camera(new Point3D(new Coordinate(0.0), new Coordinate(0.0),new Coordinate(0.0)),
                new Vector(0.0, 1.0, 0.0),
                new Vector(0.0, 0.0, -1.0));

// plane orthogonal to the view plane
        Plane plane = new Plane(new Point3D(new Coordinate(0.0), new Coordinate(0.0), new Coordinate(-3.0)), new Vector(0.0, 0.0, -1.0) );

// 45 degrees to the view plane
        Plane plane2 = new Plane(new Point3D(new Coordinate(0.0), new Coordinate(0.0), new Coordinate(-3.0)),new Vector(0.0, 0.25, -1.0));

        List<Point3D> intersectionPointsPlane = new ArrayList();

        List<Point3D> intersectionPointsPlane2 = new ArrayList();

        System.out.println("Camera:\n" + camera);

        for (int i = 0; i < HEIGHT; i++) {

            for (int j = 0; j < WIDTH; j++) {

                rays[i][j] = camera.constructRayThroughPixel(
                        WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);

                List<Point3D> rayIntersectionPoints = plane.findIntersections(rays[i][j]);

                List<Point3D> rayIntersectionPoints2 = plane2.findIntersections(rays[i][j]);

                for (Point3D iPoint : rayIntersectionPoints) {
                    intersectionPointsPlane.add(iPoint);
                }

                for (Point3D iPoint : rayIntersectionPoints2) {
                    intersectionPointsPlane2.add(iPoint);
                }

            }

        }

        assertTrue(intersectionPointsPlane.size() == 9);
        System.out.println(intersectionPointsPlane.size());
        assertTrue(intersectionPointsPlane2.size() == 9);

        for (Point3D iPoint : intersectionPointsPlane) {
            System.out.println(iPoint);
        }

        System.out.println("---");

        for (Point3D iPoint : intersectionPointsPlane2) {
            System.out.println(iPoint);
        }

    }

}