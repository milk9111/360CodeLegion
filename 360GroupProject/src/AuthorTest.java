


import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Fight
 *
 */
public class AuthorTest {

	private Author myAuthor;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myAuthor = new Author("John Doe");
	}

	/**
	 * Test method for {@link Author#Author(int)}.
	 */
	@Test
	public void testAuthor() {
		assertNotNull("myAuthor was not instantiated!", myAuthor);
		assertEquals("myAuthors myUserName should me John Doe!", "John Doe", myAuthor.getUserName());
	}


	/**
	 * Test method for {@link Author#setReview(java.io.File)}.
	 */
	@Test
	public void testAddReview() {
		File file = new File("./test.txt");
		myAuthor.addReview(file);
		assertEquals("myAuthor Review should be test.txt!", file, myAuthor.getReviewList().get(0));
	}
	
	@Test
	public void testSetUserName() {
		myAuthor.setUserName("Fred Jones");
		assertEquals("myAuthors myUserName should me Fred Jones!", "Fred Jones", myAuthor.getUserName());
	}
	
	@Test
	public void testCreateManuscript() {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		ArrayList<Author> listOfAuthors = new ArrayList<Author>();
		listOfAuthors.add(myAuthor);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, listOfAuthors);
		assertEquals("Manusripts title should be Old Yeller!", "OldYeller", manuscript.getTitle());
		assertEquals("Manusripts submitted date does not match up with provided date!", date, manuscript.getSubmittedDate());
		assertEquals("Manusripts title should be Old Yeller!", "OldYeller", manuscript.getTitle());
		assertEquals("Manusripts Author should be John Doe!", "John Doe", manuscript.getAuthors().get(0));
	}

	/**"
	 * Test method for {@link Author#setConference(Conference)}.
	 */
	@Test
	public void testSetConference() {
		//fail("Not yet implemented");
	}

}
