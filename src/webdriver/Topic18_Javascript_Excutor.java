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

public class Topic18_Javascript_Excutor	 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password;
	JavascriptExecutor jsExecutor;

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
	public void TC_01_techpanda() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(5);
		//dung JE de lay ra domain cua page
		Assert.assertEquals(executeForBrowser("return document.domain"), "live.techpanda.org");
		
		//Dung JE de lay ra ULR cua page
		Assert.assertEquals(executeForBrowser("return document.URL;"), "http://live.techpanda.org/");

		
		executeForBrowser("return document.URL;");
		
		//hightlight va click vao element
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		
		sleepInSecond(3);
		
		hightlightElement("//a[text()='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']");
		clickToElementByJS("//a[text()='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']");
		sleepInSecond(3);
		
		
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);
		
		hightlightElement("//span[text()='Newsletter']");
		scrollToElementOnDown("//span[text()='Newsletter']");
		sleepInSecond(3);
		
		//sendkeys trong JS
		//Su dung ham getRandomNumber de tranh trung lap moi lan test
		sendkeyToElementByJS("//input[@name='email']", "afc" + getRandomNumber() + "@gmail.net");
		
		hightlightElement("//span[text()='Subscribe']");
		clickToElementByJS("//span[text()='Subscribe']");
		sleepInSecond(3);
		
		//Kiem tra hien thi bang JS
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(3);
		Assert.assertEquals(executeForBrowser("return document.domain"), "demo.guru99.com");
		
		

	}

	@Test
	public void TC_02_HTML5_validation_message() {
		 driver.get("https://warranty.rode.com/");
		 
		 String registerTextBox = "//button[contains(text(),'Register')]";
		 String firstNameTextBox= "//input[@id='firstname']";
		 String surNameTextBox= "//input[@id='surname']";
		 String emailTextBox= "//div[contains(text(),'Register')]/following-sibling::div//input[@id='email']";
		 String passwordTextBox = "//input[@id='password']";
		 String confirmPasswordTextBox= "//input[@id='password-confirm']";
		 
		 clickToElementByJS("//button[contains(text(),'Register')]");
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage(firstNameTextBox), "Please fill out this field.");
		
		sendkeyToElementByJS(firstNameTextBox,"automation");
		clickToElementByJS("//button[contains(text(),'Register')]");
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage(surNameTextBox), "Please fill out this field.");

		sendkeyToElementByJS(surNameTextBox,"test");
		clickToElementByJS("//button[contains(text(),'Register')]");
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage(emailTextBox), "Please fill out this field.");
		
		sendkeyToElementByJS(emailTextBox,"test@1234@gmai.net");
		clickToElementByJS("//button[contains(text(),'Register')]");
		sleepInSecond(3);

		Assert.assertEquals(getElementValidationMessage(emailTextBox), "A part following '@' should not contain the symbol '@'.");
		
		sendkeyToElementByJS(emailTextBox,"automation@gmai.net");
		clickToElementByJS("//button[contains(text(),'Register')]");
		sleepInSecond(3);
		
		Assert.assertEquals(getElementValidationMessage(passwordTextBox), "Please fill out this field.");
		
		sendkeyToElementByJS(passwordTextBox,"password1234");
		clickToElementByJS("//button[contains(text(),'Register')]");
		sleepInSecond(3);

		Assert.assertEquals(getElementValidationMessage(confirmPasswordTextBox), "Please fill out this field.");

		
		
		
		
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
	
	public String getDomainName() {
			return (String) jsExecutor.executeScript("return.document.domain;");
			
		}
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public int getRandomNumber() {
		return new Random().nextInt(9999);
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}