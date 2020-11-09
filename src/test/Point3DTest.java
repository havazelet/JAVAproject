package Test;

import Primitives.Point3D;
import Primitives.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Point3DTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    Vector v1=new Vector(3,3,3);
    Vector v2=new Vector(3,3,3);

    @Test
    public void add()
    {

        v1.get_head().add(v2);
        if(v1.get_head().get_x().get_coordinate()==6&&v1.get_head().get_y().get_coordinate()==6&&v1.get_head().get_z().get_coordinate()==6)
            assertTrue(true);
        else fail("ERROR"+v1.toString());
    }

    @Test
    public void sub()
    {
        v1.get_head().sub(v2);
        if(v1.get_head().get_x().get_coordinate()==0&&v1.get_head().get_y().get_coordinate()==0&&v1.get_head().get_z().get_coordinate()==0)
            assertTrue(true);
        else fail("ERROR"+v1.toString());
    }

    @Test
    public void subTest()
    {
        Point3D p1=new Point3D(1,0,0);
        Point3D p2=new Point3D(1,0,1);
       Vector v1= p1.sub(p2);
        if(v1.get_head().get_x().get_coordinate()==0&&v1.get_head().get_y().get_coordinate()==0&&v1.get_head().get_z().get_coordinate()==-1)
            assertTrue(true);
        else fail("ERROR"+v1.toString());
    }
}