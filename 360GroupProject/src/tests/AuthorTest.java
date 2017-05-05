
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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

	private Author myAuthor;
	private Author myCoAuthor;

	/**
	 * @author Casey Anderson
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myAuthor = new Author("simpson@ieee.org");
		myCoAuthor = new Author("fleegle@uw.edu");
	}

	/**
	 * @author Casey Anderson
	 * Test to check Author object successfully created.
	 */
	@Test
	public void testAuthor() {
		assertNotNull("myAuthor was not instantiated!", myAuthor);
		assertEquals("myAuthors myUserName should me John Doe!", "John Doe", myAuthor.getUserName());
	}


	/**
	 * @author Casey Anderson
	 * Test to add review to an Author.
	 */
	@Test
	public void testAddReview() {
		File file = new File("./test.txt");
		myAuthor.addReview(file);
		assertEquals("myAuthor Review should be test.txt!", file, myAuthor.getReviewList().get(0));
	}
	
	/**
	 * @author Casey Anderson
	 * Test to set the user name of an Author.
	 */
	@Test
	public void testSetUserName() {
		myAuthor.setUserName("hardy@acm.org");
		assertEquals("myAuthors myUserName should me hardy@acm.org!", "hardy@acm.org", myAuthor.getUserName());
	}
	
	/**
	 * @author Casey Anderson
	 * Test to add Manuscript to an Author.
	 */
	@Test
	public void testAddManuscriptToAuthorBusinessRuleNotTriggered() {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		ArrayList<Author> listOfAuthors = new ArrayList<Author>();
		listOfAuthors.add(myAuthor);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, listOfAuthors);
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference(date, date, reviewerList);
		myAuthor.addManuscript(conference, manuscript);
		assertEquals("ManuScript was not added to list properly", manuscript.getTitle(), myAuthor.getManuscript(conference).get(0).getTitle());
		
	}
	
	/**
	 * @author Casey Anderson
	 * Test to check business rule An Author is limited to 5 Manuscripts using Author 
	 * having total of 4 Manuscripts Business rule should not be triggered.
	*/
	@Test
	public void testMaxMinusOnePriorManuscriptsAsAuthorBusinessRuleNotTriggered() {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		ArrayList<Author> listOfAuthors = new ArrayList<Author>();
		listOfAuthors.add(myAuthor);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, listOfAuthors);
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference(date, date, reviewerList);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		listOfAuthors.add(myAuthor);
		Manuscript fithManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, listOfAuthors);
		myAuthor.addManuscript(conference, fithManuscript);
		assertEquals("ManuScript was not added to list properly", fithManuscript, myAuthor.getManuscript(conference).get(4));		
	}
	
	/**
	 * @author Casey Anderson
	 * Test to check business rule An Author is limited to 5 Manuscripts using Author
	 * having total of 5 Manuscripts Business rule should be triggered.
	*/
	@Test
	public void testMaxPriorManuscriptsAsAuthorBusinessRuleTriggered() {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		ArrayList<Author> listOfAuthors = new ArrayList<Author>();
		listOfAuthors.add(myAuthor);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, listOfAuthors);
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference(date, date, reviewerList);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		listOfAuthors.add(myAuthor);
		Manuscript sixthManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, listOfAuthors);
		myAuthor.addManuscript(conference, sixthManuscript);
		assertFalse(myAuthor.getManuscript(conference).contains(sixthManuscript));	
	}

	/**
	 * @author Casey Anderson
	 * Test to check business rule An Author is limited to 5 Manuscripts using CoAuthor
	 * having total of 4 Manuscripts Business rule should not be triggered.
	*/
	@Test
	public void testMaxMinusOnePriorManuscriptsAsCoAuthorBusinessRuleNotTriggered() {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		ArrayList<Author> listOfAuthors = new ArrayList<Author>();
		listOfAuthors.add(myAuthor);
		listOfAuthors.add(myCoAuthor);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, listOfAuthors);
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference(date, date, reviewerList);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		Manuscript fithManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, listOfAuthors);
		myAuthor.addManuscript(conference, fithManuscript);
		assertEquals("ManuScript was not added to list properly", fithManuscript, myAuthor.getManuscript(conference).get(4));		
	}
	
	/**
	 * @author Casey Anderson
	 * Test to check business rule An Author is limited to 5 Manuscripts using CoAuthor
	 * having total of 5 Manuscripts Business rule should be triggered.
	*/
	@Test
	public void testMaxPriorManuscriptsAsCoAuthorBusinessRuleTriggered() {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		ArrayList<Author> listOfAuthors = new ArrayList<Author>();
		listOfAuthors.add(myAuthor);
		listOfAuthors.add(myCoAuthor);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, listOfAuthors);
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference(date, date, reviewerList);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		Manuscript sixthManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, listOfAuthors);
		myAuthor.addManuscript(conference, sixthManuscript);
		assertFalse(myAuthor.getManuscript(conference).contains(sixthManuscript));	
	}
	
	/**
	 * @author Casey Anderson
	 * Test to check business rule An Author is limited to 5 Manuscripts using both Author and CoAuthor
	 * having total of 5 Manuscripts Business rule should be triggered.
	*/
	@Test
	public void testMaxPiorManuscriptsAsBothAuthorAndCoAuthorBusinessRuleTriggered() {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		ArrayList<Author> listOfAuthors = new ArrayList<Author>();
		listOfAuthors.add(myAuthor);
		listOfAuthors.add(myCoAuthor);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, listOfAuthors);
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference(date, date, reviewerList);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		Manuscript sixthManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, listOfAuthors);
		myAuthor.addManuscript(conference, sixthManuscript);
		assertFalse(myAuthor.getManuscript(conference).contains(sixthManuscript));	
	}
	
	/**
	 * @author Casey Anderson
	 * Test to check business rule An Author is limited to 5 Manuscripts using both Author and CoAuthor
	 * having a total of 4 Manuscripts Business rule should not be triggered.
	*/
	@Test
	public void testMaxLessThanMaxPriorManuscriptsAsAuthorAndCoAuthorBusinessRuleNotTriggered() {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		ArrayList<Author> listOfAuthors = new ArrayList<Author>();
		listOfAuthors.add(myAuthor);
		listOfAuthors.add(myCoAuthor);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, listOfAuthors);
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference(date, date, reviewerList);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		Manuscript fithManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, listOfAuthors);
		myAuthor.addManuscript(conference, fithManuscript);
		assertEquals("ManuScript was not added to list properly", fithManuscript, myAuthor.getManuscript(conference).get(4));		
	}

}
