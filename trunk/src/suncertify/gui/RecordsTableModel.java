package suncertify.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import suncertify.db.Record;
import suncertify.db.Schema;

public class RecordsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<Record> resultRecords;
	
	public void setResultRecords(List<Record> records) {
		if(records == null) {
			throw new NullPointerException("Cannot update table model with an empty collection");
		}
		this.resultRecords = records;
	}

	@Override
	public int getColumnCount() {
		if(resultRecords == null) {
			return 0;
		}
		return Schema.getFieldCount();
	}

	@Override
	public int getRowCount() {
		if(resultRecords == null) {
			return 0;
		}
		return resultRecords.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex < 0 || columnIndex < 0) {
			throw new IllegalArgumentException("Requested table value is out of bounds");
		}
		if(resultRecords == null) {
			return null;
		}
		switch(columnIndex) {
		case 0:
			return resultRecords.get(rowIndex).getName().trim();
		case 1:
			return resultRecords.get(rowIndex).getLocation().trim();
		case 2:
			return resultRecords.get(rowIndex).getSize().trim();
		case 3:
			return resultRecords.get(rowIndex).isSmokingAllowed();
		case 4:
			return resultRecords.get(rowIndex).getRate().trim();
		case 5:
			return resultRecords.get(rowIndex).getDate();
		case 6:
			return resultRecords.get(rowIndex).getOwner().trim();
		default:
			return null;
		}
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		if(columnIndex < 0 || columnIndex > Schema.getFieldCount()) {
			throw new IllegalArgumentException("Specified column index is outside of available columns");
		}
		return Schema.getFieldNames()[columnIndex];
	}
}
