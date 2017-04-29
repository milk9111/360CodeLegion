
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ReviewerTest {

	private Reviewer myTestReviewer;
	private Manuscript testManuscript;
	private List<Author> authorList;
	
	@Before
	public void setup() throws Exception{
		
		List<Conference> myDummyConferenceList = new ArrayList<>();
		
		authorList = new ArrayList<Author>();
		authorList.add(new Author("Steinbeck"));
		authorList.add(new Author("Hemmingway"));
		
		testManuscript = new Manuscript();
		testManuscript.setAuthors(authorList);
		
		
		myTestReviewer = new Reviewer("Roger Ebert", myDummyConferenceList);
		
		
		
	}
	/**
	 * Test that constructor instantiates a Reviewer.
	 */
	@Test
	public void testReviewerConstructor() {
		assertNotNull("Reviewer not instantiated", myTestReviewer);
	}


	/**
	 * Tests Biz Rule 2a1: Reviewer is not a coauthor, should successfully add manuscript to Reviewers
	 * assigned manuscript list.
	 * Reviewer name = Roger Ebert
	 * Author names = Steinbeck, Hemmingway
	 */
	@Test
	public void testAssignManuscriptReviewerNotAnAuthor() {
		
		assertTrue(myTestReviewer.assignManuscript(testManuscript));
		
//		assertEquals(myTestReviewer.getAssignedManuscriptList().get(1), testManuscript);

	}
	
	/**
	 * Tests Biz Rule 2a2: Reviewer is author, should fail test.
	 */
	@Test
	public void testAssignManuscriptReviewerAuthor() {
		
		authorList.add(new Author("Roger Ebert"));
		
		myTestReviewer.assignManuscript(testManuscript);
		
		assertEquals(myTestReviewer.assignManuscript(testManuscript), "Reviewer is a coauthor on this manuscript");
		
		fail("Not yet implemented");
	}
	
	/**
	 * Tests Biz Rule 2a3: Reviewer is coauthor, should fail test.
	 */
	@Test
	public void testAssignManuscriptReviewerCoauthor() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testJustAfterDeadline() {
		
	}

}
