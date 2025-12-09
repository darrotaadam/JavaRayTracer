package RayTracer.imaging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


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

/**
 * Parseur pour les fichiers de scène.
 * Instanciée avec le nom du fichier.
 * Le contenu de la classe (attributs) est défini pendant le parsing.
 */
public class SceneFileParser {

	private ArrayList<String> fileContent;

	private List<Light> lights = new ArrayList<Light>();
	
	private List<Shape> shapes = new ArrayList<Shape>();
	
	private Point[] vertexes;
	
	private int height, width;
	
	private String output;
	
	private Camera camera;
	
	private Color ambient = new Color();
	
	private Color lastDiffuse = new Color();	// vont changer pour chaque Shape
	private Color lastSpecular = new Color();	
	private Double lastShininess=0d;
	
	private int maxverts = -1;
	
	private int maxdepth;
	
	
	
	public SceneFileParser(String fileName) throws IOException{
		this.fileContent = openFile(fileName);
		
		extractAll();
	}

	
	
	public List<Light> getLights() {
		return lights;
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Camera getCamera() {
		return camera;
	}

	public Color getAmbient() {
		return ambient;
	}

	public String getOutput() {
		return output;
	}
	
	public int getMaxDepth() {
		return maxdepth;
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
	
	
	
	
	
	/**
	 * Parsing du fichier en important les patterns reconnus dans le swtich/case
	 */
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
				case "shininess":
					setShininess(currentLine);
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
				case "maxdepth":
					setMaxDepth(currentLine);
					break;
					
			}	
			
		}		
	}
	
	
	
	
	
	private void setSize(String parsedLine) {
		try {
			this.width = Integer.parseInt(parsedLine.split(" ")[1]);
			this.height = Integer.parseInt(parsedLine.split(" ")[2]);
		}catch(Exception e) {
			throw new IllegalArgumentException("[!] Erreur lors de la lecture de l'attribut size : " + parsedLine + "\n" + e);
		}	
	}

	private void setOutput(String parsedLine) {
		try {
			this.output = parsedLine.split(" ")[1];
		}catch(Exception e) {
			throw new IllegalArgumentException("[!] Erreur lors de la lecture de l'attribut output : " + parsedLine + "\n" + e);
		}	
	}

	private void setCamera(String parsedLine) {
		try {
			Point position = new Point(
					Double.parseDouble(parsedLine.split(" ")[1]),
					Double.parseDouble(parsedLine.split(" ")[2]),
					Double.parseDouble(parsedLine.split(" ")[3])
					);
			Point looksAt = new Point(
					Double.parseDouble(parsedLine.split(" ")[4]),
					Double.parseDouble(parsedLine.split(" ")[5]),
					Double.parseDouble(parsedLine.split(" ")[6])
					);
			Vector direction = new Vector(
					Double.parseDouble(parsedLine.split(" ")[7]),
					Double.parseDouble(parsedLine.split(" ")[8]),
					Double.parseDouble(parsedLine.split(" ")[9])
					);
			double fov = Double.parseDouble(parsedLine.split(" ")[10]);
			
			this.camera = new Camera(position, looksAt, direction, fov);
			
		}catch(Exception e) {
			throw new IllegalArgumentException("[!] Erreur lors de la lecture de l'attribut Camera : " + parsedLine + "\n" + e);
		}	
	}
	
	
	private void setAmbient(String parsedLine) {
		try {
			this.ambient = new Color(
					Double.parseDouble(parsedLine.split(" ")[1]),
					Double.parseDouble(parsedLine.split(" ")[2]),
					Double.parseDouble(parsedLine.split(" ")[3])
					);
		}catch(Exception e) {
			throw new IllegalArgumentException("[!] Erreur lors de la lecture de l'attribut ambient : " + parsedLine + "\n" + e);
		}	
	}
	
	private void setDiffuse(String parsedLine) {
		try {
			this.lastDiffuse = new Color(
					Double.parseDouble(parsedLine.split(" ")[1]),
					Double.parseDouble(parsedLine.split(" ")[2]),
					Double.parseDouble(parsedLine.split(" ")[3])
					);
		}catch(Exception e) {
			throw new IllegalArgumentException("[!] Erreur lors de la lecture de l'attribut diffuse : " + parsedLine + "\n" + e);
		}
	}
	private void setSpecular(String parsedLine) {
		try {
			this.lastSpecular = new Color(
					Double.parseDouble(parsedLine.split(" ")[1]),
					Double.parseDouble(parsedLine.split(" ")[2]),
					Double.parseDouble(parsedLine.split(" ")[3])
					);
		}catch(Exception e) {
			throw new IllegalArgumentException("[!] Erreur lors de la lecture de l'attribut specular : " + parsedLine + "\n" + e);
		}
	}
	
	private void setShininess(String parsedLine) {
		try {
			this.lastShininess = Double.parseDouble(parsedLine.split(" ")[1]);
		}catch(Exception e) {
			throw new IllegalArgumentException("[!] Erreur lors de la lecture de l'attribut shininess : " + parsedLine + "\n" + e);
		}
	}
	

	
	private void setMaxverts(String parsedLine) {
		try {
			this.maxverts = Integer.parseInt(parsedLine.split(" ")[1]);
			this.vertexes = new Point[this.maxverts];
		}catch(Exception e) {
			throw new IllegalArgumentException("[!] Erreur lors de la lecture de l'attribut maxverts : " + parsedLine + "\n" + e);
		}
	}
	
	
	private void addVertex(String parsedLine) {
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
						Double.parseDouble(parsedLine.split(" ")[1]),
						Double.parseDouble(parsedLine.split(" ")[2]),
						Double.parseDouble(parsedLine.split(" ")[3])
						);
				this.vertexes[vertIndex] = position;
			}
		}catch(Exception e) {
			throw new IllegalArgumentException("[!] Erreur lors de la lecture de l'attribut vertex : " + parsedLine + "\n" + e);
		}
	}
	
	
	private void setDirectionalLight(String parsedLine) {
		try {
			
			Vector direction = new Vector(
					Double.parseDouble(parsedLine.split(" ")[1]),
					Double.parseDouble(parsedLine.split(" ")[2]),
					Double.parseDouble(parsedLine.split(" ")[3]));
			Color couleur = new Color(
					Double.parseDouble(parsedLine.split(" ")[4]),
					Double.parseDouble(parsedLine.split(" ")[5]),
					Double.parseDouble(parsedLine.split(" ")[6]));
			
			this.lights.add( new DirectionalLight( direction, couleur));
		}catch(Exception e) {
			throw new IllegalArgumentException("[!] Erreur lors de la lecture de l'attribut directional : " + parsedLine + "\n" + e);
		}
	}
	
	private void setPointLight(String parsedLine) {
		try {
			
			Point origin = new Point(
					Double.parseDouble(parsedLine.split(" ")[1]),
					Double.parseDouble(parsedLine.split(" ")[2]),
					Double.parseDouble(parsedLine.split(" ")[3]));
			Color couleur = new Color(
					Double.parseDouble(parsedLine.split(" ")[4]),
					Double.parseDouble(parsedLine.split(" ")[5]),
					Double.parseDouble(parsedLine.split(" ")[6]));
			
			this.lights.add( new PointLight( origin, couleur));
		}catch(Exception e) {
			throw new IllegalArgumentException("[!] Erreur lors de la lecture de l'attribut point : " + parsedLine + "\n" + e);
		}
	}
	
	
	
	private void addTri(String parsedLine) {
		try {
			
			if (this.vertexes == null) {
				throw new NullPointerException();
			}
			
			int aIndex = Integer.parseInt(parsedLine.split(" ")[1]);
			int bIndex = Integer.parseInt(parsedLine.split(" ")[2]);
			int cIndex = Integer.parseInt(parsedLine.split(" ")[3]);
			
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
			this.shapes.add(new Triangle(a, b, c, specular, diffuse, this.lastShininess));
		}catch(Exception e) {
			throw new IllegalArgumentException("[!] Erreur lors de la lecture de l'attribut tri : " + parsedLine + "\n" + e);
		}
	}

	
	private void addSphere(String parsedLine) {
		try {
			
			Point center = new Point(
					Double.parseDouble(parsedLine.split(" ")[1]),
					Double.parseDouble(parsedLine.split(" ")[2]),
					Double.parseDouble(parsedLine.split(" ")[3])
					);
			double radius = Double.parseDouble(parsedLine.split(" ")[4]);
			
			
			Color specular = new Color(this.lastSpecular);
			Color diffuse = new Color(this.lastDiffuse);
			
			if(this.ambient != null) {
				if(this.ambient.getR()+diffuse.getR()>1.0 || this.ambient.getG()+diffuse.getG()>1.0 || this.ambient.getB()+diffuse.getB()>1.0) {
					throw new IllegalArgumentException("[!] Erreur : les composantes de la somme ambient+diffuse ne doit pas excéder 1.");
				}
			}
			this.shapes.add(new Sphere(center, radius, specular, diffuse, this.lastShininess));
		}catch(Exception e) {
			throw new IllegalArgumentException("[!] Erreur lors de la lecture de l'attribut sphere : " + parsedLine + "\n" + e);
		}
	}
	
	
	
	private void addPlane(String parsedLine) {
		try {
			
			Point position = new Point(
					Double.parseDouble(parsedLine.split(" ")[1]),
					Double.parseDouble(parsedLine.split(" ")[2]),
					Double.parseDouble(parsedLine.split(" ")[3])
					);
			Vector normale = new Vector(
					Double.parseDouble(parsedLine.split(" ")[4]),
					Double.parseDouble(parsedLine.split(" ")[5]),
					Double.parseDouble(parsedLine.split(" ")[6])
					);
			
			Color specular = new Color(this.lastSpecular);
			Color diffuse = new Color(this.lastDiffuse);
			
			if(this.ambient != null) {
				if(this.ambient.getR()+diffuse.getR()>1.0 || this.ambient.getG()+diffuse.getG()>1.0 || this.ambient.getB()+diffuse.getB()>1.0) {
					throw new IllegalArgumentException("[!] Erreur : les composantes de la somme ambient+diffuse ne doit pas excéder 1.");
				}
			}
			this.shapes.add(new Plane(position, normale, specular, diffuse, this.lastShininess));
		}catch(Exception e) {
			throw new IllegalArgumentException("[!] Erreur lors de la lecture de l'attribut tri : " + parsedLine + "\n" + e);
		}
	}
	
	
	private void setMaxDepth(String parsedLine) {
		try {
			this.maxdepth = Integer.parseInt(parsedLine.split(" ")[1]);
		}catch(Exception e) {
			throw new IllegalArgumentException("[!] Erreur lors de la lecture de l'attribut maxdepth : " + parsedLine + "\n" + e);
		}
	}
	
	
	
}

	
	
	

	
	





