package suncertify.db;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import suncertify.tools.Message;

/**
 * Opens database file and stores schema
 * From JDK documentation: 
 * "DataInputStream is not necessarily safe for multithreaded access. Thread safety is
 * optional and is the responsibility of users of methods in this class."
 *
 */
public class DataFileParser {
	
	private File databaseFile;
	private int recordLength;
	private String[][] records;
	private boolean[] deleted;

	public DataFileParser(File file) {
		this.databaseFile = file;
	}

	/**
	 * Returns a row of data from the data file
	 * @param index row index of the data that should be retrieved
	 * @return a string array containing the data specified by index
	 * @throws RecordNotFoundException when the specified index does not return valid data
	 */
	public String[] getRecord(int index) throws RecordNotFoundException {
		if(records.length == 0 || index > records.length) {
			throw new RecordNotFoundException("Row " + index + " not in data file.");
		}
		if(records[index] == null || records[index].length == 0) {
			throw new RecordNotFoundException("Not data in row " + index);
		}
		return records[index];
	}
	
	public String getField(int index, int fieldNumber) {
		return records[index][fieldNumber];
	}
	
	public String[][] getAllRecords() {
		return records;
	}
	
	public int getRecordLength() {
		return recordLength;
	}

	public boolean isNotDeleted(int recNo) {
		return !deleted[recNo];
	}
	
	public void parse() throws Exception {
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
		
		Message.getLogger().info("Parsing the data file " + databaseFile.getAbsolutePath());
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(databaseFile);
			DataInputStream dataInputStream = new DataInputStream(fileInputStream);
			// Reading and verifying cookie, length of records and number of fields in schema
			int magicCookie = dataInputStream.readInt();
			if(magicCookie != Schema.getMagicCookie()) {
				throw new Exception("Not a proper datafile, magic cookie mismatch");
			}
			recordLength = dataInputStream.readInt();
			short numberOfFields = dataInputStream.readShort();

			long headerSize = 10;
			
			// Looping through fields of DB schema
			for(int c = 0; c < numberOfFields; c++) {
				int fieldNameLength = dataInputStream.readShort();
				headerSize += 4 + fieldNameLength;
				// read field name, no need to store it
				readString(dataInputStream, fieldNameLength);
				// read field length, no need to store it
				dataInputStream.readShort();
				//this.schema.add(nameBytes, fieldLength);
			}
			
			// Calculate how many records are present in the file
			long fileSize = databaseFile.length();			// in bytes
			long recordsSize = fileSize - headerSize;		// by requirements
			
			if(recordsSize % (recordLength + 1) != 0) { // plus one to include "deleted" flag
				throw new Exception("Records malformatted in database file.");
			}
			long recordCount = recordsSize / recordLength;
			
			// Check and convert count of records to integer for creation of the array
			if(recordCount > Integer.MAX_VALUE) {
				throw new Exception("Number of records too large: " + recordCount);
			} 
			int recordCountInt = (int) recordCount;
			this.records = new String[recordCountInt][numberOfFields];	

			deleted = new boolean[recordCountInt];
			
			// Loop through the records
			for(int c = 0; c < recordCountInt; c++) {
				// Deleted Flag
				byte deletedByte = dataInputStream.readByte();
				if(deletedByte == 1) {
					deleted[c] = true;
				} else if(deletedByte == 0) {
					deleted[c] = false;
				} else {
					throw new Exception("Bad deleted flag in in database file.");
				}
				
				// Name
				records[c][0] = readString(dataInputStream, 64);
				
				// Location
				records[c][1] = readString(dataInputStream, 64);
				
				// Size
				records[c][2] = readString(dataInputStream, 4);

				// Smoking Flag
				byte smokingByte = dataInputStream.readByte();
				if(smokingByte != 'Y' && smokingByte  != 'N') {
					throw new Exception("Bad deleted flag in in database file.");
				}
				records[c][3] = (new Character((char) smokingByte)).toString();
				
				// Rate
				records[c][4] = readString(dataInputStream, 8);

				// Date
				records[c][5] = readString(dataInputStream, 10);
				
				// ID
				records[c][6] = readString(dataInputStream, 8);
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
