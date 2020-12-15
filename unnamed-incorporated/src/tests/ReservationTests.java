package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import datamodel.Reservation;
import util.utilDB;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationTests {

	@Test
	@Order(1)
	public void testGetReservationCust() {
		List<Reservation> rl = utilDB.getReservationCust(1);
		Reservation r;
		r = rl.get(0);
		assertEquals(1, r.getId());
		assertEquals(1, r.getCustomerID());
		assertEquals("Michael Menard", r.getReservationName());
		assertEquals(1, r.getRestaurantId());
		assertEquals("02/24/1999", r.getDate());
		assertEquals("6:00 PM", r.getTime());
		assertEquals(10, r.getNumPeople());
		r = rl.get(1);
		assertEquals(2, r.getId());
		assertEquals(1, r.getCustomerID());
		assertEquals("Michael Menard", r.getReservationName());
		assertEquals(1, r.getRestaurantId());
		assertEquals("02/24/1999", r.getDate());
		assertEquals("6:00 PM", r.getTime());
		assertEquals(10, r.getNumPeople());
	}
	
	@Test
	@Order(2)
	public void testGetReservationRest() {
		List<Reservation> rl = utilDB.getReservationRest(1);
		Reservation r;
		r = rl.get(0);
		assertEquals(1, r.getId());
		assertEquals(1, r.getCustomerID());
		assertEquals("Michael Menard", r.getReservationName());
		assertEquals(1, r.getRestaurantId());
		assertEquals("02/24/1999", r.getDate());
		assertEquals("6:00 PM", r.getTime());
		assertEquals(10, r.getNumPeople());
		r = rl.get(2);
		assertEquals(3, r.getId());
		assertEquals(2, r.getCustomerID());
		assertEquals("Thing", r.getReservationName());
		assertEquals(1, r.getRestaurantId());
		assertEquals("02/24/1999", r.getDate());
		assertEquals("8:00 PM", r.getTime());
		assertEquals(3, r.getNumPeople());
	}
	
	@Test
	@Order(3)
	public void testCreateReservation() {
		utilDB.createReservation(98, 99, "test", "2020-12-14", "20:20", 20);
		List<Reservation> rl = utilDB.getReservationCust(99);
		Reservation r = rl.get(0);
		assertEquals(98, r.getRestaurantId());
		assertEquals(99, r.getCustomerID());
		assertEquals("test", r.getReservationName());
		assertEquals("2020-12-14", r.getDate());
		assertEquals("20:20", r.getTime());
		assertEquals(20, r.getNumPeople());
		rl = utilDB.getReservationRest(98);
		r = rl.get(0);
		assertEquals(98, r.getRestaurantId());
		assertEquals(99, r.getCustomerID());
		assertEquals("test", r.getReservationName());
		assertEquals("2020-12-14", r.getDate());
		assertEquals("20:20", r.getTime());
		assertEquals(20, r.getNumPeople());
	}
}
