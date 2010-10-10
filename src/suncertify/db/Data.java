package suncertify.db;

import static org.junit.Assert.fail;

import java.io.File;


public class Data implements DB {
	private DataFileParser dataFile;
	
	public Data(File fileName) {
		assert fileName != null;
		this.dataFile = new DataFileParser(fileName);
		try {
			dataFile.open();
		} catch (Exception e) {
			fail("Must not throw exception with existing file: " + e.getMessage());
		}
	}
	
	@Override
	public String[] read(int recNo) throws RecordNotFoundException {
		String[][] records = dataFile.getRecords();
		return records[recNo];
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
		// TODO Auto-generated method stub
		return null;
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
