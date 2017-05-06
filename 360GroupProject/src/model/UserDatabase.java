package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;
import java.util.UUID;

import model.User;

/**
 * @author Ryan Tran
 * This class will hold fields and methods representing interaction with the User database
 * which is persisted as a serialized object
 */
public class UserDatabase {

	private TreeMap<UUID, User> MY_USER_LIST;
	
	private final String USER_SERIALIZED_PATH = "360GroupProject/src/model/serializedModel/";
	
		
	/**
	 * Instance Methods
	 */
	
	public TreeMap<UUID, User> getAllUsers() {
		TreeMap<UUID, User> mapToReturn = new TreeMap<UUID, User>();
		return mapToReturn;
	}
	
	/**
	 * This method will save the given user to the serialized database object.
	 * This method will do so by first checking if the user's username is valid or not.
	 * @param theUser the User object to be saved to the database
	 * @return A User after it has been successfully saved to the database,
	 * else return null if not possible
	 */
	public User saveUserToDatabase(User theUser) {
		TreeMap<UUID, User> userList = deserializeUserList();

		boolean isUsernameUnique = isUsernameInListValid(userList, theUser);
	    if(isUsernameUnique) {
	    	System.out.println("Username is valid");
	    	userList.put(theUser.getID(), theUser);
	    	saveUserListToDatabase(userList);

	    } else {
	    	System.out.println("Username is invalid");
	    }
	    
	    return null;
	}
	
	public boolean isUsernameInListValid(TreeMap<UUID, User> theUserList, User theUser) {
		boolean usernameIsUnique = true;
		for (User aUserToCompare : theUserList.values()) {
			if (theUser.getUsername().equals(aUserToCompare.getUsername())) {
				usernameIsUnique = false;
			}
		}
		
		return usernameIsUnique;
	}
	
	/**
	 * Saves userlist to database
	 * @param theUserList
	 */
	public void saveUserListToDatabase(TreeMap<UUID, User> theUserList) {
	      try {
	         FileOutputStream fileOut = new FileOutputStream(USER_SERIALIZED_PATH + "users.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(theUserList);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in " + USER_SERIALIZED_PATH + "users.ser");
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	}
	
	
	/**
	 * Deserialize the user list, if user list exists
	 * @return a TreeMap of the userlist
	 */
	public TreeMap<UUID, User> deserializeUserList() {
		TreeMap<UUID, User> userListToReturn = null;

		try {
			FileInputStream fileIn = new FileInputStream(USER_SERIALIZED_PATH + "users.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			userListToReturn = (TreeMap<UUID, User>) in.readObject();
			in.close();
			fileIn.close();

		// return nulls on error
		} catch(IOException i) {
			i.printStackTrace();
			return null;
		} catch(ClassNotFoundException c) {
			System.out.println("User List could not be found");
			c.printStackTrace();
			return null;
		}
		
		return userListToReturn;
	}
	

	public void createEmptySerializedUserList() {
		TreeMap<UUID, User> emptyUserList = new TreeMap<UUID, User>();
		
	      try {
	         FileOutputStream fileOut = new FileOutputStream(USER_SERIALIZED_PATH + "users.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(emptyUserList);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in " + USER_SERIALIZED_PATH + "users.ser");
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	   }
}
