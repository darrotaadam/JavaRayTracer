package RayTracer.geometry;

import java.util.Optional;

import RayTracer.Scene;
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
	
	
	public Color computeDiffusionLambert(Light light) {
		if (light instanceof DirectionalLight dirLight) {
			return computeDiffusionLambert(dirLight);
		}
		else if (light instanceof PointLight pointLight) {
			return computeDiffusionLambert(pointLight);
		}
		return new Color();
	}
	
	
	private Color computeDiffusionLambert(DirectionalLight light) {
		return light.getColor()
				.schurProduct(this.shape.getDiffuse())
				.multiply(Math.max(this.normale.produitScalaire(light.getDirection()), (double)0));
	}
	
	private Color computeDiffusionLambert(PointLight light) {
		Vector lightDirection = light.getOrigin().sub(this.position).normalisation();
	    return this.shape.getDiffuse()
	        .schurProduct(light.getColor())
	        .multiply(Math.max(0, this.normale.produitScalaire(lightDirection)));
	}
	
	
	
	public boolean isShadowed(Light light, Scene scene) {
		if (light instanceof DirectionalLight dirLight) {
			return isShadowed(dirLight, scene);
		}
		else if (light instanceof PointLight pointLight) {
			return isShadowed(pointLight, scene);
		}
		return false;
	}
	
	private boolean isShadowed(DirectionalLight light,Scene scene) {
		Vector directionToLight = light.getDirection().normalisation().multByScalar(-1);
		Ray shadowRay = new Ray(directionToLight.multByScalar(1e-4).add(this.position), directionToLight);
		Optional<Intersection> shadowSource = scene.findClosestIntersection(shadowRay);		
		return shadowSource.isPresent();		
	}
	
	private boolean isShadowed(PointLight light, Scene scene) {
		Vector directionToLight = light.getOrigin().sub(this.position).normalisation();
		Ray shadowRay = new Ray(directionToLight.multByScalar(1e-4).add(this.position), directionToLight);
		Optional<Intersection> shadowSource = scene.findClosestIntersection(shadowRay);
		return shadowSource.isPresent();
	}
	
	
	
	
	
	
	
	
}
