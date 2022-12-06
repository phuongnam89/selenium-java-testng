package webdriver;


import java.util.Date; 



import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic20_Implicit_explicit	 {
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
	
	public void TC_01_Not_enough_time() {
		
		
	}
	@Test
	public void TC_02_Element_not_found() {
		//2.1: Implicit = explicit
		//2.2: Implicit < explicit
		//2.3: Implicit > explicit
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);

		driver.get("https://www.facebook.com/");
		//implicit luôn chạy trước, sau đó mới tới explicit
		//Explicit sẽ chạy sau implicit từ 0,5-2 giây
		//ví dụ implicit là 5, explicit là 6
		//implicit chạy 0,5-1 giây thì explicit chạy 6 giây, vậy thời gian sẽ là 5+2=7(+-1 giây)
		
		System.out.println("Thời gian bắt đầu của explicit: " + getTimeStamp());
		
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println(" Thời gian kết thúc của explicit: " + getTimeStamp());
			
		}
		
		
	}

	//@Test
	public void TC_03_more_than() {
		
		
	}
		
	@Test
	public void TC_04_Element_not_found_explicit_tham_so_by() {
		explicitWait = new WebDriverWait(driver, 10);
		driver.get("https://www.facebook.com/");
		//Explicit- By là tham số nhận vào của hàm explicit- ví dụ invisiblityOfElementLacated(by)
		//Impilicit = 0
		//Tổng thời gian = explicit
		try {
					explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
				} catch (Exception e) {
					e.printStackTrace();
					//System.out.println(" Thời gian kết thúc của explicit: " + getTimeStamp());
					
				}	
	}
	@Test
	public void TC_05_Element_not_found_explicit_tham_so_WebElement() {
		explicitWait = new WebDriverWait(driver, 10);
		driver.get("https://www.facebook.com/");
		
		//Nếu  driver.findElement chạy pass thì mới chạy tiếp
		//Nếu nó chạy fail thì kết thúc luôn chứ ko chạy explicit
		//VisibilityOf: tìm element bên ngoài, nếu thấy thì mới vào trong, ko thấy thì ko chạy
		//visibilityOfElementLocated: tìm element, kiểm tra visible
		try {
					explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
				} catch (Exception e) {
					e.printStackTrace();
					//System.out.println(" Thời gian kết thúc của explicit: " + getTimeStamp());
					
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
	
	//Show ra mốc thời gian tại thời điểm gọi hàm này
	//time-stamp =  ngày-giờ-phút-giây
	public String getTimeStamp() {
		Date date = new Date();
		return date.toString();
	}
	
}