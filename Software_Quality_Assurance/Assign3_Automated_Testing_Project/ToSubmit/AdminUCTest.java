package selenium;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.List;
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
public class AdminUCTest {

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
	    driver.get("http://localhost:8080/admin");
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
	  /*
	   * This test validates UC1 and  UC3 Add Book flow
	   */
	  public void testUC1AdminSignInAndAddBookMainFlow() {
		  WebElement userLoginName = driver.findElement(By.id("loginId"));
		  userLoginName.sendKeys("admin");
		  WebElement userLoginPass = driver.findElement(By.id("loginPasswd"));
		  userLoginPass.sendKeys("password");
		  WebElement userLoginBtn = driver.findElement(By.id("loginBtn"));
		  userLoginBtn.click();
		  WebDriverWait wait = new WebDriverWait(driver, 60);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addBook-form")));
		  WebElement addBtn = driver.findElement(By.xpath("//button[@name='addBook'][@type='submit']"));
		  //WebElement addBtn = driver.findElement(By.className("button"));
		  String expected = "Add";
		  String actual = addBtn.getText();
		  assertEquals(expected, actual);
		  
		  //Add Book

		  WebElement addBookCategory = driver.findElement(By.id("addBook-category"));
		  WebElement addBookBookId = driver.findElement(By.name("bookId"));
		  WebElement addBookTitle = driver.findElement(By.name("shortDescription"));
		  WebElement addBookAuthors = driver.findElement(By.name("authors"));
		  WebElement addBookDesc = driver.findElement(By.name("longDescription"));
		  WebElement addBookCost = driver.findElement(By.name("cost"));
		  WebElement addButton = driver.findElement(By.xpath("//button[@name='addBook'][@type='submit']"));
		 		  
		  addBookCategory.sendKeys("Science");
		  addBookBookId.sendKeys("12346");
		  addBookTitle.sendKeys("C programming Language");
		  addBookAuthors.sendKeys("Dennis Ritchie");
		  addBookDesc.sendKeys("Learn how to program C");
		  addBookCost.sendKeys("100");
		  addButton.click();
		  
		  wait = new WebDriverWait(driver, 60);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("feedback")));
		  WebElement addBookMsg = driver.findElement(By.xpath("//div[@id='feedback']/h2"));
		  String expectedMsg = "Successfully added book";
		  String actualMsg = addBookMsg.getText();
		  assertEquals(expectedMsg, actualMsg);
				  
		  
		  
	  }
	  @Test
	  /*
	   * This test validates UC1 and UC5 Delete Book flow
	   */
	  public void testUC1AdminSignInAndDeleteBookMainFlow() {
		  WebElement userLoginName = driver.findElement(By.id("loginId"));
		  
		  userLoginName.sendKeys("admin");
		  WebElement userLoginPass = driver.findElement(By.id("loginPasswd"));
		  userLoginPass.sendKeys("password");
		  WebElement userLoginBtn = driver.findElement(By.id("loginBtn"));
		  userLoginBtn.click();
		  WebDriverWait wait = new WebDriverWait(driver, 60);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addBook-form")));
		  WebElement addBtn = driver.findElement(By.xpath("//button[@name='addBook'][@type='submit']"));
		  //WebElement addBtn = driver.findElement(By.className("button"));
		  String expected = "Add";
		  String actual = addBtn.getText();
		  assertEquals(expected, actual);
		  
		  WebElement catSearch = driver.findElement(By.id("searchBtn"));
		  catSearch.click();
		  
		  wait = new WebDriverWait(driver, 60);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content']/h1")));
		  //WebElement h1Msg = driver.findElement(By.xpath("//div[@class='content']/h1"));
		  
		  int allRows = driver.findElements(By.xpath("//div[@class='content']/table/tbody/tr")).size();
		  //All books are loaded
		  WebElement delButton12346 = driver.findElement(By.xpath("//button[@id='del-12346']"));
		  delButton12346.click();
		  
		  wait = new WebDriverWait(driver, 60);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchBtn")));
		  WebElement catSearch2 = driver.findElement(By.id("searchBtn"));
		  catSearch2.click();
		  
		  
		  //WebElement h1Msg = driver.findElement(By.xpath("//div[@class='content']/h1"));
		  int newRows = driver.findElements(By.xpath("//div[@class='content']/table/tbody/tr")).size();
		  assertEquals(allRows-1, newRows);
	
	  }
	  @Test 
	  /**
	   * UC 2
	   */
	  public void adminSignOutTest() {
		  WebElement userLoginName = driver.findElement(By.id("loginId"));
		  
		  userLoginName.sendKeys("admin");
		  WebElement userLoginPass = driver.findElement(By.id("loginPasswd"));
		  userLoginPass.sendKeys("password");
		  WebElement userLoginBtn = driver.findElement(By.id("loginBtn"));
		  userLoginBtn.click();
		  WebDriverWait wait = new WebDriverWait(driver, 60);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addBook-form")));
		  
		  WebElement signOutBtn = driver.findElement(By.xpath("//input[@value='Sign out']"));
		  signOutBtn.click();
		  
		  wait = new WebDriverWait(driver, 60);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='loginBtn']")));
		  
		  String expected = "You have been logged out";
		  WebElement signOutMsg = driver.findElement(By.xpath("//div[@class='content']/div"));
		  
		  assertEquals(expected, signOutMsg.getText());
		  
		  
	  }
}
