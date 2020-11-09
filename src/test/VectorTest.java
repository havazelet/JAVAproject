package Test;

import Primitives.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VectorTest {

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
        v1.add(v2);
        if(v1.get_head().get_x().get_coordinate()==6&&v1.get_head().get_y().get_coordinate()==6&&v1.get_head().get_z().get_coordinate()==6)
            assertTrue(true);
        else fail("ERROR"+v1.toString());
    }

    @Test
    public void subtract()
    {
        v1.subtract(v2);
        if(v1.get_head().get_x().get_coordinate()==0&&v1.get_head().get_y().get_coordinate()==0&&v1.get_head().get_z().get_coordinate()==0)
            assertTrue(true);
        else fail("ERROR"+v1.toString());
    }

    @Test
    public void scale()
    {
        v1.scale(2);
        if(v1.get_head().get_x().get_coordinate()==6&&v1.get_head().get_y().get_coordinate()==6&&v1.get_head().get_z().get_coordinate()==6)
            assertTrue(true);
        else fail("ERROR "+v1.toString());
    }

    @Test
    public void length()
    {
        if(v1.length()==(3*Math.sqrt(3)))
            assertTrue(true);
        else fail("ERROR "+v1.length());

    }

    @Test
    public void normalize()
    {
    v1.normalize();
    if(v1.get_head().get_x().get_coordinate()==(3/(3*Math.sqrt(3))))
        assertTrue(true);
    else fail("ERROR "+v1.toString());
    }

    @Test
    public void crossProduct()
    {
        Vector v1=new Vector(1,2,3);
        Vector v2=new Vector(4,2,0);
        v1.crossProduct(v2);
        Vector v3=v1.crossProduct(v2);
        if(v3.get_head().get_x().get_coordinate()==-6&&v3.get_head().get_y().get_coordinate()==12&&v3.get_head().get_z().get_coordinate()==-6)
            assertTrue(true);
        else fail("ERROR "+v3.toString());
    }

    @Test
    public void dotProduct()
    {
       if( v1.dotProduct(v2)==27)
          assertTrue(true);
        else fail("ERROR "+v1.dotProduct(v2));

    }
}