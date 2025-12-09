package RayTracer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import ImageComparator.ImageComparator;
import RayTracer.raytracer.Renderer;
import RayTracer.imaging.Scene;

public class Main {

	public static void main(String[] args) {
		
		if(args.length == 0) {
			System.out.println("Usage: java -jar raytracer.jar <file.scene | --all>");
			System.out.println("[*] --all : Generate images using .scene and .test files in the TestScenes directory. Only usable from the root directory of the git repository");
			System.out.println("[*] --compare image1 image2 : Compare image1 and image2, count different pixels, and generate a differential image imageDiff.png");
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
		    else if("--compare".equals(args[0])) {
		    	System.out.println("Usage: java -jar raytracer.jar <file.scene | --all>");
				System.out.println("[*] --all : Generate images using .scene and .test files in the TestScenes directory. Only usable from the root directory of the git repository");
				System.out.println("[*] --compare image1 image2 : Compare image1 and image2, count different pixels, and generate a differential image imageDiff.png");
				System.exit(1);
		    }
		    
			else {
				System.out.println("[*] Starting to render scene "+ args[0]);
				processScene(Paths.get(args[0]));
			}
		}
		
		
		
		
		if( args.length > 1) {
			
			if( args.length ==3 && "--compare".equals(args[0])) {
				try {
					Path image1 = Paths.get(args[1]);
					Path image2 = Paths.get(args[2]);					
					compareImages(image1, image2);
				}catch(InvalidPathException e) {
					System.out.println("[!] Nom de fichier(s) incorrect(s)");
					e.printStackTrace();
					System.exit(1);
				}
					
			}
			else {
				
				System.out.println("[*] Second argument " + args[1] + "not understandable");
				System.out.println("Usage: java -jar raytracer.jar <file.scene | --all>");
				System.out.println("[*] --all : Generate images using .scene and .test files in the TestScenes directory. Only usable from the root directory of the git repository");
				System.out.println("[*] --compare image1 image2 : Compare image1 and image2, count different pixels, and generate a differential image imageDiff.png");
			}
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
	    List<String> sceneFiles = new ArrayList<String>();
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
		List<String> sceneDirs = new ArrayList<String>();	// Répertoires de scènes à traiter
		
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
	
	
	private static void compareImages(Path image1, Path image2) {

		BufferedImage img1 = openImage(image1);
		BufferedImage img2 = openImage(image1);
		
		System.out.println("Images chargées avec succès");
		System.out.println("Image 1: " + image1.getFileName().toString() + " (" + img1.getWidth() + "x" + img1.getHeight() + ")");
        System.out.println("Image 2: " + image1.getFileName().toString() + " (" + img2.getWidth() + "x" + img2.getHeight() + ")");
		
		ImageComparator comparateur = new ImageComparator();
		int differentPixels = comparateur.getDifferentPixels(img1, img2);
        
        System.out.println("Nombre de pixels différents : " + differentPixels);
		
        BufferedImage imageDifferentielle = comparateur.imageDifferencielle(img1, img2);
        
        String savedImage = saveImage(imageDifferentielle, Paths.get(System.getProperty("user.dir")+"/imageDiff.png").toString());
       
        if (savedImage != null) 
        	System.out.println("Image différentielle créée à " + savedImage);
	}
	
	
	
	
	private static BufferedImage openImage(Path filePath) {
        try (InputStream stream = Files.newInputStream(filePath)){
        	BufferedImage image = ImageIO.read(stream);
        	return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
            
        }
        
	}
	

	
}
