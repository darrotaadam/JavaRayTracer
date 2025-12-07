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
	private Double shininess;
	private Vector normale;
	
	
	public Color getSpecular() {
		return specular;
	}
	public Color getDiffuse() {
		return diffuse;
	}
	public Double getShininess() {
		return shininess;
	}
	
	public Triangle(Point a, Point b, Point c, Color specular, Color diffuse, Double shininess) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.specular = specular;
		this.diffuse = diffuse;
		this.shininess = shininess;
		this.normale = this.b.sub(this.a).produitVectoriel(this.c.sub(this.a)).normalisation();

	}
	
	

	@Override
	public Optional<Intersection> intersect(Ray rayon) {

	    Vector edge1 = b.sub(a);
	    Vector edge2 = c.sub(a);

	    Vector normalePlan = rayon.getDirection().produitVectoriel(c.sub(a));
	    double det = b.sub(a).produitScalaire(normalePlan);

	    if (Math.abs(det) < 1e-6) {
	    	return Optional.empty();
	    }
	    

	    Vector tVec = rayon.getOrigin().sub(a);
	    double beta = tVec.produitScalaire(normalePlan) / det;
	    if (beta < 0 || beta > 1) {
	    	return Optional.empty();
	    }

	    Vector qVec = tVec.produitVectoriel(b.sub(a));
	    double gamma = rayon.getDirection().produitScalaire(qVec) / det;
	    if (gamma < 0 || beta + gamma > 1) {
	    	return Optional.empty();
	    }

	    double t = (c.sub(a)).produitScalaire(qVec) / det;
	    if (t <= 1e-6) {
	    	return Optional.empty();
	    }

	    Point intersectPosition = rayon.getDirection().multByScalar(t).add(rayon.getOrigin());
	    return Optional.of(new Intersection(t, intersectPosition, normale, this));
	}

	
	

	

}
