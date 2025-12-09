package RayTracer.geometry;

import java.util.Optional;

import RayTracer.imaging.Color;
import RayTracer.raytracer.Ray;


/**
 * Représente une sphère dans l'espace.
 * Est une Shape.
 */
public class Sphere implements Shape{

	private Point center;
	private double radius;
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
	 * Constructeur de la Sphere, utilisant les coordonées x,y,z pour le centre
	 * @param x
	 * @param y
	 * @param z
	 * @param radius
	 * @param specular
	 * @param diffuse
	 * @param shininess
	 */
	public Sphere(double x, double y, double z, double radius, Color specular, Color diffuse, Double shininess) {
		this.center = new Point(x, y, z);
		this.radius= radius;
		this.specular = specular;
		this.diffuse = diffuse;
		this.shininess = shininess;
	}
	/**
	 * Constructeur de la Sphere, utilisant un Point pour le centre
	 * @param center
	 * @param radius
	 * @param specular
	 * @param diffuse
	 * @param shininess
	 */
	public Sphere(Point center, double radius, Color specular, Color diffuse, Double shininess) {
		this.center = center;
		this.radius= radius;
		this.specular = specular;
		this.diffuse = diffuse;
		this.shininess = shininess;
	}
	
	
	/**
	 * Cherche une intersection avec le rayon passé en paramètre
	 * @param rayon	Rayon pour lequel on cherche une intersection sur la sphère
	 */
	@Override
	public Optional<Intersection> intersect(Ray rayon) {
		double a = rayon.getDirection().produitScalaire(rayon.getDirection());
		double b = rayon.getOrigin().sub(this.center)
							.produitScalaire(rayon.getDirection()) * 2;
		double c = rayon.getOrigin().sub(this.center)
							.produitScalaire(rayon.getOrigin().sub(this.center)) 
							-(this.radius * this.radius);
		double delta = (b*b) - 4 * a * c;
		Point position;
		Vector normale;
		
		if(delta < 0d) {
			return Optional.empty();
		}
		if(delta == 0d) {
			double t = -b / (2*a);
			position = rayon.getDirection().multByScalar(t).add(rayon.getOrigin());
			normale = (position.sub(this.center)).normalisation();
			return Optional.of(new Intersection(t, position, normale, this));
		}
		if(delta >0d) {
			double sqrtDelta = Math.sqrt(delta);
			double t1 = (-b + sqrtDelta) / (2 *a);
			double t2 = (-b - sqrtDelta) / (2 *a);
			
			if(t2 > 0d) {
				position = rayon.getDirection().multByScalar(t2).add(rayon.getOrigin());
				normale = (position.sub(this.center)).normalisation();
				return Optional.of(new Intersection(t2, position, normale, this));
			}
			else if(t1 > 0d) {
				position = rayon.getDirection().multByScalar(t1).add(rayon.getOrigin());
				normale = (position.sub(this.center)).normalisation();
				return Optional.of(new Intersection(t1, position, normale, this));
			}
			
		}
		return Optional.empty() ;
	}
	
	
	
	
	
	
	
	
}
