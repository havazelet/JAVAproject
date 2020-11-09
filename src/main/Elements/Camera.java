package Elements;

import Primitives.*;

import java.util.Objects;

public class Camera {

    private Point3D _p0;
    private Vector _vUp;
    private Vector _vTo;
    private Vector _vRight;


    // ***************** Constructors ********************** //
    public Camera()
    {
        super();
        this._p0 = new Point3D(0,0,200);
        this._vUp = new Vector(0,1,0);
        this._vTo =new Vector(0,0,-1) ;
        this._vRight = new Vector(1,0,0);
    }

    public Camera(Point3D p0, Vector vUp, Vector vTo) {
        this._p0 = new Point3D(p0);
        this._vUp = new Vector(vUp);
        this._vTo = new Vector(vTo);
        this._vRight=new Vector(vTo.crossProduct(vUp));
    }

    public Camera(Point3D _p0, Vector _vUp, Vector _vTo, Vector _vRight) {
        this._p0 = _p0;
        this._vUp = _vUp;
        this._vTo = _vTo;
        this._vRight = _vRight;
    }
    public Camera(Camera ncamera) {
//        this._p0 = ncamera._p0;
//        this._vUp = ncamera._vUp;
//        this._vTo = ncamera._vTo;
//        this._vRight = ncamera._vRight;
        this._p0 = new Point3D(ncamera.get_p0());
        this._vUp = new Vector(ncamera.get_vUp());
        this._vTo = new Vector(ncamera.get_vTo());
        this._vRight = new Vector(ncamera.get_vRight());

    }


// ***************** Getters/Setters ********************** //

    public Point3D get_p0() {
        return _p0;
    }

    public void set_p0(Point3D _p0) {
        this._p0 = _p0;
    }

    public Vector get_vUp() {
        return _vUp;
    }

    public void set_vUp(Vector _vUp) {
        this._vUp = _vUp;
    }

    public Vector get_vTo() {
        return _vTo;
    }

    public void set_vTo(Vector _vTo) {
        this._vTo = _vTo;
    }

    public Vector get_vRight() {
        return _vRight;
    }

    public void set_vRight(Vector _vRight) {
        this._vRight = _vRight;
    }

// ***************** Administration  ******************** //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camera camera = (Camera) o;
        return Objects.equals(_p0, camera._p0) &&
                Objects.equals(_vUp, camera._vUp) &&
                Objects.equals(_vTo, camera._vTo) &&
                Objects.equals(_vRight, camera._vRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_p0, _vUp, _vTo, _vRight);
    }

    @Override
    public String toString() {
        return "Camera{" +
                "_p0=" + _p0 +
                ", _vUp=" + _vUp +
                ", _vTo=" + _vTo +
                ", _vRight=" + _vRight +
                '}';
    }
    // ***************** Operations ******************** //

//    public Ray constructRayThroughPixel(int Nx, int Ny, double i, double j, double screenDistance, double screenWidth, double screenHeight) {
//        Vector Vt = new Vector(_vTo);
//        Vt.scale(screenDistance);
//        Point3D Pc = new Point3D(_p0);
//        Pc.add(Vt);
//
//        double Rx=screenWidth/Nx;
//        double Ry=screenHeight/Ny;
//
//        double Xi=(i-(Nx+1)/2)*Rx;
//        double Yi=(i-(Ny+1)/2)*Ry;
//
//        Vector V1=new Vector(_vRight);
//        Vector V2=new Vector(_vUp);
//
//        V1.scale(Xi);
//        V2.scale(Yi);
//        V1.subtract(V2);
//        Pc.add(V1);
//
//        Vector direction=new Vector(Pc,_p0);
//        direction.normalize();
//        Ray Pij=new Ray(_p0,direction);
//
//        return Pij;
//    }
    //    }
//    }

    public Ray constructRayThroughPixel(int Nx, int Ny, double x, double y, double screenDist, double screenWidth, double screenHeight)
        throws Exception {
    Vector Vt = new Vector(_vTo);
    Vt.scale(screenDist);
    Point3D Pc = new Point3D(_p0);
    Pc.add(Vt);
    double Rx = screenWidth / Nx;
    double Ry = screenHeight / Ny;
    double help = (x - (Nx / 2.0)) * Rx + (Rx / 2.0);
    Vector Vr = new Vector(_vRight);
    Vr.scale(help);
    help = (y - (Ny / 2.0d)) * Ry + (Ry / 2.0);
    Vector Vu = new Vector(_vUp);
    Vu.scale(help);
    Vr.subtract(Vu);
    Pc.add(Vr);
    Vector dir = new Vector(Pc,new Point3D(_p0));
    dir.normalize();
    return new Ray(_p0, dir);
}
}