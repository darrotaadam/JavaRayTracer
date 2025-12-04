package RayTracer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		//String GIT_ROOT_DIR = "/home/ad/Documents/Cours/POO/JavaRayTracer";	// répertoire du repo sur le système
		//String GIT_ROOT_DIR = "C:\\Users\\AdamDARROT\\OneDrive - ALLEO\\Bureau\\JavaRayTracer";
		String GIT_ROOT_DIR = System.getProperty("user.dir");
		String SCENES = GIT_ROOT_DIR + "/TestScenes";					// Répertoire global des scènes de test
		String resultImagesDir = GIT_ROOT_DIR + "/ResultScenesImages";	// Répertoire où placer les nouvelles images générées
		String[] sceneDirs = { "/jalon2", "/jalon3", "/jalon4" };	// Répertoires de scènes à traiter
		
		
		
		
		//creation du repertoire ResultScenesImages
		try {
			Files.createDirectories(Paths.get(resultImagesDir));
		}catch(Exception e) {
			System.out.println("Could not create directory " + resultImagesDir);
			e.printStackTrace();
			System.exit(1);
		}
		
		
		Scene scene;
		Renderer renderer;
		BufferedImage resultImage;
		String resultImageName;
		
		
		//generation des images de chaque jalon dans tous les sous répertoires de ResultScenesImages créés
		for(int i=0; i<sceneDirs.length; i++) {
			try {
				Files.createDirectories(Paths.get(resultImagesDir + sceneDirs[i]));
			}catch(Exception e) {
				System.out.println("Could not create directory " + resultImagesDir + sceneDirs[i]);
				e.printStackTrace();
				System.exit(1);
			}
			
			List<String> sceneFiles = listSceneFiles(new File(Paths.get(SCENES+sceneDirs[i]).toString()) );
			for(int j=0; j<sceneFiles.size(); j++) {
				System.out.println("[*] Import de " + sceneFiles.get(j));
				
				try {
					scene = new Scene(Paths.get(SCENES + sceneDirs[i] + "/" + sceneFiles.get(j)).toString() );
					renderer = new Renderer(scene);
					resultImage = renderer.render();
					resultImageName = Paths.get( resultImagesDir + sceneDirs[i] + "/" + sceneFiles.get(j).substring(0,sceneFiles.get(j).indexOf(".")) +".png").toString() ;
					saveImage(resultImage, resultImageName);
					System.out.println("[*]Image sauvegardée !  -> " + resultImageName);
				}catch(Exception e) {
					System.out.println("Erreur de génération sur la scène " + sceneFiles.get(j));
					e.printStackTrace();
					continue;
				}
				System.out.println("");
			}
			System.out.println("----------------------");
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
	
	
	
	
	
	
	private static List<String> listSceneFiles( File folder) {
	    List<String> sceneFiles = new ArrayList();
	    for (final File fileEntry : folder.listFiles()) {
	        if (!fileEntry.isDirectory()) {
	        	if(fileEntry.getName().endsWith(".test")) {
	        		sceneFiles.add(fileEntry.getName());
	        	}
	        }
	    }
	    return sceneFiles;
	}
	
	
}
