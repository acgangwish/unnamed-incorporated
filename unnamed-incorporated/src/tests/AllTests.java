package tests;

import org.junit.runner.RunWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;

@RunWith(JUnitPlatform.class)
@SelectClasses({UserTests.class, RestaurantTests.class, ReservationTests.class})
public class AllTests {
	
}
