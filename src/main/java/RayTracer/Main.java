package RayTracer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		String GIT_ROOT_DIR = "/home/ad/Documents/Cours/POO/JavaRayTracer";
		Scene test1 = new Scene(GIT_ROOT_DIR + "/TestScenes/jalon3/tp35.test");
	
		Renderer renderer = new Renderer(test1);
		
		BufferedImage resultImage = renderer.render();
	
		saveImage(resultImage, "/home/ad/result.png");
		
		System.out.println("Image sauvegardée !");
		
	}
	
	
	private static String saveImage(BufferedImage image, String filePath) {
		Path outPath = Paths.get(filePath);
		try( OutputStream stream = Files.newOutputStream(outPath) ){
			ImageIO.write(image, "png", stream);
			return filePath;
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
}
