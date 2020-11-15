package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Resturant")
public class Resturant {
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rID") // specify the column name. Without it, it will use method name
	private Integer rID;
	
	@Column(name="rname")
	private String rname;
	
	@Column(name="description")
	private String desc;
	
	@Column(name="addr")
	private String addr;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="hours")
	private String hours;
	
	
	public Resturant(Integer rID, String rname, String desc, String addr, String city, String state, String hours) {
		super();
		this.rID = rID;
		this.rname = rname;
		this.desc = desc;
		this.addr = addr;
		this.city = city;
		this.state = state;
		this.hours = hours;
	}

	

	public Resturant(String rname, String desc, String addr, String city, String state, String hours) {
		super();
		this.rname = rname;
		this.desc = desc;
		this.addr = addr;
		this.city = city;
		this.state = state;
		this.hours = hours;
	}



	public Integer getrID() {
		return rID;
	}

	public void setrID(Integer rID) {
		this.rID = rID;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}
	
	
	
	
	

}
