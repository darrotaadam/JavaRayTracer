package RayTracer.geometry;

import java.util.Optional;

import RayTracer.imaging.Scene;
import RayTracer.imaging.Color;
import RayTracer.raytracer.DirectionalLight;
import RayTracer.raytracer.Light;
import RayTracer.raytracer.PointLight;
import RayTracer.raytracer.Ray;


public class Intersection {

	private double distance;
	private Point position;
	private Vector normale;
	private Shape shape;
	private Color color;
	
	
	public Intersection(double distance, Point position, Vector normale, Shape shape) {
		this.distance = distance;
		this.position = position;
		this.normale = normale;
		this.shape = shape;
	}
	
	public double getDistance() {
		return this.distance;
	}
	public Color getColor() {
		return this.color;
	}
	public void setColor(Color couleur) { 
        this.color = couleur;
    }
	
	
	public Point getPosition() {
		return position;
	}

	public Color computeDiffusionLambert(Light light) {
		Vector lightDirection = getLightDirection(light);
		return light.getColor()
		        .schurProduct(this.shape.getDiffuse())
		        .multiply(Math.max((double) 0, this.normale.produitScalaire(lightDirection)));
	}
	
	
	
	
	public Color computeBlinnPhong(Light light, Vector eyeDir) {
		Vector lightDirection = getLightDirection(light);
		Vector h = lightDirection.add(eyeDir).normalisation();
		return this.shape.getSpecular()
				.schurProduct(light.getColor())
				.multiply(Math.pow(Math.max(0d, h.produitScalaire(this.normale)), this.shape.getShininess()));
	}
	
	
	/* Calcul de la reflexion */
	
	public Color computeCouleurReflet(Vector eyeDir, Scene scene, int depth) {

		if (depth <= 0) {
			return new Color();
		}
		
		
				
		Vector dirReflechie = eyeDir.add( 
				this.normale.multByScalar(this.normale.produitScalaire(eyeDir.multByScalar(-1.0)) *2.0 )	
		);
		Ray lumiereReflechie = new Ray(
				dirReflechie.multByScalar(1e-6d).add(this.position), 
				dirReflechie
		);
		
		Optional<Intersection> sourceReflet = scene.findClosestIntersection(lumiereReflechie);
		
		if (sourceReflet.isEmpty()) {
			return new Color();	
		}
		
		
		 // Compute the reflected color at the next hit
	    Intersection p = sourceReflet.get();
	    Vector nextEyeDir = dirReflechie.multByScalar(-1.0);

	    Color couleurReflet = new Color(scene.getAmbient());
	    for (Light light : scene.getLights()) {
	        
	            
	            couleurReflet = couleurReflet.add(p.computeBlinnPhong(light, nextEyeDir));
	        
	    }

	    // Add recursive component
	    Color couleurRefletRecursif =
	        p.computeCouleurReflet(nextEyeDir, scene, depth - 1);

	    // Final color = specular * (direct + recursive)
	    return this.shape.getSpecular().schurProduct(
	        couleurReflet.add(couleurRefletRecursif)
	    );
		
		/*
		return this.shape.getSpecular().add(
				sourceReflet.get().computeCouleurReflet(lumiereReflechie.getDirection(), scene, depth-1)
		);*/
		
		
	}
	
	
	public boolean isShadowed(Light light, Scene scene) {
		Vector lightDirection = getLightDirection(light);
		Ray shadowRay = new Ray(lightDirection.multByScalar(1e-6d).add(this.position), lightDirection);
		Optional<Intersection> shadowSource = scene.findClosestIntersection(shadowRay);
		return shadowSource.isPresent();
	}
	


	
	
	
	
	private Vector getLightDirection(Light light) {
	    if (light instanceof DirectionalLight dir) {
	        return dir.getDirection().normalisation();
	    }
	    if (light instanceof PointLight point) {
	        return point.getOrigin().sub(this.position).normalisation();
	    }
	    return new Vector(0,0,0);
	}
	
	
	
	private Vector getDirectionToLight(Light light) {
	    if (light instanceof DirectionalLight dir) {
	        return dir.getDirection().multByScalar(-1d).normalisation();
	    }
	    if (light instanceof PointLight point) {
	        return point.getOrigin().sub(this.position).multByScalar(-1d).normalisation();
	    }
	    return new Vector(0,0,0);
	}
	
	
}
