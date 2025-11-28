package RayTracer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SceneFileParser {

	private List<String> fileContent;

	
	
	
	
	
	public SceneFileParser(String fileName) throws IOException{
		this.fileContent = openFile(fileName);
	
		this.
	}
	
	
	
	private List openFile(String fileName) throws IOException {
		
		Path filePath = Paths.get(fileName);
		try {
			List<String> lines = Files.readAllLines(filePath);
			return lines;
		} catch (IOException e) {
			throw new IOException("Failed to open file : " + fileName) ;
		}
	}
	
	
	
	private Camera getCamera() {
		
	}
	
	
	
}
