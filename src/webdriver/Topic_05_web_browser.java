package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Topic_05_web_browser {
	WebDriver driver;
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage

	@Test
	public void TC_01_() {
		// nó sẽ đóng tab/window mà nó đang đứng =>2
		driver.close();
		// ko quan tâm bao nhiêu tab/window, đóng hết luôn
		driver.quit();
		//Có thể lưu nó vào 1 biến để sử dụng co các bước sau
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		
		//Tìm nhiều elements
		List<WebElement> checkboxes = driver.findElements(By.xpath(""));
		
		//Mở ra 1 Url nào đó
		driver.get("https://gamek.vn/vi-sao-truyen-co-tich-han-quoc-khong-bat-dau-bang-ngay-xua-ngay-xua-178221006105340358.chn");
		
		//Click vào link tiếng việt
		option opt = driver.manage();
		//Trả về url của page hiện tại
		driver.getCurrentUrl()
		
		Timeouts time = opt.timeouts();
		
	
	
	}
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window
}
