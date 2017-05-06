import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Author;
import model.Conference;
import model.Manuscript;
import model.Reviewer;
import model.User;

/**
 * The Manuscript unit tests.
 * 
 * @author Connor Lundberg
 * @version 4/30/2017
 */
public class ManuscriptTest {
	Manuscript myPaper;

	/**
	 * The set up before each unit test. Just instantiates a simple Manuscript.
	 * 
	 * @author Connor Lundberg
	 * @version 5/5/2017
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
	 * 
	 * @author Connor Lundberg
	 * @version 5/5/2017
	 */
	@Test
	public void getReviews_TestGetReviewsForNullValues () {
		
		assertTrue (myPaper.getReviews() instanceof ArrayList<?>);
		for (String str : myPaper.getReviews()) {
			System.out.println(str);
			assertEquals (str instanceof String, true);
		}
	}
	
	
	/**
	 * A test for the getReviews and addReview methods. This is a test to make sure
	 * the addReview method is adding the correct review string and attaching it to 
	 * the correct Reviewer while the getReviews is returning the correct reviews
	 * associated with the Manuscript.
	 * 
	 * @author Connor Lundberg
	 * @version 5/5/2017
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
	 * A test for the addAuthor method. It may not be very long, but it is important
	 * and therefore warrants its own test. This simply creates a new Author and adds
	 * it to the Manuscript's list of authors then checks if that Author is found within
	 * the returned list.
	 * 
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	@Test
	public void addAuthor_TestAddAuthorForCorrectAuthorAdded () {
		Author testAuthor = new Author ("Jiminiy Cricket");
		
		myPaper.addAuthor(testAuthor);
		
		assertTrue ("The Manuscript contained the added author", myPaper.getAuthors().contains(testAuthor));
	}
	
	
	/**
	 * A test for the getAuthors method. It is a simple test to check if the Authors
	 * attached to a Manuscript are not null and that they are part of the Author class.
	 * 
	 * @author Connor Lundberg
	 * @version 5/5/2017
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
	 * 
	 * @author Connor Lundberg
	 * @version 5/5/2017
	 */
	@Test
	public void assignReviewer_TestAssignReviewerForNullValues () {
		Manuscript testPaper = new Manuscript ();
		//Reviewer testReviewer = new Reviewer("Terry Pratchett", new ArrayList<Conference>());
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
	 * 
	 * @author Connor Lundberg
	 * @version 5/5/2017
	 */
	@Test
	public void submitManuscript_TestSubmitManuscriptForCorrectPaperReturned () {
		Manuscript testPaper = myPaper.submitManuscript();
		assertEquals (testPaper instanceof Manuscript, true);
		for (int i = 0; i < testPaper.getAuthors().size(); i++) {
			assertEquals(testPaper.getAuthors().get(i), myPaper.getAuthors().get(i));
		}
		
		for (int i = 0; i < testPaper.getReviews().size(); i++) {
			assertEquals(testPaper.getReviews().get(i), myPaper.getReviews().get(i));
		}
		
		
		for (int i = 0; i < testPaper.getReviewers().size(); i++) {
			assertEquals(testPaper.getReviewers().get(i), myPaper.getReviewers().get(i));
		}
		
		assertEquals(testPaper.getSubmittedDate(), myPaper.getSubmittedDate());
		
		assertEquals(testPaper.getTitle(), myPaper.getTitle());
	}
	

}




















