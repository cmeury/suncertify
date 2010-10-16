package suncertify.db;

import java.util.Date;

/**
 * A database record in the format suitable outside the db access code, the fields
 * are defined using proper data types.
 * @author Ced
 *
 */
public class Record {
	
	public enum Smoking {
		ALLOWED, NOTALLOWED;
	}
	
	private String name;
	private String location;
	private String size;
	private Smoking smokingAllowed;
	private String rate;
	private Date date;
	private String owner;
	
	public Record(String name, String location, String size,
			Smoking smokingAllowed, String rate, Date date, String owner) {
		super();
		this.name = name;
		this.location = location;
		this.size = size;
		this.smokingAllowed = smokingAllowed;
		this.rate = rate;
		this.date = date;
		this.owner = owner;
	}
	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public String getSize() {
		return size;
	}

	public Smoking isSmokingAllowed() {
		return smokingAllowed;
	}

	public String getRate() {
		return rate;
	}

	public Date getDate() {
		return date;
	}

	public String getOwner() {
		return owner;
	}
	
	@Override
	public String toString() {
		String div = " / ";
		return 	"Name: " + name + div +
				"Location: " + location + div +
				"Size: " + size + div +
				"Smoking: " + smokingAllowed + div +
				"Rate: " + rate + div +
				"Date: " + date + div +
				"Owner: " + owner + div;
	}
}
