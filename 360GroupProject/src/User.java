import java.util.List;

/**
 * This is a abstract class to hold information common for all user types.
 * 
 * @author Casey Anderson
 * @version 1 
 *
 */
public abstract class User {
	
	/**
	 * The User name for the system to recognize the current user.
	 */
	public String MyUserName;
	
	/**
	 * The list of all current conferences the user has access to.
	 */
	public List<Conference> myConferenceList;
	
	/**
	 * Contructor for the User. 
	 * @param theName The chosen user name.
	 * @param theConferenceList List of all current conferences.
	 */
	public User(String theUserName, List<Conference> theConferenceList) {
		MyUserName = theUserName;
		myConferenceList = theConferenceList;
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
	abstract public List<Conference> getConfernceList();
	
	/**
	 * Method to change the Users username.
	 * @param theName The new username desired.
	 */
	public void setName(String theName) {
		MyUserName = theName;
	}
	
	/**
	 * Mehod to return the current username.
	 * @return The user name associated with current user.
	 */
	public String getName() {
		return MyUserName;
	}
}
