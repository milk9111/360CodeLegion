import model.Account;
import model.Author;
import model.Conference;
import model.Manuscript;
import model.Reviewer;
import model.User;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.UUID;

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
	private Conference myConference;
	private ArrayList<UUID> myListOfAuthors;
	private TreeMap<UUID, Account> myListOfAccountAuthors;

	/**
	 * @author Casey Anderson
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() {
		myConference = new Conference("Alchemy Conference", new Date(), new Date());
		myAuthor = new Author("simpson@ieee.org", myConference);
		myCoAuthor = new Author("fleegle@uw.edu", myConference);
		myListOfAuthors = new ArrayList<UUID>();
		myListOfAccountAuthors = new TreeMap<UUID, Account>();
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
		myListOfAuthors.add(myAuthor.getMyID());
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, myListOfAuthors);
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference("RSA", date, date, reviewerList);
		this.myListOfAccountAuthors.put(, value)
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myListOfAuthors.add(myAuthor.getMyID());
		Manuscript fithManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, myListOfAuthors);
		myAuthor.addManuscript(conference, fithManuscript);
		assertTrue("ManuScript was not added to list properly", myAuthor.getManuscript(conference).contains(fithManuscript.getMyID()));
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
		myListOfAuthors.add(myAuthor.getMyID());
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, myListOfAuthors);
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference("RSA", date, date, reviewerList);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myListOfAuthors.add(myAuthor.getMyID());
		Manuscript sixthManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, myListOfAuthors);
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
		myListOfAuthors.add(myAuthor.getMyID());
		myListOfAuthors.add(myCoAuthor.getMyID());
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, myListOfAuthors);
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference("RSA", date, date, reviewerList);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		Manuscript fithManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, myListOfAuthors);
		myAuthor.addManuscript(conference, fithManuscript);
		assertTrue("ManuScript was not added to list properly", myAuthor.getManuscript(conference).contains(fithManuscript.getMyID()));
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
		myListOfAuthors.add(myAuthor.getMyID());
		myListOfAuthors.add(myCoAuthor.getMyID());
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, myListOfAuthors);
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference("RSA", date, date, reviewerList);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		Manuscript sixthManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, myListOfAuthors);
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
		myListOfAuthors.add(myAuthor.getMyID());
		myListOfAuthors.add(myCoAuthor.getMyID());
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, myListOfAuthors);
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference("RSA", date, date, reviewerList);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		Manuscript sixthManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, myListOfAuthors);
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
		myListOfAuthors.add(myAuthor.getMyID());
		myListOfAuthors.add(myCoAuthor.getMyID());
		Manuscript manuscript = new Manuscript("Old Yeller", date, mapOfReviewers, myListOfAuthors);
		ArrayList<Reviewer> reviewerList = new ArrayList<Reviewer>();
		Conference conference = new Conference("RSA", date, date, reviewerList);
		myCoAuthor.addManuscript(conference, manuscript);
		myCoAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		myAuthor.addManuscript(conference, manuscript);
		Manuscript fithManuscript = new Manuscript("Nano Tech", date, mapOfReviewers, myListOfAuthors);
		myAuthor.addManuscript(conference, fithManuscript);
		assertTrue("ManuScript was not added to list properly", myAuthor.getManuscript(conference).contains(fithManuscript.getMyID()));
	}

}
