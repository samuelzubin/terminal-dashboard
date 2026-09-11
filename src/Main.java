import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
  private static String weatherData;
  private static long lastWeatherFetch = 0;
  private static final long WEATHER_FETCH_INTERVAL = 30 * 60 * 1000;  // 30 mins

  public static void main(String[] args) {
    loadEnv("src/.env");

    String taskPath = System.getProperty("TASK_PATH");

    TaskList    list     = new TaskList();
    TaskReader  reader   = new TaskReader(taskPath);
    InputParser parser   = new InputParser(taskPath);
    Scanner     scanner  = new Scanner(System.in);

    while (true) {
      // clear screen
      System.out.print("\033[H\033[2J");
      System.out.flush();

      reader.getTasks(list);
      updateWeather();
      Dashboard.print(list, weatherData);

      String input = scanner.nextLine();
      parser.HandleInput(input, list);

      try {
        Thread.sleep(500);
      }
      catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  private static void updateWeather() {
    long now = System.currentTimeMillis();
    if (now - lastWeatherFetch > WEATHER_FETCH_INTERVAL || lastWeatherFetch == 0) {
      weatherData = WeatherFetcher.getWeather();
      lastWeatherFetch = now;
    }
  }

  private static void loadEnv(String filePath) {
    try {
      File file = new File(filePath);
      if (!file.exists()) {
        System.out.println("Warning: .env file not found at " + file.getAbsolutePath());
        return;
      }

      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();
        if (line.isEmpty() || line.startsWith("#")) {
          continue;
        }

        String[] parts = line.split("=", 2);
        if (parts.length == 2) {
          String key = parts[0].trim();
          String value = parts[1].trim();
          value = value.replaceAll("^[\"']|[\"']$", "");
          System.setProperty(key, value);
        }
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("Could not read .env file: " + e.getMessage());
    }
  }
}
