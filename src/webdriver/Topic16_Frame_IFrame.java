package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic16_Frame_IFrame	 {
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
	public void TC_01_IFrame() {
		driver.get("https://kyna.vn/");
		//verify iframe facebook hien thi
		//Facebook iframe van la 1 element cua trang
		
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src, 'kyna.vn')]")).isDisplayed());
		
		//can switch to vao cai Iframe de co the verify
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src, 'kyna.vn')]")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()= '165K likes']")).getText(), "165K likes");
		System.out.println(driver.findElement(By.xpath("//div[text()= '165K likes']")));
		
		//switch ve main page
		driver.switchTo().defaultContent();
		
		//switch vao chat iframe
		driver.switchTo().frame("cs_chat_iframe");
		
		driver.findElement(By.cssSelector("div.button_bar")).click();
		
		driver.findElement(By.xpath("//input[@ng-model='login.username']")).sendKeys("Thao Le");
		driver.findElement(By.xpath("//input[@ng-model='login.phone']")).sendKeys("0948416788");
		
		//chon Select
		new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("HỖ TRỢ KỸ THUẬT");
		
		//quay tro ve main page
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		for (WebElement course : courseName) {
			Assert.assertTrue(course.getText().contains("Excel"));
		}
		
		

		
		
		
	}

	@Test
	public void TC_02_ValidatePageTitle() {
		 
	}

	@Test
	public void TC_03_LoginFormDisplayed() {
		
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