import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;

public class TaskReader {
    
    private Path filePath;

		TaskReader(String path) {
			filePath = Path.of(path);
		}

    void getTasks(TaskList taskList) {
			taskList.clear();
			try {
					List<String> lines = Files.readAllLines(filePath);
					boolean inToday = false;

					for (String line : lines) {
							String trimmed = line.trim();

							if (trimmed.equalsIgnoreCase("## today")) {
								inToday = true;
								continue;
							}

							if (inToday && trimmed.startsWith("## ")) {
								break;
							}
							
							if (inToday) {
								if (trimmed.startsWith("- [ ]")) {
										String taskContent = trimmed.substring(5).trim();
										taskList.list.add(new Task(taskContent, false));
								} 
								else if (trimmed.startsWith("- [x]") || trimmed.startsWith("- [X]")) {
										String taskContent = trimmed.substring(5).trim();
										taskList.list.add(new Task(taskContent, true));
								}
						}
					}
			} 
			catch (IOException e) {
					System.out.println("Error: " + e.getMessage());
			}
    }
}