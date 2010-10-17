package suncertify.db;


/**
 * Information about the file format as specified by the assignment
 * 
 */
public class Schema {

	private static final String[] fieldNames = { "Name", "City",
			"Size", "Smoking", "Rate", "Date", "Owner", };

	private static final int magicCookie = 257;

	public static int getMagicCookie() {
		return magicCookie;
	}

	public static String getFieldNames(int index) {
		return fieldNames[index];
	}

	public static int getFieldCount() {
		return fieldNames.length;
	}
}
