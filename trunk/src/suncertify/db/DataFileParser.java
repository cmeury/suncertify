package suncertify.db;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	private Schema schema;
	private String[][] records;

	public String[][] getRecords() {
		return records;
	}

	public DataFileParser(File file) {
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
		
		Message.getLogger().info("Trying to open data file " + databaseFile.getAbsolutePath());
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

			List<String[]> dynamicRecords = new ArrayList<String[]>();

			for(int c = 0; c < recordCount; c++) {
				String[] currentRecord = new String[numberOfFields];
				
				// Deleted Flag
				byte deletedByte = dataInputStream.readByte();
				if(deletedByte != 1 && deletedByte != 0) {
					throw new Exception("Bad deleted flag in in database file.");
				}
				currentRecord[0] = (new Character((char) deletedByte)).toString();
				
				// Name
				currentRecord[1] = readString(dataInputStream, 64);
				
				// Location
				currentRecord[2] = readString(dataInputStream, 64);
				
				// Size
				currentRecord[3] = readString(dataInputStream, 4);

				// Smoking Flag
				byte smokingByte = dataInputStream.readByte();
				if(smokingByte != 'Y' && smokingByte  != 'N') {
					throw new Exception("Bad deleted flag in in database file.");
				}
				currentRecord[4] = (new Character((char) smokingByte)).toString();
				
				// Rate
				currentRecord[5] = readString(dataInputStream, 8);
				
//				String dateString = readString(dataInputStream, 10);
//				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//				Date date = dateFormat.parse(dateString);
				
				// Date
				currentRecord[6] = readString(dataInputStream, 10);
				
				// ID
				currentRecord[7] = readString(dataInputStream, 8);
				
				dynamicRecords.add(currentRecord);
			}
			
			this.records = (String[][]) dynamicRecords.toArray();
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
