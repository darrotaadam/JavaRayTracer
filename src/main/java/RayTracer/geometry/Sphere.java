package RayTracer.geometry;

public class Sphere implements Shape{

	private Point center;
	private double radius;
	
	
	public Sphere(double x, double y, double z, double radius) {
		this.center = new Point(x, y, z);
		this.radius= radius;
	}
	public Sphere(Point center, double radius) {
		this.center = center;
		this.radius= radius;
	}
}
