import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
	ArrayList<Task> list = new ArrayList<>();
	
	void addTask(Task t) {
		if (list.contains(t)) {
			System.out.println("Task \"" + t.description + "\" already exists");
			return;
		}

		list.add(t);
	}

	void removeTask(Task t) {
		if (!list.contains(t)) { 
			System.out.println("Task does not exist"); 
			return;
		}

		list.remove(t);
	}

	void markCompleted(Task t) {
		if (!list.contains(t)) { 
			System.out.println("Task does not exist"); 
			return;
		}
		t.completed = true;
	}

	void clear() {
		list.clear();
	}

	@Override
  public Iterator<Task> iterator() {
    return list.iterator();
  }
}
