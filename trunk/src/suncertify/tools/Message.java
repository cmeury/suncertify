package suncertify.tools;

import java.util.logging.Logger;

/**
 * Presents text to the user
 * @author Ced
 *
 */
public class Message {

	private static Logger logger = Logger.getLogger("suncertify");

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
	
	public static Logger getLogger() {
		return logger;
	}

}
