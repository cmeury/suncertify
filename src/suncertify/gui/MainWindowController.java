package suncertify.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		this.model = model;
		this.view = view;
		this.view.addSearchListener(new SearchListener());
		this.view.addSearchDocumentListener(new SearchDocumentListener());
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				searchAction();
			}
		});
	}
	
	private void searchAction() {
		Message.getLogger().info("Search text changed or search button pressed");
		model.search(view.getColumnComboBoxIndex(), view.getSearchText());
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

	private class SearchListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			searchAction();
		}
	}
	
}
