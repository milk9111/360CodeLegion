import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

/**
 * The Manuscript unit tests.
 * 
 * @author Connor Lundberg
 * @version 4/30/2017
 */
public class ManuscriptTest {
	private Manuscript myPaper;

	/**
	 * The set up before each unit test. Just instantiates a simple Manuscript.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		HashMap<Reviewer, String> reviews = new HashMap<Reviewer, String> ();
		ArrayList<Author> authors = new ArrayList<Author> ();
		String title = "Aerodynamics of a Hamburger";
		Date date = new Date();
		
		myPaper = new Manuscript(title, date, reviews, authors);
	}
	

	/**
	 * A test for the getReviews() method. It is just a check to make sure that the
	 * reviews are not null.
	 */
	@Test
	public void getReviews_TestGetReviewsForNullValues () {
		assertTrue (myPaper.getReviews() != null);
		for (String str : myPaper.getReviews()) 
			assertEquals (str instanceof String, true);
	}
	
	
	/**
	 * A test for the getReviews and addReview methods. This is a test to make sure
	 * the addReview method is adding the correct review string and attaching it to 
	 * the correct Reviewer while the getReviews is returning the correct reviews
	 * associated with the Manuscript.
	 */
	@Test
	public void addReviewAndGetReview_TestAddReviewAndGetReviewForCorrectValues () {
		Reviewer testReviewer = new Reviewer("William King", null);
		myPaper.assignReviewer(testReviewer);
		String reviewToAdd = "This paper was fantastic.";
		
		myPaper.addReview(testReviewer, reviewToAdd);

		for (String str : myPaper.getReviews()) 
			assertEquals (str, reviewToAdd);
	}
	
	
	/**
	 * A test for the getAuthors method. It is a simple test to check if the Authors
	 * attached to a Manuscript are not null and that they are part of the Author class.
	 */
	@Test
	public void getAuthors_TestGetAuthorsForNullValues () {
		assert (myPaper.getAuthors() != null);
		for (Author au : myPaper.getAuthors()) 
			assertEquals (au instanceof Author, true);
	}
	
	
	/**
	 * A test for the assignReviewer method. This is a test to check that assignReviewer
	 * is adding the passed Reviewer to the Reviewer list attached to the Manuscript.
	 */
	@Test
	public void assignReviewer_TestAssignReviewerForNullValues () {
		Manuscript testPaper = new Manuscript ();
		Reviewer testReviewer = new Reviewer("Terry Pratchett", new ArrayList<Conference>());
		testPaper.assignReviewer(testReviewer);
		
		assert (testPaper.getReviewers() != null);
		for (Reviewer rev : testPaper.getReviewers()) {
			assertEquals (rev instanceof Reviewer, true);
			assertEquals (rev, testReviewer);
		}
		
	}
	
	
	/**
	 * A test for the submitManuscript method. This is a test to check if the Manuscript
	 * returned is the same as the original. This is not mean the same Manuscript reference,
	 * but the Manuscript information within.
	 */
	@Test
	public void submitManuscript_TestSubmitManuscriptForCorrectPaperReturned () {
		assertEquals (myPaper.submitManuscript() instanceof Manuscript, true);
		assertEquals (myPaper.submitManuscript(), myPaper);
	}
	

}




















