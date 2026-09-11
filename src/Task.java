import java.util.Objects;

public class Task {
	String description;
	boolean completed;

	Task(String desc) {
		description = desc;
    completed = false;
	}

	Task(String desc, boolean comp) {
		description = desc; 
    completed = comp;
	}

	String display() {
		if (!completed) { return "[ ] " + description; }
		else 						{ return "[X] " + description; }
	}

	@Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj  == null || getClass() != obj.getClass()) return false;
    Task task = (Task) obj;
    return Objects.equals(description, task.description);
  }
}
