package model;

import java.util.TreeMap;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import model.Conference;

/**
 * This is a abstract class to hold information common for all user types.
 * Additionally, holds static methods and fields for Users as a whole
 * 
 * @author Casey Anderson, Ryan Tran
 * @version 1 
 *
 */
public abstract class User {
	
	/**
	 * The User name for the system to recognize the current user.
	 */
	private String MyUserName;
	
	/**
	 * Unique identifer ID for User
	 */
	private UUID myID;
	
	/**
	 * The list of all current conferences the user has access to.
	 */
	protected List<Conference> myConferenceList;
	
	/**
	 * The conference this user role is associated with.
	 * REQUIRED. Whether a program chair, subchair, reviewer, or author, all roles must be
	 * associated with a conference
	 */
	private Conference myAssociatedConference;
	
	/**
	 * Constructor for the User. 
	 * @param theUserName The chosen user name.
	 * @param theConferenceList List of all current conferences.
	 */
	public User(String theUserName, List<Conference> theConferenceList) {
		myID = UUID.randomUUID();
		MyUserName = theUserName;
		myConferenceList = theConferenceList;
	}

	/**
	 * Constructor for the User. Only supplying the username.
	 * @param theUserName The chosen user name.
	 */
	public User(String theUserName, Conference theConference) {
		myID = UUID.randomUUID();
		MyUserName = theUserName;
		myAssociatedConference = theConference;
	}
	
	/**
	 * Constructor for User with a conference
	 * This is possible because the user is NOT the account.
	 * the ACCOUNT class will hold the user's username, name, email, password ect.
	 * While the User class, and its subtypes are meant to hold roles associating the account
	 * with a conference and a role.
	 */
	public User(Conference theConference) {
		myID = UUID.randomUUID();
		myAssociatedConference = theConference;
	}
	
	/**
	 * Method to set the Conference List to the desired list of conferences.
	 * @param theConferenceList The conference list to be associated with this user.
	 */
	public void setConferences(List<Conference> theConferenceList) {
		myConferenceList = theConferenceList;
	}
	
	/**
	 * Abstract method to return a custom List of conferences to each user type.
	 * @return The conference list appropriate to the user type.
	 */
	abstract public List<Conference> getConferenceList();
	
	/**
	 * Method to change the Users username.
	 * @param theUserName The new username desired.
	 */
	public void setUserName(String theUserName) {
		MyUserName = theUserName;
	}

	
	/**
	 * Method to return the current username.
	 * @return The user name associated with current user.
	 */
	public String getUsername() {
		return MyUserName;
	}
	
	/**
	 * Method to return the Usertype's ID. 
	 * DIFFERENT from Account ID
	 * @return UUID indicating user type ID
	 */
	public UUID getMyID() {
		return myID;
	}
	
	public Conference getMyAssociatedConference() {
		return this.myAssociatedConference;
	}
	

}
