package londonweatherforecast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
public class WeatherData {
	
	    public static JSONObject getWeatherData() {
	        try {
	            URL url = new URL(
	                    "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22");
	            HttpURLConnection con = (HttpURLConnection) url.openConnection();
	            con.setRequestMethod("GET");
	            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            String inputLine;
	            StringBuffer content = new StringBuffer();
	            while ((inputLine = in.readLine()) != null) {
	                content.append(inputLine);
	            }
	            in.close();
	            con.disconnect();
	            return new JSONObject(content.toString());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public static String getWeather(String date, JSONObject data) {
	        JSONArray list = data.getJSONArray("list");
	        for (int i = 0; i < list.length(); i++) {
	            JSONObject item = list.getJSONObject(i);
	            if (item.getString("dt_txt").contains(date)) {
	                return Double.toString(item.getJSONObject("main").getDouble("temp"));
	            }
	        }
	        return "No data found for that date";
	    }

	    public static String getWindSpeed(String date, JSONObject data) {
	        JSONArray list = data.getJSONArray("list");
	        for (int i = 0; i < list.length(); i++) {
	            JSONObject item = list.getJSONObject(i);
	            if (item.getString("dt_txt").contains(date)) {
	                return Double.toString(item.getJSONObject("wind").getDouble("speed"));
	            }
	        }
	        return "No data found for that date";
	    }

	    public static String getPressure(String date, JSONObject data) {
	        JSONArray list = data.getJSONArray("list");
	        for (int i = 0; i < list.length(); i++) {
	            JSONObject item = list.getJSONObject(i);
	            if (item.getString("dt_txt").contains(date)) {
	                return Double.toString(item.getJSONObject("main").getDouble("pressure"));
	            }
	        }
	        return "No data found for that date";
	    }
	
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    JSONObject data = getWeatherData();
   // System.out.println(data);
    while (true) {
    	
        System.out.println("1. Get weather");
        System.out.println("2. Get wind speed");
        System.out.println("3. Get pressure");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
           System.out.print("Enter the date (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            String result = getWeather(date, data);
            System.out.println("Weather: " + result);
        } else if (choice.equals("2")) {
            System.out.print("Enter the date (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            String result = getWindSpeed(date, data);
            System.out.println("Wind speed: " + result);
        } else if (choice.equals("3")) {
            System.out.print("Enter the date (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            String result = getPressure(date, data);
            System.out.println("Pressure: " + result);
        } else if (choice.equals("0")) {
            break;
        } else {
            System.out.println("Invalid choice");
        }
    }
    scanner.close();
}
}

