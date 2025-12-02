package RayTracer.geometry;

public class Intersection {

	private double distance;
	private Point position;
	private Vector normale;
	private Shape shape;
	
	
	public Intersection(double distance) {
		this.distance = distance;
		//pafini 
	}
	
	public double getDistance() {
		return this.distance;
	}
	
	
}
