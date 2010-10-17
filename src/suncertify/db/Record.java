package suncertify.db;

import java.util.Date;

/**
 * A database record in the format suitable outside the db access code, the fields
 * are defined using proper data types.
 * @author Ced
 *
 */
public class Record {
	
	private String name;
	private String location;
	private String size;
	private boolean smokingAllowed;
	private String rate;
	private Date date;
	private String owner;
	private int recNo;
	
	public Record(String name, String location, String size,
			boolean smokingAllowed, String rate, Date date, String owner, int recNo) {
		super();
		this.name = name;
		this.location = location;
		this.size = size;
		this.smokingAllowed = smokingAllowed;
		this.rate = rate;
		this.date = date;
		this.owner = owner;
		this.recNo = recNo;
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

	public boolean isSmokingAllowed() {
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
	
	public int getRecNo() {
		return recNo;
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
