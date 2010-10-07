package suncertify.db;

import static org.junit.Assert.*;

import org.junit.Test;

public class FieldTest {

	@Test
	public void testField() {
		Field field = new Field("blah", 500);
		assertEquals(500, field.getFieldLength());
		assertTrue(field.getName().equals("blah"));
	}

	@Test
	public void testGetName() {
		Field field = new Field("blah", 500);
		assertTrue(field.getName().equals("blah"));
	}

	@Test
	public void testGetFieldLength() {
		Field field = new Field("blah", 500);
		assertEquals(500, field.getFieldLength());
	}

	@Test
	public void testToString() {
		Field field = new Field("blah", 500);
		assertTrue(field.toString().equals("blah(500)"));
	}

}
