package selenium.test;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.test.employees.Application;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Dataprovider {

	Application app;

	@DataProvider(name = "data-provider")
	public Object[][] getData() {
		JSONArray employees = app.getEmployees();

		List<String> firstName = new ArrayList<String>();
		for(int i = 0; i < employees.size(); i++) {
			firstName.add((String) employees.get(i));
		}
		return new Object[][] {{firstName}};
	}

	@Test(dataProvider = "data-provider")
	public void get_first_name(List<String> employees) {
				System.setProperty("webdriver.chrome.driver","C:\\Users\\dare-\\Desktop\\Selenium Tutorial\\chrome-driver\\chromedriver.exe");
				WebDriver driver = new ChromeDriver();

				driver.manage().window().maximize();
				driver.get("https://trial-qsvt2q.trial.operations.dynamics.com/?mode=trial");
				WebElement email = driver.findElement(By.id("i0116"));
				email.sendKeys("dejan@paurus.si");
				WebElement next = driver.findElement(By.id("idSIButton9"));
				next.click();

				WebElement password = driver.findElement(By.id("i0118"));
				password.sendKeys("L3tsT3st");
				new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9"))).click();
				WebElement no = driver.findElement(By.id("idBtn_Back"));
				no.click();

		for (int i = 0; i < employees.size(); i++) {
			driver.get("https://trial-qsvt2q.trial.operations.dynamics.com/?mode=trial&cmp=DAT&mi=HcmWorkerListPage_Employees");
			WebElement addNew = driver.findElement(By.xpath("//*[@id=\"hcmworkerlistpage_employees_1_HRNew_Worker\"]"));
			addNew.click();

			String firstName = "FirstName";
			new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.name(firstName)));
			WebElement firstNameElement = driver.findElement(By.name(firstName));
			firstNameElement.sendKeys(employees.get(i));

			String employmentDate = "EmploymentStartDate";
			new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.name(employmentDate)));
			WebElement date = driver.findElement(By.name(employmentDate));
			date.sendKeys("3/29/2022 12:00:00 AM");

			String helper = "LastNamePrefix";
			new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.name(helper)));
			WebElement myHelper = driver.findElement(By.name(helper));
			myHelper.click();

			String hire = "OK";
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.name(hire)));
			WebElement hireAndAdd = driver.findElement(By.name(hire));
			hireAndAdd.click();

		}


	}

}
