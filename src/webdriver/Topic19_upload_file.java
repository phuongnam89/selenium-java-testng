package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic19_upload_file	 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password;
	JavascriptExecutor jsExecutor;
	
	
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		
	}
	//@Test
	public void TC_01_One_file_per_time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//truyen vao ham da khai bao o tren
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(beachFilePath);
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(eldenRingFilePath);
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(dota2FilePath);
		sleepInSecond(1);
			
		//Verify da duoc upload len thanh cong ( Links)
		//Verify bang text() va co chua ham ben trong '" + ten ham + '"
		//Chua chac chan lam nhung phai viet theo cau truc tren thi moi nhan ham
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + beachFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + dota2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='game4v-elden-ring-1-1643452176-52.jpg']")).isDisplayed());
		
		//Verify da duoc upload len thanh cong ( Img )
		//Luc nay anh da duoc upload thanh cong nen se chuyen tu links sang img (thẻ p sang thẻ img)
		//img[contains(@src, '" + dota2 + '")]
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + beachFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + dota2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + eldenRing + "']")).isDisplayed());

		
		
	}

	@Test
	public void TC_02_Upload_multiple_files() {
		//Chi co thay doi duy nhat la them \n o phan sendKeys	
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//truyen vao ham da khai bao o tren
		//Kiem tra trong code xem co dong Multiple thi moi up nhieu file 1 lan dc
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(beachFilePath + "\n" + dota2FilePath + "\n" + eldenRingFilePath);
		
			
		//Verify da duoc upload len thanh cong ( Links)
		//Verify bang text() va co chua ham ben trong '" + ten ham + '"
		//Chua chac chan lam nhung phai viet theo cau truc tren thi moi nhan ham
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + beachFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + dota2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='game4v-elden-ring-1-1643452176-52.jpg']")).isDisplayed());
		
		//Verify da duoc upload len thanh cong ( Img )
		//Luc nay anh da duoc upload thanh cong nen se chuyen tu links sang img (thẻ p sang thẻ img)
		//img[contains(@src, '" + dota2 + '")]
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + beachFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + dota2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + eldenRing + "']")).isDisplayed());

		
		 
	}

	@Test
	public void TC_03_LoginFormDisplayed() {
		
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
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