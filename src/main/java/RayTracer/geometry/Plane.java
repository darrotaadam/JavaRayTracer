package RayTracer.geometry;

import java.util.Optional;

import RayTracer.imaging.Color;
import RayTracer.raytracer.DirectionalLight;
import RayTracer.raytracer.Ray;


/**
 * Représente un plan dans l'espace.
 * Est une Shape.
 */

public class Plane implements Shape{

	/**
	 * position position du plan
	 */
	public Point position;
	/**
	 * normale du plan
	 */
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
	
	/**
	 * Constructeur du Plan. 
	 * @param position position du plan dans l'espace
	 * @param normale normale du plan
	 * @param specular couleur specular du plan
	 * @param diffuse couleur diffuse du plan
	 * @param shininess brillance
	 */
	public Plane(Point position, Vector normale, Color specular, Color diffuse, Double shininess) {
		this.normale = normale;
		this.position = position;
		this.specular = specular;
		this.diffuse = diffuse;
		this.shininess = shininess;
	}

	/**
	 * Cherche une intersection avec le rayon passé en paramètre
	 * @param rayon	Rayon pour lequel on cherche une intersection sur le plan
	 */
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
