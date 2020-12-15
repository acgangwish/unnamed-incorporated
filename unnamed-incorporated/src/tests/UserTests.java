package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import datamodel.User;
import util.utilDB;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTests {

	@Test
	@Order(1)
	public void testGetCustomerUserList() {
		List<User> ul = utilDB.getCustomerUserList();
		User u;
		u = ul.get(0);
		assertEquals(2, u.getId());
		assertEquals("John Doe", u.getUsername());
		u = ul.get(1);
		assertEquals(3, u.getId());
		assertEquals("Jane Doe", u.getUsername());
	}
	
	@Test
	@Order(2)
	public void testGetRestaurantUserList() {
		List<User> ul = utilDB.getRestaurantUserList();
		User u;
		u = ul.get(0);
		assertEquals(1, u.getId());
		assertEquals("Sals101", u.getUsername());
	}
	
	@Test
	@Order(3)
	public void testCreateUserCustomer() {
		utilDB.createUser("testCustomer", "test1234", false);
		List<User> ul = utilDB.getCustomerUserList();
		User u = ul.get(ul.size() - 1);
		assertEquals("testCustomer", u.getUsername());
	}
	
	@Test
	@Order(4)
	public void testCreateUserRestaurant() {
		utilDB.createUser("testRestaurant", "test1234", true);
		List<User> ul = utilDB.getRestaurantUserList();
		User u = ul.get(ul.size() - 1);
		assertEquals("testRestaurant", u.getUsername());
	}
}
