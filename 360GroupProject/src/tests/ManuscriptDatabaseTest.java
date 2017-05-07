/**
 * 
 */


import static org.junit.Assert.*;

import java.util.TreeMap;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.Manuscript;
import model.ManuscriptDatabase;

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
		fail("Not yet implemented");
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
	public void createEmptySerializedAccountList_ForNoListAvailable_ShouldCreateNewList() {
		myManuscriptDatabase.createEmptySerializedManuscriptList();
		TreeMap<UUID, Manuscript> validList = myManuscriptDatabase.deserializeManuscriptList();
		assertTrue(validList.size() == 0);
	}

}
