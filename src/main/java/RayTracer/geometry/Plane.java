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


	@Override
	public Optional<Intersection> intersect(Ray rayon) {
		Double top = this.position.sub(rayon.getOrigin()).produitScalaire(this.normale); 
		Double bottom = rayon.getDirection().produitScalaire(this.normale);
		
		if (Math.abs(bottom) < 1e-6) {
			return Optional.empty();
		}
		
		Double t = top/bottom;
		
		if(t < 1e-6) {	// est soit au niveau de la caméra, soit derrière
			return Optional.empty();
		}
		
		position = rayon.getDirection().multByScalar(t).add(rayon.getOrigin());

		return Optional.of(new Intersection(t, this.position, this.normale, this));
		
	}

	
	
	
	
	
}
