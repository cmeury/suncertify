package suncertify.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import suncertify.db.Record;
import suncertify.db.Schema;

public class RecordsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<Record> resultRecords;
	
	public void setResultRecords(List<Record> records) {
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
		if(resultRecords == null) {
			return null;
		}
		switch(columnIndex) {
		case 0:
			return resultRecords.get(rowIndex).getName();
		case 1:
			return resultRecords.get(rowIndex).getLocation();
		case 2:
			return resultRecords.get(rowIndex).getSize();
		case 3:
			return resultRecords.get(rowIndex).isSmokingAllowed();
		case 4:
			return resultRecords.get(rowIndex).getRate();
		case 5:
			return resultRecords.get(rowIndex).getDate();
		case 6:
			return resultRecords.get(rowIndex).getOwner();
		default:
			return null;
		}
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return Schema.getFieldNames()[columnIndex];
	}
}
