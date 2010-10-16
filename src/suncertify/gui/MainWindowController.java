package suncertify.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import suncertify.tools.Message;


/**
 * Sets up and displays the main frame
 *
 */
public class MainWindowController {
	private MainWindowModel model;
	private MainWindowView view;
	
	public MainWindowController(MainWindowModel model, MainWindowView view) {
		if(model == null) {
			throw new NullPointerException("Cannot construct controller without a model");
		}
		if(view == null) {
			throw new NullPointerException("Cannot construct controller without a view");
		}
		this.model = model;
		this.view = view;
		this.view.addSearchDocumentListener(new SearchDocumentListener());
		this.view.addCaseListener(new CaseCheckBoxListener());
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				searchAction();
			}
		});
	}
	
	private void searchAction() {
		Message.infoToLog("Search text changed or search button pressed");
		model.search(view.getNameSearchText(), view.getLocationSearchText());
	}

	private class SearchDocumentListener implements DocumentListener {
		@Override
		public void removeUpdate(DocumentEvent e) {
			searchAction();
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			searchAction();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			searchAction();
		}
	}
	
	private class CaseCheckBoxListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			switch(e.getStateChange()) {
			case ItemEvent.SELECTED:
				model.setCaseSensitive(true);
				break;
			case ItemEvent.DESELECTED:
				model.setCaseSensitive(false);
				break;
			}
			searchAction();
		}
	}
}
