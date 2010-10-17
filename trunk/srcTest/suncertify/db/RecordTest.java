package suncertify.db;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class RecordTest {

	Record record;
	
	@Before
	public void setUp() throws Exception {
		record = new Record("Hilton", "Basel", "3", true,"$50", new Date(1),"22", 1);
	}

	@Test
	public void testGetRecNo() {
		assertEquals(1, record.getRecNo());
	}
	@Test
	public void testGetName() {
		assertTrue(record.getName().equals("Hilton"));
	}

	@Test
	public void testGetLocation() {
		assertTrue(record.getLocation().equals("Basel"));
	}

	@Test
	public void testGetSize() {
		assertTrue(record.getSize().equals("3"));
	}

	@Test
	public void testIsSmokingAllowed() {
		assertTrue(record.isSmokingAllowed() == true);
	}

	@Test
	public void testGetRate() {
		assertTrue(record.getRate().equals("$50"));
	}

	@Test
	public void testGetDate() {
		assertTrue(record.getDate().equals(new Date(1)));
	}

	@Test
	public void testGetId() {
		assertTrue(record.getOwner().equals("22"));
	}

	@Test
	public void testRecord() {
		// Tested in setup
	}

	@Test
	public void testToString() {
		// Method using in testing only
	}

}
