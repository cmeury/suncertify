package suncertify.gui;

import java.io.File;

import javax.swing.table.TableModel;

import suncertify.db.DB;
import suncertify.db.Data;

/**
 * Provides the view with data to display
 *
 */
public class MainWindowModel {
	private DB db;
	
	public MainWindowModel(File fileName) {
		assert fileName != null;
		this.db = new Data(fileName);
		
	}

	public DB getDB() {
		return this.db;
	}
	
	public TableModel getTableModel() {
		return new RecordsTableModel(this.db);
	}
}

