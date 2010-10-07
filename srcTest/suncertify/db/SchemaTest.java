package suncertify.db;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SchemaTest {

	@Test
	public void testSchema() {
		Schema schema = new Schema();
		assertTrue(schema.toString().equals("[]"));
	}

	@Test
	public void testAdd() {
		Schema schema = new Schema();
		schema.add("field1", 1893);
		assertTrue(schema.toString().equals("[field1(1893)]"));
		schema.add("field2", 420);
		assertTrue(schema.toString().equals("[field1(1893), field2(420)]"));
	}

	@Test
	public void testToString() {
		Schema schema = new Schema();
		schema.add("field1", 1893);
		assertTrue(schema.toString().equals("[field1(1893)]"));
	}

}
