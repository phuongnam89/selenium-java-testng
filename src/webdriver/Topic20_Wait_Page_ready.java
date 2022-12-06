package webdriver;


import java.util.Random;
import java.util.concurrent.TimeUnit;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic20_Wait_Page_ready	 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;
	
	
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
		explicitWait = new WebDriverWait(driver, 10);
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
	}
	@Test
	public void TC_01_ValidateCurrentUrl() {
		
	}

	@Test
	public void TC_02_Test_project() {
		 driver.get("https://blog.testproject.io/");
		
		 //Load 1 lần
		 System.out.println("Wait cho page ready lần 1");
		 Assert.assertTrue(isPageLoadedSuccess());
		 
		 //Xử lý popup ( nếu có)
		 //if (driver.findElement(By.cssSelector("div.mailch-wrap")).isDisplayed()) {
			// System.out.println("Close popup");
			 //driver.findElement(By.cssSelector("div#close-mailch")).click();
	//}
		 
		 //Di chuột (hover) vào element search -  hành động này sẽ làm page load tiếp 1 lần nữa
		 //Cái web này nó thế, ai biết đâu
		 System.out.println("Hover chuột vào search textbox");
		 action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
		 
		 System.out.println("Enter value to search box");
		 driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
		 driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		 
		 Assert.assertTrue(driver.findElement(By.cssSelector("h2.page-title>span")).isDisplayed());
		 
		 Assert.assertTrue(isPageLoadedSuccess());
		 
		 //Tạo ra 1 list để verify
		 List<WebElement> postTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
		 for (WebElement title : postTitle) {
			 String postTitleText = title.getText();
			 System.out.println(postTitleText);
			 Assert.assertTrue(postTitleText.contains("Selenium"));
		 }
		 

		 
		
		
		
		
		
		
		
		
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
		//driver.quit();
	}
	//Hàm để đợi cho page ready
	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return(Boolean)jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
				}
			};
			return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
			
		
	}
}