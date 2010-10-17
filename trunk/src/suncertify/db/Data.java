package suncertify.db;

import static org.junit.Assert.fail;

import java.io.File;

import suncertify.tools.Message;


public class Data implements DB {
	private DataFileParser dataFileParser;
	
	public Data(File fileName) {
		if(fileName == null) {
			throw new NullPointerException("Path to data file is needed");
		}
		this.dataFileParser = new DataFileParser(fileName);
		try {
			dataFileParser.parse();
		} catch (Exception e) {
			fail("Must not throw exception with existing file: " + e.getMessage());
		}
	}
	
	@Override
	public String[] read(int recNo) throws RecordNotFoundException {
		String[] record = dataFileParser.getRecord(recNo);
		return record;
	}

	@Override
	public void update(int recNo, String[] data, long lockCookie)
			throws RecordNotFoundException, SecurityException {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(int recNo, long lockCookie)
			throws RecordNotFoundException, SecurityException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] find(String[] criteria) {
		if(criteria == null) {
			throw new NullPointerException("To find records, criteria have to be given.");
		}
		if(criteria.length != Schema.getFieldCount()) {
			throw new IllegalArgumentException("Invalid criteria array");
		}

		Message.infoToLog("Trying to find records based on following criteria: " + criteria.toString());
		
		String[][] allRecords = dataFileParser.getAllRecords();
		//Set<Integer> resultIndices = new TreeSet<Integer>();
		boolean[] match = new boolean[allRecords.length];
		for(int i = 0; i < allRecords.length; i++) {
			match[i] = true;
		}
		
		// loop through the fields
		for(int col = 0; col < Schema.getFieldCount(); col++) {
			// when the criterion is null, we just leave the matchingRows list as it is
			if(criteria[col] != null) {
				String search = criteria[col].toLowerCase().trim();

				// looping through rows and explicitely set to true or false depending on the matching
				for(int row = 0; row < allRecords.length; row++) {
					String field = allRecords[row][col].toLowerCase().trim();
					if(field.startsWith(search)) {
						match[row] = true;
					} else {
						match[row] = false;
					}
				}
			
			}
		}

		// as the interface dictates, we have to convert back to primitive types
		int matchCount = 0;
		for(int i = 0; i < allRecords.length; i++) {
			if(match[i] == true && !dataFileParser.isDeleted(i)) {
				matchCount++;
			}
		}

		// if we have not found any matches return an empty array
		if(matchCount == 0) {
			return new int[0];
		}
		
		// loop through the boolean array and fill up the result rows array with content
		int index = 0;
		int[] matchingRows = new int[matchCount];
		for(int i = 0; i < allRecords.length; i++) {
			if(match[i] == true && !dataFileParser.isDeleted(i)) {
				matchingRows[index] = i;
				index++;
			}
		}
		return matchingRows;
	} 

	@Override
	public int create(String[] data) throws DuplicateKeyException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long lock(int recNo) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void unlock(int recNo, long cookie) throws RecordNotFoundException,
			SecurityException {
		// TODO Auto-generated method stub

	}

}
