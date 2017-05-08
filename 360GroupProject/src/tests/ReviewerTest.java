
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.Account;
import model.AccountDatabase;
import model.Author;
import model.Conference;
import model.ConferenceDatabase;
import model.Manuscript;
import model.ManuscriptDatabase;
import model.Reviewer;
import model.User;


public class ReviewerTest {

	private static final int MAX_CAPACITY = 8;
	private Reviewer myTestReviewer;
	private Manuscript myTestManuscript;
	private List<UUID> myAuthorList;
	private List<Manuscript> myManuscriptList;
	private List<Conference> myEmptyConferenceList;
	private Conference myConference;
	private Account myAccount;
	
	@Before
	public void setup() throws Exception{
		
		myEmptyConferenceList = new ArrayList<>();
		myConference = new Conference("Science Conference", new Date(), new Date());
		
		myAuthorList = new ArrayList<UUID>();
		myAuthorList.add(new Author("Steinbeck", myConference).getMyID());
		myAuthorList.add(new Author("Hemmingway", myConference).getMyID());
		
		myTestManuscript = new Manuscript();
		myTestManuscript.setAuthors(myAuthorList);
		
		myManuscriptList = new ArrayList<Manuscript>();
		myManuscriptList.add(myTestManuscript);
		
		
		myTestReviewer = new Reviewer(myTestManuscript, myConference);
		
		myAccount = new Account("Snowberry");
		new AccountDatabase().createEmptySerializedAccountList();
		new ManuscriptDatabase().createEmptySerializedManuscriptList();
		new ConferenceDatabase().createEmptySerializedConferenceList();
	}
	
	@Test
	public void assignManuscript_ForValidManuscript_shouldSucceed() {
		// inti Author and save to Account
		new AccountDatabase().saveNewAccountToDatabase(myAccount);
		Author newAuthor = new Author(myConference);
		myAccount.addAuthorRoleToAccount(newAuthor);

		Manuscript newManu = new Manuscript("New Manu Title", new Date(), myAccount.getMyAuthor(), new File(""), myConference);
		Reviewer newReviewer = new Reviewer(myConference);
		myAccount.setReviewer(newReviewer);
		assertTrue(myAccount.getMyReviewer().getMyID().equals(newReviewer.getMyID()));
		
		// assign manuscript
		Reviewer updatedReviewer = myAccount.getMyReviewer();
		assertTrue("manuscript should be assigned", updatedReviewer.assignManuscript(newManu));
		TreeMap<UUID, HashSet<UUID>> mapOfReviewerAndConf = myAccount.getMyReviewer().getMyAssignedManuscriptsAndConferenceList();

		assertTrue(mapOfReviewerAndConf.get(myConference.getMyID()).size() > 0);
	}
	
	/**
	 * Test that constructor instantiates a Reviewer.
	 */
	@Test
	public void testReviewerConstructor() {
		assertNotNull("Reviewer object Null", myTestReviewer);
		assertEquals("Reveiwers name should equal default test name \"Roger Ebert\"", myTestReviewer.getUsername(), "Roger Ebert");
	}


	/**
	 * Tests Biz Rule 2a1: Reviewer is not a coauthor, 
	 * should successfully add manuscript to Reviewers
	 * assigned manuscript list.
	 * Reviewer name = Roger Ebert
	 * Author names = Steinbeck, Hemmingway
	 */
	@Test
	public void testAssignManuscript_ReviewerNotAnAuthor_SuccessfullyAssign() {

		assertTrue("assignManuscript method should return true.", myTestReviewer.assignManuscript(myTestManuscript));
		
		assertEquals("A successful assignment should increase assignedManuscriptList from 0 to 1", myTestReviewer.getMyAssignedManuscriptsAndConferenceList().size(), 1);

	}
	
	/**
	 * Tests Biz Rule 2a2: Reviewer is author, should return false
 	 * and assigned manuscript list should stay at size 1.
	 */
	@Test
	public void testAssignManuscript_ReviewerIsAuthor_FailToAssign() {

		//Add Reviewer to authorlist for a manuscript 
		myAuthorList.add(0, new Author("Roger Ebert", myConference).getMyID());
		myTestManuscript.setAuthors(myAuthorList);
		myManuscriptList.clear();
		myManuscriptList.add(0, myTestManuscript);
		myTestReviewer = new Reviewer("Roger Ebert", new ArrayList<Conference>());
		myTestReviewer.assignManuscript(myTestManuscript);
		
		assertFalse("Reviewer cannot be assigned a paper when is an author", myTestReviewer.assignManuscript(myTestManuscript));
		
		assertEquals("Reviewer AssignedManuscriptList should stay at 0",myTestReviewer.getMyAssignedManuscriptsAndConferenceList().size(), 0);

		
	}
	
	/**
	 * Tests Biz Rule 2a3: Reviewer is coauthor, should fail test 
	 * and assigned manuscript list should stay at size 0.
	 */
	@Test
	public void testAssignManuscript_ReviewerCoauthor_FailToAssign() {
				
		myAuthorList.add(new Author("Roger Ebert", myConference).getMyID());
		myTestManuscript.setAuthors(myAuthorList);
		myManuscriptList.clear();
		myManuscriptList.add(0, myTestManuscript);

		myTestReviewer = new Reviewer("Roger Ebert", (ArrayList<Conference>) myEmptyConferenceList);

		assertFalse("Reviewer cannot be assigned a paper when is an author", myTestReviewer.assignManuscript(myTestManuscript));
		assertEquals(myTestReviewer.getMyAssignedManuscriptsAndConferenceList().size(), 1);
	}
	
	/**
	 * Tests Biz Rule 2b1: Reviewer has 2 assigned manuscripts, well under max.  
	 * Should return true and assign manuscript to Reviewers list.
	 */
	@Test
	public void testAssignManuscript_ReviewerUnderMaxAssignedManuscripts_SuccessfullyAssign() {
		
		myManuscriptList.add(myTestManuscript);

//		myTestReviewer = new Reviewer("Roger Ebert", myEmptyConferenceList, myManuscriptList);
		
		myTestReviewer = new Reviewer("Roger Ebert", new ArrayList<Conference>());
		myTestReviewer.assignManuscript(myTestManuscript);


		assertTrue("Should add to assigned Man List" , myTestReviewer.assignManuscript(myTestManuscript));
		assertEquals("Reviewer AssignedManuscriptList should stay at 1", myTestReviewer.getMyAssignedManuscriptsAndConferenceList().size(), 3);

	}
	
	/**
	 * Tests Biz Rule 2b2: Reviewer has MAX_CAPACITY-1 assigned manuscripts, which is max-1.  
	 * Should return true and assign manuscript to Reviewers list.
	 */
	@Test
	public void testAssignManuscript_ReviewerMAXLess1AssignedManuscripts_SuccessfullyAssign() {
		for (int i = myManuscriptList.size(); i < MAX_CAPACITY-1; i++) {
			myManuscriptList.add(myTestManuscript);
		}
		assertTrue("Should add to AssignedManuscriptList" , myTestReviewer.assignManuscript(myTestManuscript));
		assertEquals("AssignedManuscriptList should reach MAX capacity", myTestReviewer.getMyAssignedManuscriptsAndConferenceList().size(), MAX_CAPACITY);


		
	}
	
	/**
	 * Tests Biz Rule 2b3: Reviewer has MAX_CAPACITY assigned manuscripts.  
	 * Should return false and not assign manuscript to Reviewers list.
	 */
	@Test
	public void testAssignManuscript_ReviewerOverCapacityAssignedManuscripts_FailToAssign() {
		for (int i = myManuscriptList.size(); i < MAX_CAPACITY; i++) {
			myManuscriptList.add(myTestManuscript);
		}
		
		assertFalse("Should not add to assigned Man List" , myTestReviewer.assignManuscript(myTestManuscript));
		assertEquals(myTestReviewer.getMyAssignedManuscriptsAndConferenceList().size(), MAX_CAPACITY);

		
	}


}
