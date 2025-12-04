package RayTracer;

import java.util.Optional;

import RayTracer.geometry.Intersection;
import RayTracer.geometry.Orthonormal;
import RayTracer.geometry.Vector;
import RayTracer.imaging.Color;
import RayTracer.imaging.Pixel;
import RayTracer.raytracer.Ray;

public class RayTracer {

	Orthonormal space;
	Scene scene;
	
	public RayTracer(Scene scene) {
		this.scene = scene;
		this.space = new Orthonormal(scene.getCamera().getUpDirection(), scene.getCamera().getPosition(), scene.getCamera().getLooksAt());
	}
	
	
	public Color getPixelColor(int i, int j) {
		Pixel pixel = getPixelSize(scene.getCamera().getFov(), scene.getWidth(), scene.getHeight());
		
		//calculate d ray
		Vector d = computeD(i, j, pixel);
		Ray ray = new Ray(scene.getCamera().getPosition(), d);
		//find intersection p
		
		Optional<Intersection> p = scene.findClosestIntersection(ray);
		if( p.isPresent()) {
			
			Color couleurPoint = new Color(this.scene.getAmbient());
			for(int l=0; l<this.scene.getLights().size(); l++) {
				if (! p.get().isShadowed(this.scene.getLights().get(l), this.scene)) {
					couleurPoint = couleurPoint.add(p.get().computeDiffusionLambert(this.scene.getLights().get(l)));					
				}				
			}
			p.get().setColor(couleurPoint);
			return p.get().getColor();
			
			//return scene.getAmbient();
		}
		else {
			return new Color();
		}
	}
	
	
	private Vector computeD(double i, double j, Pixel pixel) {
		double a = pixel.getWidth() *(i - scene.getWidth()/2 + 0.5) / (scene.getWidth()/2);
		double b = pixel.getHeight() *(j - scene.getHeight()/2 + 0.5) / (scene.getHeight()/2);
		Vector d = space.getU().multByScalar(a).add(space.getV().multByScalar(b)).sub(space.getW()).normalisation();
		return d;
	}
	
	private Pixel getPixelSize(double fov, int imgWidth, int imgHeight) {
		double fovRadient = (fov * Math.PI) / 180;
		double pixelHeight = Math.tan(fovRadient/2);
		double pixelWidth = pixelHeight * ((double)imgWidth / imgHeight);
		return new Pixel(pixelWidth, pixelHeight);
	}
	
	
	
	
}
