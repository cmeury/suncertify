package suncertify;

import java.io.File;

import javax.swing.SwingUtilities;

import suncertify.gui.MainWindowController;
import suncertify.gui.MainWindowModel;
import suncertify.gui.MainWindowView;

/**
 * Creates needed objects and starts the application
 *
 */
public class Application {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				File existingFile = new File("srcTest/suncertify/db/db-1x1.db");

				MainWindowModel model = new MainWindowModel(existingFile);
				MainWindowView view = new MainWindowView(model);
				@SuppressWarnings("unused")
				MainWindowController controller = new MainWindowController(model, view);
				
				view.setVisible(true);
			}
		});
	}
}
