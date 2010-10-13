package suncertify.tools;

/**
 * Provides commonly used strings from a centralized location to ease maintenance
 *
 */
public class Strings {
	private static final String applicationName = "URLyBird Booking System";
	private static final String mainWindowIntroLabelText = "Following is the list of records in the data file:";
	private static final String mainWindownameLabelText = "Search for Name:";
	private static final String mainWindowlocationLabelText = "Search for Location:";
	private static final String mainWindowCaseCheckBoxText = "Case sensitive";
	
	public static String getMainwindowNameLabelText() {
		return mainWindownameLabelText;
	}
	
	public static String getMainWindowLocationLabelText() {
		return mainWindowlocationLabelText;
	}
	
	public static String getMainWindowIntroLabelText() {
		return mainWindowIntroLabelText;
	}
	
	public static String getApplicationName() {
		return applicationName;
	}
	
	public static String getMainWindowCaseCheckBoxText() {
		return mainWindowCaseCheckBoxText;
	}
}
