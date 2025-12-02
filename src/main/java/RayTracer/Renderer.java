package RayTracer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import RayTracer.imaging.Color;

public class Renderer {

	private Scene scene;
	
	
	
	public Renderer(Scene scene) {
		this.scene = scene;
	}
	
	
	public BufferedImage render() {
		BufferedImage renderedImage = new BufferedImage(scene.getHeight(), scene.getWidth(), BufferedImage.TYPE_3BYTE_BGR);
		
		RayTracer rayTracer = new RayTracer(scene);
		
		for(int i=0; i<scene.getHeight(); i++) {
			for(int j=0; j<scene.getWidth(); j++) {
				renderedImage.setRGB(i, j, rayTracer.getPixelColor(i, j).toRGB() );
			}
		}
		
		return renderedImage;
		
	}
	
	
	
	

	
	
}
