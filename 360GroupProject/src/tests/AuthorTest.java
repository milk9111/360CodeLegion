import model.Author;
import model.Conference;
import model.Manuscript;
import model.Reviewer;

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
	public void setUp() {
		myAuthor = new Author("simpson@ieee.org");
		myCoAuthor = new Author("fleegle@uw.edu");
	}

	/**
	 * @author Casey Anderson
	 * Test to add review to an Author.
	 */
	@Test
	public void testAddReview_WithTestFile_ShouldHaveTextFileInAuthorReviewList() {
		File file = new File("./test.txt");
		myAuthor.addReview(file);
		assertEquals("myAuthor Review should be test.txt!", file, myAuthor.getReviewList().get(0));
	}
	
	/**
	 * @author Casey Anderson
	 * Test to check business rule An Author is limited to max Manuscripts using Author 
	 * having total of max minus one prior Manuscripts Business rule should not be triggered.
	 * @throws Exception 
	*/
	@Test
	public void testAddManuscript_MaxMinusOnePriorManuscriptsAsAuthor_ManuscriptAddedToAuthor() throws Exception {
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
	 * Test to check business rule An Author is limited to max Manuscripts using Author
	 * having total of max prior Manuscripts Business rule should be triggered.
	 * @throws Exception 
	*/
	@Test(expected = IllegalArgumentException.class)
	public void testAddManuscript_MaxPriorManuscriptsAsAuthor_IllegalArguentThrown() throws Exception {
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
	}

	/**
	 * @author Casey Anderson
	 * Test to check business rule An Author is limited to max Manuscripts using CoAuthor
	 * having total of max minus one prior Manuscripts Business rule should not be triggered.
	 * @throws Exception 
	*/
	@Test
	public void testAddManuscripts_MaxMinusOnePriorManuscriptsAsCoAuthor_ManuscriptAddedToAuthor() throws Exception {
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
	 * Test to check business rule An Author is limited to max Manuscripts using CoAuthor
	 * having total of max prior Manuscripts Business rule should be triggered.
	 * @throws Exception 
	*/
	@Test(expected = IllegalArgumentException.class)
	public void testAddManuscript_MaxPriorManuscriptsAsCoAuthor_IllegalArgumentThrown() throws Exception {
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
	}
	
	/**
	 * @author Casey Anderson
	 * Test to check business rule An Author is limited to max Manuscripts using both Author and CoAuthor
	 * having total of max prior Manuscripts Business rule should be triggered.
	 * @throws Exception 
	*/
	@Test(expected = IllegalArgumentException.class)
	public void testAddManuscript_MaxPiorManuscriptsAsBothAuthorAndCoAuthor_IllegalArgumentThrown() throws Exception {
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
	}
	
	/**
	 * @author Casey Anderson
	 * Test to check business rule An Author is limited to max Manuscripts using both Author and CoAuthor
	 * having a total of max minus one prior Manuscripts Business rule should not be triggered.
	 * @throws Exception 
	*/
	@Test
	public void testAddManuscript_MaxLessThanMaxPriorManuscriptsAsAuthorAndCoAuthor_ManuscriptAddedToAuthor() throws Exception {
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
