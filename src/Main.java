import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		String taskPath = System.getenv("TASK_PATH");
		TaskList list = new TaskList();
		TaskReader reader = new TaskReader(taskPath);
		InputParser parser = new InputParser(taskPath);

		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			// clear screen
      System.out.print("\033[H\033[2J");
      System.out.flush();
			
			reader.getTasks(list);
			Dashboard.print(list);
			String input = scanner.nextLine();
			parser.HandleInput(input, list);;

			try {
					Thread.sleep(500);
			} 
			catch (InterruptedException e) {
					Thread.currentThread().interrupt();
			}
		}

	}
}
