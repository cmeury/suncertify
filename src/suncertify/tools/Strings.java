package suncertify.tools;

/**
 * Provides commonly used strings from a centralized location to ease maintenance
 *
 */
public class Strings {
	private static final String applicationName = "URLyBird Booking System";
	private static final String mainWindowIntroLabelText = "Following is the list of records in the data file:";
	private static final String mainWindowSearchLabelText = "Full Text Search: ";
	private static final String mainWindowSearchButtonText = "Search";
	
	public static String getMainWindowSearchButtonText() {
		return mainWindowSearchButtonText;
	}

	public static String getMainWindowIntroLabelText() {
		return mainWindowIntroLabelText;
	}
	
	public static String getMainWindowSearchLabelText() {
		return mainWindowSearchLabelText;
	}

	public static String getApplicationName() {
		return applicationName;
	}
	
}
