package model;

import java.util.TreeMap;
import java.util.UUID;

/**
 * @author Ryan Tran
 *
 */
public class ConferenceDatabase {

	private TreeMap<UUID, Conference> myConferenceList;

	private final String CONFERENCE_SERIALIZED_PATH = "360GroupProject/src/model/serializedModel/";
	
	public void AccountDatabase() {
		this.myConferenceList = null;
	}
	
	

}
