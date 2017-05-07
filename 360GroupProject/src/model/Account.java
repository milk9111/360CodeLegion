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
    private TreeMap<UUID, Author> myAuthors;
    private Reviewer myReviewer;
    private SubprogramChair mySubprogramChair;
    private UUID myID;
    private String myUsername;
    
    public Account(String theUsername) {
    	this.myID = UUID.randomUUID();
    	this.myUsername = theUsername;
        this.myAuthors = new TreeMap<UUID, Author>();
    }
    
    public Account(TreeMap<UUID, Author> theAuthors, Reviewer myReviewer, SubprogramChair mySubprogramChair) {
    	this.myID = UUID.randomUUID();
        this.myAuthors = theAuthors;
        this.myReviewer = myReviewer;
        this.mySubprogramChair = mySubprogramChair;
    }
    

    public TreeMap<UUID, Author> getMyAuthor() {
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

    public Reviewer getMyReviewer() {
        return myReviewer;
    }

    public void setMyReviewer(Reviewer myReviewer) {
        this.myReviewer = myReviewer;
    }

    public SubprogramChair getMySubprogramChair() {
        return mySubprogramChair;
    }

    public void setMySubprogramChair(SubprogramChair mySubprogramChair) {
        this.mySubprogramChair = mySubprogramChair;
    }
    
    public String getMyUsername() {
    	return this.myUsername;
    }
    
    public UUID getMyID() {
    	return this.myID;
    }


}
