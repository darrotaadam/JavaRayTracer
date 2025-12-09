package RayTracer.raytracer;

import java.util.Optional;

import RayTracer.geometry.Intersection;
import RayTracer.geometry.Orthonormal;
import RayTracer.geometry.Vector;
import RayTracer.imaging.Color;
import RayTracer.imaging.Pixel;
import RayTracer.imaging.Scene;

/**
 * Principal compartiment du Ray-Tracer à proprement parler.
 * Consitue le lanceur de rayon servant à déterminer la couleur de chaque pixel de l'image.
 */

public class RayTracer {

	Orthonormal space;
	Scene scene;
	
	/**
	 * Instanciation avec la Scene scene désignant la scène de l'image à générer
	 * @param scene Scene
	 */
	public RayTracer(Scene scene) {
		this.scene = scene;
		this.space = new Orthonormal(scene.getCamera().getUpDirection(), scene.getCamera().getPosition(), scene.getCamera().getLooksAt());
	}
	
	/**
	 * Cherche l'intersection la plus proche et calcule la couleur en cumulant la couleur ambiante, la couleur de Lambert et celle de Phong
	 * @param i indice en largeur
	 * @param j indice en hauteur
	 * @return color
	 */
	public Color getPixelColor(int i, int j) {
		/**
		 * Création d'un pixel de taille à déterminer
		 */
		Pixel pixel = getPixelSize(scene.getCamera().getFov(), scene.getWidth(), scene.getHeight());
		
		/**
		 * Calcul du rayon d allant de l'oeil au centre du pixel
		 */
		Vector d = computeD(i, j, pixel);
		
		/**
		 * Création d'un rayon allant de la caméra à la scène dans la direction du vecteur d
		 */
		Ray ray = new Ray(scene.getCamera().getPosition(), d);
		
		/**
		 * On trouve la potentielle intersection la plus proche
		 */
		Optional<Intersection> p = scene.findClosestIntersection(ray);
		if( p.isPresent()) {
			Vector eyeDir = this.scene.getCamera().getPosition().sub(p.get().getPosition()).normalisation();
			
			Color couleurPoint = new Color(this.scene.getAmbient());
			for(int l=0; l<this.scene.getLights().size(); l++) {
				/**
				 * Si la lumière n'est pas bloquée, on cumule les différentes sources de couleur sur le pixel
				 */
				if (! p.get().isShadowed(this.scene.getLights().get(l), this.scene)) {
					couleurPoint = couleurPoint.add(p.get().computeDiffusionLambert(this.scene.getLights().get(l)));	
					couleurPoint = couleurPoint.add(p.get().computeBlinnPhong(this.scene.getLights().get(l), eyeDir));
					couleurPoint = couleurPoint.add(p.get().computeCouleurReflet(eyeDir, this.scene, this.scene.getMaxDepth()));
				}				
			}
			p.get().setColor(couleurPoint);
			return p.get().getColor();
		}
		else {
			return new Color();
		}
	}
	
	/**
	 * Calcul du vecteur direction d allant de la caméra au centre du pixel
	 * @param i indice en largeur du pixel
	 * @param j indice en hauteur du pixel
	 * @param pixel pixel en question
	 * @return Vector
	 */
	private Vector computeD(double i, double j, Pixel pixel) {
		double a = pixel.getWidth() *(i - scene.getWidth()/2 + 0.5) / (scene.getWidth()/2);
		double b = pixel.getHeight() *(j - scene.getHeight()/2 + 0.5) / (scene.getHeight()/2);
		Vector d = space.getU().multByScalar(a).add(space.getV().multByScalar(b)).sub(space.getW()).normalisation();
		return d;
	}
	
	/**
	 * Calcul des dimensions du pixel
	 * @param fov Champ de vision
	 * @param imgWidth Largeur de l'image
	 * @param imgHeight Hauteur de l'image
	 * @return pixel
	 */
	private Pixel getPixelSize(double fov, int imgWidth, int imgHeight) {
		double fovRadient = (fov * Math.PI) / 180;
		double pixelHeight = Math.tan(fovRadient/2);
		double pixelWidth = pixelHeight * ((double)imgWidth / imgHeight);
		return new Pixel(pixelWidth, pixelHeight);
	}
	
	
	
	
}
