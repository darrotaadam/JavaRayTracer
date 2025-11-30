package RayTracer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import RayTracer.geometry.Point;
import RayTracer.geometry.Vector;

public class testPoint {
	
	@Test
	void testSub(){
		Point point1 = new Point(10, 26, 30);
		Point point2 = new Point(43, -26, 1);
		Vector difference = point1.sub(point2);
		
		assertThat(difference.getX())
		.as("La différence de 10 et 43 est -33.")
		.isEqualTo(-33.0);
		assertThat(difference.getY())
		.as("La différence de 26 et -26 est 0.")
		.isEqualTo(52.0);
		assertThat(difference.getZ())
		.as("La différence de 30 et 1 est 29.")
		.isEqualTo(29.0);
	}
	
	@Test
	void testMultByScalar() {
		Point point1 = new Point(10,26,30);
		double scalar1 = 3.0;
		Point produit1 = point1.multByScalar(scalar1);
		
		assertThat(produit1.getX())
		.as("10 * 3.0 = 30")
		.isEqualTo(30.0);
		assertThat(produit1.getY())
		.as("26 * 3.0 = 78.0")
		.isEqualTo(78.0);
		assertThat(produit1.getZ())
		.as("30 * 3.0 = 90.0")
		.isEqualTo(90.0);
		
		
		Point point2 = new Point(43,-26,1);
		double scalar2 = -8.5;
		Point produit2 = point2.multByScalar(scalar2);
		
		assertThat(produit2.getX())
		.as("43 * -8.5 = -365.5")
		.isEqualTo(-365.5);
		assertThat(produit2.getY())
		.as("-26 * -8.5 = 221.0")
		.isEqualTo(221.0);
		assertThat(produit2.getZ())
		.as("1 * -8.5 = -8.5")
		.isEqualTo(-8.5);
	}
	
	
	@Test
	void testEquals() {
		Point point1 = new Point(1, 1, 1);
		Point point1bis = point1;
		Point point2 = new Point(43, -26, 1);
		Point point3 = new Point(-1, -1, -1);
		Point point4 = new Point(0, 0, 0);
		Point point5 = new Point(1, 1, 1);
		
		assertThat(point1.equals(point1bis))
		.as("les Point (1,1,1) et (1,1,1) sont sont  égaux")
		.isEqualTo(true);
		
		assertThat(point1.equals(point2))
		.as("les Point (1,1,1) et (43,-26,1) ne sont pas égaux")
		.isEqualTo(false);

		assertThat(point1.equals(point3))
		.as("les Point (1,1,1) et (-1, -1, -1) ne sont pas égaux")
		.isEqualTo(false);

		assertThat(point1.equals(point4))
		.as("les Point (1,1,1) et (0, 0, 0) ne sont pas égaux")
		.isEqualTo(false);
		
		assertThat(point1.equals(point5))
		.as("les Point (1,1,1) et (1, 1, 1) sont égaux")
		.isEqualTo(true);
	}
	
	
	
	
}
