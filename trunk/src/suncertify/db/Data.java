package suncertify.db;

import static org.junit.Assert.fail;

import java.io.File;


public class Data implements DB {
	private DataFileParser dataFileParser;
	
	public Data(File fileName) {
		assert fileName != null;
		this.dataFileParser = new DataFileParser(fileName);
		try {
			dataFileParser.open();
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
		// at the moment this method returns all row indices irrespective of the criteria argument
		String[][] allRecords = dataFileParser.getAllRecords();
		int length = allRecords.length;
		int[] results = new int[length];
		for(int c = 0; c < length; c++) {
			results[c] = c;
		}
		return results;
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
