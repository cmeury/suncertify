package suncertify.db;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class DataFileParserTest {
	
	private final File existingFile = new File("srcTest/suncertify/db/db-1x1.db");

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDataFileParser() {
		// File exists
		File noFile = new File("funnylookingcamel.db");
		@SuppressWarnings("unused")
		DataFileParser datafile = null;
		try {
			datafile = new DataFileParser(noFile);
			fail("Must throw exception with non-existing file");
		} catch (Exception e) {
		}

		// File exists
		try {
			datafile = new DataFileParser(existingFile);
		} catch (Exception e) {
			fail("Must not throw exception with existing file: " + e.getMessage());
		}
	}

	@Test
	public void testGetRecord() {
		// File exists
		DataFileParser datafile = new DataFileParser(existingFile);
		try {
			datafile.parse();
		} catch (Exception e) {
			fail("Must not throw exception with existing file: " + e.getMessage());
		}
		String records[] = null;
		try {
			records = datafile.getRecord(0);
		} catch (RecordNotFoundException e) {
			fail("Record must be found");
		}
		assertNotNull(records);
		assertTrue(records[0].startsWith("Palace"));
		assertTrue(records[1].startsWith("Small"));
		assertTrue(records[2].startsWith("2"));
		assertTrue(records[3].startsWith("Y"));
		assertTrue(records[4].startsWith("$150"));
		assertTrue(records[5].startsWith("2005/07/27"));
		assertTrue(records[6].startsWith(""));

	}

	@Test
	public void testGetAllRecords() {
		// File exists
		DataFileParser datafile = new DataFileParser(existingFile);
		try {
			datafile.parse();
		} catch (Exception e) {
			fail("Must not throw exception with existing file: " + e.getMessage());
		}
		String records[] = null;
		try {
			records = datafile.getRecord(0);
		} catch (RecordNotFoundException e) {
			fail("Record must be found");
		}
		assertNotNull(records);
		assertTrue(datafile.getAllRecords().length == 31);
	}


	@Test
	public void testIsNotDeleted() {
		// File exists
		DataFileParser datafile = new DataFileParser(existingFile);
		try {
			datafile.parse();
		} catch (Exception e) {
			fail("Must not throw exception with existing file: " + e.getMessage());
		}
		String records[] = null;
		try {
			records = datafile.getRecord(0);
		} catch (RecordNotFoundException e) {
			fail("Record must be found");
		}
		assertNotNull(records);
		int length = datafile.getAllRecords().length;
		for(int i = 0; i < length; i++) {
			assertTrue(!datafile.isDeleted(i));
		}
	}

	@Test
	public void testParse() {
		// File exists
		DataFileParser datafile = new DataFileParser(existingFile);
		try {
			datafile.parse();
		} catch (Exception e) {
			fail("Must not throw exception with existing file: " + e.getMessage());
		}
		String records[] = null;
		try {
			records = datafile.getRecord(0);
		} catch (RecordNotFoundException e) {
			fail("Record must be found");
		}
		assertNotNull(records);
		assertTrue(records[0].startsWith("Palace"));
		assertTrue(records[1].startsWith("Small"));
		assertTrue(records[2].startsWith("2"));
	}
}
