package RayTracer.imaging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import RayTracer.imaging.Color;
import RayTracer.raytracer.DirectionalLight;
import RayTracer.raytracer.Light;
import RayTracer.raytracer.Ray;
import RayTracer.geometry.Intersection;
import RayTracer.geometry.Shape;

public class Scene {
	
	private int width;
	private int height;
	private Camera camera;
	private String output = "output.png";
	private Color ambient ;
	private List<Light> lights = new ArrayList<>();
	private List<Shape> shapes = new ArrayList<>();
	private int maxdepth;
	
	
	public Scene(String fileName) {
		importSceneFile(fileName);	// va définir les valeurs des attributs
		
	}
	
		
	/* Getters */
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public Camera getCamera() {
		return camera;
	}
	public String getOutput() {
		return output;
	}
	public Color getAmbient() {
		return ambient;
	}
	public List<Light> getLights() {
		return lights;
	}
	public List<Shape> getShapes() {
		return shapes;
	}
	public int getMaxDepth() {
		return maxdepth;
	}
	

	public void printSummary() {
		System.out.println("[*] Scene id " + this.hashCode());
		System.out.println("[*] Size " + this.getWidth() + "x" + this.getHeight());
	}
	

	private void importSceneFile(String fileName) {
		try {
			SceneFileParser parser = new SceneFileParser(fileName);
			this.height = parser.getHeight();
			this.width = parser.getWidth();
			this.camera = parser.getCamera();
			this.output = parser.getOutput();
			this.ambient = parser.getAmbient();
			this.lights = parser.getLights();
			this.shapes = parser.getShapes();
			this.maxdepth = parser.getMaxDepth();
		}catch(IOException e) {
			System.out.println(e);
			System.out.println("[x] Exiting.");
			System.exit(1);
		}
		
	}

	


	
	
	
	public Optional<Intersection> findClosestIntersection(Ray rayon){
		Optional<Intersection> closestIntersection = Optional.empty();
		for(int i=0; i<this.shapes.size(); i++) {
			Optional<Intersection> intersection = shapes.get(i).intersect(rayon);
			if(intersection.isPresent()) {
				if( closestIntersection.isEmpty() 	 ||		intersection.get().getDistance() < closestIntersection.get().getDistance()) { 
					closestIntersection = intersection;
				}
			}
		}
		return closestIntersection;
	}
	
	
	
	
}
