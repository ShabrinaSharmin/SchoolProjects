package selenium;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UserUCTests {

	static Process server;
	  private WebDriver driver;

	  @BeforeAll
	  public static void setUpBeforeClass() throws Exception {
	    ProcessBuilder pb = new ProcessBuilder("java", "-jar", "bookstore5.jar");
	    server = pb.start();
	  }

	  @BeforeEach
	  void setUp() {
	    // Pick your browser
	    // driver = new FirefoxDriver();
	     
	    WebDriverManager.chromedriver().setup();
	   // driver = new ChromeDriver();
	    driver = new SafariDriver();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.get("http://localhost:8080/");
	    // wait to make sure Selenium is done loading the page
	    WebDriverWait wait = new WebDriverWait(driver, 60);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title")));
	  }

	  @AfterEach
	  public void tearDown() {
	    driver.close();
	  }

	  @AfterAll
	  public static void tearDownAfterClass() throws Exception {
	    server.destroy();
	  }
	  @Test
	  /**
	   * UC4
	   */
	  public void browseCatalogueTest() {
		  WebElement catSearch = driver.findElement(By.id("searchBtn"));
		  catSearch.click();
		  
		  WebDriverWait wait = new WebDriverWait(driver, 60);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content']/h1")));
		  
		  WebElement catMsg = driver.findElement(By.xpath("//div[@class='content']/h1"));
		  String actual = catMsg.getText();		 
		  String expected = "We currently have the following items in category  ''";
		  assertEquals(expected, actual);
		  
	  }
	  @Test
	  /*
	   * UC 6, 7, 8, 9
	   */
	  public void viewUpdateCheckoutTest() {
		  WebElement catSearch = driver.findElement(By.id("searchBtn"));
		  catSearch.click();
		  
		  WebDriverWait wait = new WebDriverWait(driver, 60);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content']/h1")));
		  WebElement catMsg = driver.findElement(By.xpath("//div[@class='content']/h1"));
		  String actual = catMsg.getText();		 
		  String expected = "We currently have the following items in category  ''";
		  assertEquals(expected, actual);
		  
		  WebElement orderHall001Button = driver.findElement(By.id("order-hall001"));
		  orderHall001Button.click();
		  //WebElement orderHall002Button = driver.findElement(By.id("order-hall002"));
		  //orderHall002Button.click();
		  
		  WebElement cartBtn = driver.findElement(By.id("cartLink"));
		  cartBtn.click();
		  
		  wait = new WebDriverWait(driver, 60);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='checkout']")));
		  
		  //Cart update
		  WebElement updateNumber1Text = driver.findElement(By.id("hall001"));
		  updateNumber1Text.sendKeys("12");
		  
		  
		  WebElement updateNumber1Btn = driver.findElement(By.name("updateOrder"));
		  updateNumber1Btn.click();
		  
		  WebElement totalCost = driver.findElement(By.id("tothall001"));
		  actual = totalCost.getText();
		  expected = "$479.40";
		  assertEquals(expected, actual);
		  
		  //Proceed to checkout
		  
		  WebElement checkoutBtn = driver.findElement(By.name("checkout"));
		  checkoutBtn.click();
		  
		  wait = new WebDriverWait(driver, 60);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content']/table[@id='checkoutTable']")));
		  
		  WebElement msg = driver.findElement(By.cssSelector("p"));
		  String msgFirst = msg.getText().trim().split("\\s+")[0];
		  
		  expected = "We";
		  assertEquals(expected, msgFirst);
  
		  
	  }

}
