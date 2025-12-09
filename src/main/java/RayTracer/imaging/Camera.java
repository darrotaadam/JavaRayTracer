package RayTracer.imaging;

import RayTracer.geometry.Point;
import RayTracer.geometry.Vector;


/**
 * Représente un point de vue, un oeil, une caméra.
 */

public class Camera {
	private Point position;
	private Point looksAt;
	private Vector upDirection;	// direction vers le haut de l'oeil: orientation de la caméra 
	private double fov;
	
	
	public Camera() {
	}
	/**
	 * Constructeur de la Camera
	 * @param position	Point désignant la position dans l'espace de la Camera
	 * @param looksAt	Point vers lequel regarde la caméra
	 * @param upDirection	Vector désignant la direction vers le haut de l'oeil
	 * @param fov	Double désignant le champ de vision en degrés
	 */
	public Camera(Point position, Point looksAt, Vector upDirection, double fov) {
		this.position = position;
		this.looksAt = looksAt;
		this.upDirection = upDirection;
		this.fov = fov;
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
	
	
	public Point getPosition() {
		return position;
	}
	public Point getLooksAt() {
		return looksAt;
	}
	public Vector getUpDirection() {
		return upDirection;
	}
	public double getFov() {
		return fov;
	}
	
	
	
}
