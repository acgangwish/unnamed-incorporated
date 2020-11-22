package datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class User {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID") // specify the column name. Without it, it will use method name
	private Integer UID;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "RESTAURANTORCUSTOMER") // value is 0 if the login is for a restaurant or 1 if the login is for a customer
	private int restOrCust;

	public User(String username, String password, int restOrCust) {
		super();
		this.username = username;
		this.password = password;
		this.restOrCust = restOrCust;
	}

	public Integer getUID() {
		return UID;
	}

	public void setUID(Integer uID) {
		UID = uID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRestOrCust() {
		return restOrCust;
	}

	public void setRestOrCust(int restOrCust) {
		this.restOrCust = restOrCust;
	}
	
	
}
