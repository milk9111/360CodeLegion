package model;

import java.io.Serializable;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Created by Josiah on 5/6/2017.
 * @author Josiah, Ryan Tran
 */
public class Account implements Serializable {

	// TODO: change theses to lists and add conference ids to each user type
	// that way, a user can have a user type for multiple conferneces
	/**
	 * Map of Key: Conference Ids, Value: Author Object
	 * An account can be an author to multiple conferences so this list represents 
	 * that multi conference relationship with one author object per author role for a conference
	 */
    private TreeMap<UUID, Author> myAuthors;
    
    /**
     * Map of Key: Conference Ids, Value: Reviewer Object
     * Map representing relationship between a user being a reviewer to a conference
     */
    private TreeMap<UUID, Reviewer> myReviewers;
    
    /**
     * Map of Key: Conference Ids, Value: Subprogram Chair Object
     * Map representing relationships between a conference and subprogram chair
     */
    private TreeMap<UUID, SubprogramChair> mySubprogramChairs;
    private UUID myID;
    private String myUsername;
    
    public Account(String theUsername) {
    	this.myID = UUID.randomUUID();
    	this.myUsername = theUsername;
        this.myAuthors = new TreeMap<UUID, Author>();
        this.myReviewers = new TreeMap<UUID, Reviewer>();
    }
    
    public Account(TreeMap<UUID, Author> theAuthors, TreeMap<UUID, Reviewer> theReviewers, TreeMap<UUID, SubprogramChair> theSubprogramChairs) {
    	this.myID = UUID.randomUUID();
        this.myAuthors = theAuthors;
        this.myReviewers = theReviewers;
        this.mySubprogramChairs = theSubprogramChairs;
    }
    

    public TreeMap<UUID, Author> getMyAuthorList() {
        return this.myAuthors;
    }

    /**
     * Adds a single author role to the account
     * @param theAuthor The author to add to the account's authors list
     */
    public void addAuthorRoleToAccount(Author theAuthor) {
        this.myAuthors.put(theAuthor.getMyAssociatedConference().getMyID(), theAuthor);
    }
    
    /**
     * Returns the author associated with the conference parameter
     * @param theConference conference who's id we are checking to see if the author belongs to it
     * @return An Author object belonging to the given conference
     * preconditions: Assumes the an author associated with the given conference exists, use
     * doesAuthorAssociatedWithConferenceExists
     */
    public Author getAuthorAssociatedWithConference(Conference theConference) {
    	return this.myAuthors.get(theConference.getMyID());
    }
    
    /**
     * Returns true or false depending on whether or not the the account has an author
     * that belongs to the given conference
     * @param theAuthor author to check if one exists within the user's myAuthors that also belongs
     * to the given conference
     * @param theConference Conference to check 
     * @return
     */
    public boolean doesAutorAssociatedWithConferenceExist(Conference theConference) {
    	boolean isAuthorAssociated = false;

    	for(Author anAuthor : this.myAuthors.values()) {
    		if(anAuthor.getMyAssociatedConference().getMyID().equals(theConference.getMyID())) {
    			isAuthorAssociated = true;
    		}
    	}
    	
    	return isAuthorAssociated;
    }

    public TreeMap<UUID, Reviewer> getMyReviewers() {
        return myReviewers;
    }

    public void addReviewerRoleToAccount(Reviewer theReviewer, Conference theConference) {
    	this.myReviewers.put(theConference.getMyID(), theReviewer);
    }

    public TreeMap<UUID, SubprogramChair> getMySubprogramChairList() {
        return this.mySubprogramChairs;
    }

    /**
     * Adds a given subprogram chair, and its associated conference to the account's subprogram chair role list
     * @param theSubprogramChair the subprogram chair to add to the account's subchair list
     * @param theConference the conference to associate the subchair with
     */
    public void addSubprogramChairRoleToAccount(SubprogramChair theSubprogramChair, Conference theConference) {
        this.mySubprogramChairs.put(theConference.getMyID(), theSubprogramChair);
    }
    
    public String getMyUsername() {
    	return this.myUsername;
    }
    
    public UUID getMyID() {
    	return this.myID;
    }


}
