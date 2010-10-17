package suncertify.db;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import suncertify.tools.Message;

public class DataProxy {

	@SuppressWarnings("unused")			// will add methods that need to manipulate the DB later on
	private DB db;
	private List<Record> records;
	
	public DataProxy(DB db) {
		this.db = db;
		
		// retrieving all entries. because there is no method for that in the interface, we have to
		// supply the find() method with an array of null strings
		String[] criteria = new String[Schema.getFieldCount()];
		for(int i = 0; i < criteria.length; i++) {
			criteria[i] = null;
		}
		int[] allIndices = db.find(criteria);
		
		// loop through the result and create Record objects that are added to the list field
		this.records = new ArrayList<Record>();
		for(int i = 0; i < allIndices.length; i++) {
			String[] results = null;
			try {
				 results = db.read(allIndices[i]);
                boolean smokingAllowed;
                if(results[3].equals("Y")) {
                        smokingAllowed = true;
                } else {
                        smokingAllowed = false;
                }
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = dateFormat.parse(results[5]);
				
				Record currentRecord = new Record(results[0], results[1], results[2], smokingAllowed, results[4], date, results[6], i);
				this.records.add(currentRecord);
			} catch (RecordNotFoundException e) {
				Message.errorToUserAndLog("Could not find result index in database", e);
			} catch (ParseException e) {
				Message.warningToUserAndLog("Reading of datafile entry " + i + " incomplete, could not parse date: " + results[5]);
			}
		}
	}
	
	public List<Record> getRecords() {
		return records;
	}

	/**
	 * Returns records that are valid regarding the supplied filter. A <i>null</i> value is acceptable and
	 * for either argument and will lead to unfiltered results.
	 * @param name pass null if this condition should not be taken into account
	 * @param location pass null if this condition should not be taken into account
	 * @return
	 */
	public List<Record> getFilteredRecords(String name, String location, boolean caseSensitive) {
		// Differentiate between four cases:
		// 1) One of the arguments null -> return null
		// 2) Location is empty string -> search with name parameter
		// 3) Name is empty string-> search with location parameter
		// 4) else: search with both name and location according to search mode
		
		// TODO This logic should actually belong one level above to the main window model
		List<Record> results = new ArrayList<Record>();

		if(name == null || location == null) {
			return null;
		}
		
		name = caseSensitive ? name : name.toLowerCase();
		location = caseSensitive ? location : location.toLowerCase();

		if(location.equals("")) {
			for(Record currentRecord : records) {
				String curName = caseSensitive ? currentRecord.getName() : currentRecord.getName().toLowerCase();
				if(curName.startsWith(name)) {
					results.add(currentRecord);
				}
			}
			return results;
		}

		if(name.equals("")) {
			for(Record currentRecord : records) {
				String curLoc = caseSensitive ? currentRecord.getLocation() : currentRecord.getLocation().toLowerCase();
				if(curLoc.startsWith(location)) {
					results.add(currentRecord);
				}
			}
			return results;
		}

		// both arguments are not null
		for(Record currentRecord : records) {
			String curName = caseSensitive ? currentRecord.getName() : currentRecord.getName().toLowerCase();
			String curLoc = caseSensitive ? currentRecord.getLocation() : currentRecord.getLocation().toLowerCase();
			if(curName.startsWith(name) && curLoc.startsWith(location)) {
				results.add(currentRecord);
			}
		}
		return results;
	}
}
