package RayTracer;

public class Scene {

	private SceneFileParser parser;
	
	
	public Scene(String fileName) {
		parser = new SceneFileParser(fileName);
		
		
	}
}
