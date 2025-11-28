package RayTracer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import RayTracer.geometry.Point;
import RayTracer.geometry.Vector;
import RayTracer.imaging.Color;
import RayTracer.raytracer.DirectionnalLight;
import RayTracer.raytracer.Light;
import RayTracer.raytracer.PointLight;


public class SceneFileParser {

	private ArrayList<String> fileContent;

	private List<Light> lights;
	
	private Point[] vertexes;
	
	private int height, width;
	
	private String output;
	
	private Camera camera;
	
	private Color ambient;
	
	private Color lastDiffuse;	// vont changer pour chaque Shape
	private Color lastSpecular;	
	
	private int maxverts;
	
	
	
	
	public SceneFileParser(String fileName) throws IOException{
		this.fileContent = openFile(fileName);
		
		extractAll();
	}

	
	
	
	
	private ArrayList<String> openFile(String fileName) throws IOException {
		
		Path filePath = Paths.get(fileName);
		try {
			ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(filePath);
			return lines;
		} catch (IOException e) {
			throw new IOException("Failed to open file : " + fileName) ;
		}
	}
	
	
	
	private void extractAll() {
		String currentLine;
		for(int i=0 ; i < this.fileContent.size(); i++) {
			currentLine = this.fileContent.get(i).trim();
			
			switch( currentLine.split(" ")[0]) {
				//scène
				case "size":
					setSize(currentLine);
					break;
				case "output":
					setOutput(currentLine);
					break;
				case "camera":
					setCamera(currentLine);
					break;
				// couleurs
				case "ambient":
					setAmbient(currentLine);
					break;
				case "diffuse":
					setDiffuse(currentLine);
					break;
				case "specular":
					setSpecular(currentLine);
					break;
				// lumieres
				case "directionnal":
					setDirectionnalLight(currentLine);
					break;
				case "point":
					setPointLight(currentLine);
					break;
				//formes
				case "maxverts":
					setMaxverts(currentLine);
					break;
				case "vertex":
					setMaxverts(currentLine);
					break;
				
			}	
			
		}		
	}
	
	
	
	
	
	private void setSize(String sizeLine) {
		try {
			this.width = Integer.parseInt(sizeLine.split(" ")[1]);
			this.height = Integer.parseInt(sizeLine.split(" ")[2]);
		}catch(Exception e) {
			throw new NoSuchElementException("[!] Erreur lors de la lecture de l'attribut size : " + sizeLine + "\n" + e);
		}	
	}

	private void setOutput(String sizeLine) {
		try {
			this.output = sizeLine.split(" ")[1];
		}catch(Exception e) {
			throw new NoSuchElementException("[!] Erreur lors de la lecture de l'attribut output : " + sizeLine + "\n" + e);
		}	
	}

	private void setCamera(String sizeLine) {
		try {
			Point position = new Point(
					Double.parseDouble(sizeLine.split(" ")[1]),
					Double.parseDouble(sizeLine.split(" ")[2]),
					Double.parseDouble(sizeLine.split(" ")[3])
					);
			Point looksAt = new Point(
					Double.parseDouble(sizeLine.split(" ")[4]),
					Double.parseDouble(sizeLine.split(" ")[5]),
					Double.parseDouble(sizeLine.split(" ")[6])
					);
			Vector direction = new Vector(
					Double.parseDouble(sizeLine.split(" ")[7]),
					Double.parseDouble(sizeLine.split(" ")[8]),
					Double.parseDouble(sizeLine.split(" ")[9])
					);
			double fov = Double.parseDouble(sizeLine.split(" ")[10]);
			
			this.camera = new Camera(position, looksAt, direction, fov);
			
		}catch(Exception e) {
			throw new NoSuchElementException("[!] Erreur lors de la lecture de l'attribut Camera : " + sizeLine + "\n" + e);
		}	
	}
	
	
	private void setAmbient(String sizeLine) {
		try {
			this.ambient = new Color(
					Double.parseDouble(sizeLine.split(" ")[1]),
					Double.parseDouble(sizeLine.split(" ")[2]),
					Double.parseDouble(sizeLine.split(" ")[3])
					);
		}catch(Exception e) {
			throw new NoSuchElementException("[!] Erreur lors de la lecture de l'attribut ambient : " + sizeLine + "\n" + e);
		}	
	}
	
	private void setDiffuse(String sizeLine) {
		try {
			this.lastDiffuse = new Color(
					Double.parseDouble(sizeLine.split(" ")[1]),
					Double.parseDouble(sizeLine.split(" ")[2]),
					Double.parseDouble(sizeLine.split(" ")[3])
					);
		}catch(Exception e) {
			throw new NoSuchElementException("[!] Erreur lors de la lecture de l'attribut diffuse : " + sizeLine + "\n" + e);
		}
	}
	private void setSpecular(String sizeLine) {
		try {
			this.lastSpecular = new Color(
					Double.parseDouble(sizeLine.split(" ")[1]),
					Double.parseDouble(sizeLine.split(" ")[2]),
					Double.parseDouble(sizeLine.split(" ")[3])
					);
		}catch(Exception e) {
			throw new NoSuchElementException("[!] Erreur lors de la lecture de l'attribut specular : " + sizeLine + "\n" + e);
		}
	}
	
	

	
	private void setMaxverts(String sizeLine) {
		try {
			this.maxverts = Integer.parseInt(sizeLine.split("")[1]);
			this.vertexes = new Point[this.maxverts];
		}catch(Exception e) {
			throw new NoSuchElementException("[!] Erreur lors de la lecture de l'attribut maxverts : " + sizeLine + "\n" + e);
		}
	}
	
	
	private void addVertex(String sizeLine) {
		try {
			int vertIndex=0;
			while(vertIndex < this.maxverts && this.vertexes[vertIndex]!= null) {
				vertIndex++;
			}
			if(vertIndex < this.maxverts) {
				Point position = new Point(
						Double.parseDouble(sizeLine.split(" ")[1]),
						Double.parseDouble(sizeLine.split(" ")[2]),
						Double.parseDouble(sizeLine.split(" ")[3])
						);
				this.vertexes[vertIndex] = position;
			}
		}catch(Exception e) {
			throw new NoSuchElementException("[!] Erreur lors de la lecture de l'attribut vertex : " + sizeLine + "\n" + e);
		}
	}
	
	
	private void setDirectionnalLight(String sizeLine) {
		try {
			
			Vector direction = new Vector(
					Double.parseDouble(sizeLine.split(" ")[1]),
					Double.parseDouble(sizeLine.split(" ")[2]),
					Double.parseDouble(sizeLine.split(" ")[3]));
			Color couleur = new Color(
					Double.parseDouble(sizeLine.split(" ")[4]),
					Double.parseDouble(sizeLine.split(" ")[5]),
					Double.parseDouble(sizeLine.split(" ")[6]));
			
			this.lights.add( new DirectionnalLight( direction, couleur));
		}catch(Exception e) {
			throw new NoSuchElementException("[!] Erreur lors de la lecture de l'attribut directionnal : " + sizeLine + "\n" + e);
		}
	}
	
	private void setPointLight(String sizeLine) {
		try {
			
			Point origin = new Point(
					Double.parseDouble(sizeLine.split(" ")[1]),
					Double.parseDouble(sizeLine.split(" ")[2]),
					Double.parseDouble(sizeLine.split(" ")[3]));
			Color couleur = new Color(
					Double.parseDouble(sizeLine.split(" ")[4]),
					Double.parseDouble(sizeLine.split(" ")[5]),
					Double.parseDouble(sizeLine.split(" ")[6]));
			
			this.lights.add( new PointLight( origin, couleur));
		}catch(Exception e) {
			throw new NoSuchElementException("[!] Erreur lors de la lecture de l'attribut directionnal : " + sizeLine + "\n" + e);
		}
	}
	
	
	
	
}

	
	
	

	
	





