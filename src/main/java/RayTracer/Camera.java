package RayTracer;

import RayTracer.geometry.Point;
import RayTracer.geometry.Vector;

public class Camera {
	private Point position;
	private Point looksAt;
	private Vector upDirection;	// direction vers le haut de l'oeil: orientation de la caméra 
	private double fov;
	
	
	public Camera() {
	}
	
	public void setPosition(double x,double y,double z) {
		this.position = new Point(x, y, z);
	}
	public void setPosition(Point p) {
		this.position = p;
	}
	
	
	public void looksAt(double u,double v,double w) {
		this.looksAt = new Point(u, v, w);
	}
	public void looksAt(Point target) {
		this.looksAt = target;
	}
	
	
	public void setUpDirection(double m,double n,double o) {
		this.upDirection = new Vector(m, n, o);
	}
	public void setUpDirection(Vector d) {
		this.upDirection = d;
	}
	
	
	public void setFov(double angle) {
		this.fov = angle;
	}
	
	
}
