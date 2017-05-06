package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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

		boolean isUsernameUnique = userList.containsValue(theUser.getUsername());
	    if(isUsernameUnique) {
	    	System.out.println("Username is already taken");
	    } else {
	    	System.out.println("Username is valid");
	    }
	    
	    return null;
	}
	
	
	/**
	 * Deserialize the user list, if user list exists
	 * @return a TreeMap of the userlist
	 */
	public TreeMap<UUID, User> deserializeUserList() {
		TreeMap<UUID, User> userListToReturn = null;

		try {
			FileInputStream fileIn = new FileInputStream("serializedModel/users.ser");
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
}
