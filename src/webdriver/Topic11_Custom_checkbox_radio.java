package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic11_Custom_checkbox_radio	 {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password;
	

	@BeforeClass
		public void beforeClass() {
		if (osName.contains("Mac OS")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		
	} else {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		
	}
		
		Random rand = new Random();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;

		
		
	}
	//@Test
	public void TC_01_Javascript() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		//Sử dụng jsExecutor để click ngay cả khi bị che
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Checked']/preceding::span/input")));
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Checked']/preceding::span/input")).isSelected());
		
		
		
	}

	//@Test
	public void TC_02_Custom_radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[contains(text(),'Spring')]/preceding-sibling::span/input")));
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(), 'Spring')]/preceding-sibling::span/input")).isSelected());

		 
	}

	//@Test
	public void TC_03_Default_Checkbox_or_Radio_button() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		//check vao checkbox
		checkToCheckBox("//ul[@class='fieldlist']/li/input[@id='eq1']");
		
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='fieldlist']/li/input[@id='eq1']")).isSelected());
		
		//bo check checkbox
		unCheckToCheckBox("//ul[@class='fieldlist']/li/input[@id='eq1']");
		Assert.assertFalse(driver.findElement(By.xpath("//ul[@class='fieldlist']/li/input[@id='eq1']")).isSelected());
		
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
		//check vao radio box
		
		driver.findElement(By.cssSelector("input#engine3")).click();
		
		//verify da duoc chon
		Assert.assertTrue(driver.findElement(By.cssSelector("input#engine3")).isSelected());
		
		//kiem tra xem da duoc chon hay chua, neu chua thi bam chon
		if (!driver.findElement(By.cssSelector("input#engine3")).isSelected());
		driver.findElement(By.cssSelector("input#engine3")).click();
		
		//done
	}
	@Test
	public void TC_03_Custom_Checkbox_or_Radio_button() {
		
			driver.get("https://material.angular.io/components/radio/examples");
			
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[contains(text(),'Summer')]/preceding-sibling::span/input")));
			sleepInSecond(3);
			//driver.findElement(By.xpath("//label[@for='mat-radio-4-input']/span[@class='mat-radio-container']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Summer')]/preceding-sibling::span/input")).isSelected());
			
		
		
		if(!driver.findElement(By.xpath("//span[contains(text(),'Summer')]/preceding-sibling::span/input")).isSelected());
		driver.findElement(By.xpath("//span[contains(text(),'Summer')]/preceding-sibling::span/input")).click();
		
		
		
		
		
		
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
		//driver.quit();
	}
	
	public void checkToCheckBox(String xpathLocator) {
		if (!driver.findElement(By.xpath(xpathLocator)).isSelected()) {
			driver.findElement(By.xpath(xpathLocator)).click();
		}
			
		}
		public void unCheckToCheckBox(String xpathLocator) {
			if (driver.findElement(By.xpath(xpathLocator)).isSelected()) {
				driver.findElement(By.xpath(xpathLocator)).click();
			}
	}
}