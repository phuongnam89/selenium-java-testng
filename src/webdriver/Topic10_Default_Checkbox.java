package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic10_Default_Checkbox	 {
	WebDriver driver;
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
		
		
	}
	@Test
	public void TC_01_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		//Bấm chọn vào check box
		//driver.findElement(By.xpath("//input[@value='Cancer']")).click();
		//driver.findElement(By.xpath("//input[@value='Gout']")).click();
		checkToCheckboxOrRadio("//input[@value='Cancer']");
		checkToCheckboxOrRadio("//input[@value='Gout']");
		
		//Verify chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Cancer']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Gout']")).isSelected());
		
		
		// Bấm chọn radio box
		driver.findElement(By.xpath("//input[@value='3-4 days']")).click();
		driver.findElement(By.xpath("//input[@value='No']")).click();
		
		//Verify được chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='3-4 days']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='No']")).isSelected());
		
		//Bỏ chọn checkbox
		UncheckToCheckbox("//input[@value='Cancer']");
		UncheckToCheckbox("//input[@value='Gout']");
		
		//Verify bỏ chọn thành công
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='Cancer']")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='Gout']")).isSelected());
		
		//Bỏ chọn radio thì phải chọn sang 1 radio khác
		//Bỏ chọn 3-4 days và No để chọn sang 1-2 cups/day và 2+ packs/day
		checkToCheckboxOrRadio("//input[@value='Never']");
		checkToCheckboxOrRadio("//input[@value='2+ packs/day']");
		
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='3-4 days']")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='No']")).isSelected());

	}

	
	public void checkToCheckboxOrRadio(String xpathLocator) {
	if (!driver.findElement(By.xpath(xpathLocator)).isSelected()) {
		driver.findElement(By.xpath(xpathLocator)).click();
		
	}
	}
	
	public void UncheckToCheckbox(String xpathLocator) {
		if (driver.findElement(By.xpath(xpathLocator)).isSelected()) {
			driver.findElement(By.xpath(xpathLocator)).click();
		
	}
	}

	//@Test
	public void TC_02_Selected_All() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> allCheckBox = driver.findElements(By.xpath("//input[@type='checkbox']"));
		
		//Dung vong lap de chon va click chon
		for (WebElement checkbox : allCheckBox) {
			if (!checkbox.isSelected());
			checkbox.click();
		}
		
	
	
	for (WebElement checkbox : allCheckBox) {
		Assert.assertTrue(checkbox.isSelected());
	}

	
		//Dung vong lap de bo chon 
			for (WebElement checkbox : allCheckBox) {
				if (checkbox.isSelected());
				checkbox.click();
			}
			//Verify la da bo chon toan bo check box
			for (WebElement checkbox : allCheckBox) {
				Assert.assertFalse(checkbox.isSelected());
			}

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
}
