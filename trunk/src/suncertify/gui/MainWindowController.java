package suncertify.gui;


/**
 * Sets up and displays the main frame
 *
 */
public class MainWindowController {
	private MainWindowModel model;
	private MainWindowView view;
	
	public MainWindowController(MainWindowModel model, MainWindowView view) {
		this.model = model;
		this.view = view;
	}
}
