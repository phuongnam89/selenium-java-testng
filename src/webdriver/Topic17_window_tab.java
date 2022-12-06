package webdriver;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic17_window_tab	 {
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
	public void TC_01_Window_Tab_By_ID() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Trường hợp này chỉ có 2 window/tab
		//Lấy ra tất cả ID của các window/tab đang có
		Set<String> allWindowIds = driver.getWindowHandles();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		//tạo 1 vòng lặp for để kiểm tra
		//nếu cái id đó khác với id hiện tại thì nó sẽ switch qua
		
		for(String id : allWindowIds) {
			if(!id.equals(driver.getWindowHandle()));
			driver.switchTo().window(id);
			
		}
		
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium");
		sleepInSecond(2);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		
		
		
	}

	//@Test
	public void TC_02_More_than_2_window_tab_By_Title() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		
		//Dùng hàm bên dưới để switch qua window/tab có Title là "Google"
		switchToWindowByPageTitle("Google");
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium");
		sleepInSecond(2);
		
		Assert.assertEquals(driver.getTitle(), "Google");
		
		driver.close();
		
		//Quay trở về window ban đầu(parent)
		switchToWindowByPageTitle("SELENIUM WEBDRIVER FORM DEMO");
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		
		switchToWindowByPageTitle("FACEBOOK");
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
		
		driver.close();
		
		switchToWindowByPageTitle("SELENIUM WEBDRIVER FORM DEMO");
		
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		switchToWindowByPageTitle("Tiki");
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		sleepInSecond(2);
		driver.close();
		
		switchToWindowByPageTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
			 
	}

	//@Test
	public void TC_03_Kynavn_window_tab() {
		
		driver.get("https://kyna.vn/");
		sleepInSecond(2);
		driver.findElement(By.xpath("//img[@alt='facebook' and @width='44']")).click();
		
		switchToWindowByPageTitle("Kyna.vn - Home | Facebook");
		
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Home | Facebook");
		
		
		switchToWindowByPageTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
		
		driver.findElement(By.xpath("//img[@alt='youtube' and @width='44']")).click();
		
		switchToWindowByPageTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		
		switchToWindowByPageTitle("Kyna.vn - Học online cùng chuyên gia");
		
		driver.findElement(By.xpath("//img[contains(@src, 'dathongbao')]")).click();
		
		switchToWindowByPageTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
		Assert.assertEquals(driver.getTitle(), "Thông tin website thương mại điện tử - Online.Gov.VN");
		
		switchToWindowByPageTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
		
		//Chỗ này độ phân giải màn hình ko đủ nên element bị che
		//Sử dụng lăn chuột để kéo màn hình xuống cuối trang
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
		
		
		driver.findElement(By.xpath("//img[contains(@src, 'logoCCDV')]")).click();
		
		switchToWindowByPageTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
		Assert.assertEquals(driver.getTitle(), "Thông tin website thương mại điện tử - Online.Gov.VN");
		
		switchToWindowByPageTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
		
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src, 'kyna.vn')]")).isDisplayed());
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src, 'kyna.vn')]")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()= '165K likes']")).getText(), "165K likes" );
		
		driver.switchTo().defaultContent();
		
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
	}
	
	//@Test
	public void TC_03_techpanda_window_tab() {
		
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).getText(), "The product Sony Xperia has been added to comparison list.");
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).getText(), "The product Samsung Galaxy has been added to comparison list.");

		driver.findElement(By.xpath("//span[text()='Compare']")).click();
		
		switchToWindowByPageTitle("Products Comparison List - Magento Commerce");
		
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		driver.close();
		switchToWindowByPageTitle("Mobile");
		
		Assert.assertEquals(driver.getTitle(), "Mobile");
		
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		
		driver.switchTo().alert().accept();
		
		//chua hieu sao bi loi o dong cuoi nay
		//Assert.assertEquals(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")), "The comparison list was cleared.");
		
	}
		
	@Test
	public void TC4_cambridge_window() {
		driver.get("https://dictionary.cambridge.org/vi/");
		
		driver.findElement(By.xpath("//span[text()='Đăng nhập']")).click();
		
		switchToWindowByPageTitle("Login");
		
		
		
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@data-bound-to='loginID' and text()='This field is required']")).getText(), "This field is required");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@data-bound-to='password' and text()='This field is required']")).getText(), "This field is required");
		
		sleepInSecond(2);
		driver.findElement(By.xpath("//input[@name='username' and @aria-label='Email']")).sendKeys("automationfc.com@gmail.com");
		driver.findElement(By.xpath("//input[@name='password' and @placeholder='Password *']")).sendKeys("Automation000***");
		
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
		sleepInSecond(5);
		
		switchToWindowByPageTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Automation FC']")).getText(), "Automation FC");
		
		

	}
				




				
				
		
		
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Dung duoc cho duy nhat 2 IDs ( Window/tab)
	
	public void switchToWindowByID(String ortherID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id :allWindowIDs) {
			if(!id.equals(ortherID));
			driver.switchTo().window(id);
			sleepInSecond(2);
		}
		
	}
	
	//Cach nay dua vao Title cua tung window/tab
	//Moi window/tab lai co 1 Title khac nhau
	//Dung duoc cho tu 2 ID tro len ( Window/Tab)
	//no co the dung cho case ben tren
	
	public void switchToWindowByPageTitle(String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		for (String id : allWindowIDs) {
			
			//switch qua tung ID truoc
			driver.switchTo().window(id);
			
			//int count = 1;
			
			//Lay ra cai Title cua tung ID
			String actualPageTitle = driver.getTitle();
			
			//System.out.println("ActualTitle = " + actualPageTitle + count);
			//count++;
			
			if (actualPageTitle.equals(expectedPageTitle)) {
			sleepInSecond(2);
			break;
			
			}	
			}
	}
	public void closeAllWindowWithoutParent(String parentID ) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if(!id.equals(parentID));
			driver.switchTo().window(id);
			driver.close();
			sleepInSecond(2);
		//phai switch ve window parent
		//du bi tat nhung cai driver van o window/tab da bi tat
		}
		driver.switchTo().window(parentID);
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}