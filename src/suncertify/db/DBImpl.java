package suncertify.db;


public class DBImpl implements DB {


	
	@Override
	public String[] read(int recNo) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
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
