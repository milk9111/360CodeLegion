import model.Account;
import model.AccountDatabase;
import model.Author;
import model.Conference;
import model.Manuscript;
import model.ManuscriptDatabase;
import model.Reviewer;
import model.User;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is test the Author class to make sure everything is implemented correctly.
 * 
 * @author Casey Anderson
 * @version 1 
 *
 */
public class AuthorTest {

	private Account myAccount;
	private Account myCoAuthAccount;
	private Author myAuthor;
	private Author myCoAuthor;
	private Conference myConference;
	private AccountDatabase myAccountDatabase;
	private ManuscriptDatabase myManuscriptDatabase;
	// ID of Authors
	private ArrayList<UUID> myListOfAuthors;
	// Key: Account ID, Value: Account Obj
	private TreeMap<UUID, Account> myListOfAccountAuthors;

	/**
	 * @author Casey Anderson
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() {
		HashMap<Reviewer, String> reviews = new HashMap<Reviewer, String> ();
		ArrayList<UUID> authors = new ArrayList<UUID>();
		String title = "Aerodynamics of a Hamburger";
		Date date = new Date();
		
		myAccountDatabase = new AccountDatabase();
		myManuscriptDatabase = new ManuscriptDatabase();
		myAccountDatabase.createEmptySerializedAccountList();
		myManuscriptDatabase.createEmptySerializedManuscriptList();
		myAccount = new Account("Chase");
		myCoAuthAccount = new Account("Steven");
		myConference = new Conference("Alchemy Conference", new Date(), new Date());
		myAuthor = new Author("simpson@ieee.org", myConference);
		myCoAuthor = new Author("fleegle@uw.edu", myConference);
		myListOfAuthors = new ArrayList<UUID>();
		myListOfAccountAuthors = new TreeMap<UUID, Account>();
	}

	
	@Test
	public void  addManuscript_withValidManuscript_shouldSaveToDB() {
		// init user with Author and save to DB
		this.myAccountDatabase.saveNewAccountToDatabase(myAccount);
		myAccount.addAuthorRoleToAccount(myAuthor);
		
		assertTrue("account's author ID should be equal to temp author", myAccount.getMyAuthor().getMyID().equals(myAuthor.getMyID()));
		
		// init manuscript
		Manuscript validManuscript = new Manuscript("All is gone", new Date(), myAuthor, new File("/C:/serializedModel/testFile.txt"), myConference);
		try {
			myAuthor.addManuscript(myConference, validManuscript);
			
			ArrayList<Manuscript> savedManuList = this.myManuscriptDatabase.getManuscriptsBelongingToAuthor(myAuthor);
			assertTrue("Returned Manuscript should be 1, indicating the new manuscript was saved", savedManuList.size() > 0);
			
			Author updatedAuth = this.myAccountDatabase.getAllAccounts().get(myAccount.getMyID()).getMyAuthor();
			assertTrue(updatedAuth.getMyID().equals(myAuthor.getMyID()));
			assertTrue(updatedAuth.getMyListOfConferenceIDs().size() > 0);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue("No error message should exist", e.getMessage() == null);
		}
	}
	
	/**
	 * @author Casey Anderson
	 * Test to add review to an Author.
	 */
	@Test
	public void testAddReview_WithTestFile_ShouldHaveTextFileInAuthorReviewList() {
		File file = new File("./test.txt");
		myAuthor.addReview(file);
		assertEquals("myAuthor Review should be test.txt!", file, myAuthor.getReviewList().get(0));
	}
	
	/**
	 * @author Casey Anderson
	 * Test to check business rule An Author is limited to max Manuscripts using Author 
	 * having total of max minus one prior Manuscripts Business rule should not be triggered.
	 * @throws Exception 
	*/
	@Test
	public void testAddManuscript_MaxMinusOnePriorManuscriptsAsAuthor_ManuscriptAddedToAuthor() throws Exception {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		myListOfAuthors.add(myAuthor.getMyID());
		
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference("RSA", date, date, reviewerList);
		myAccount.addAuthorRoleToAccount(myAuthor);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, myListOfAuthors, new File(""), conference);
		myAccountDatabase.updateAndSaveAccountToDatabase(myAccount);
		this.myListOfAccountAuthors.put(myAccount.getMyID(), myAccount);
		

		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);

		myListOfAuthors.add(myAuthor.getMyID());
		
		Manuscript fithManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, myListOfAuthors, new File(""), conference);
		myAuthor.addManuscript(conference, fithManuscript);
		
		// save changes to DB
		myAccount.addAuthorRoleToAccount(myAuthor);
		myAccountDatabase.updateAndSaveAccountToDatabase(myAccount);

		TreeMap<UUID, Account> allAccts = new AccountDatabase().getAllAccounts();
		assertTrue(allAccts.size() > 0);
		//myAuthor = new AccountDatabase().getAllAccounts().get(myAccount.getMyID()).getAuthorAssociatedWithConference(conference);
		assertTrue("ManuScript was not added to list properly", myAuthor.getManuscriptsAssociatedWithConference(conference).contains(fithManuscript.getMyID()));
	}
	
	/**
	 * @author Casey Anderson
	 * Test to check business rule An Author is limited to max Manuscripts using Author
	 * having total of max prior Manuscripts Business rule should be triggered.
	 * @throws Exception 
	*/
	@Test(expected = IllegalArgumentException.class)
	public void testAddManuscript_MaxPriorManuscriptsAsAuthor_IllegalArguentThrown() throws Exception {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		ArrayList<Author> listOfAuthors = new ArrayList<Author>();
		myListOfAuthors.add(myAuthor.getMyID());
		
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference("RSA", date, date, reviewerList);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, myListOfAuthors, new File(""),conference);
		// add author to account's author list
		myAccount.addAuthorRoleToAccount(myAuthor);
		this.myListOfAccountAuthors.put(myAccount.getMyID(), myAccount);

		// add manuscripts
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myListOfAuthors.add(myAuthor.getMyID());
		Manuscript sixthManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, myListOfAuthors, new File(""), conference);
		myAuthor.addManuscript(conference, sixthManuscript);
	}

	/**
	 * @author Casey Anderson
	 * Test to check business rule An Author is limited to max Manuscripts using CoAuthor
	 * having total of max minus one prior Manuscripts Business rule should not be triggered.
	 * @throws Exception 
	*/
	@Test
	public void testAddManuscripts_MaxMinusOnePriorManuscriptsAsCoAuthor_ManuscriptAddedToAuthor() throws Exception {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		myListOfAuthors.add(myAuthor.getMyID());
		myListOfAuthors.add(myCoAuthor.getMyID());
		
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference("RSA", date, date, reviewerList);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, myListOfAuthors, new File(""), conference);
		myCoAuthAccount.addAuthorRoleToAccount(myCoAuthor);
		this.myListOfAccountAuthors.put(myCoAuthAccount.getMyID(), myCoAuthAccount);
		
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);

		Manuscript fithManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, myListOfAuthors, new File(""), conference);
		myAuthor.addManuscript(conference, fithManuscript);
		
		myAccount.addAuthorRoleToAccount(myAuthor);
		this.myListOfAccountAuthors.put(myAccount.getMyID(), myAccount);
		
		assertTrue("ManuScript was not added to list properly", myAuthor.getManuscriptsAssociatedWithConference(conference).contains(fithManuscript.getMyID()));
	}
	
	/**
	 * @author Casey Anderson
	 * Test to check business rule An Author is limited to max Manuscripts using CoAuthor
	 * having total of max prior Manuscripts Business rule should be triggered.
	 * @throws Exception 
	*/
	@Test(expected = IllegalArgumentException.class)
	public void testAddManuscript_MaxPriorManuscriptsAsCoAuthor_IllegalArgumentThrown() throws Exception {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		myListOfAuthors.add(myAuthor.getMyID());
		myListOfAuthors.add(myCoAuthor.getMyID());
		

		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference("RSA", date, date, reviewerList);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, myListOfAuthors, new File(""),conference);
		myCoAuthAccount.addAuthorRoleToAccount(myCoAuthor);
		this.myListOfAccountAuthors.put(myCoAuthAccount.getMyID(), myCoAuthAccount);
		
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);

		Manuscript sixthManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, myListOfAuthors, new File(""),conference);
		
		myAccount.addAuthorRoleToAccount(myAuthor);
		this.myListOfAccountAuthors.put(myAccount.getMyID(), myAccount);
		myAuthor.addManuscript(conference, sixthManuscript);
		
		
	}
	
	/**
	 * @author Casey Anderson
	 * Test to check business rule An Author is limited to max Manuscripts using both Author and CoAuthor
	 * having total of max prior Manuscripts Business rule should be triggered.
	 * @throws Exception 
	*/
	@Test(expected = IllegalArgumentException.class)
	public void testAddManuscript_MaxPiorManuscriptsAsBothAuthorAndCoAuthor_IllegalArgumentThrown() throws Exception {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		myListOfAuthors.add(myAuthor.getMyID());
		myListOfAuthors.add(myCoAuthor.getMyID());
		
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference("RSA", date, date, reviewerList);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, myListOfAuthors, new File(""),conference);
		
		// adding authors to their respective account and then account list
		myAccount.addAuthorRoleToAccount(myAuthor);
		myCoAuthAccount.addAuthorRoleToAccount(myCoAuthor);
		
		this.myListOfAccountAuthors.put(myAccount.getMyID(), myAccount);
		this.myListOfAccountAuthors.put(myCoAuthAccount.getMyID(), myCoAuthAccount);
		
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);

		Manuscript sixthManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, myListOfAuthors, new File(""), conference);
	    myAuthor.addManuscript(conference, sixthManuscript);
	}
	
	/**
	 * @author Casey Anderson
	 * Test to check business rule An Author is limited to max Manuscripts using both Author and CoAuthor
	 * having a total of max minus one prior Manuscripts Business rule should not be triggered.
	 * @throws Exception 
	*/
	@Test
	public void testAddManuscript_MaxLessThanMaxPriorManuscriptsAsAuthorAndCoAuthor_ManuscriptAddedToAuthor() throws Exception {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		myListOfAuthors.add(myAuthor.getMyID());
		myListOfAuthors.add(myCoAuthor.getMyID());
		
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference("RSA", date, date, reviewerList);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, myListOfAuthors, new File(""), conference);
		// adding authors to author account list
		myCoAuthAccount.addAuthorRoleToAccount(myCoAuthor);
		myAccount.addAuthorRoleToAccount(myAuthor);
		
		this.myListOfAccountAuthors.put(myAccount.getMyID(), myAccount);
		this.myListOfAccountAuthors.put(myCoAuthAccount.getMyID(), myCoAuthAccount);
		
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		Manuscript fithManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, myListOfAuthors, new File(""), conference);
		myAuthor.addManuscript(conference, fithManuscript);
		assertTrue("ManuScript was not added to list properly", myAuthor.getManuscriptsAssociatedWithConference(conference).contains(fithManuscript.getMyID()));
	}

}
