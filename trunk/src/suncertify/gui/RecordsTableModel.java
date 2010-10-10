package suncertify.gui;

import javax.swing.table.AbstractTableModel;

import suncertify.tools.Strings;

public class RecordsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private String[][] results;
	
	public void setResults(String[][] results) {
		this.results = results;
	}
	
	@Override
	public int getColumnCount() {
		if(results == null) {
			return 0;
		}
		return Strings.getColumnNames().length;
	}

	@Override
	public int getRowCount() {
		if(results == null) {
			return 0;
		}
		return results.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(results == null) {
			return null;
		}
		return results[rowIndex][columnIndex];
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return Strings.getColumnNames()[columnIndex];
	}
}
