package ImageComparator;

import java.awt.image.BufferedImage;

public class ImageComparator {

	
	public ImageComparator() {
		
	}
	
	public int getDifferentPixels(BufferedImage image1, BufferedImage image2) {
		
		int minHeight = min(image1.getHeight(), image2.getHeight());
		int minWidth = min(image1.getWidth(), image2.getWidth());
		
		int maxHeight = max(image1.getHeight(), image2.getHeight());
		int maxWidth = max(image1.getWidth(), image2.getWidth());
		
		int differentPixels = 0;
		
		for (int x=0; x<minWidth ; x++) {
			for (int y=0; y<minHeight ; y++) {
				if( image1.getRGB(x, y) != image2.getRGB(x, y) )	differentPixels++ ;
			}
		}
		
		differentPixels += maxHeight*maxWidth - minHeight*minWidth;
		
		return differentPixels;
	}
	
	
	
	public BufferedImage imageDifferencielle(BufferedImage image1, BufferedImage image2) {
		int minHeight = min(image1.getHeight(), image2.getHeight());
		int minWidth = min(image1.getWidth(), image2.getWidth());
		
		int maxHeight = max(image1.getHeight(), image2.getHeight());
		int maxWidth = max(image1.getWidth(), image2.getWidth());
		
		
		BufferedImage imageDifferentielle = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_3BYTE_BGR);
		
		
		for (int x=0; x<minWidth ; x++) {
			for (int y=0; y<minHeight ; y++) {
				imageDifferentielle.setRGB(x, y, image1.getRGB(x, y)-image2.getRGB(x, y));
			}
		}
		/*
		for (int x=0; x<minWidth ; x++) {
			for (int y=0; y<minHeight ; y++) {
				if(Math.abs(image1.getRGB(x, y)-image2.getRGB(x, y)) > 0d) {
					imageDifferentielle.setRGB(x, y, 255);
				}else {
					imageDifferentielle.setRGB(x, y, 0);
				}
			}
		}*/
		
		
		// étend aux dimensions de l'image la plus grande
		 for (int y = minHeight; y < maxHeight; y++) {
		        for (int x = 0; x < maxWidth; x++) {
		        	imageDifferentielle.setRGB(x, y, -1);
		        }
		    }
		 for (int y = 0; y < minHeight; y++) {
		        for (int x = minWidth; x < maxWidth; x++) {
		        	imageDifferentielle.setRGB(x, y, -1);
		        }
		    }
		
		return imageDifferentielle;
	}
	
	
	
	
	private static int min(int a, int b) {
		int min;
		if (a>b) {
			min = b;
		}else {
			min = a; 
		}
		return min;
	}
	
	
	private static int max(int a, int b) {
		int max;
		if(a>b) {
			max = a;
		}else {
			max = b;
		}
		return max;
	}
	
	
}
