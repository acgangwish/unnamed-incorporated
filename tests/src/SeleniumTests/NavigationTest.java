package SeleniumTests;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NavigationTest {
  private static WebDriver driver;
  private static String baseUrl;
  private boolean acceptNextAlert = true;
  private static StringBuffer verificationErrors = new StringBuffer();

  @BeforeAll
  public static void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
	driver = new ChromeDriver();
	baseUrl = "https://www.google.com/";
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  @Order(1)
  public void testHomeNavigation() throws Exception {
    driver.get("http://ec2-3-133-90-197.us-east-2.compute.amazonaws.com:8080/unnamed-incorporated/HomePage.html");
    Thread.sleep(600);
    driver.findElement(By.linkText("Sign In")).click();
    Thread.sleep(600);
    driver.navigate().back();
    Thread.sleep(600);
    Assert.assertTrue(driver.getCurrentUrl().endsWith("HomePage.html"));
    driver.findElement(By.linkText("Info")).click();
    Thread.sleep(600);
    Assert.assertTrue(driver.getCurrentUrl().endsWith("UserReservation"));
    driver.navigate().back();
    Thread.sleep(600);
    Assert.assertTrue(driver.getCurrentUrl().endsWith("HomePage.html"));
    driver.findElement(By.linkText("Log Out")).click();
    Thread.sleep(600);
    Assert.assertTrue(driver.getCurrentUrl().endsWith("LogOut"));
    driver.navigate().back();
    Thread.sleep(600);
    Assert.assertTrue(driver.getCurrentUrl().endsWith("HomePage.html"));
    driver.findElement(By.xpath("//input[@value='Search']")).click();
    Thread.sleep(600);
    Assert.assertTrue(driver.getCurrentUrl().endsWith("Results"));
    driver.navigate().back();
    Thread.sleep(600);
    Assert.assertTrue(driver.getCurrentUrl().endsWith("HomePage.html"));
  }
  
  @Test
  @Order(2)
  public void testInfoNavigation() throws Exception {
	driver.findElement(By.linkText("Info")).click();
	Thread.sleep(600);
	Assert.assertTrue(driver.getCurrentUrl().endsWith("UserReservation"));
	driver.findElement(By.linkText("Home")).click();
	Thread.sleep(600);
	Assert.assertTrue(driver.getCurrentUrl().endsWith("HomePage.html"));
  }
  
  @Test
  @Order(3)
  public void testSignInNavigation() throws Exception {
	driver.findElement(By.linkText("Sign In")).click();
	Thread.sleep(600);
	Assert.assertTrue(driver.getCurrentUrl().endsWith("SignIn.html"));
	driver.findElement(By.linkText("Info")).click();
	Thread.sleep(600);
	Assert.assertTrue(driver.getCurrentUrl().endsWith("UserReservation"));
	driver.navigate().back();
	Thread.sleep(600);
	Assert.assertTrue(driver.getCurrentUrl().endsWith("SignIn.html"));
	driver.findElement(By.linkText("Home")).click();
	Thread.sleep(600);
	Assert.assertTrue(driver.getCurrentUrl().endsWith("HomePage.html"));
  }
  
  @Test
  @Order(4)
  public void testSearchNavigation() throws Exception {
	driver.findElement(By.xpath("//input[@value='Search']")).click();
	Thread.sleep(600);
	Assert.assertTrue(driver.getCurrentUrl().endsWith("Results"));
	driver.findElement(By.linkText("Sign In")).click();
	Thread.sleep(600);
	Assert.assertTrue(driver.getCurrentUrl().endsWith("SignIn.html"));
	driver.navigate().back();
	Thread.sleep(600);
	Assert.assertTrue(driver.getCurrentUrl().endsWith("Results"));
	driver.findElement(By.linkText("Home")).click();
	Thread.sleep(600);
	Assert.assertTrue(driver.getCurrentUrl().endsWith("HomePage.html"));
	driver.navigate().back();
	Thread.sleep(600);
	Assert.assertTrue(driver.getCurrentUrl().endsWith("Results"));
	driver.findElement(By.linkText("Info")).click();
	Thread.sleep(600);
	Assert.assertTrue(driver.getCurrentUrl().endsWith("UserReservation"));
	driver.navigate().back();
	Thread.sleep(600);
	Assert.assertTrue(driver.getCurrentUrl().endsWith("Results"));
	driver.findElement(By.linkText("Log Out")).click();
	Thread.sleep(600);
	Assert.assertTrue(driver.getCurrentUrl().endsWith("LogOut"));
	driver.navigate().back();
	Thread.sleep(600);
	Assert.assertTrue(driver.getCurrentUrl().endsWith("Results"));
  }
  
  @AfterAll
  public static void tearDown() throws Exception {
	driver.quit();
	String verificationErrorString = verificationErrors.toString();
	if (!"".equals(verificationErrorString)) {
	  fail(verificationErrorString);
	}
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
