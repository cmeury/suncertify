package suncertify.db;

/**
 * Represents one field of the database file schema
 * 
 */
public class Field {
	
	private String name;
	private int fieldLength;
	
	public Field(String name, int fieldLength) {
		this.name = name;
		this.fieldLength = fieldLength;
	}
	
	public String getName() {
		return name;
	}

	public int getFieldLength() {
		return fieldLength;
	}
	
	@Override
	public String toString() {
		return name + "(" + fieldLength + ")";
	}
}
