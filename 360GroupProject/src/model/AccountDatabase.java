package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

	private TreeMap<UUID, Account> MY_ACCOUNT_LIST;
	
	private final String Account_SERIALIZED_PATH = "360GroupProject/src/model/serializedModel/";
	
		
	/**
	 * Instance Methods
	 */
	
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
	 * This method will save the given accounts to the serialized database object.
	 * This method will do so by first checking if the accounts's Username is valid or not.
	 * @param theAccount the Account object to be saved to the database
	 * @return A Account after it has been successfully saved to the database,
	 * else return null if not possible
	 */
	public Account saveAccountToDatabase(Account theAccount) {
		TreeMap<UUID, Account> accountList = deserializeAccountList();

		boolean isUsernameUnique = isUsernameInListValid(accountList, theAccount.getMyUsername());
	    if(isUsernameUnique) {
	    	accountList.put(theAccount.getMyID(), theAccount);
	    	saveAccountListToDatabase(accountList);

	    } else {
	    	System.out.println("Username is invalid");
	    }
	    
	    return null;
	}
	
	/**
	 * This method will check if the given username belongs to an account that is part of the system
	 * @param theAccountList the account list to check against
	 * @param theAccount the Account 
	 * @return
	 */
	public boolean isUsernameInListValid(TreeMap<UUID, Account> theAccountList, String theUsername) {
		boolean UsernameIsUnique = true;
		for (Account aAccountToCompare : theAccountList.values()) {
			if (theUsername.equals(aAccountToCompare.getMyUsername())) {
				UsernameIsUnique = false;
			}
		}
		
		return UsernameIsUnique;
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
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	   }
	
	public void printContents() {
		TreeMap<UUID, Account> currentList = deserializeAccountList();
		for (Map.Entry<UUID, Account> entry : currentList.entrySet()) {
		     System.out.println("ID: " + entry.getKey() + ". Value: " + entry.getValue().getMyUsername());
		}
	}
}