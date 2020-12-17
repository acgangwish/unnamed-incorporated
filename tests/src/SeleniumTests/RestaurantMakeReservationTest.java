package SeleniumTests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class RestaurantMakeReservationTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  private int Month;
  private int Day;
  private int Hours;
  private int Minutes;
  private int People;
  
  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
	driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testRestaurantMakeReservation() throws Exception {
	Month = ThreadLocalRandom.current().nextInt(1, 13);
    switch(Month) {
    	case(2):
    		Day = ThreadLocalRandom.current().nextInt(1, 30);
    	break;
    	case(4):
    	case(6):
    	case(9):
    	case(11):
    		Day = ThreadLocalRandom.current().nextInt(1, 31);
    	break;
    	default:
    		Day = ThreadLocalRandom.current().nextInt(1, 32);
    }
    Hours = ThreadLocalRandom.current().nextInt(9,23);
    Minutes = ThreadLocalRandom.current().nextInt(0, 60);
    People = ThreadLocalRandom.current().nextInt(1, 11);
	
    
    
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
    driver.findElement(By.xpath("//input[@value='Search']")).click();
    Thread.sleep(600);
    driver.findElement(By.xpath("//form[@id='select1']/li")).click();
    Thread.sleep(600);
    driver.findElement(By.xpath("//input[@id=\"personName\"]")).sendKeys("Sals");
    Thread.sleep(600);
    driver.findElement(By.xpath("//input[@id=\"date\"]")).sendKeys(String.format("%02d-%02d-2020", Month, Day));
    Thread.sleep(600);
    driver.findElement(By.xpath("//input[@id=\"time\"]")).sendKeys(String.format("%02d:%02d", Hours, Minutes));
    Thread.sleep(600);
    driver.findElement(By.xpath("//input[@id=\"numPeople\"]")).sendKeys(String.valueOf(People));
    Thread.sleep(600);
    driver.findElement(By.xpath("//input[@value='Submit Reservation']")).submit();
    Thread.sleep(600);
    
    Assert.assertEquals("Sign In", driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/h2")).getAttribute("innerHTML"));
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
