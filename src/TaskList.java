import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
	static ArrayList<Task> list = new ArrayList<>();
	
	static void addTask(Task t) {
		if (list.contains(t)) {
			System.out.println("Task \"" + t.description + "\" already exists");
			return;
		}

		list.add(t);
	}

	static void removeTask(Task t) {
		if (!list.contains(t)) { 
			System.out.println("Task does not exist"); 
			return;
		}

		list.remove(t);
	}

	static void markCompleted(Task t) {
		if (!list.contains(t)) { 
			System.out.println("Task does not exist"); 
			return;
		}
		t.completed = true;
	}

	@Override
  public Iterator<Task> iterator() {
    return list.iterator();
  }
}
