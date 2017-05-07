/**
 * 
 */


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.Account;
import model.Author;
import model.Conference;
import model.Manuscript;
import model.ManuscriptDatabase;
import model.Reviewer;

/**
 * @author BlueAccords
 *
 */
public class ManuscriptDatabaseTest {

	private ManuscriptDatabase myManuscriptDatabase;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myManuscriptDatabase = new ManuscriptDatabase();
		this.myManuscriptDatabase.createEmptySerializedManuscriptList();
	}

	/**
	 * Test method for {@link model.ManuscriptDatabase#getAllManuscripts()}.
	 */
	@Test
	public void testGetAllManuscripts() {
		Manuscript validManuscript = new Manuscript("Study of the Sciences", new Date(), new ArrayList<Author>());
		myManuscriptDatabase.saveManuscriptToDatabase(validManuscript);
		
		TreeMap<UUID, Manuscript> validList = myManuscriptDatabase.getAllManuscripts();
		assertTrue(validList instanceof TreeMap);
		assertTrue(validList.size() == 1);
	}
	
	
	@Test
	public void saveManuscriptToDatabase_forValidManuscript_shouldSucceed() {
		Manuscript validManuscript = new Manuscript("Study of the Sciences", new Date(), new ArrayList<Author>());
		myManuscriptDatabase.saveManuscriptToDatabase(validManuscript);

		TreeMap<UUID, Manuscript> validList = myManuscriptDatabase.deserializeManuscriptList();
		assertTrue("list size should only be 1 and have only 1 Manuscript saved", validList.size() == 1);
		assertTrue(validList.get(validManuscript.getMyID()).getTitle().equals(validManuscript.getTitle()));
	}

	/**
	 * Test method for {@link model.ManuscriptDatabase#deserializeManuscriptList()}.
	 */
	@Test
	public void testDeserializeManuscriptList() {
		TreeMap<UUID, Manuscript> validList = myManuscriptDatabase.deserializeManuscriptList();
		assertTrue(validList instanceof TreeMap);
	}
	
	@Test
	public void createEmptySerializedManuscriptList_ForNoListAvailable_ShouldCreateNewList() {
		myManuscriptDatabase.createEmptySerializedManuscriptList();
		TreeMap<UUID, Manuscript> validList = myManuscriptDatabase.deserializeManuscriptList();
		assertTrue(validList.size() == 0);
	}
	
	@Test
	public void getManuscriptsBelongingToAuthor_forAuthorWithManuscripts_shouldReturnValidList() {
		Account userAccount = new Account("RyanTran");
		Conference primaryConference = new Conference("Education of Electronics", new Date(), new Date());
		Author authorRole = new Author(primaryConference);
		Manuscript manuA = new Manuscript("Alpha script", new Date(), authorRole);
		Manuscript manuB = new Manuscript("Beta script", new Date(), authorRole);
		Manuscript manuC = new Manuscript("Charlie script", new Date(), authorRole);
		
		myManuscriptDatabase.saveManuscriptToDatabase(manuA);
		myManuscriptDatabase.saveManuscriptToDatabase(manuB);
		myManuscriptDatabase.saveManuscriptToDatabase(manuC);
		
		ArrayList<Manuscript> returnedList = myManuscriptDatabase.getManuscriptsBelongingToAuthor(authorRole);
		
		assertTrue(returnedList.size() == 3);
	}

}
