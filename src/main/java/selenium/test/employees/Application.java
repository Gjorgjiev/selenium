package selenium.test.employees;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.net.HttpURLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Application {

	public static void main(String[] args) throws ParseException {
		System.out.println(getEmployees());

	}
	
	public static JSONArray getEmployees() {
		JSONArray employees = new JSONArray();
		try {
			System.setProperty("http.agent", "Chrome");
			URL url = new URL("https://reqres.in/api/users");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();

			if (responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {
				String inline = "";
				Scanner scanner = new Scanner(url.openStream());
				while (scanner.hasNext()) {
					inline += scanner.nextLine();
				}
				scanner.close();

				JSONParser parser = new JSONParser();
				JSONObject dataObj = (JSONObject) parser.parse(inline);
				JSONArray array = (JSONArray) dataObj.get("data");
				for(int i = 0; i < array.size(); i++) {
					JSONObject firstName = (JSONObject) array.get(i);
					if(firstName.containsKey("first_name")) {
						employees.add(firstName.get("first_name"));
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employees;

	}
	
	

}
