package RayTracer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import javax.imageio.ImageIO;


public class Main{
	
	public static void main(String[] args) {
		if(args.length != 2){    
            System.out.println("[!] Paramètres: fichier1.png fichier2.png");
            throw new IllegalArgumentException("Incorrect count of parameters");
        }
		
		BufferedImage img1 = openImage(args[0]);
		BufferedImage img2 = openImage(args[1]);
		if(img1 == null || img2 == null) {
			throw new IllegalArgumentException("Fichier.s introuvable.s");
		}
		
		System.out.println("Images chargées avec succès");
		System.out.println("Image 1: " + args[0] + " (" + img1.getWidth() + "x" + img1.getHeight() + ")");
        System.out.println("Image 2: " + args[1] + " (" + img2.getWidth() + "x" + img2.getHeight() + ")");
		
		ImageComparator comparateur = new ImageComparator();
		int differentPixels = comparateur.getDifferentPixels(img1, img2);
        
        
		
	}
	
	
	private static BufferedImage openImage(String filename) {
		Path inPath = Paths.get(filename);
        try (InputStream stream = Files.newInputStream(inPath)){
        	BufferedImage image = ImageIO.read(stream);
        	return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
            
        }
        
	}
	
	
	
}