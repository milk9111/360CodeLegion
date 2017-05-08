package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Created by Josiah on 5/6/2017.
 * @author Josiah, Ryan Tran
 */
public class Account implements Serializable {

	// TODO: change theses to lists and add conference ids to each user type
	// that way, a user can have a user type for multiple conferneces
    private Author myAuthor;
    
    private Reviewer myReviewer;
    
    private ConferenceDatabase myConferenceDatabase;
    
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
        this.myAuthor = null;
        this.myReviewer = null;
        this.mySubprogramChairs = new TreeMap<UUID, SubprogramChair>();
        this.myConferenceDatabase = new ConferenceDatabase();
    }
    
    public Account(Author theAuthor, Reviewer theReviewer, TreeMap<UUID, SubprogramChair> theSubprogramChairs) {
    	this.myID = UUID.randomUUID();
        this.myAuthor = theAuthor;
        this.myReviewer = theReviewer;
        this.mySubprogramChairs = theSubprogramChairs;
        this.myConferenceDatabase = new ConferenceDatabase();
    }
    

    public Author getMyAuthor() {
        return this.myAuthor;
    }

    /**
     * Adds a single author role to the account
     * @param theAuthor The author to add to the account's authors list
     */
    public void addAuthorRoleToAccount(Author theAuthor) {
<<<<<<< HEAD

    	//System.out.println(theAuthor.getMyID());
    	//System.out.println(theAuthor.getMyAssociatedConference());
        this.myAuthors.put(theAuthor.getMyAssociatedConference().getMyID(), theAuthor);

        this.myAuthor = theAuthor;

=======
       this.myAuthor = theAuthor;
>>>>>>> b550676266f5e1fb94fc6c9aa159eb0a46c56f7a
    }
    
    public HashSet<UUID> getManuscriptsOfAccountAssociatedWithConference(Conference theConference) {
    	HashSet<UUID> returnHash = null;
    	
    	returnHash = this.myAuthor.getManuscriptsAssociatedWithConference(theConference);
    	
    	return returnHash;
    }
    /**
     * Returns true or false depending on whether or not the the account has an author
     * that belongs to the given conference
     * @param theAuthor author to check if one exists within the user's myAuthors that also belongs
     * to the given conference
     * @param theConference Conference to check 
     * @return boolean
     */
    public boolean doesAuthorAssociatedWithConferenceExist(Conference theConference) {
    	boolean isAuthorAssociated = false;
<<<<<<< HEAD
    	System.out.println(theConference);
    	for(Author anAuthor : this.myAuthors.values()) {
    		if(anAuthor.getMyAssociatedConference().getMyID().equals(theConference.getMyID())) {
    			isAuthorAssociated = true;
    		}
    	}
    	
=======
>>>>>>> b550676266f5e1fb94fc6c9aa159eb0a46c56f7a
    	isAuthorAssociated = this.myAuthor.isConferenceAssociatedWithAuthor(theConference);
    	
    	return isAuthorAssociated;
    }
    
    /**
     * Subprogram Chair Methods
     */

    /**
     * This method will check the given conference list, and cross reference it with the
     * list of subprogram chairs of the accounts and their associated conferences, and finally
     * will return a list of conferences to which the the account has a subprogram chair association
     * preconditions: Assumes the passed in list is not null
     * @param theConfList the latest and up to date deserialized conference list
     * @return All conferences that are related to a subprogram chair user type belonging to the account
     * May also return an empty ArrayList if no conferences available
     */
    public ArrayList<Conference> getAllConferencesAssociatedWithMySubprogramChairList(TreeMap<UUID, Conference> theConfList) {
    	ArrayList<Conference> returnList = new ArrayList<Conference>();
    	
    	for(UUID aConferenceID : this.mySubprogramChairs.keySet()) {
    		if(theConfList.containsKey(aConferenceID)) {
    			returnList.add(theConfList.get(aConferenceID));
    		}
    	}
    	
    	return returnList;
    }
    
    
    /**
     * Returns a list of conferences associated with this account's author roles
     * @param theConfList
     * @return
     */
    public ArrayList<Conference> getAllConferencesAssociatedWithMyAuthorList(TreeMap<UUID, Conference> theConfList) {
    	ArrayList<Conference> returnList = new ArrayList<Conference>();
    	Set<UUID> listOfConfIds = this.myAuthor.getMyListOfConferenceIDs();
    	
    	for(UUID confID : listOfConfIds) {
    		Conference singleConf = this.myConferenceDatabase.getSingleConference(confID);
    		if(singleConf != null) {
    			returnList.add(singleConf);
    		}
    	}
    	    	
    	return returnList;
    }

    
    /**
     * Adds a given subprogram chair, and its associated conference to the account's subprogram chair role list
     * @param theSubprogramChair the subprogram chair to add to the account's subchair list
     * @param theConference the conference to associate the subchair with
     */
    public void addSubprogramChairRoleToAccount(SubprogramChair theSubprogramChair, Conference theConference) {
        this.mySubprogramChairs.put(theConference.getMyID(), theSubprogramChair);
    }

    /**
     * Gets a list of the account's subprogram user roles with associated conferences as key
     * @return a treeMap consisting of the account's subprogram chair types and their associated conferences as keys
     */
    public TreeMap<UUID, SubprogramChair> getMySubprogramChairList() {
        return this.mySubprogramChairs;
    }


    /**
     * Gets account's reviewer
     * @return a treemap of reviewers, and their conference id keys
     */
    public Reviewer getMyReviewer() {
        return this.myReviewer;
    }

    public void setReviewer(Reviewer theReviewer) {
    	this.myReviewer = theReviewer;
    }
    
    public String getMyUsername() {
    	return this.myUsername;
    }
    
    public UUID getMyID() {
    	return this.myID;
    }


}
