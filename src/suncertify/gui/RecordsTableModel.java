package suncertify.gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import suncertify.db.Record;
import suncertify.db.Schema;

public class RecordsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Record> resultRecords = new ArrayList<Record>();
	
	public void setRecords(List<Record> records) {
		if(records == null) {
			throw new NullPointerException("Cannot update table model with an empty collection");
		}
		this.resultRecords = records;
		fireTableDataChanged();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex < 0 ) {
			throw new IllegalArgumentException("Requested column is out of bounds");
		}
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return Boolean.class;
		case 4:
			return String.class;
		case 5:
			return Date.class;
		case 6:
			return String.class;
		default:
			return null;
		}
	}
	@Override
	public int getColumnCount() {
		return Schema.getFieldCount();
	}

	@Override
	public int getRowCount() {
		return resultRecords.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex < 0 || columnIndex < 0 || rowIndex > resultRecords.size()) {
			throw new IllegalArgumentException("Requested table value is out of bounds");
		}
		Record record = resultRecords.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return record.getName().trim();
		case 1:
			return record.getLocation().trim();
		case 2:
			return record.getSize().trim();
		case 3:
			return record.isSmokingAllowed();
		case 4:
			return record.getRate().trim();
		case 5:
			return record.getDate();
		case 6:
			return record.getOwner().trim();
		default:
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return Schema.getFieldNames(column);
	}

}
