package suncertify.db;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class DataFileTest {

	
	@Test
	public void testOpen() {

		// File exists
		File noFile = new File("funnylookingcamel.db");
		DataFileParser datafile = new DataFileParser(noFile);
		try {
			datafile.parse();
			fail("Must throw exception with non-existing file");
		} catch (Exception e) {
		}

		// File exists
		File existingFile = new File("srcTest/suncertify/db/db-1x1.db");
		datafile = new DataFileParser(existingFile);
		try {
			datafile.parse();
		} catch (Exception e) {
			fail("Must not throw exception with existing file: " + e.getMessage());
		}
		String schemaString = datafile.getSchema().toString();
		String expectation = "[name(64), location(64), size(4), smoking(1), rate(8), date(10), owner(8)]";
		assertTrue("Schema read from test file not matching expectations", schemaString.equals(expectation));

		int length = datafile.getRecordLength();
		assertEquals("Expected total length of record in test file not correctly returned", 159, length);
	}
	
}
