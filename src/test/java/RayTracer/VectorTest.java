package RayTracer;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.offset;
import org.junit.jupiter.api.Test;

public class VectorTest {
	
	@Test
	void testAdd() {
		Vector vec1 = new Vector(10, 26, 30);
		Vector vec2 = new Vector(43, -26, 1);
		Vector sum = vec1.add(vec2);
		
		assertThat(sum.getX())
		.as("La somme de 10 et 43 est 53.")
		.isEqualTo(53.0);
		assertThat(sum.getY())
		.as("La somme de 26 et -26 est 0.")
		.isEqualTo(0.0);
		assertThat(sum.getZ())
		.as("La somme de 30 et 1 est 31.")
		.isEqualTo(31.0);
	}
	
	@Test
	void testSub(){
		Vector vec1 = new Vector(10, 26, 30);
		Vector vec2 = new Vector(43, -26, 1);
		Vector sum = vec1.sub(vec2);
		
		assertThat(sum.getX())
		.as("La différence de 10 et 43 est -33.")
		.isEqualTo(-33.0);
		assertThat(sum.getY())
		.as("La différence de 26 et -26 est 0.")
		.isEqualTo(52.0);
		assertThat(sum.getZ())
		.as("La différence de 30 et 1 est 29.")
		.isEqualTo(29.0);
	}
	
	@Test
	void testMultByScalar() {
		Vector vec1 = new Vector(10,26,30);
		double scalar1 = 3.0;
		Vector produit1 = vec1.multByScalar(scalar1);
		
		assertThat(produit1.getX())
		.as("10 * 3.0 = 30")
		.isEqualTo(30.0);
		assertThat(produit1.getY())
		.as("26 * 3.0 = 78.0")
		.isEqualTo(78.0);
		assertThat(produit1.getZ())
		.as("30 * 3.0 = 90.0")
		.isEqualTo(90.0);
		
		
		Vector vec2 = new Vector(43,-26,1);
		double scalar2 = -8.5;
		Vector produit2 = vec2.multByScalar(scalar2);
		
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
	void testProduitScalaire() {
		Vector vec1 = new Vector(10, 26, 30);
		Vector vec2 = new Vector(43, -26, 1);
		Vector vec3 = new Vector(34.3, -25, -1);
		
		assertThat(vec1.produitScalaire(vec2))
		.as("(10,26,30).(43, -26, 1) = -216 ")
		.isEqualTo(-216);
		
		assertThat(vec1.produitScalaire(vec3))
		.as("(10,26,30).(34.3, -25, -1) = -216 ")
		.isEqualTo(-337);
		
		assertThat(vec2.produitScalaire(vec3))
		.as("(43,-26,1).(34.3, -25, -1) = 2123.9 ")
		.isCloseTo(2123.9,offset(0.1));
	}
	
	
	@Test
	void testProduitVectoriel() {
		Vector vec1 = new Vector(10, 26, 30);
		Vector vec2 = new Vector(43, -26, 1);
		Vector prodVec = vec1.produitVectoriel(vec2);
		
		assertThat(prodVec.getX())
		.as("resultat.x = 806")
		.isEqualTo(806);
		
		assertThat(prodVec.getY())
		.as("resultat.y = 1280")
		.isEqualTo(1280);
		
		assertThat(prodVec.getZ())
		.as("resultat.z = -1378")
		.isEqualTo(-1378);
	}
	
	@Test
	void testLongueur() {
		Vector vec1 = new Vector(10, 26, 30);
		Vector vec2 = new Vector(43, -26, 1);
		
		assertThat(vec1.length())
		.as("||(10,26,30)|| ~= 40.938978")
		.isCloseTo(40.938, offset(0.1));
		
		assertThat(vec2.length())
		.as("||(43,-26,1)|| ~= 50.259327")
		.isCloseTo(50.259, offset(0.1));
	}
	
	@Test
	void testNormalisation() {
		Vector vec1 = new Vector(1, 1, 1);
		Vector vec2 = new Vector(43, -26, 1);
		
		Vector norm1 = vec1.normalisation();
		assertThat(norm1.getX())
		.as("norm1.x ~= 0.577350")
		.isCloseTo(0.577350, offset(0.01));
		
		assertThat(norm1.getY())
		.as("norm1.x ~= 0.577350")
		.isCloseTo(0.577350, offset(0.01));
		
		assertThat(norm1.getZ())
		.as("norm1.z ~= 0.577350")
		.isCloseTo(0.577350, offset(0.01));
	
		
		Vector norm2 = vec2.normalisation();
		assertThat(norm2.getX())
		.as("norm1.x ~= 0.8555625")
		.isCloseTo(0.8555625, offset(0.01));
		
		assertThat(norm2.getY())
		.as("norm1.x ~= -0.5173169")
		.isCloseTo(-0.5173169, offset(0.01));
		
		assertThat(norm2.getZ())
		.as("norm1.z ~= 0.01989680")
		.isCloseTo(0.01989680, offset(0.01));
	}
	
	
	
}
