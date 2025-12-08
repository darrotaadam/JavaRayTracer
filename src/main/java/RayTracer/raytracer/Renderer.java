package RayTracer.raytracer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import RayTracer.raytracer.RayTracer;
import RayTracer.imaging.Scene;
import RayTracer.imaging.Color;


public class Renderer {

	private Scene scene;
	
	
	
	public Renderer(Scene scene) {
		this.scene = scene;
	}
	
	
	public BufferedImage render() {
		BufferedImage renderedImage = new BufferedImage(scene.getWidth(), scene.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		String sceneName = scene.getOutput();
		RayTracer rayTracer = new RayTracer(scene);
		long totalPixels = (long) scene.getWidth() * scene.getHeight();
		int flippedY;
		int i,j;
		long current;
		double progress;
		for(i=0; i<scene.getWidth(); i++) {
			for(j=0; j<scene.getHeight(); j++) {
				flippedY = scene.getHeight() - 1 - j;
				renderedImage.setRGB(
					i, 
					j, 
					rayTracer.getPixelColor(i, flippedY).toRGB() 
				);
				

			}
			if(i%20 == 0) {
				current = (long) i * scene.getHeight();
		        progress = (int)(100.0 * current) / totalPixels;
		        System.out.print("\rProgress on " + sceneName + " : " + progress + "%" + "    ");
		        System.out.flush();
			}

		}
		return renderedImage;	
	}
	
}
 