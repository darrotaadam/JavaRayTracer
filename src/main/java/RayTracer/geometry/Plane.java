package RayTracer.geometry;

import java.util.Optional;

import RayTracer.imaging.Color;
import RayTracer.raytracer.DirectionalLight;
import RayTracer.raytracer.Ray;

public class Plane implements Shape{

	public Point position;
	public Vector normale;
	
	private Color specular;
	private Color diffuse;
	private Double shininess;
	
	public Color getSpecular() {
		return specular;
	}
	public Color getDiffuse() {
		return diffuse;
	}
	public Double getShininess() {
		return shininess;
	}
	
	
	public Plane(Point position, Vector normale, Color specular, Color diffuse, Double shininess) {
		this.normale = normale;
		this.position = position;
		this.specular = specular;
		this.diffuse = diffuse;
		this.shininess = shininess;
	}

	public Optional<Intersection> intersect(Ray rayon) {
		return Optional.empty() ;
	}


	
	
}
