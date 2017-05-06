import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.Account;
import model.Account;
import model.Account;
import model.AccountDatabase;

/**
 * 
 */

/**
 * @Account BlueAccords
 *
 */
public class AccountDatabaseTest {

	private AccountDatabase myAccountDatabase;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myAccountDatabase = new AccountDatabase();
		myAccountDatabase.createEmptySerializedAccountList();
	}

	/**
	 * Test method for {@link AccountDatabase#getAllAccounts()}.
	 */
	@Test
	public void testGetAllAccounts() {
		TreeMap<UUID, Account> validList = myAccountDatabase.getAllAccounts();
		
		assertTrue(validList instanceof TreeMap);
		assertTrue(validList.size() == 0);
	}

	/**
	 * Test method for {@link AccountDatabase#saveAccountToDatabase(Account)}.
	 */
	@Test
	public void testSaveAccountToDatabase_forAValidAccount_shouldSaveToDatabase() {
		Account validAccount = new Account("Ryan");
		myAccountDatabase.saveAccountToDatabase(validAccount);

		TreeMap<UUID, Account> validList = myAccountDatabase.deserializeAccountList();
		assertTrue("list size should only be 1 and have only 1 Account saved", validList.size() == 1);
		assertTrue(validList.get(validAccount.getMyID()).getMyUsername().equals(validAccount.getMyUsername()));
	}
	
	@Test
	public void saveAccounttoDatabase_forNONUniqueUsername_shouldFailToSave() {
		Account validAccount = new Account("Ryan");
		Account invalidAccount = new Account("Ryan");

		myAccountDatabase.saveAccountToDatabase(validAccount);
		myAccountDatabase.saveAccountToDatabase(invalidAccount);

		TreeMap<UUID, Account> validList = myAccountDatabase.deserializeAccountList();
		assertTrue(validList.size() == 1);
		System.out.println(validList.toString());

	}

	/**
	 * Test method for {@link AccountDatabase#deserializeAccountList()}.
	 */
	@Test
	public void testDeserializeAccountList() {
		TreeMap<UUID, Account> validList = myAccountDatabase.deserializeAccountList();
		
		System.out.println(validList.toString());
		assertTrue(validList instanceof TreeMap);
	}
	
	@Test
	public void createEmptySerializedAccountList_ForNoListAvailable_ShouldCreateNewList() {
		myAccountDatabase.createEmptySerializedAccountList();
	}

}
