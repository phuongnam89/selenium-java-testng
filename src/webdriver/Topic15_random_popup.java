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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic15_random_popup	 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password;
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
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		
	}
	//@Test
	public void TC_01_Fixed_popup_not_in_DOM() {
		driver.get("https://tiki.vn/");
		
		//Tao ra  1 list sau đó kiểm tra list đó có size=0. Do bị ảnh hưởng của implicitlyWait bên trên nên Element sẽ tìm đến 15 giây
		List<WebElement> popupLogin = driver.findElements(By.cssSelector("div.styles__Root-sc-2hr4xa-0"));
		Assert.assertEquals(popupLogin.size(), 0);
		
		driver.findElement(By.xpath("//span[text()='Đăng Nhập / Đăng Ký']")).click();
		
		//Chạy lại popupLogin để cập nhật lại size, hiện tại nó = 1
		popupLogin = driver.findElements(By.cssSelector("div.styles__Root-sc-2hr4xa-0"));
		Assert.assertEquals(popupLogin.size(), 1);
		
		//Click X để tắt popup

		driver.findElement(By.xpath("//img[@class='close-img']")).click();
		//Verify là đã bị tắt, size=0, chạy lại popupLogin để cập nhật size
		popupLogin = driver.findElements(By.cssSelector("div.styles__Root-sc-2hr4xa-0"));
		Assert.assertEquals(popupLogin.size(), 0);

		
		
	}

	@Test
	public void TC_02_Random_popup_In_DOM() {
		//Hiển thị trong những ngày Sales/lễ tết, hết đợt sẽ không hiển thị, code phải chạy đc trong cả 2 trường hợp mà không bị lỗi
		driver.get("https://www.kmplayer.com/home");
		
		WebElement popupLayer = driver.findElement(By.cssSelector("div.pop-conts"));
		
		//Đặt hàm if để kiểm tra xem popup có hiển thị không
		
		if (popupLayer.isDisplayed()) {
			//Dung JxExecutor để click X tắt popup do X đang bị che bởi Element khác
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("img#support-home")));
			
		}
		
		
		 
	}

	@Test
	public void TC_03_Random_popup_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(5);
		
		//Không có trong DOM, chỉ khi popup hiển thị thì mới có code
		//Không thể dùng findElement
		
		List<WebElement> popupContent = driver.findElements(By.cssSelector("div.popup-content"));
		
		if (popupContent.size()>0 && popupContent.get(0).isDisplayed()) {
			driver.findElement(By.id("popup-name")).sendKeys("Thao Le");
			driver.findElement(By.id("popup-email")).sendKeys("lethao@gmail.com");
			driver.findElement(By.id("popup-phone")).sendKeys("0948416788");
			
			sleepInSecond(5);
			
			

			
			
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
		driver.quit();
	}
}