import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskParser {
    
    private String filePath;

		TaskParser(String path) {
			filePath = path;
		}

    void getTasks(TaskList taskList) {
			taskList.clear();
			try {
					List<String> lines = Files.readAllLines(Path.of(filePath));
					
					for (String line : lines) {
							String trimmed = line.trim();
							
							if (trimmed.startsWith("- [ ]")) {
									String taskContent = trimmed.substring(5).trim();
									taskList.addTask(new Task(taskContent, false));
							} 
							else if (trimmed.startsWith("- [x]") || trimmed.startsWith("- [X]")) {
									String taskContent = trimmed.substring(5).trim();
									taskList.addTask(new Task(taskContent, true));
							}
					}
			} 
			catch (IOException e) {
					System.out.println("Error: " + e.getMessage());
			}
    }
}