import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		String taskPath = System.getenv("TASK_PATH");
		TaskList list = new TaskList();
		TaskParser parser = new TaskParser(taskPath);

		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			// clear screen
      System.out.print("\033[H\033[2J");
      System.out.flush();
			
			parser.getTasks(list);
			Dashboard.print(list);
			// String input = scanner.nextLine();
			// System.out.println(input);

			try {
					Thread.sleep(2000);
			} 
			catch (InterruptedException e) {
					Thread.currentThread().interrupt();
			}
		}

	}
}
