import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task> {
  ArrayList<Task> list = new ArrayList<>();
  
  void addTask(Task t, Path filePath) {
    if (list.contains(t)) {
      System.out.println("Task \"" + t.description + "\" already exists");
      return;
    }
    list.add(t);
    updateFile(filePath);
  }

  void removeTask(int index, Path filePath) {
    if (index < 0 || index >= list.size()) { 
      System.out.println("Task does not exist"); 
      return;
    }
    list.remove(index);
    updateFile(filePath);
  }

  void markCompleted(int index, Path filePath) {
    if (index < 0 || index >= list.size()) { 
      System.out.println("Task does not exist"); 
      return;
    }
    Task t = list.get(index);
    t.completed = !t.completed;
    updateFile(filePath);
  }

  void updateFile(Path filePath) {
    try {
      List<String> lines = Files.readAllLines(filePath);
      List<String> updatedLines = new ArrayList<>();
      
      boolean inToday = false;

      for (String line : lines) {
        String trimmed = line.trim();

        if (trimmed.equalsIgnoreCase("## today")) {
          inToday = true;
          updatedLines.add(line);
          
          for (Task t : list) {
            String checkbox = t.completed ? "- [x]" : "- [ ]";
            updatedLines.add(checkbox + " " + t.description);
          }
          continue;
        }

        if (inToday) {
          if (trimmed.startsWith("## ")) {
            inToday = false;
          } else {
            continue;
          }
        }

        updatedLines.add(line);
      }
      
      Files.write(filePath, updatedLines);
    }
		catch (IOException e) {
      System.out.println("An error occurred while saving tasks.");
    }
  }

  void clear() {
    list.clear();
  }

  @Override
  public Iterator<Task> iterator() {
    return list.iterator();
  }
}