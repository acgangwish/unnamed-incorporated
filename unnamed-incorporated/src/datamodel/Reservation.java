package datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "RESERVATIONS")
public class Reservation {

@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
@Column (name = "ID")
private int id;

@Column (name = "RESTAURANT_ID")
private int restaurantId;

@Column (name = "CUSTOMER_USERNAME")
private String customerUsername;

@Column (name = "DATE")
private String date;

@Column (name = "TIME")
private String time;

@Column (name = "NUMPEOPLE")
private int numPeople;

public Reservation(int id, int restaurantId, String customerUsername, String date, String time, int numPeople) {
	super();
	this.id = id;
	this.restaurantId = restaurantId;
	this.customerUsername = customerUsername;
	this.date = date;
	this.time = time;
	this.numPeople = numPeople;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getRestaurantId() {
	return restaurantId;
}

public void setRestaurantId(int restaurantId) {
	this.restaurantId = restaurantId;
}

public String getCustomerUsername() {
	return customerUsername;
}

public void setCustomerUsername(String customerUsername) {
	this.customerUsername = customerUsername;
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

public String getTime() {
	return time;
}

public void setTime(String time) {
	this.time = time;
}

public int getNumPeople() {
	return numPeople;
}

public void setNumPeople(int numPeople) {
	this.numPeople = numPeople;
}


}
