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
	private String MyUserName;
	
	/**
	 * The list of all current conferences the user has access to.
	 */
	protected List<Conference> myConferenceList;
	
	/**
	 * Contructor for the User. 
	 * @param theUserName The chosen user name.
	 * @param theConferenceList List of all current conferences.
	 */
	public User(String theUserName, List<Conference> theConferenceList) {
		MyUserName = theUserName;
		myConferenceList = theConferenceList;
	}

		/**
	 * Contructor for the User. Only supplying the username.
	 * @param theUserName The chosen user name.
	 */
	public User(String theUserName) {
		MyUserName = theUserName;
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
	 * @param theName The new username desired.
	 */
	public void setName(String theName) {
		MyUserName = theName;
	}


	/**
	 * Method to change the Users username.
     * @return returns the user's username as a string
	 */
	public String getUserName() {
		return this.MyUserName;
	}
	
	/**
	 * Mehod to return the current username.
	 * @return The user name associated with current user.
	 */
	public String getName() {
		return MyUserName;
	}
}
