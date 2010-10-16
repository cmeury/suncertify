package suncertify.tools;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * Presents warning and error message to the user and prints stack traces to the log
 */
public class Message {

	private static Logger logger = Logger.getLogger("suncertify");

	public static void warningToUserAndLog(String message) {
		logger.log(Level.WARNING, message);
		JOptionPane.showMessageDialog(null, message, Strings.getApplicationName() + " - Warning", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void errorToUserAndLog(String message, Exception e) {
		logger.log(Level.SEVERE, message, e);
		JOptionPane.showMessageDialog(null, message, Strings.getApplicationName() + " - Warning", JOptionPane.WARNING_MESSAGE);
	}

	public static void errorToUserAndLog(Exception e) {
		logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
		JOptionPane.showMessageDialog(null, "Application encountered a fatal error in method:\n" + e.getStackTrace()[0].toString(), "Fatal error.", JOptionPane.ERROR_MESSAGE);
	}

	public static void infoToLog(String message) {
		logger.info(message);
	}

	public static void warningToLog(String message) {
		logger.warning(message);
	}

}
