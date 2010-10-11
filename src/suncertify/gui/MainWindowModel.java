package suncertify.gui;

import java.io.File;

import javax.swing.table.TableModel;

import suncertify.db.DB;
import suncertify.db.Data;
import suncertify.db.RecordNotFoundException;
import suncertify.db.Schema;
import suncertify.tools.Message;

/**
 * Provides the view with data to display
 *
 */
public class MainWindowModel {
	private DB db;
	
	private RecordsTableModel recordsTableModel;
	
	public MainWindowModel(File fileName) {
		assert fileName != null;
		this.db = new Data(fileName);
		this.recordsTableModel = new RecordsTableModel();
	}

	public DB getDB() {
		return this.db;
	}
	
	public TableModel getTableModel() {
		return this.recordsTableModel;
	}
	
	public synchronized void search(int columnIndex, String searchString) {
		String[] criteria = new String[Schema.getFieldCount()];
		if(searchString.equals("")) {
			criteria[columnIndex] = null;
		} else {
			criteria[columnIndex] = searchString;
		}
		int[] resultIndices = db.find(criteria);
		String[][] results = new String[resultIndices.length][Schema.getFieldCount()];
		for(int c=0; c < resultIndices.length; c++) {
			try {
				results[c] = db.read(resultIndices[c]);
			} catch (RecordNotFoundException e) {
				Message.error("Could not find result index in database", e);
			}
		}
		Message.getLogger().fine("Updating table model and firing a change event");
		recordsTableModel.setResults(results);
		recordsTableModel.fireTableStructureChanged();		// hardcore, should be done more fine-grained
	}
	
	public void showAllRecords() {
		search(0, "");
	}
	
}

