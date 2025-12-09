package RayTracer.geometry;

import java.util.Optional;

import RayTracer.imaging.Scene;
import RayTracer.imaging.Color;
import RayTracer.raytracer.DirectionalLight;
import RayTracer.raytracer.Light;
import RayTracer.raytracer.PointLight;
import RayTracer.raytracer.Ray;

/**
 * Décrit le point d'intersection entre un rayon Ray, et une Shape.
 */
public class Intersection {

	private double distance;
	private Point position;
	private Vector normale;
	private Shape shape;
	private Color color;
	
	/**
	 * Crée un point d'intersection
	 * @param distance Double distance entre la source du Ray et le point d'intersection
	 * @param position Point décrivant la position de l'intersection
	 * @param normale Vecteur normale à la surface touchée
	 * @param shape Shape touchée par le Ray à l'intersection
	 */
	public Intersection(double distance, Point position, Vector normale, Shape shape) {
		this.distance = distance;
		this.position = position;
		this.normale = normale;
		this.shape = shape;
	}
	/*Getters, Setters*/
	/**
	 * Getter pour la distance
	 * @return distance distance à la source du Ray
	 */
	public double getDistance() {
		return this.distance;
	}
	/**
	 * Getter pour la Couleur
	 * @return color couleur à l'intersection
	 */
	public Color getColor() {
		return this.color;
	}
	/**
	 * Setter pour la couleur
	 * @param couleur couleur à l'intersection
	 */
	public void setColor(Color couleur) { 
        this.color = couleur;
    }
	/**
	 * Getter pour la position
	 * @return position position de l'intersection
	 */
	public Point getPosition() {
		return position;
	}

	
	/**
	 * Calcule la couleur en utilisant la formule de Lambert
	 * @param light lumière source
	 * @return Color
	 */
	public Color computeDiffusionLambert(Light light) {
		Vector lightDirection = getLightDirection(light);
		return light.getColor()
		        .schurProduct(this.shape.getDiffuse())
		        .multiply(Math.max((double) 0, this.normale.produitScalaire(lightDirection)));
	}
	
	
	
	/**
	 * Calcule la couleur en utilisant la formule de Blinn Phong
	 * @param light	lumière source
	 * @param eyeDir vecteur direction du regard
	 * @return Color
	 */
	public Color computeBlinnPhong(Light light, Vector eyeDir) {
		Vector lightDirection = getLightDirection(light);
		Vector h = lightDirection.add(eyeDir).normalisation();
		return this.shape.getSpecular()
				.schurProduct(light.getColor())
				.multiply(Math.pow(Math.max(0d, h.produitScalaire(this.normale)), this.shape.getShininess()));
	}
	
	

	/**
	 * Calcule la couleur au point d'instersection en se basant sur les reflets.
	 * Ne fonctionne pas correctement.
	 * @param eyeDir vecteur direction du regard
	 * @param scene scène actuelle
	 * @param depth profondeur max des reflets dans la récursion
	 * @return Color
	 */
	public Color computeCouleurReflet(Vector eyeDir, Scene scene, int depth) {
		/**
		 * Condition d'arrêt de la récursion
		 */
		if (depth <= 0) {
			return new Color();
		}
		
		/**
		 * Calcul du vecteur r correspondant à la direction réfléchie du regard.
		 */
		Vector dirReflechie = eyeDir.add( 
				this.normale.multByScalar(this.normale.produitScalaire(eyeDir.multByScalar(-1.0)) *2.0 )	
		);
		/**
		 * Crée un rayon dans la direction reflechi du regard
		 */
		Ray lumiereReflechie = new Ray(
				dirReflechie.multByScalar(1e-6d).add(this.position), 
				dirReflechie
		);
		/**
		 * Cherche une source de reflet la plus proche.
		 */
		Optional<Intersection> sourceReflet = scene.findClosestIntersection(lumiereReflechie);
		
		/**
		 * Si pas d'objet à refléter, retourne du noir
		 */
		if (sourceReflet.isEmpty()) {
			return new Color();	
		}
		
		/**
		 * Détermine le prochain lancer de rayon, et ajoute la couleur du reflet trouvé à celle de base
		 */
	    Intersection p = sourceReflet.get();
	    Vector nextEyeDir = dirReflechie.multByScalar(-1.0);

	    Color couleurReflet = new Color(scene.getAmbient());
	    for (Light light : scene.getLights()) {       
	            couleurReflet = couleurReflet.add(p.computeBlinnPhong(light, nextEyeDir));
	    }

	    Color couleurRefletRecursif =
	        p.computeCouleurReflet(nextEyeDir, scene, depth - 1);

	    return this.shape.getSpecular().schurProduct(
	        couleurReflet.add(couleurRefletRecursif)
	    );
		
	}
	
	
	
	/**
	 * Détermine si le point d'intersection est ombragé par une Shape se trouvant entre la light et lui.
	 * @param light source de lumière
	 * @param scene scène actuelle
	 * @return boolean
	 */
	public boolean isShadowed(Light light, Scene scene) {
		Vector lightDirection = getLightDirection(light);
		Ray shadowRay = new Ray(lightDirection.multByScalar(1e-6d).add(this.position), lightDirection);
		Optional<Intersection> shadowSource = scene.findClosestIntersection(shadowRay);
		return shadowSource.isPresent();
	}
	


	
	
	
	
	private Vector getLightDirection(Light light) {
	    if (light instanceof DirectionalLight dir) {
	        return dir.getDirection().normalisation();
	    }
	    if (light instanceof PointLight point) {
	        return point.getOrigin().sub(this.position).normalisation();
	    }
	    return new Vector(0,0,0);
	}
	
	
	
	private Vector getDirectionToLight(Light light) {
	    if (light instanceof DirectionalLight dir) {
	        return dir.getDirection().multByScalar(-1d).normalisation();
	    }
	    if (light instanceof PointLight point) {
	        return point.getOrigin().sub(this.position).multByScalar(-1d).normalisation();
	    }
	    return new Vector(0,0,0);
	}
	
	
}
