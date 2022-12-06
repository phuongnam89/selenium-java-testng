package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic20_Static_wait	 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password;
	WebDriverWait explicitWait;
	
	

	@BeforeClass
		public void beforeClass() {
		if (osName.contains("Mac OS")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		
	} else {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		
	}
		
		Random rand = new Random();
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 10);
		
		
		driver.manage().window().maximize();
		
		
		
	}
	@Test
	public void TC_01_Not_enough_time() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click vao start 
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		//sleep cung trong 2 giay
		sleepInSecond(2);
		
		//verify
		Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");
	}

	@Test
	public void TC_02_Enough_time() {
		
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click vao start 
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		sleepInSecond(5);
		
		//verify
		Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");
	}

	@Test
	public void TC_03_more_than() {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click vao start 
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		sleepInSecond(10);
		
		//verify
		Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");
		
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