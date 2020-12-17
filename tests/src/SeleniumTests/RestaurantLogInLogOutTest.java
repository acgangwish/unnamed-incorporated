package SeleniumTests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class RestaurantLogInLogOutTest {
  private static WebDriver driver;
  private static String baseUrl;
  private boolean acceptNextAlert = true;
  private static StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass
  public static void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
	driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testRestaurantLogIn() throws Exception {
    driver.get("http://ec2-3-133-90-197.us-east-2.compute.amazonaws.com:8080/unnamed-incorporated/HomePage.html");
    Thread.sleep(600);
    driver.findElement(By.linkText("Sign In")).click();
    Thread.sleep(600);
    driver.findElement(By.xpath("//input[@id=\"restIn\"]")).click();
    Thread.sleep(600);
    driver.findElement(By.xpath("//input[@name=\"usernameIn\"]")).sendKeys("Sals101");
    Thread.sleep(600);
    driver.findElement(By.xpath("//input[@name=\"passwordIn\"]")).sendKeys("test1234");
    Thread.sleep(600);
    driver.findElement(By.xpath("//input[@value='Sign In']")).submit();
    Thread.sleep(600);
    driver.findElement(By.linkText("Info")).click();
    Thread.sleep(600);
    String userInfo = driver.findElement(By.xpath("((//*[@class=\"res\"])[2]/a)[2]")).getAttribute("innerHTML");
    
    Assert.assertEquals(userInfo, "Michael Menard");
  }
  
  @Test
  public void testRestaurantLogOut() throws Exception {
	  driver.findElement(By.linkText("Home")).click();
	  Thread.sleep(600);
	  driver.findElement(By.linkText("Log Out")).click();
	  Thread.sleep(600);
	  driver.findElement(By.linkText("Info")).click();
	  Thread.sleep(600);
	  int infoNum = driver.findElements(By.xpath("//*[@class=\"res\"]")).size();
	  
	  Assert.assertEquals(infoNum, 1);
  }

  @AfterClass
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
