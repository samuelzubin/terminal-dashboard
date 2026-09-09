public class Dashboard {
	static TaskList list = new TaskList();

	static void print() {
		System.out.println("┌───────────────────────────────────────────────┐");
    System.out.println("|                     Tasks                     |");
    System.out.println("├───────────────────────────────────────────────┤");
    System.out.println("|                                               |");

		if (TaskList.list.isEmpty()) {
			System.out.println("|                 Nothing to do!                |");
		}

		else {
			for (Task task : list) {
				System.out.printf("| %-45.45s |\n", task.display());  // padding or truncating based on task length
			}
		}

    System.out.println("|                                               |");
    System.out.println("└───────────────────────────────────────────────┘");
	}
}