package RayTracer;

import java.awt.image.BufferedImage;

public class ImageComparator {

	
	public ImageComparator() {
		
	}
	
	public int getDifferentPixels(BufferedImage image1, BufferedImage image2) {
		// parcourt les images en largeur/longueur selon l'image la plus petite
		
		int minHeight = min(image1.getHeight(), image2.getHeight());
		int minWidth = min(image1.getWidth(), image2.getWidth());
		
		int differentPixels = 0;
		for (int wIndex=0; wIndex<minWidth ; wIndex++) {
			for (int hIndex=0; hIndex<minHeight ; hIndex++) {
				
				
			}
		}
		
		
	}
	
	
	private static int min(int a, int b) {
		int min;
		if (a>b) {
			min = b;
		}
		else {
			min = a; 
		}
		return min;
	}
	
}
