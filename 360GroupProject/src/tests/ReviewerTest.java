
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ReviewerTest {

	private Reviewer myTestReviewer;
	private Manuscript testManuscript;
	private List<Author> authorList;
	private List<Manuscript> manuscriptList;
	private List<Conference> myDummyConferenceList;
	
	@Before
	public void setup() throws Exception{
		
		myDummyConferenceList = new ArrayList<>();
		
		authorList = new ArrayList<Author>();
		authorList.add(new Author("Steinbeck"));
		authorList.add(new Author("Hemmingway"));
		
		testManuscript = new Manuscript();
		testManuscript.setAuthors(authorList);
		
		manuscriptList = new ArrayList<Manuscript>();
		manuscriptList.add(testManuscript);
		
		
		myTestReviewer = new Reviewer("Roger Ebert", myDummyConferenceList, manuscriptList);
		
		
		
	}
	/**
	 * Test that constructor instantiates a Reviewer.
	 */
	@Test
	public void testReviewerConstructor() {
		assertNotNull("Reviewer not instantiated", myTestReviewer);
		assertEquals(myTestReviewer.getName(), "Roger Ebert");
	}


	/**
	 * Tests Biz Rule 2a1: Reviewer is not a coauthor, 
	 * should successfully add manuscript to Reviewers
	 * assigned manuscript list.
	 * Reviewer name = Roger Ebert
	 * Author names = Steinbeck, Hemmingway
	 */
	@Test
	public void testAssignManuscriptReviewerNotAnAuthor() {

		assertTrue("assignManuscript method should return true.", myTestReviewer.assignManuscript(testManuscript));
		
		assertEquals(myTestReviewer.getAssignedManuscriptList().size(), 2);

	}
	
	/**
	 * Tests Biz Rule 2a2: Reviewer is author, should return false
 	 * and assigned manuscript list should stay at size 1.
	 */
	@Test
	public void testAssignManuscriptReviewerAuthor() {

		
		authorList.add(0, new Author("Roger Ebert"));
		testManuscript.setAuthors(authorList);
		manuscriptList.clear();
		manuscriptList.add(0, testManuscript);
		myTestReviewer = new Reviewer("Roger Ebert", myDummyConferenceList, manuscriptList);
		
		assertFalse(myTestReviewer.assignManuscript(testManuscript));
		
		assertEquals(myTestReviewer.getAssignedManuscriptList().size(), 1);
		
	}
	
	/**
	 * Tests Biz Rule 2a3: Reviewer is coauthor, should fail test 
	 * and assigned manuscript list should stay at size 1.
	 */
	@Test
	public void testAssignManuscriptReviewerCoauthor() {
				
		authorList.add(new Author("Roger Ebert"));
		testManuscript.setAuthors(authorList);
		manuscriptList.clear();
		manuscriptList.add(0, testManuscript);
		myTestReviewer = new Reviewer("Roger Ebert", myDummyConferenceList, manuscriptList);

		assertFalse(myTestReviewer.assignManuscript(testManuscript));
		assertEquals(myTestReviewer.getAssignedManuscriptList().size(), 1);
	}
	
	/**
	 * Tests Biz Rule 2b1: Reviewer has 2 assigned manuscripts, well under max.  
	 * Should return true and assign manuscript to Reviewers list.
	 */
	@Test
	public void testAssignManuscriptReviewer2assignedManuscripts() {
		
		manuscriptList.add(testManuscript);

		myTestReviewer = new Reviewer("Roger Ebert", myDummyConferenceList, manuscriptList);

		assertTrue("Should add to assigned Man List" , myTestReviewer.assignManuscript(testManuscript));
		assertEquals(myTestReviewer.getAssignedManuscriptList().size(), 3);

	}
	
	/**
	 * Tests Biz Rule 2b2: Reviewer has 7 assigned manuscripts, which is max-1.  
	 * Should return true and assign manuscript to Reviewers list.
	 */
	@Test
	public void testAssignManuscriptReviewer7assignedManuscripts() {
		for (int i = manuscriptList.size(); i < 7; i++) {
			manuscriptList.add(testManuscript);
		}
		assertTrue("Should add to assigned Man List" , myTestReviewer.assignManuscript(testManuscript));
		assertEquals(myTestReviewer.getAssignedManuscriptList().size(), 8);


		
	}
	
	/**
	 * Tests Biz Rule 2b3: Reviewer has 8 assigned manuscripts, which is max.  
	 * Should return false and not assign manuscript to Reviewers list.
	 */
	@Test
	public void testAssignManuscriptReviewer8assignedManuscripts() {
		for (int i = manuscriptList.size(); i < 8; i++) {
			manuscriptList.add(testManuscript);
		}
		
		assertFalse("Should not add to assigned Man List" , myTestReviewer.assignManuscript(testManuscript));
		assertEquals(myTestReviewer.getAssignedManuscriptList().size(), 8);

		
	}


}
