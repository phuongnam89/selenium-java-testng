package basic_java;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_switch_case 	 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password;
	Scanner scanner = new Scanner(System.in);

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
		firstName = "Le";
		lastName = "Thao";
		emailAddress = "thaole93@gmail.com";
		
	}
	//@Test
	public void TC_01_ValidateCurrentUrl() {
		int month = scanner.nextInt();
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			System.out.println("Thang nay co 31 ngay");
		break;
		case 2:
			System.out.println("Thang nay co 28 ngay");
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			System.out.println("Thang nay co 30 ngay");
			break;
			default:
				System.out.println("Vui long nhap trong khoang 1-12");
			
			
		}
	}

	//@Test
	public void TC_02_ValidatePageTitle() {
	int number = scanner.nextInt();
	switch (number) {
	case 1:
	System.out.println("One");
	break;
	case 2:
		System.out.println("Two");
		break;
	case 3:
		System.out.println("Three");
		break;
	case 4:
		System.out.println("four");
		break;
	case 5:
		System.out.println("Five");
		break;
	case 6:
		System.out.println("Six");
		break;
	case 7:
		System.out.println("seven");
		break;
	case 8:
		System.out.println("Eight");
		break;
	case 9:
		System.out.println("Nine");
		break;
		case 10:
			System.out.println("Ten");
			break;
			default:
				System.out.println("Vui long nhap tu 1 den 10");
;

	}
	

	
	}

	//@Test
	public void TC_03_LoginFormDisplayed() {
		int firstNumber = scanner.nextInt();
		int secondNumber = scanner.nextInt();
		
		while (firstNumber < secondNumber) {
			
			if(firstNumber % 3 ==0 && firstNumber %5 ==0) {
				
			
			System.out.println(firstNumber);
			
			}
			firstNumber++;
		}
		
		
		
	}
	@Test
	public void TC_03_Login() {
		int firstNumber = scanner.nextInt();
		int i = 1;
		
		while (firstNumber > 0) {
			
		i *= firstNumber;
			
		firstNumber--;
		}
			System.out.println(i);
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