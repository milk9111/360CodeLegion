package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import model.Account;

/**
 * @author Ryan Tran
 * This class will hold fields and methods representing interaction with the Account database
 * which is persisted as a serialized object
 */
public class AccountDatabase {

	private TreeMap<UUID, Account> myAccountList;
	
	/**
	 * serialized folder to prevent error from being thrown.
	 */
	private final String Account_SERIALIZED_PATH = "/C:/serializedModel/";
	
	public void AccountDatabase() {
		myAccountList = null;
	}
	
	/**
	 * Returns a list of accounts by deserializing the account object file
	 * precondition: Assumes an existing account file is on the file system
	 * if not, then you can create one using createEmptySerializedAccountList();
	 * @return
	 */
	public TreeMap<UUID, Account> getAllAccounts() {
		TreeMap<UUID, Account> mapToReturn = deserializeAccountList();
		return mapToReturn;
	}
	
	/**
	 * Given the user name returns the associated user account from the account.
	 * Returns null if account is not found
	 * precondition: username belongs to valid account
	 * @param theAccountList The account list to search for the username
	 * @param theUsername the username to find the associated account for
	 * @return an account associated with the passed in username
	 */
	public Account getAccountByUsername(TreeMap<UUID, Account> theAccountList, String theUsername) {
		Account accountToReturn = null;

		for (Account aAccountToCompare : theAccountList.values()) {
			if (theUsername.equals(aAccountToCompare.getMyUsername())) {
				accountToReturn = aAccountToCompare;
			}
		}
		
		return accountToReturn;
	}
	
	/**
	 * Checks to see if a given account exists within the database and returns
	 * a boolean stating so
	 * @param theAccount the account to check if exists within database
	 * @return boolean, true if account exists within db, false otherwise
	 */
	public boolean doesAccountExistWithinDatabase(Account theAccount) {
		boolean accountExists = false;
		TreeMap<UUID, Account> listOfAccounts = deserializeAccountList();
		
		for(Account acctToCompare : listOfAccounts.values()) {
			if(acctToCompare.getMyID().equals(theAccount.getMyID())) {
				accountExists = true;
			}
		}
		
		return accountExists;
	}
	
	/**
	 * This method will save the given accounts to the serialized database object.
	 * This method will do so by first checking if the accounts's Username is valid or not.
	 * @param theAccount the Account object to be saved to the database
	 * @return A Account after it has been successfully saved to the database,
	 * else return null if not possible
	 */
	public Account saveNewAccountToDatabase(Account theAccount) {
		TreeMap<UUID, Account> accountList = deserializeAccountList();

		boolean usernameExists = doesUsernameExistInDB(accountList, theAccount.getMyUsername());
	    if(!usernameExists) {
	    	accountList.put(theAccount.getMyID(), theAccount);
	    	saveAccountListToDatabase(accountList);
	    } else {
	    	System.out.println("Username is invalid");
	    }
	    
	    return null;
	}
	
	/**
	 * This method will accept an account and save it to the serialized account list, updating
	 * the already existing account within the list.
	 * preconditions: Assumes the given account already exists within the list.
	 * @param theAccount the updated Account object to be saved to the database
	 * @return An Account after it has been successfully saved to the database,
	 */
	public Account updateAndSaveAccountToDatabase(Account theAccount) {
		TreeMap<UUID, Account> accountList = deserializeAccountList();
		if(accountList.containsKey(theAccount.getMyID())) {
			accountList.put(theAccount.getMyID(), theAccount);
			saveAccountListToDatabase(accountList);

			return accountList.get(theAccount.getMyID());
		} else {
			return null;
		}
	}
	
	/**
	 * This method will check if the given username belongs to an account that is part of the system
	 * @param theAccountList the account list to check against
	 * @param theUsername the Account
	 * @return
	 */
	public boolean doesUsernameExistInDB(TreeMap<UUID, Account> theAccountList, String theUsername) {
		boolean usernameExistsInDB = false;
		for (Account aAccountToCompare : theAccountList.values()) {
			System.out.println(aAccountToCompare.getMyUsername());
			System.out.println(theUsername);
			if (theUsername.equals(aAccountToCompare.getMyUsername())) {
				usernameExistsInDB = true;
			}
		}
		return usernameExistsInDB;
	}
	
	/**
	 * Saves accountList to database
	 * @param theAccountList
	 */
	public void saveAccountListToDatabase(TreeMap<UUID, Account> theAccountList) {
	      try {
	         FileOutputStream fileOut = new FileOutputStream(Account_SERIALIZED_PATH + "accounts.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(theAccountList);
	         out.close();
	         fileOut.close();
	         // System.out.printf("Serialized data is saved in " + Account_SERIALIZED_PATH + "accounts.ser");
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	}
	
	
	/**
	 * Deserialize the account list, if account list exists
	 * @return a TreeMap of the accountlist
	 */
	public TreeMap<UUID, Account> deserializeAccountList() {
		TreeMap<UUID, Account> accountListToReturn = null;

		try {
			FileInputStream fileIn = new FileInputStream(Account_SERIALIZED_PATH + "accounts.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			accountListToReturn = (TreeMap<UUID, Account>) in.readObject();
			in.close();
			fileIn.close();

		// return nulls on error
		} catch(IOException i) {
			i.printStackTrace();
			return null;
		} catch(ClassNotFoundException c) {
			System.out.println("Account List could not be found");
			c.printStackTrace();
			return null;
		}
		
		return accountListToReturn;
	}
	

	public void createEmptySerializedAccountList() {
		TreeMap<UUID, Account> emptyAccountList = new TreeMap<UUID, Account>();
		
	      try {
	         FileOutputStream fileOut = new FileOutputStream(Account_SERIALIZED_PATH + "accounts.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(emptyAccountList);
	         out.close();
	         fileOut.close();
	         // System.out.printf("Serialized data is saved in " + Account_SERIALIZED_PATH + "accounts.ser");
	         // any ideas?
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	   }
	
	/**
	 * Returns a list of all accounts with a Reviewer object initiated
	 * @return
	 */
	public ArrayList<Reviewer> getListOfAllReviewers() {
		ArrayList<Reviewer> listToReturn = new ArrayList<Reviewer>();
		TreeMap<UUID, Account> accountList = deserializeAccountList();
		// check for empty db of accounts
		if(accountList.size() == 0) {
			return null;
		}
		
		for(Account anAcct : accountList.values()) {
			if(anAcct.getMyReviewer() != null) {
				listToReturn.add(anAcct.getMyReviewer());
			}
		}
		
		return listToReturn;
		
	}
	
	/**
	 * Gets an account by their reviewer
	 * @param theReviewer
	 * @return
	 */
	public Account getAccountByReviewer(Reviewer theReviewer) {
		Account returnAccount = null;
		TreeMap<UUID, Account> accountList = deserializeAccountList();

		for(Account anAcct : accountList.values()) {
			if(anAcct.getMyReviewer() != null) {
				if(anAcct.getMyReviewer().getMyID().equals(theReviewer.getMyID())) {
					returnAccount = anAcct;
				}
			}
		}
		
		return returnAccount;
	}
	
	public void printContents() {
		TreeMap<UUID, Account> currentList = deserializeAccountList();
		for (Map.Entry<UUID, Account> entry : currentList.entrySet()) {
		     System.out.println("ID: " + entry.getKey() + ". Value: " + entry.getValue().getMyUsername());
		}
	}
	
}
