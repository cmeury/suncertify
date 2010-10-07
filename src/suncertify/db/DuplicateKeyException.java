package suncertify.db;

/**
 * Gets thrown when trying to add a data-set which already exists
 */
public class DuplicateKeyException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private String description;
	
	// Sets the description to the original exception message
	public DuplicateKeyException() {
		this.description = super.getMessage();
	}
	
	// Stores given string as description
	public DuplicateKeyException(String description) {
		this.description = description;
	}
	
	@Override
	public String getMessage() {
		return description;
	}
}
