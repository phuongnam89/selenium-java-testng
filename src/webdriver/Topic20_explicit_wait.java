package webdriver;

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

public class Topic20_explicit_wait	 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password;
	WebDriverWait explicitWait;
	
	//tao ra cac ham tung anh
		String beachFileName = "beach.jpg";
		String eldenRing = "dota2_social.jpg";
		String dota2 = "game4v-elden-ring-1-1643452176-52.jpg";
		
		//tao duong dan den cac file
		String beachFilePath = projectPath + "\\upload_file\\" + beachFileName;
		String eldenRingFilePath = projectPath + "\\upload_file\\" + eldenRing;
		String dota2FilePath = projectPath + "\\upload_file\\" + dota2;
		

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
	//@Test
	public void TC_01_Not_enough_time() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click vao start 
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		//đợi cho đến khi 1 cái element được hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		//verify
		Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");
	}

	//@Test
	public void TC_02_Enough_time() {
		
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click vao start 
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

		
		//verify
		Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");
	}

	//@Test
	public void TC_03_more_than() {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//Click vao start 
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		sleepInSecond(10);
		
		//verify
		Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");
	}
		
	@Test
	public void TC_03_Date_Time_Calendar() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		explicitWait = new WebDriverWait(driver,15);
		
		//Đợi cho Date Picker được hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));
		
		//Verify muc Seleted Dates la chua co ngay nao duoc chon
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(), "No Selected Dates to display.");
		
		//Đợi cho đến khi ngày 19 hiển thị
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='19']")));
		
		//Bây giờ mới click vào ngày 19
			driver.findElement(By.xpath("//a[text()='19']")).click();
		
		//Wait cho Ajax loading biến mất
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
			
			
		//Wait cho đến khi ngày 19 được chọn trở lại	
			explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='19']")));
			
		//Verify mục Selected Dates đã được chọn ngày 19
			Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(), "Saturday, November 19, 2022");
			
			
	}
	@Test
	public void TC_03_Upload() {
		
		driver.get("https://gofile.io/uploadFiles");
		
		//Khởi tạo lại explicitwait để tăng thời gian
		//Case này mất khá nhiều thời gian cho việc upload
		explicitWait = new WebDriverWait(driver, 45);
		
		//Wait cho đến khi element AddFiles được hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-primary btn-lg mb-1 uploadButton']")));
		
		
		//Tìm thằng input type=file để sendkey chứ ko phải  addfiles
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(beachFilePath + "\n" + eldenRingFilePath + "\n" + dota2FilePath);
		
		//Wait cho loading icon bien mat
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='rowUploadProgress-list']")));
		
		//Wait cho tin nhan upload thanh cong xuat hien
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		
		//Verify tin nhan upload thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
		
		//Wait+ nhấn chọn nút showFile
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='rowUploadSuccess-showFiles']"))).click();
		
		//wait+verify nut Download o tung anh
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + beachFileName + "']/parent::a/parent::div//following-sibling::div[@class='col-md text-center text-md-right']/a"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + eldenRing + "']/parent::a/parent::div//following-sibling::div[@class='col-md text-center text-md-right']/a"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + dota2 + "']/parent::a/parent::div//following-sibling::div[@class='col-md text-center text-md-right']/a"))).isDisplayed());

		//Wait+verify cho nut Play
		
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + beachFileName + "']/parent::a/parent::div//following-sibling::div[@class='col-md text-center text-md-right']/button[@class='rowFolder-option contentPlay btn btn-default btn-xs p-1']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + eldenRing + "']/parent::a/parent::div//following-sibling::div[@class='col-md text-center text-md-right']/button[@class='rowFolder-option contentPlay btn btn-default btn-xs p-1']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + dota2 + "']/parent::a/parent::div//following-sibling::div[@class='col-md text-center text-md-right']/button[@class='rowFolder-option contentPlay btn btn-default btn-xs p-1']"))).isDisplayed());

		
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