package RayTracer;


import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;



public class SceneTest {

	private String GIT_ROOT_DIR = "/home/ad/Documents/Cours/POO/JavaRayTracer";
	
		
	/* Doivent échouer, diffuse+ambient > 1*/
	@Test
	public void testScene1() {
	    IllegalArgumentException exception =
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Scene(GIT_ROOT_DIR + "/TestScenes/jalon2/test1.scene");
	        });
	}
	

	@Test
	public void testScene2() {
	    IllegalArgumentException exception =
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Scene(GIT_ROOT_DIR + "/TestScenes/jalon2/test2.scene");
	        });
	}

	@Test
	public void testScene3() {
	    IllegalArgumentException exception =
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Scene(GIT_ROOT_DIR + "/TestScenes/jalon2/test3.scene");
	        });
	}

	@Test
	public void testScene4() {
	    IllegalArgumentException exception =
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Scene(GIT_ROOT_DIR + "/TestScenes/jalon2/test4.scene");
	        });
	}
	
	
	
	
	/* Doivent réussir */
	
	@Test
	public void testScene5() {
        Scene scene5 = new Scene(GIT_ROOT_DIR + "/TestScenes/jalon2/test5.scene");
        scene5.printSummary();
	}
	
	@Test
	public void testScene6() {
        Scene scene6 = new Scene(GIT_ROOT_DIR + "/TestScenes/jalon2/test6.scene");
        scene6.printSummary();
	}
	
	
	
	
}
