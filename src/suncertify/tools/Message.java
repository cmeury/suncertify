package suncertify.tools;

/**
 * Presents text to the user
 * @author Ced
 *
 */
public class Message {

	private static String newline = System.getProperty("line.separator");
	
	public static void warning(String message) {
		System.err.println(message);
	}
	
	public static void error(String message, Exception e) {
		System.err.println(message + newline);
		e.printStackTrace();
	}

	public static void error(Exception e) {
		System.err.println("Application encountered a fatal error." + newline);
		e.printStackTrace();
	}
}
