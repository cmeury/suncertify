package suncertify.db;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Opens database file and stores schema
 * From JDK documentation: 
 * "DataInputStream is not necessarily safe for multithreaded access. Thread safety is
 * optional and is the responsibility of users of methods in this class."
 *
 */
public class DataFile {
	
	private File databaseFile = null;
	private int recordLength = 0;
	private Schema schema = null;
	
	public DataFile(File file) {
		this.databaseFile = file;
		this.schema = new Schema();
	}

	public int getRecordLength() {
		return recordLength;
	}

	public Schema getSchema() {
		return schema;
	}

	public void open() throws Exception {
		if(this.databaseFile == null) {
			throw new Exception("No database file specified");
		}
		if(this.databaseFile.exists() == false) {
			throw new Exception("Database file not found");
		}
		if(this.databaseFile.canRead() == false) {
			throw new Exception("Cannot read specified database file");
		}
		if(this.databaseFile.canWrite() == false) {
			throw new Exception("Cannot write to specified database file");
		}
		
		try {
			FileInputStream fileInputStream = new FileInputStream(databaseFile);
			DataInputStream dataInputStream = new DataInputStream(fileInputStream);
			
			// Reading cookie, length of records and number of fields in schema
			
			int magicCookie = dataInputStream.readInt();
			if(magicCookie != 257) {
				throw new Exception("Not a proper datafile, magic cookie mismatch");
			}
			recordLength = dataInputStream.readInt();
			short numberOfFields = dataInputStream.readShort();
						
			// Looping through fields of db schema
			
			for(int c = 0; c < numberOfFields; c++) {
				int fieldNameLength = dataInputStream.readShort();
				byte[] nameBytes = new byte[fieldNameLength];
				dataInputStream.readFully(nameBytes,0,fieldNameLength);
				short fieldLength = dataInputStream.readShort();
				this.schema.add(new String(nameBytes), fieldLength);
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
