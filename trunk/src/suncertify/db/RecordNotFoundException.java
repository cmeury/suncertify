package suncertify.db;

/**
 * Is thrown when a specified record does not exist or is marked as deleted
 * in the database file.
 * 
 */
public class RecordNotFoundException extends Exception {
	
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
