
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ReviewerTest {

	private Reviewer myTestReviewer; 
	
	@Before
	public void setup() throws Exception{
		
		List<Conference> myDummyConferenceList = new ArrayList();
		
		List<Author> myAuthorList = new ArrayList();
		//having trouble making dummy list so that I can test this.  
		Author testAuthor = new Author("Steinbeck", myDummyConferenceList);
		myAuthorList.add();
		
		
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
	 * Tests Biz Rule 2a1: Reviewer is not a coauthor, should pass test.
	 */
	@Test
	public void testAssignManuscriptReviewerNotAnAuthor() {
		
		fail("Not yet implemented");
	}
	
	/**
	 * Tests Biz Rule 2a2: Reviewer is author, should fail test.
	 */
	@Test
	public void testAssignManuscriptReviewerAuthor() {
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
