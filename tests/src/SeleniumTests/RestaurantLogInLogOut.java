package SeleniumTests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class RestaurantLogInLogOut {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "YOURPATH\\unnamed-incorporated\\tests\\lib\\chromedriver.exe");
	driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testRestaurantLogInLogOut() throws Exception {
    driver.get("http://ec2-3-133-90-197.us-east-2.compute.amazonaws.com:8080/unnamed-incorporated/HomePage.html");
    Thread.sleep(1000);
    driver.findElement(By.linkText("Sign In")).click();
    Thread.sleep(400);
    driver.findElement(By.id("restIn")).click();
    Thread.sleep(400);
    driver.findElement(By.name("usernameIn")).click();
    driver.findElement(By.name("usernameIn")).clear();
    Thread.sleep(400);
    driver.findElement(By.name("usernameIn")).sendKeys("Sals101");
    Thread.sleep(400);
    driver.findElement(By.name("passwordIn")).click();
    driver.findElement(By.name("passwordIn")).clear();
    Thread.sleep(400);
    driver.findElement(By.name("passwordIn")).sendKeys("test1234");
    Thread.sleep(400);
    driver.findElement(By.xpath("//input[@value='Sign In']")).click();
    Thread.sleep(400);
    driver.findElement(By.linkText("Info")).click();
    Thread.sleep(400);
    String userInfo = driver.findElement(By.xpath("((//*[@class=\"res\"])[2]/a)[2]")).getAttribute("innerHTML");
    driver.findElement(By.linkText("Log Out")).click();
    Thread.sleep(400);
    driver.findElement(By.linkText("Info")).click();
    Integer infoNum = driver.findElements(By.xpath("//*[@class=\"res\"]")).size();
    
    Assert.assertTrue(userInfo.contentEquals("Michael Menard") && infoNum == 1);
  }

  @After
  public void tearDown() throws Exception {
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
