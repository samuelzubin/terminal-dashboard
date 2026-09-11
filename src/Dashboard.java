public class Dashboard {
  static TaskList taskList = new TaskList();

  static void print(TaskList taskList, String weatherInfo) {
    int i = 0;
    System.out.println("┌───────────────────────────────────────────────────────────────────────┐");
    System.out.println("|                                  Today                                |");
    System.out.println("├───────────────────────────────────────────────────────────────────────┤");

    System.out.printf("| %-69s |\n", weatherInfo);

    System.out.println("├───────────────────────────────────────────────────────────────────────┤");
    System.out.println("|                                                                       |");

    if (taskList.list.isEmpty()) {
      System.out.println("|                            Nothing to do!                             |");
    } 
    else {
      for (Task task : taskList) {
        System.out.printf("| (%d) %-65.65s |\n", ++i, task.display()); 
      }
    }

    System.out.println("|                                                                       |");
    System.out.println("└───────────────────────────────────────────────────────────────────────┘");
  }
}
