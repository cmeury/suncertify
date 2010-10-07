package suncertify.db;

import java.util.ArrayList;
import java.util.List;

/**
 * Schema of the database, basically a list of fields
 *
 */
public class Schema {

	List<Field> fieldList;
	
	public Schema() {
		this.fieldList = new ArrayList<Field>();
	}
	
	/**
	 *  Adds a new field to the schema
	 * @param name the name of the database field
	 * @param fieldLength amount of bytes covered by this database field
	 */
	public void add(String name, int fieldLength) {
		this.fieldList.add(new Field(name, fieldLength));
	}
	
	@Override
	public String toString() {
		return fieldList.toString();
	}
}
