package suncertify.db;

import static org.junit.Assert.*;

import org.junit.Test;

public class DuplicateKeyExceptionTest {

	@Test
	public void testDuplicateKeyException() {
		DuplicateKeyException duplicateKeyException = new DuplicateKeyException();
		assertEquals(((Exception ) duplicateKeyException).getMessage(), duplicateKeyException.getMessage());
	}

	@Test
	public void testDuplicateKeyExceptionString() {
		String desc = "DESCRIPTION";
		DuplicateKeyException duplicateKeyException = new DuplicateKeyException(desc);
		assertEquals(desc, duplicateKeyException.getMessage());
	}

	@Test
	public void testGetMessage() {
		// Tested above
	}

}
