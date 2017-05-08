package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Subprogram chair class to handle all fields/methods of a subprogram chair.
 * Primary responsibilities of a subprogram chair is to assign manuscripts to reviewers to review
 * and to submit their recommendation for a manuscript to the program chair
 * @author Ryan Tran
 * @date 4/28/17
 * @version 1.0
 */
public class SubprogramChair extends User implements Serializable {

    /**
     * List manuscripts assigned to the subprogram chair
     */
    private Map<UUID, HashSet<UUID>> myAssignedManuscripts;
    
    /**
     * List of assigned manuscripts because I have no idea how the map works.
     * 
     * @author Connor Lundberg
     */
    private ArrayList<Manuscript> myManuscripts;
    
    /**
     * This follows the same logic as above. I don't understand the map, so this
     * is simple a list of lists because a manuscript can have more than one reviewer.
     * 
     * @author Connor Lundberg
     */
    private ArrayList<ArrayList<Reviewer>> myReviewers;


    /**
     * Constructor for the subprogram chair
     * @param theUsername Username of the subprogram chair
     */
    public SubprogramChair(String theUsername, Conference theConference) {
    	super(theUsername, theConference);
        this.myAssignedManuscripts = new HashMap<UUID, HashSet<UUID>>();
        this.myManuscripts = new ArrayList<Manuscript>();
        this.myReviewers = new ArrayList<ArrayList<Reviewer>>();
        super.myConferenceList = new ArrayList<Conference>();
    }
    
    /**
     * Constructor that just associates the subprogram chair with a conference
     * @param theConference
     */
    public SubprogramChair(Conference theConference) {
    	super(theConference);
    	this.myAssignedManuscripts = new HashMap<UUID, HashSet<UUID>>();
    	this.myManuscripts = new ArrayList<Manuscript>();
    	this.myReviewers = new ArrayList<ArrayList<Reviewer>>();
        super.myConferenceList = new ArrayList<Conference>();
    }
    
    
    /**
     * Assigns the Manuscript to the Manuscripts list.
     * 
     * @param theManuscript The Manuscript being added.
     * @author Connor Lundberg
     * @version 5/8/2017
     */
    public void assignManuscriptToSubChair (Manuscript theManuscript) {
    	myManuscripts.add(theManuscript);
    	myReviewers.add(new ArrayList<Reviewer>());
    }
    
    
    /**
     * Returns the myManuscripts ArrayList. Again, just using it because
     * I don't want to fight the Map.
     * 
     * @return The array list
     * @author Connor Lundberg
     * @version 5/8/2017
     */
    public ArrayList<Manuscript> getManuscripts () {
    	return myManuscripts;
    }
    
    
    /**
     * Adds a reviewer to the reviewers list given the specified manuscript list position.
     * 
     * @param theReviewer The reviewer to add
     * @param theManuscriptListPos The position in the assigned manuscripts list
     * @author Connor Lundberg
     * @version 5/8/2017
     */
    public void addReviewer (Reviewer theReviewer, int theManuscriptListPos) {
//    	System.out.println(myReviewers == null);
//    	System.out.println(myReviewers.get(theManuscriptListPos) == null);
    	myReviewers.get(theManuscriptListPos).add(theReviewer);
    }
    
    
    /**
     * Returns the reviewer list for the specified manuscript list position. This is
     * assuming the position provided is within the ArrayList.
     * 
     * @param theManuscriptListPos The manuscript reviewers position to retrieve
     * @return The manuscript reviewers
     * @author Connor Lundberg
     * @version 5/8/2017
     */
    public ArrayList<Reviewer> getReviewerListForManuscript (int theManuscriptListPos) {
    	return myReviewers.get(theManuscriptListPos);
    }
    
    
    /**
     * Finds the position of the manuscript inside the assigned manuscripts list if it's
     * there.
     * 
     * @param theManuscriptToFind The requested manuscript
     * @return The manuscript position in the list, -1 otherwise.
     */
    public int findManuscriptPos (Manuscript theManuscriptToFind) {
    	int manuscriptPos = -1;
    	for (int i = 0; i < myManuscripts.size(); i++) {
    		if (myManuscripts.get(i).getMyID().equals(theManuscriptToFind.getMyID())) {
    			manuscriptPos = i;
    		}
    	}
    	// System.out.println(manuscriptPos);
    	return manuscriptPos;
    }
    
    /**
     * This method will return a list of manuscripts that the subprogram chair is assigned to.
     * preconditions: Assumes the manuscript list parameter is non-null and the subprogrma chair
     * is assigned to at least 1 manuscript
     * @param theManuList the global manuscripts to retrieve manuscripts from.
     * @return a culled manuscript list consisting of only ones the subprogram chair is assigned to.
     */
    public List<Manuscript> getMyAssignedManuscripts(TreeMap<UUID, Manuscript> theManuList) {
    	ArrayList<Manuscript> listToReturn = new ArrayList<Manuscript>();
    	
    	for(Manuscript listManu : theManuList.values()) {
    		if(this.isManuscriptWithinMyAssignedManuscripts(listManu)) {
    			listToReturn.add(listManu);
    		}
    	}
    	
    	return listToReturn;
    }
    
    /**
     * Checks to see if the passed in manuscript exists within this subprogram chair's list of assigned manuscripts
     * and returns a boolean indicating so.
     * @param theManuscript the manuscript to search for within my assigned manuscripts
     * @return a boolean, true if manuscript exists with assigned manuscripts, false otherwise
     */
    public boolean isManuscriptWithinMyAssignedManuscripts(Manuscript theManuscript) {
    	boolean doesExist = false;
    	if(this.myAssignedManuscripts == null || 
    	   this.myAssignedManuscripts.size() == 0) {
    		return false;
    	}

    	for(HashSet<UUID> myManuList : this.myAssignedManuscripts.values()) {
    		if(myManuList.contains(theManuscript.getMyID())) {
    			doesExist = true;
    		}
    	}
    	
    	return doesExist;
    }


    /**
     * This method should return a list of all reviewers in the system
     * @return a list of Reviewer objects
     */
    public List<Reviewer> getReviewers() {
    	// Queries the system wide database for a list of all reviewers
        return Database.getReviewersList();
    }
    
    /**
     * Returns the subprog chair's list of assigned manuscripts
     * @return
     */
    public HashMap<UUID, HashSet<UUID>> getMyAssignedManuscriptsMap() {
    	return (HashMap<UUID, HashSet<UUID>>) this.myAssignedManuscripts;
    }

    /**
	 * Subprogram chair specific method to return a list of conferences its been assigned
	 * @return The conference list appropriate to the user type.
	 */
    @Override
    public List<Conference> getConferenceList() {
        return super.myConferenceList;
    }
    
}
