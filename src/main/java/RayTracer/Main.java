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
import RayTracer.raytracer.Renderer;
import RayTracer.imaging.Scene;

public class Main {

	public static void main(String[] args) {
		
		if(args.length == 0) {
			System.out.println("Usage: java -jar raytracer.jar <file.scene | --all>");
			System.out.println("[*] --all : Generate images using .scene and .test files in the TestScenes directory. Only usable from the root directory of the git repository");
			System.exit(1);
		}
		
		
		if (args.length == 1) {
		    if ("--all".equals(args[0])) {
				if(Files.exists(Paths.get("TestScenes"))) {					
					System.out.println("[*] Starting to render every scene");
					processAll();
				}
				else {
					System.out.println("[*] --all : Generate images using files in the TestScenes directory. Only usable from the root directory of the git repository");
				}
			}
			else {
				System.out.println("[*] Starting to render scene "+ args[0]);
				processScene(Paths.get(args[0]));
			}
		}
		
		if( args.length > 1) {
			System.out.println("[*] Second argument " + args[1] + "not understandable");
			System.out.println("Usage: java -jar raytracer.jar <file.scene | --all>");
			System.out.println("[*] --all : Generate images using .scene and .test files in the TestScenes directory. Only usable from the root directory of the git repository");
		}
		
		
	}
	
	
	/*
	private static Path askForScene() {
		
	}
	*/
	
	
	
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
	        	if(fileEntry.getName().endsWith(".test") || fileEntry.getName().endsWith(".scene")) {
	        		sceneFiles.add(fileEntry.getName());
	        	}
	        }
	    }
	    return sceneFiles;
	}
	
	
	
	private static void processAll() {
		// répertoire du repo sur le système
		String GIT_ROOT_DIR = System.getProperty("user.dir");
		String SCENES = GIT_ROOT_DIR + "/TestScenes";					// Répertoire global des scènes de test
		String resultImagesDir = GIT_ROOT_DIR + "/ResultScenesImages";	// Répertoire où placer les nouvelles images générées
		List<String> sceneDirs = new ArrayList();	// Répertoires de scènes à traiter
		
		for (final File fileEntry : new File(Paths.get(resultImagesDir).toString()).listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	sceneDirs.add(fileEntry.getName());
	        }
	    }
		
		
		
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
		for(int i=0; i<sceneDirs.size(); i++) {
			try {
				Files.createDirectories(Paths.get(resultImagesDir + "/"+ sceneDirs.get(i)));
			}catch(Exception e) {
				System.out.println("Could not create directory " + resultImagesDir + "/"+ sceneDirs.get(i));
				e.printStackTrace();
				System.exit(1);
			}
			
			List<String> sceneFiles = listSceneFiles(new File(Paths.get(SCENES+"/"+sceneDirs.get(i)).toString()) );
			for(int j=0; j<sceneFiles.size(); j++) {
				System.out.println("[*] Import de " + sceneFiles.get(j));
				
				try {
					scene = new Scene(Paths.get(SCENES +"/"+ sceneDirs.get(i) + "/" + sceneFiles.get(j)).toString() );
					renderer = new Renderer(scene);
					resultImage = renderer.render();
					resultImageName = Paths.get( resultImagesDir + "/"+ sceneDirs.get(i) + "/" + scene.getOutput()).toString() ;
					saveImage(resultImage, resultImageName);
					System.out.println("[*]Image sauvegardée !  -> " + resultImageName);
				}catch(Exception e) {
					System.out.println("Erreur de génération sur la scène " + sceneFiles.get(j));
					e.printStackTrace();
					continue;
				}
				System.out.println("");
			}
			System.out.println("----------------------------------------------");
		}
		
		System.out.println("###########\n[*] Fin\n###########");
	}
	
	
	private static void processScene(Path sceneFileName) {
		Scene scene;
		Renderer renderer;
		BufferedImage resultImage;
		String resultImageName;
		
		
		try {
			scene = new Scene(sceneFileName.toString());
			renderer = new Renderer(scene);
			resultImage = renderer.render();
			resultImageName = Paths.get( scene.getOutput()).toString() ;
			saveImage(resultImage, resultImageName);
			System.out.println("[*]Image sauvegardée !  -> " + resultImageName);
		}catch(Exception e) {
			System.out.println("Erreur de génération sur la scène " + sceneFileName.toString());
			e.printStackTrace();
		}
	}
	
	
	
	
}
