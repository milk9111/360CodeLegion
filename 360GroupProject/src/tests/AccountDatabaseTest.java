import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import model.Account;
import model.Account;
import model.Account;
import model.AccountDatabase;
import model.Author;
import model.Conference;
import model.Manuscript;
import model.Reviewer;

/**
 * 
 */

/**
 * @Account Ryan Tran
 *
 */
public class AccountDatabaseTest {

	private AccountDatabase myAccountDatabase;
	private Conference myConference;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myAccountDatabase = new AccountDatabase();
		myAccountDatabase.createEmptySerializedAccountList();
		this.myConference = new Conference("RSA", new Date(), new Date());
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
	
	@Test
	public void getListOfAllReviewers_forDBWithReviewers_shouldReturnReviewers() {
		// init and save accounts to db
		Account newAcctA = new Account("Ryan");
		Account newAcctB = new Account("Paul");
		Account newAcctC = new Account("Steve");
		
		myAccountDatabase.saveNewAccountToDatabase(newAcctA);
		myAccountDatabase.saveNewAccountToDatabase(newAcctB);
		myAccountDatabase.saveNewAccountToDatabase(newAcctC);
		
		// init manuscripts and their authors
		newAcctA.addAuthorRoleToAccount(new Author(this.myConference));
		newAcctB.addAuthorRoleToAccount(new Author(this.myConference));
		newAcctC.addAuthorRoleToAccount(new Author(this.myConference));
		
		Manuscript newManuA = new Manuscript("Manu a title", new Date(), newAcctA.getMyAuthor(), new File(""));
		Manuscript newManuB = new Manuscript("Manu b title", new Date(), newAcctB.getMyAuthor(), new File(""));
		Manuscript newManuC = new Manuscript("Manu c title", new Date(), newAcctC.getMyAuthor(), new File(""));
		
		// add reviewers to user and save them
		Reviewer newReviewA = new Reviewer(newManuA, this.myConference);
		Reviewer newReviewB = new Reviewer(newManuB, this.myConference);
		Reviewer newReviewC = new Reviewer(newManuC, this.myConference);

		newAcctA.setReviewer(newReviewA);
		newAcctB.setReviewer(newReviewB);
		newAcctC.setReviewer(newReviewC);
		
		
		ArrayList<Reviewer> allReviewersList = myAccountDatabase.getListOfAllReviewers();
		assertTrue(allReviewersList.size() == 3);
		
		newAcctA = myAccountDatabase.deserializeAccountList().get(newAcctA.getMyID());
		assertTrue(newAcctA.getMyReviewer().getMyID().equals(newReviewA.getMyID()));
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
		myAccountDatabase.printContents();

	}

	/**
	 * Test method for {@link AccountDatabase#deserializeAccountList()}.
	 */
	@Test
	public void testDeserializeAccountList() {
		TreeMap<UUID, Account> validList = myAccountDatabase.deserializeAccountList();
		
		assertTrue(validList instanceof TreeMap);
	}
	
	@Test
	public void createEmptySerializedAccountList_ForNoListAvailable_ShouldCreateNewList() {
		myAccountDatabase.createEmptySerializedAccountList();
	}
	
	@Test
	public void updateAndSaveAccountToDatabase_ForsingleAccount_ShouldUpdateAccount() {
		Conference validConference = new Conference("The science of everything", new Date(), new Date());
		
		// add single account to list
		Account validAccount = new Account("Ryan");
		myAccountDatabase.saveAccountToDatabase(validAccount);
		
		TreeMap<UUID, Account> validList = myAccountDatabase.deserializeAccountList();
		assertTrue(validList.size() == 1);
		
		// update account
		Author authorToAddtoAccount = new Author(validConference);
		validAccount.addAuthorRoleToAccount(authorToAddtoAccount);
		
		myAccountDatabase.updateAndSaveAccountToDatabase(validAccount);
		
		// check if updates were properly saved to serialized object
		validList = myAccountDatabase.deserializeAccountList();
		
		//TODO: refactor test

	}
	
	@Test
	public void doesAccountExistWithinDatabase_forExistingAccount_shouldExist() {
		// add single account to list
		Account validAccount = new Account("Ryan");
		myAccountDatabase.saveAccountToDatabase(validAccount);
		
		TreeMap<UUID, Account> validList = myAccountDatabase.deserializeAccountList();
		assertTrue(validList.size() == 1);
		assertTrue(myAccountDatabase.doesAccountExistWithinDatabase(validAccount));
	}
	
	@Test
	public void doesAccountExistWithinDatabase_forAcctNotInDB_shouldNotExist() {
		// add single account to list
		Account validAccount = new Account("Ryan");
		Account invalidAccount = new Account("acctNotInDB");
		myAccountDatabase.saveAccountToDatabase(validAccount);
		
		TreeMap<UUID, Account> validList = myAccountDatabase.deserializeAccountList();
		assertTrue(validList.size() == 1);
		assertTrue(myAccountDatabase.doesAccountExistWithinDatabase(validAccount));
		assertFalse(myAccountDatabase.doesAccountExistWithinDatabase(invalidAccount));
	}


}
