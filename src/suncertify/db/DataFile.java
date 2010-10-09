package suncertify.db;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import suncertify.db.Record.Deleted;
import suncertify.db.Record.Smoking;
import suncertify.tools.Message;

/**
 * Opens database file and stores schema
 * From JDK documentation: 
 * "DataInputStream is not necessarily safe for multithreaded access. Thread safety is
 * optional and is the responsibility of users of methods in this class."
 *
 */
public class DataFile {
	
	private File databaseFile;
	private int recordLength;
	private Schema schema;
	private List<Record> records;
	
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
		records = new ArrayList<Record>();
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
		
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(databaseFile);
			DataInputStream dataInputStream = new DataInputStream(fileInputStream);
			// Reading and verifying cookie, length of records and number of fields in schema
			int magicCookie = dataInputStream.readInt();
			if(magicCookie != 257) {
				throw new Exception("Not a proper datafile, magic cookie mismatch");
			}
			recordLength = dataInputStream.readInt();
			short numberOfFields = dataInputStream.readShort();
			
			long headerSize = 10;
			
			// Looping through fields of db schema
			for(int c = 0; c < numberOfFields; c++) {
				int fieldNameLength = dataInputStream.readShort();
				headerSize += 4 + fieldNameLength;
				String nameBytes = readString(dataInputStream, fieldNameLength);
				short fieldLength = dataInputStream.readShort();
				this.schema.add(nameBytes, fieldLength);
			}
			
			// Loop through the records
			
			// Calculate how many records are present in the file
			long fileSize = databaseFile.length();			// in bytes
			long recordsSize = fileSize - headerSize;		// by requirements
			
			if(recordsSize % (recordLength + 1) != 0) { // plus one to include "deleted" flag
				throw new Exception("Records malformatted in database file.");
			}
			long recordCount = recordsSize / recordLength;
			for(int c = 0; c < recordCount; c++) {
				byte deletedByte = dataInputStream.readByte();
				Record.Deleted isDeleted;
				if(deletedByte == 1) {
					isDeleted = Deleted.YES;
				} else if(deletedByte  == 0) {
					isDeleted = Deleted.NO;
				}
				else {
					throw new Exception("Bad deleted flag in in database file.");
				}

				String name = readString(dataInputStream, 64);
				String location = readString(dataInputStream, 64);
				String size = readString(dataInputStream, 4);

				byte smokingByte = dataInputStream.readByte();
				Record.Smoking smokingAllowed;
				if(smokingByte == 'Y') {
					smokingAllowed = Smoking.ALLOWED;
				} else if(smokingByte  == 'N') {
					smokingAllowed = Smoking.NOTALLOWED;
				}
				else {
					throw new Exception("Bad deleted flag in in database file.");
				}

				String rate = readString(dataInputStream, 8);
				
				String dateString = readString(dataInputStream, 10);
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = dateFormat.parse(dateString);
				
				String id = readString(dataInputStream, 8);
				Record record = new Record(isDeleted, name, location, size, smokingAllowed, rate, date, id);
				records.add(record);
			}
			
		} catch(EOFException e) {
			Message.error("Unexpected end of data file encountered.", e);
		} catch(Exception e) {
			Message.error("Unable to parse data file.", e);
		} finally {
			fileInputStream.close();
		}
	}

	private String readString(DataInputStream dataInputStream,
			int fieldNameLength) throws IOException {
		byte[] nameBytes = new byte[fieldNameLength];
		dataInputStream.readFully(nameBytes,0,fieldNameLength);
		StringBuffer sb = new StringBuffer();
		for(int c = 0; c < nameBytes.length; c++) {
			sb.append((char) nameBytes[c]);
		}
		return sb.toString();
	}
}
