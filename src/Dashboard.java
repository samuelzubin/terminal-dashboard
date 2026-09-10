public class Dashboard {
	static TaskList taskList = new TaskList();

	static void print(TaskList taskList) {
		System.out.println("┌───────────────────────────────────────────────────────────────────────┐");
    System.out.println("|                                 Today                                 |");
    System.out.println("├───────────────────────────────────────────────────────────────────────┤");
    System.out.println("|                                                                       |");

		if (taskList.list.isEmpty()) {
			System.out.println("|                             Nothing to do!                            |");
		}

		else {
			for (Task task : taskList) {
				System.out.printf("| %-69.69s |\n", task.display());  // padding or truncating based on task length
			}
		}

    System.out.println("|                                                                       |");
    System.out.println("└───────────────────────────────────────────────────────────────────────┘");
	}
}