package RayTracer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import RayTracer.imaging.Color;
import RayTracer.raytracer.Light;
import RayTracer.geometry.Shape;

public class Scene {
	
	private int width;
	private int height;
	private Camera camera;
	private String output = "output.png";
	private Color ambient = new Color();
	private List<Light> lights = new ArrayList<>();
	private List<Shape> shapes = new ArrayList<>();
	
	
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
			
		}catch(IOException e) {
			System.out.println(e);
			System.out.println("[x] Exiting.");
			System.exit(1);
		}
		
	}

	
	public void printSummary() {
		System.out.println("[*] Scene id " + this.hashCode());
		System.out.println("[*] Size " + this.getWidth() + "x" + this.getHeight());
	}
	

	
	
}
