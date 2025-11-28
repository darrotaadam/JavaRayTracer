package RayTracer.geometry;

import RayTracer.imaging.Color;

public class Sphere implements Shape{

	private Point center;
	private double radius;
	private Color specular;
	private Color diffuse;
	
	public Sphere(double x, double y, double z, double radius, Color specular, Color diffuse) {
		this.center = new Point(x, y, z);
		this.radius= radius;
		this.specular = specular;
		this.diffuse = diffuse;
	}
	public Sphere(Point center, double radius, Color specular, Color diffuse) {
		this.center = center;
		this.radius= radius;
		this.specular = specular;
		this.diffuse = diffuse;
	}
}
