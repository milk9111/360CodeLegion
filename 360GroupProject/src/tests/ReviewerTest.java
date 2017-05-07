
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Author;
import model.Conference;
import model.Manuscript;
import model.Reviewer;
import model.User;


public class ReviewerTest {

	private static final int MAX_CAPACITY = 8;
	private Reviewer myTestReviewer;
	private Manuscript myTestManuscript;
	private List<Author> myAuthorList;
	private List<Manuscript> myManuscriptList;
	private List<Conference> myEmptyConferenceList;
	private Conference myConference;
	
	@Before
	public void setup() throws Exception{
		
		myEmptyConferenceList = new ArrayList<>();
		myConference = new Conference("Science Conference", new Date(), new Date());
		
		myAuthorList = new ArrayList<Author>();
		myAuthorList.add(new Author("Steinbeck", myConference));
		myAuthorList.add(new Author("Hemmingway", myConference));
		
		myTestManuscript = new Manuscript();
		myTestManuscript.setAuthors(myAuthorList);
		
		myManuscriptList = new ArrayList<Manuscript>();
		myManuscriptList.add(myTestManuscript);
		
		
		myTestReviewer = new Reviewer("Roger Ebert", myEmptyConferenceList, myManuscriptList);
		
		
		
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
		
		assertEquals("A successful assignment should increase assignedManuscriptList from 1 to 2", myTestReviewer.getAssignedManuscriptList().size(), 2);

	}
	
	/**
	 * Tests Biz Rule 2a2: Reviewer is author, should return false
 	 * and assigned manuscript list should stay at size 1.
	 */
	@Test
	public void testAssignManuscript_ReviewerIsAuthor_FailToAssign() {

		//Add Reviewer to authorlist for a manuscript 
		myAuthorList.add(0, new Author("Roger Ebert", myConference));
		myTestManuscript.setAuthors(myAuthorList);
		myManuscriptList.clear();
		myManuscriptList.add(0, myTestManuscript);
		myTestReviewer = new Reviewer("Roger Ebert", myEmptyConferenceList, myManuscriptList);
		
		assertFalse("Reviewer cannot be assigned a paper when is an author", myTestReviewer.assignManuscript(myTestManuscript));
		
		assertEquals("Reviewer AssignedManuscriptList should stay at 1",myTestReviewer.getAssignedManuscriptList().size(), 1);
		
	}
	
	/**
	 * Tests Biz Rule 2a3: Reviewer is coauthor, should fail test 
	 * and assigned manuscript list should stay at size 1.
	 */
	@Test
	public void testAssignManuscript_ReviewerCoauthor_FailToAssign() {
				
		myAuthorList.add(new Author("Roger Ebert", myConference));
		myTestManuscript.setAuthors(myAuthorList);
		myManuscriptList.clear();
		myManuscriptList.add(0, myTestManuscript);
		myTestReviewer = new Reviewer("Roger Ebert", myEmptyConferenceList, myManuscriptList);

		assertFalse("Reviewer cannot be assigned a paper when is an author", myTestReviewer.assignManuscript(myTestManuscript));
		assertEquals(myTestReviewer.getAssignedManuscriptList().size(), 1);
	}
	
	/**
	 * Tests Biz Rule 2b1: Reviewer has 2 assigned manuscripts, well under max.  
	 * Should return true and assign manuscript to Reviewers list.
	 */
	@Test
	public void testAssignManuscript_ReviewerUnderMaxAssignedManuscripts_SuccessfullyAssign() {
		
		myManuscriptList.add(myTestManuscript);

		myTestReviewer = new Reviewer("Roger Ebert", myEmptyConferenceList, myManuscriptList);

		assertTrue("Should add to assigned Man List" , myTestReviewer.assignManuscript(myTestManuscript));
		assertEquals("Reviewer AssignedManuscriptList should stay at 1", myTestReviewer.getAssignedManuscriptList().size(), 3);

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
		assertEquals("AssignedManuscriptList should reach MAX capacity", myTestReviewer.getAssignedManuscriptList().size(), MAX_CAPACITY);


		
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
		assertEquals(myTestReviewer.getAssignedManuscriptList().size(), MAX_CAPACITY);

		
	}


}
