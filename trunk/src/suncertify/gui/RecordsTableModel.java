package suncertify.gui;

import javax.swing.table.AbstractTableModel;

import suncertify.db.DB;
import suncertify.db.RecordNotFoundException;
import suncertify.tools.Message;
import suncertify.tools.Strings;

public class RecordsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
//	private Schema schema;
	private DB db;

	
	public RecordsTableModel(DB db) {
		assert db != null;
		this.db = db;
//		schema = dataFileParser.getSchema();
	}
	@Override
	public int getColumnCount() {
		int length = 0;
		try {
			String[] firstRecord = null;
			firstRecord = db.read(0);
			length = firstRecord.length;
		} catch (RecordNotFoundException e) {
			Message.error("Cannot calculate column count", e);
		} 
		return length;
	}

	@Override
	public int getRowCount() {
		return db.find(null).length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String[] record = null;
		try {
			record = db.read(rowIndex);
		} catch (RecordNotFoundException e) {
			Message.error("Cannot get value at [" + rowIndex + "][" + columnIndex + "]", e);
		}
		return record[columnIndex];
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return Strings.getColumnNames()[columnIndex];
	}
}
