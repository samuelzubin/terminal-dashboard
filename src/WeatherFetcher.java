import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherFetcher {

  public static String getWeather() {
    String apiKey = System.getProperty("OPENWEATHER_API_KEY");
    String city = System.getProperty("WEATHER_CITY");
    if (apiKey == null || apiKey.isEmpty()) {
      return "API Key Missing";
    }

    try {
      String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
      String urlString = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=imperial", encodedCity, apiKey);

      HttpClient client = HttpClient.newBuilder()
      .connectTimeout(Duration.ofSeconds(10))
      .build();

      HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(urlString))
      .GET()
      .build();

      HttpResponse<String> response = client.send(
        request, 
        HttpResponse.BodyHandlers.ofString()
      );

      if (response.statusCode() == 200) {
        String json = response.body();
        String cityName = getValue(json, "\"name\":\"([^\"]+)\"");
        String tempStr = getValue(json, "\"temp\":([0-9.-]+)");
        int temp = (int) Math.round(Double.parseDouble(tempStr));
        String description = getValue(json, "\"description\":\"([^\"]+)\"");

        return String.format("%s • %d°F • %s", cityName, temp, description);
      }
    }
    catch (Exception e) {
    }
    return "Weather unavailable";
  }

  private static String getValue(String json, String regex) {
    Matcher matcher = Pattern.compile(regex).matcher(json);
    if (matcher.find()) {
      return matcher.group(1);
    }
    return "N/A";
  }
}
