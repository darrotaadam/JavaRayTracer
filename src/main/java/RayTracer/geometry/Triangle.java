package RayTracer.geometry;

import RayTracer.imaging.Color;

public class Triangle implements Shape{
	
	private Point a;
	private Point b;
	private Point c;

	private Color specular;
	private Color diffuse;
	
	public Triangle(Point a, Point b, Point c, Color specular, Color diffuse) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.specular = specular;
		this.diffuse = diffuse;
	}
	
	
	
	
}
