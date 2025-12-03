package RayTracer.geometry;

import RayTracer.imaging.Color;
import RayTracer.raytracer.DirectionalLight;
import RayTracer.raytracer.Light;
import RayTracer.raytracer.PointLight;


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
	
	
}
