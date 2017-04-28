import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ManuscriptTest {
	Manuscript myPaper;

	@Before
	public void setUp() throws Exception {
		HashMap<Reviewer, String> reviews = new HashMap<Reviewer, String> ();
		ArrayList<Author> authors = new ArrayList<Author> ();
		
		myPaper = new Manuscript(reviews, authors);
	}
	

	@Test
	public void testGetReviews () {
		assert (myPaper.getReviews() != null);
		for (String str : myPaper.getReviews()) 
			assertEquals (str instanceof String, true);
	}
	
	
	@Test
	public void testGetAuthors () {
		assert (myPaper.getAuthors() != null);
		for (Author au : myPaper.getAuthors()) 
			assertEquals (au instanceof Author, true);
	}
	
	
	
	@Test
	public void testAssignReviewer () {
		Manuscript testPaper = new Manuscript ();
		
		testPaper.assignReviewer(new Reviewer());
		
		assert (testPaper.getReviewers() != null);
		for (Reviewer rev : testPaper.getReviewers()) 
			assertEquals (rev instanceof Reviewer, true);
	}
	
	
	@Test
	public void testSubmitManuscript () {
		Manuscript testPaper = new Manuscript ();
		
		assertEquals (testPaper.submitManuscript() instanceof Manuscript, true);
		assertEquals (testPaper.submitManuscript(), myPaper);
	}
	

}




















