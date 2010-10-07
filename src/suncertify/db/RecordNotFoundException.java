package suncertify.db;

/**
 * Is thrown when a specified record does not exist or is marked as deleted
 * in the database file.
 * 
 */
public class RecordNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String description;
	
	// Sets the description to the original exception message
	public RecordNotFoundException() {
		this.description = super.getMessage();
	}
	
	// Stores given string as description
	public RecordNotFoundException(String description) {
		this.description = description;
	}
	
	@Override
	public String getMessage() {
		return description;
	}
}
