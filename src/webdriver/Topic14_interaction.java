package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.ClickAndHoldAction;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic14_interaction	 {
	WebDriver driver;
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password;
	
	Actions action;
	JavascriptExecutor jsExecutor;
	

	@BeforeClass
		public void beforeClass() {
		if (osName.contains("Mac OS")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		
	} else {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		
		
		
	}
		
		Random rand = new Random();
		driver = new ChromeDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
	}
	//@Test
	public void TC_01_Hover() {
		driver.get("https://fptshop.com.vn/");
		WebElement dienthoaiFPT = driver.findElement(By.xpath("//a[@title='ĐIỆN THOẠI']"));
		
		sleepInSecond(5);
		action.moveToElement(dienthoaiFPT).perform();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//a[@title='Apple (iPhone)']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[text()= 'Apple (MacBook)']")).getText(), "Apple (MacBook)");
		
		
		
	}

	//@Test
	public void TC_02_ValidatePageTitle() {
		 driver.get("https://automationfc.github.io/jquery-selectable/");
		 	List<WebElement> allElement = driver.findElements(By.cssSelector("ol#selectable>li"));
		 	sleepInSecond(5);
		 	
		 //nhan va giu so 1
		 action.clickAndHold(allElement.get(0))
		 //di chuyen den so 11
		 .moveToElement(allElement.get(10))
		 //tha chuot ra
		 .release()
		 //thuc thi hanh dong
		 .perform();
		 
		 
		 
		 
		 
		 
	}

	private Object get(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	//@Test
	public void TC_03_LoginFormDisplayed() {
		
		driver.get("https://automationfc.github.io/basic-form/");
		
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		sleepInSecond(3);
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
				
		
		
	}
	
	@Test
	public void TC_03_Drag_And_Drop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget']")).isDisplayed(), "Drag the small circle here.");

		action.moveToElement(driver.findElement(By.xpath("//div[@id='draggable']")))
		
		
		.clickAndHold(driver.findElement(By.xpath("//div[@id='draggable']")))
		.moveToElement(driver.findElement(By.xpath("//div[@id='droptarget']")))
		.release()
		.perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget']")).isDisplayed(), "You did great!");

		
		
		
		
	}
		
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}