package suncertify.tools;

/**
 * Provides commonly used strings from a centralized location to ease maintenance
 *
 */
public class Strings {
	private static final String applicationName = "URLyBird Booking System";
	private static final String mainWindowLabel = "Following is the list of records in the data file:";
	private static final String[] columnNames = {
			"Deleted",
			"Name",
			"City",
			"Size",
			"Smoking",
			"Rate",
			"Date",
			"Owner",
			};

	public static String[] getColumnNames() {
		return columnNames;
	}

	public static String getMainwindowlabel() {
		return mainWindowLabel;
	}

	public static String getApplicationName() {
		return applicationName;
	}
	
}
