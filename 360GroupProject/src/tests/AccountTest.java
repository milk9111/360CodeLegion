/**
 * 
 */


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.Account;
import model.AccountDatabase;
import model.Author;
import model.Conference;
import model.ConferenceDatabase;
import model.Reviewer;
import model.SubprogramChair;

/**
 * @author Ryan Tran
 * @version 5/7/17
 *
 */
public class AccountTest {
	
	private Account myAccount;
	private AccountDatabase myAccountDatabase;
	private ConferenceDatabase myConferenceDatabase;
	private Conference myConference;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.myAccount = new Account("Linolin");
		this.myConference = new Conference("Theory of Relics", new Date(), new Date());
		this.myAccountDatabase = new AccountDatabase();
		this.myConferenceDatabase = new ConferenceDatabase();
		
		this.myAccountDatabase.createEmptySerializedAccountList();
		this.myConferenceDatabase.createEmptySerializedConferenceList();
	}

	/**
	 * Test method for {@link model.Account#addAuthorRoleToAccount(model.Author)}.
	 */
	@Test
	public void testAddAuthorRoleToAccount() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Account#getAuthorAssociatedWithConference(model.Conference)}.
	 */
	@Test
	public void testGetAuthorAssociatedWithConference() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Account#doesAutorAssociatedWithConferenceExist(model.Conference)}.
	 */
	@Test
	public void testDoesAutorAssociatedWithConferenceExist() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Account#getAllConferencesOfAccountSubprogramChairList(java.util.TreeMap)}.
	 */
	@Test
	public void testGetAllConferencesOfAccountSubprogramChairList() {
		// add and initialize conferences
		
		Conference validConfA = generateNewConference("Alpha Conference");
		Conference validConfB = generateNewConference("Bravo Conference");
		Conference validConfC = generateNewConference("Charlie Conference");
		Conference validConfD = generateNewConference("Delta Conference");
		
		ArrayList<Conference> confList = new ArrayList<Conference>();
		
		confList.add(validConfA);
		confList.add(validConfB);
		confList.add(validConfC);
		confList.add(validConfD);
		
		// save conferences to database
		for(Conference aConf : confList) {
			this.myAccount.addSubprogramChairRoleToAccount(new SubprogramChair(aConf), aConf);
			this.myConferenceDatabase.saveConferenceToDatabase(aConf);
		}
		
		// save account to database
		this.myAccountDatabase.saveAccountToDatabase(this.myAccount);
		
		// retrieve updated lists from database
		TreeMap<UUID, Conference> confDBList = this.myConferenceDatabase.deserializeConferenceList();
		assertTrue(confDBList.size() == 4);

		TreeMap<UUID, Account> acctDBList = this.myAccountDatabase.deserializeAccountList();
		assertTrue(acctDBList.size() == 1);
		
		Account mainAcct = acctDBList.get(myAccount.getMyID());

		ArrayList<Conference> associatedConfList = mainAcct.getAllConferencesOfAccountSubprogramChairList(confDBList);
		assertTrue(associatedConfList.size() == 4);
		
		boolean conferenceFound = false;
		// validate if one of the conferences is in the returned list
		for(Conference aConf : associatedConfList) {
			if(aConf.getMyID().equals(validConfA.getMyID())) {
				conferenceFound = true;
			}
		}
		
		assertTrue(conferenceFound);
		
		

	}

	/**
	 * Test method for {@link model.Account#addSubprogramChairRoleToAccount(model.SubprogramChair, model.Conference)}.
	 */
	@Test
	public void testAddSubprogramChairRoleToAccount() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Account#addReviewerRoleToAccount(model.Reviewer, model.Conference)}.
	 */
	@Test
	public void testAddReviewerRoleToAccount() {
		fail("Not yet implemented");
	}
	
	/**
	 * Helper method to create a conference
	 * @param theConfName the conference name
	 * @return the conference
	 */
	private Conference generateNewConference(String theConfName) {
		return new Conference(theConfName, new Date(), new Date());
	}

}
