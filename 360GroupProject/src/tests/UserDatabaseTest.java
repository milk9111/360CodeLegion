import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author BlueAccords
 *
 */
public class UserDatabaseTest {

	private UserDatabase myUserDatabase;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myUserDatabase = new UserDatabase();
	}

	/**
	 * Test method for {@link UserDatabase#getAllUsers()}.
	 */
	@Test
	public void testGetAllUsers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link UserDatabase#saveUserToDatabase(User)}.
	 */
	@Test
	public void testSaveUserToDatabase_forAValidUser_shouldSaveToDatabase() {
		User validUser = new Author("Ryan");
		myUserDatabase.saveUserToDatabase(validUser);

		TreeMap<UUID, User> validList = myUserDatabase.deserializeUserList();
		assertTrue(validList.size() == 1);
		System.out.println(validList.toString());
	}
	
	@Test
	public void saveUsertoDatabase_forNONUniqueUsername_shouldFailToSave() {
		User validUser = new Author("Ryan");
		User invalidUser = new Author("Ryan");

		myUserDatabase.saveUserToDatabase(validUser);
		myUserDatabase.saveUserToDatabase(invalidUser);

		TreeMap<UUID, User> validList = myUserDatabase.deserializeUserList();
		assertTrue(validList.size() == 1);
		System.out.println(validList.toString());

	}

	/**
	 * Test method for {@link UserDatabase#deserializeUserList()}.
	 */
	@Test
	public void testDeserializeUserList() {
		TreeMap<UUID, User> validList = myUserDatabase.deserializeUserList();
		
		System.out.println(validList.toString());
		assertTrue(validList instanceof TreeMap);
	}
	
	@Test
	public void createEmptySerializedUserList_ForNoListAvailable_ShouldCreateNewList() {
		myUserDatabase.createEmptySerializedUserList();
	}

}
