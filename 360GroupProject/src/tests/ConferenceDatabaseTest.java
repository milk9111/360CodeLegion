/**
 * 
 */


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.Account;
import model.AccountDatabase;
import model.Conference;
import model.ConferenceDatabase;
import model.Reviewer;

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

	@Test
	public void saveConferenceToDatabase_forValidConf_shouldSucceed() {
		Conference validConference = new Conference("music conference", new Date(), new Date(), new ArrayList<Reviewer>());
		myConferenceDatabase.saveConferenceToDatabase(validConference);

		TreeMap<UUID, Conference> validList = myConferenceDatabase.deserializeConferenceList();
		assertTrue("list size should only be 1 and have only 1 Account saved", validList.size() == 1);
		assertTrue(validList.get(validConference.getMyID()).getMyName().equals(validConference.getMyName()));
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
