package RayTracer;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class VectorTest {
	
	@Test
	void testAdd() {
		Vector vec1 = new Vector(10, 26, 30);
		Vector vec2 = new Vector(43, -26, 1);
		Vector sum = vec1.add(vec2);
		
		assertThat(sum.getX())
		.as("La somme de 10 et 43 est 53.")
		.isEqualTo(53.0);
	}
}
