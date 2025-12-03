package ImageComparator;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;



public class Main{
	
	public static void main(String[] args) {
		if(args.length != 3){    
			System.out.println("[!] Paramètres: fichier1.png fichier2.png fichier_export.png");
            throw new IllegalArgumentException("Incorrect count of parameters, received "+ args.length);
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
        
        System.out.println("Nombre de pixels différents : " + differentPixels);
		
        
        BufferedImage imageDifferentielle = comparateur.imageDifferencielle(img1, img2);
        
        String savedImage = saveImage(imageDifferentielle, args[2]);
        
        if (savedImage != null) System.out.println("Image différentielle créée à " + savedImage);
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