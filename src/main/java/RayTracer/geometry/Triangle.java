package RayTracer.geometry;

import java.util.Optional;

import RayTracer.imaging.Color;
import RayTracer.raytracer.Ray;

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
	
	
	public Optional<Intersection> intersect(Ray rayon) {
		return Optional.empty() ;
	}
	
}
