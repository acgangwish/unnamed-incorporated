import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	SeleniumTests.Navigation.class,
	SeleniumTests.CustomerLogInLogOut.class,
	SeleniumTests.RestaurantLogInLogOut.class,
	SeleniumTests.UserMakeReservation.class,
	SeleniumTests.RestaurantMakeReservation.class
})

public class TestSuite {
	
}