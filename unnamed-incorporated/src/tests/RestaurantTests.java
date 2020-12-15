package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import datamodel.Resturant;
import util.utilDB;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestaurantTests {

	@Test
	@Order(1)
	public void testGetResturant() {
		Resturant r = utilDB.getResturant((Integer) 1);
		assertEquals(1, r.getrID());
		assertEquals("Sal's Pizzaria", r.getRname());
		assertEquals("We are a pizza resturant from a long line of italians. We serve all sorts of pizzas", r.getDesc());
		assertEquals("1234 Some St", r.getAddr());
		assertEquals("Omaha", r.getCity());
		assertEquals("NE", r.getState());
		assertEquals("Monday-Saturday 9-3\nSunday closed", r.getHours());
	}

	@Test
	@Order(2)
	public void testCreateResturant() {
		utilDB.createRes(99, "test", "this is a test", "1234 Test Ave", "Omaha", "NE", "Always Closed");
		Resturant r = utilDB.getResturant(99);
		assertEquals(99, r.getrID());
		assertEquals("test", r.getRname());
		assertEquals("this is a test", r.getDesc());
		assertEquals("1234 Test Ave", r.getAddr());
		assertEquals("Omaha", r.getCity());
		assertEquals("NE", r.getState());
		assertEquals("Always Closed", r.getHours());
	}
	
	@Test
	@Order(3)
	public void testGetResturantList() {
		List<Resturant> rl = utilDB.getResturantList();
		Resturant r;
		r = rl.get(0);
		assertEquals(1, r.getrID());
		assertEquals("Sal's Pizzaria", r.getRname());
		assertEquals("We are a pizza resturant from a long line of italians. We serve all sorts of pizzas", r.getDesc());
		assertEquals("1234 Some St", r.getAddr());
		assertEquals("Omaha", r.getCity());
		assertEquals("NE", r.getState());
		assertEquals("Monday-Saturday 9-3\nSunday closed", r.getHours());
		r = rl.get(1);
		assertEquals(99, r.getrID());
		assertEquals("test", r.getRname());
		assertEquals("this is a test", r.getDesc());
		assertEquals("1234 Test Ave", r.getAddr());
		assertEquals("Omaha", r.getCity());
		assertEquals("NE", r.getState());
		assertEquals("Always Closed", r.getHours());
	}
}
