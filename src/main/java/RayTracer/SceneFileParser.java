package RayTracer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import RayTracer.geometry.Plane;
import RayTracer.geometry.Point;
import RayTracer.geometry.Shape;
import RayTracer.geometry.Sphere;
import RayTracer.geometry.Triangle;
import RayTracer.geometry.Vector;
import RayTracer.imaging.Color;
import RayTracer.raytracer.DirectionalLight;
import RayTracer.raytracer.Light;
import RayTracer.raytracer.PointLight;


public class SceneFileParser {

	private ArrayList<String> fileContent;

	private List<Light> lights;
	
	private List<Shape> shapes;
	
	private Point[] vertexes;
	
	private int height, width;
	
	private String output;
	
	private Camera camera;
	
	private Color ambient;
	
	private Color lastDiffuse;	// vont changer pour chaque Shape
	private Color lastSpecular;	
	
	private int maxverts = -1;
	
	
	
	
	public SceneFileParser(String fileName) throws IOException{
		this.fileContent = openFile(fileName);
		
		this.shapes = new ArrayList<Shape>();
		this.lights = new ArrayList<Light>();
		
		
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
			
			if (currentLine.isEmpty() || currentLine.startsWith("#"))
			    continue;
			
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
				case "directional":
					setDirectionalLight(currentLine);
					break;
				case "point":
					setPointLight(currentLine);
					break;
				//formes
				case "maxverts":
					setMaxverts(currentLine);
					break;
				case "vertex":
					addVertex(currentLine);
					break;
				case "tri":
					addTri(currentLine);
					break;
				case "sphere":
					addSphere(currentLine);
					break;
				case "plane":
					addPlane(currentLine);
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
			this.maxverts = Integer.parseInt(sizeLine.split(" ")[1]);
			this.vertexes = new Point[this.maxverts];
		}catch(Exception e) {
			throw new NoSuchElementException("[!] Erreur lors de la lecture de l'attribut maxverts : " + sizeLine + "\n" + e);
		}
	}
	
	
	private void addVertex(String sizeLine) {
		try {
			if(this.maxverts < 0) {
				throw new IllegalArgumentException("Il est obligatoire de déclarer maxverts avant le/les vertex.");
			}
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
	
	
	private void setDirectionalLight(String sizeLine) {
		try {
			
			Vector direction = new Vector(
					Double.parseDouble(sizeLine.split(" ")[1]),
					Double.parseDouble(sizeLine.split(" ")[2]),
					Double.parseDouble(sizeLine.split(" ")[3]));
			Color couleur = new Color(
					Double.parseDouble(sizeLine.split(" ")[4]),
					Double.parseDouble(sizeLine.split(" ")[5]),
					Double.parseDouble(sizeLine.split(" ")[6]));
			
			this.lights.add( new DirectionalLight( direction, couleur));
		}catch(Exception e) {
			throw new NoSuchElementException("[!] Erreur lors de la lecture de l'attribut directional : " + sizeLine + "\n" + e);
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
			throw new NoSuchElementException("[!] Erreur lors de la lecture de l'attribut point : " + sizeLine + "\n" + e);
		}
	}
	
	
	
	private void addTri(String sizeLine) {
		try {
			
			if (this.vertexes == null) {
				throw new NullPointerException();
			}
			
			int aIndex = Integer.parseInt(sizeLine.split(" ")[1]);
			int bIndex = Integer.parseInt(sizeLine.split(" ")[2]);
			int cIndex = Integer.parseInt(sizeLine.split(" ")[3]);
			
			if (this.maxverts < 0) {
				throw new IllegalArgumentException("[!] La valeur de maxverts doit être définie avant d'utiliser/déclarer des vertex");
			}
			if( (aIndex <0 || aIndex >= this.maxverts) 		||	 	(bIndex <0 || bIndex >= this.maxverts) 	|| 		(cIndex<0 || cIndex >= this.maxverts)) { 
				throw new IndexOutOfBoundsException("L'indice du vortex sélectioné doit être supérieur à 0 et inférieur à maxverts . "); 
			}
			Point a = new Point(	this.vertexes[	aIndex	]		) ;
			Point b = new Point(	this.vertexes[	bIndex	]		) ;
			Point c = new Point(	this.vertexes[	cIndex	]		) ;
			
			Color specular = new Color(this.lastSpecular);
			Color diffuse = new Color(this.lastDiffuse);
			
			if(this.ambient != null) {
				if(this.ambient.getR()+diffuse.getR()>1.0 || this.ambient.getG()+diffuse.getG()>1.0 || this.ambient.getB()+diffuse.getB()>1.0) {
					throw new IllegalArgumentException("[!] Erreur : les composantes de la somme ambient+diffuse ne doit pas excéder 1.");
				}
			}
			this.shapes.add(new Triangle(a, b, c, specular, diffuse));
		}catch(Exception e) {
			throw new NoSuchElementException("[!] Erreur lors de la lecture de l'attribut tri : " + sizeLine + "\n" + e);
		}
	}

	
	private void addSphere(String sizeLine) {
		try {
			
			Point center = new Point(
					Double.parseDouble(sizeLine.split(" ")[1]),
					Double.parseDouble(sizeLine.split(" ")[2]),
					Double.parseDouble(sizeLine.split(" ")[3])
					);
			double radius = Double.parseDouble(sizeLine.split(" ")[4]);
			
			
			Color specular = new Color(this.lastSpecular);
			Color diffuse = new Color(this.lastDiffuse);
			
			if(this.ambient != null) {
				if(this.ambient.getR()+diffuse.getR()>1.0 || this.ambient.getG()+diffuse.getG()>1.0 || this.ambient.getB()+diffuse.getB()>1.0) {
					throw new IllegalArgumentException("[!] Erreur : les composantes de la somme ambient+diffuse ne doit pas excéder 1.");
				}
			}
			this.shapes.add(new Sphere(center, radius, specular, diffuse));
		}catch(Exception e) {
			throw new NoSuchElementException("[!] Erreur lors de la lecture de l'attribut tri : " + sizeLine + "\n" + e);
		}
	}
	
	
	
	private void addPlane(String sizeLine) {
		try {
			
			Point position = new Point(
					Double.parseDouble(sizeLine.split(" ")[1]),
					Double.parseDouble(sizeLine.split(" ")[2]),
					Double.parseDouble(sizeLine.split(" ")[3])
					);
			Vector normale = new Vector(
					Double.parseDouble(sizeLine.split(" ")[4]),
					Double.parseDouble(sizeLine.split(" ")[5]),
					Double.parseDouble(sizeLine.split(" ")[6])
					);
			
			Color specular = new Color(this.lastSpecular);
			Color diffuse = new Color(this.lastDiffuse);
			
			if(this.ambient != null) {
				if(this.ambient.getR()+diffuse.getR()>1.0 || this.ambient.getG()+diffuse.getG()>1.0 || this.ambient.getB()+diffuse.getB()>1.0) {
					throw new IllegalArgumentException("[!] Erreur : les composantes de la somme ambient+diffuse ne doit pas excéder 1.");
				}
			}
			this.shapes.add(new Plane(position, normale, specular, diffuse));
		}catch(Exception e) {
			throw new NoSuchElementException("[!] Erreur lors de la lecture de l'attribut tri : " + sizeLine + "\n" + e);
		}
	}
	
	
	
}

	
	
	

	
	





