import java.nio.file.Path;

public class InputParser {
	private Path filePath;

	InputParser(String path) {
		filePath = Path.of(path);
	}

	void HandleInput(String input, TaskList taskList) {
		if (input.startsWith("+")) {
			String parsed = input.substring(1).trim();
			Task newTask = new Task(parsed);

			taskList.addTask(newTask, filePath);
		}

		else if (input.startsWith("-")) {
			try {
				int taskIndex = Integer.parseInt(input.substring(1)) - 1;
				taskList.removeTask(taskIndex, filePath);
			}
			catch (NumberFormatException | IndexOutOfBoundsException e) {
    		System.out.println("Enter a valid task number");
			}
		}

		else if (input.startsWith("c")) {
			try {
				int taskIndex = Integer.parseInt(input.substring(1)) - 1;
				taskList.markCompleted(taskIndex, filePath);
			}
			catch (NumberFormatException | IndexOutOfBoundsException e) {
    		System.out.println("Enter a valid task number");
			}
		}
	}
}