package suncertify.db;

import static org.junit.Assert.*;

import org.junit.Test;

public class RecordNotFoundExceptionTest {

	@Test
	public void testRecordNotFoundException() {
		RecordNotFoundException recordNotFoundException = new RecordNotFoundException();
		assertEquals(((Exception ) recordNotFoundException).getMessage(), recordNotFoundException.getMessage());
	}

	@Test
	public void testRecordNotFoundExceptionString() {
		String desc = "DESCRIPTION";
		RecordNotFoundException recordNotFoundException = new RecordNotFoundException(desc);
		assertEquals(desc, recordNotFoundException.getMessage());
	}

	@Test
	public void testGetMessage() {
		// Tested above
	}

}
