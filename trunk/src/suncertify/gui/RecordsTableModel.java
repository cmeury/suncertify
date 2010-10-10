package suncertify.gui;

import javax.swing.table.AbstractTableModel;

import suncertify.db.DB;
import suncertify.db.DataFileParser;
import suncertify.db.Schema;

public class RecordsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static DataFileParser dataFileParser;
	private Schema schema;
	private String[][] records;
	private DB db;
	
	public RecordsTableModel(DB db) {
		assert db != null;
		this.db = db;
		records = db.getRecords();
		schema = dataFileParser.getSchema();
	}
	@Override
	public int getColumnCount() {
		return records.length;
	}

	@Override
	public int getRowCount() {
		return records[0].length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return records[columnIndex][rowIndex];
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return schema.getName(columnIndex);
	}
}
