package RayTracer;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.offset;
import org.junit.jupiter.api.Test;


public class ColorTest {
	
	@Test
	public void testConstruct() {
		Color couleur1 = new Color(0, 1, 0.5);
		assertThat(couleur1.getR())
		.as("r = 0.0")
		.isEqualTo(0.0);
		
		assertThat(couleur1.getG())
		.as("g = 1.0")
		.isEqualTo(1.0);

		assertThat(couleur1.getB())
		.as("b = 0.5")
		.isEqualTo(0.5);
		
		
		Color couleur2 = new Color(-5, 100);
		assertThat(couleur2.getR())
		.as("r = 0.0")
		.isEqualTo(0.0);
		
		assertThat(couleur2.getG())
		.as("g = 1.0")
		.isEqualTo(1.0);

		assertThat(couleur2.getB())
		.as("b = 0.0")
		.isEqualTo(0.0);
		
	}
	
	@Test
	public void testAdd() {
		Color couleur1 = new Color(0, 1, 0.5);
		Color couleur2 = new Color(-5, 100);
		Color somme = couleur1.add(couleur2);
		
		assertThat(somme.getR())
		.as("R : 0-5 => 0.0")
		.isEqualTo(0.0);
		assertThat(somme.getG())
		.as("G : 1+100 => 1.0")
		.isEqualTo(1.0);
		assertThat(somme.getB())
		.as("B : 0.5 + 0.0 => 0.5")
		.isEqualTo(0.5);
	}
	
	@Test void testMult() {
		Color couleur1 = new Color(0, 1, 0.02);
		double scalar = 2;
		Color somme = couleur1.multiply(scalar);
		
		assertThat(somme.getR())
		.as("R : 0 *2  => 0.0")
		.isEqualTo(0.0);
		assertThat(somme.getG())
		.as("G : 1 *2 => 1.0")
		.isEqualTo(1.0);
		assertThat(somme.getB())
		.as("B : 0.02 * 2 => 0.04")
		.isEqualTo(0.04);
	}
	
	@Test
	public void testSchurProduct() {
		Color couleur1 = new Color(0, 1, 0.5);
		Color couleur2 = new Color(-5, 100, 0.02);
		Color somme = couleur1.schurProduct(couleur2);
		
		assertThat(somme.getR())
		.as("R : 0 * (-5) => 0.0")
		.isEqualTo(0.0);
		assertThat(somme.getG())
		.as("G : 1*100 => 1.0")
		.isEqualTo(1.0);
		assertThat(somme.getB())
		.as("B : 0.5 * 0.02 => 0.01")
		.isEqualTo(0.01);
	}
	
	
	
}
