
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

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myAuthor = new Author("John Doe");
	}

	/**
	 * Test to check Author object successfully created.
	 */
	@Test
	public void testAuthor() {
		assertNotNull("myAuthor was not instantiated!", myAuthor);
		assertEquals("myAuthors myUserName should me John Doe!", "John Doe", myAuthor.getUserName());
	}


	/**
	 * Test to add review to an Author.
	 */
	@Test
	public void testAddReview() {
		File file = new File("./test.txt");
		myAuthor.addReview(file);
		assertEquals("myAuthor Review should be test.txt!", file, myAuthor.getReviewList().get(0));
	}
	
	/**
	/ Test to set the user name of an Author.
	*/
	@Test
	public void testSetUserName() {
		myAuthor.setUserName("Fred Jones");
		assertEquals("myAuthors myUserName should me Fred Jones!", "Fred Jones", myAuthor.getUserName());
	}
	
	/**
	/ Test to add Manuscript to an Author.
	*/
	@Test
	public void testAddManuscriptToAuthor() {
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
	/ Test to check buissness rule An Author is limited to 5 Manuscripts using Author 
	/ having total of 4 Manuscripts Buissness rule should not be triggered.
	*/
	@Test
	public void testMax-1PriorManuscriptsAsAuthor() {
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
	/ Test to check buissness rule An Author is limited to 5 Manuscripts using Author
	/ having total of 5 Manuscripts Buissness rule should be triggered.
	*/
	@Test
	public void testMaxPriorManuscriptsAsAuthor() {
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
	/ Test to check buissness rule An Author is limited to 5 Manuscripts using CoAuthor
	/ having total of 4 Manuscripts Buissness rule should not be triggered.
	*/
	@Test
	public void testMax-1PriorManuscriptsAsCoAuthor() {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		ArrayList<Author> listOfAuthors = new ArrayList<Author>();
		listOfAuthors.add(myAuthor);
		Author CoAuthor = new Author("Batman");
		listOfAuthors.add(CoAuthor);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, listOfAuthors);
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference(date, date, reviewerList);
		CoAuthor.addManuscript(conference, manuscript);
		CoAuthor.addManuscript(conference, manuscript);
		CoAuthor.addManuscript(conference, manuscript);
		CoAuthor.addManuscript(conference, manuscript);
		Manuscript fithManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, listOfAuthors);
		myAuthor.addManuscript(conference, fithManuscript);
		assertEquals("ManuScript was not added to list properly", fithManuscript, myAuthor.getManuscript(conference).get(4));		
	}
	
	/**
	/ Test to check buissness rule An Author is limited to 5 Manuscripts using CoAuthor
	/ having total of 5 Manuscripts Buissness rule should be triggered.
	*/
	@Test
	public void testMaxPriorManuscriptsAsCoAuthor() {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		ArrayList<Author> listOfAuthors = new ArrayList<Author>();
		listOfAuthors.add(myAuthor);
		Author CoAuthor = new Author("Batman");
		listOfAuthors.add(CoAuthor);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, listOfAuthors);
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference(date, date, reviewerList);
		CoAuthor.addManuscript(conference, manuscript);
		CoAuthor.addManuscript(conference, manuscript);
		CoAuthor.addManuscript(conference, manuscript);
		CoAuthor.addManuscript(conference, manuscript);
		CoAuthor.addManuscript(conference, manuscript);
		Manuscript sixthManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, listOfAuthors);
		myAuthor.addManuscript(conference, sixthManuscript);
		assertFalse(myAuthor.getManuscript(conference).contains(sixthManuscript));	
	}
	
	/**
	/ Test to check buissness rule An Author is limited to 5 Manuscripts using both Author and CoAuthor
	/ having total of 5 Manuscripts Buissness rule should be triggered.
	*/
	@Test
	public void testMaxPiorManuscriptsAsBothAuthorAndCoAuthor() {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		ArrayList<Author> listOfAuthors = new ArrayList<Author>();
		listOfAuthors.add(myAuthor);
		Author CoAuthor = new Author("Batman");
		listOfAuthors.add(CoAuthor);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, listOfAuthors);
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference(date, date, reviewerList);
		CoAuthor.addManuscript(conference, manuscript);
		CoAuthor.addManuscript(conference, manuscript);
		CoAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		Manuscript sixthManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, listOfAuthors);
		myAuthor.addManuscript(conference, sixthManuscript);
		assertFalse(myAuthor.getManuscript(conference).contains(sixthManuscript));	
	}
	
	/**
	/ Test to check buissness rule An Author is limited to 5 Manuscripts using both Author and CoAuthor
	/ having a total of 4 Manuscripts Buissness rule should not be triggered.
	*/
	@Test
	public void testMaxLessThanMaxPriorManuscriptsAsAuthorAndCoAuthor() {
		Date date = new Date();
		HashMap<Reviewer, String> mapOfReviewers = new HashMap<Reviewer, String>();
		ArrayList<Author> listOfAuthors = new ArrayList<Author>();
		listOfAuthors.add(myAuthor);
		Author CoAuthor = new Author("Batman");
		listOfAuthors.add(CoAuthor);
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, listOfAuthors);
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference(date, date, reviewerList);
		CoAuthor.addManuscript(conference, manuscript);
		CoAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		Manuscript fithManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, listOfAuthors);
		myAuthor.addManuscript(conference, fithManuscript);
		assertEquals("ManuScript was not added to list properly", fithManuscript, myAuthor.getManuscript(conference).get(4));		
	}

}
