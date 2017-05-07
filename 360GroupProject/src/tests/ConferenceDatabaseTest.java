/**
 * 
 */


import static org.junit.Assert.*;

import java.util.TreeMap;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.Account;
import model.AccountDatabase;
import model.Conference;
import model.ConferenceDatabase;

/**
 * @author BlueAccords
 *
 */
public class ConferenceDatabaseTest {
	
	private ConferenceDatabase myConferenceDatabase;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myConferenceDatabase = new ConferenceDatabase();
		myConferenceDatabase.createEmptySerializedConferenceList();
	}

	
	/**
	 * Test method for {@link AccountDatabase#deserializeAccountList()}.
	 */
	@Test
	public void deserializeAccountList_forEmptyList_shouldReturnEmptyList() {
		TreeMap<UUID, Conference> validList = myConferenceDatabase.deserializeConferenceList();
		assertTrue(validList instanceof TreeMap);
	}
	
	@Test
	public void createEmptySerializedAccountList_ForNoListAvailable_ShouldCreateNewList() {
		myConferenceDatabase.createEmptySerializedConferenceList();
		TreeMap<UUID, Conference> validList = myConferenceDatabase.deserializeConferenceList();
		assertTrue(validList.size() == 0);
	}

}
