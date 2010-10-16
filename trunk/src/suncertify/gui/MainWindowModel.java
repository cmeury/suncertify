package suncertify.gui;

import java.io.File;

import javax.swing.table.TableModel;

import suncertify.db.DB;
import suncertify.db.Data;
import suncertify.db.DataProxy;
import suncertify.tools.Message;

/**
 * Provides the view with data to display
 *
 */
public class MainWindowModel {
	private DataProxy dataProxy;
	private RecordsTableModel recordsTableModel;
	private boolean caseSensitive;
	
	public MainWindowModel(File fileName) {
		assert fileName != null;
		DB db = new Data(fileName);
		this.dataProxy = new DataProxy(db);
		this.recordsTableModel = new RecordsTableModel();
	}
	
	public TableModel getTableModel() {
		return this.recordsTableModel;
	}
	
	public synchronized void search(String name, String location) {
		if(name == null || location == null) {
			throw new NullPointerException("Need String objects to perform a search");
		}
		Message.infoToLog("Updating table model and firing a change event");
		if(caseSensitive == false) {
			name = name.toLowerCase();
			location = location.toLowerCase();
		}
		recordsTableModel.setResultRecords(dataProxy.getFilteredRecords(name, location, caseSensitive));
		updateTable();
	}

	public void updateTable() {
		recordsTableModel.fireTableStructureChanged();		// hardcore, should be done more fine-grained
	}
	
	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

}

