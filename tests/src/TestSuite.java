import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	SeleniumTests.NavigationTest.class,
	SeleniumTests.CustomerLogInLogOutTest.class,
	SeleniumTests.RestaurantLogInLogOutTest.class,
	SeleniumTests.UserMakeReservationTest.class,
	SeleniumTests.RestaurantMakeReservationTest.class
})

public class TestSuite {
	
}