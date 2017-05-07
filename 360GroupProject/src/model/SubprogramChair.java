package model;

import java.util.ArrayList;
import java.util.List;
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
public class SubprogramChair extends User {

	// TODO: refactor to be a list of UUIDs of manuscripts
    /**
     * List manuscripts assigned to the subprogram chair
     */
    private List<UUID> myAssignedManuscripts;

    // TODO: refactor to UUIDs of Conferences
    /**
     * List of conferences assigned to the subprogram chair by a program chair
     */
    private List<Conference> myAssignedConferences;

    /**
     * Constructor for the subprogram chair
     * @param theUsername Username of the subprogram chair
     */
    public SubprogramChair(String theUsername, Conference theConference) {
    	super(theUsername, theConference);
        this.myAssignedManuscripts = new ArrayList<UUID>();
        this.myAssignedConferences = new ArrayList<Conference>();
        super.myConferenceList = new ArrayList<Conference>();
    }
    
    /**
     * Constructor that just associates the subprogam chair with a conference
     * @param theConference
     */
    public SubprogramChair(Conference theConference) {
    	super(theConference);
    	this.myAssignedManuscripts = new ArrayList<UUID>();
        this.myAssignedConferences = new ArrayList<Conference>();
        super.myConferenceList = new ArrayList<Conference>();
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

    	for(UUID myManuID : this.myAssignedManuscripts) {
    		if(myManuID.equals(theManuscript.getMyID())) {
    			doesExist = true;
    		}
    	}
    	
    	return doesExist;
    }

    /**
     * This method should return a list of conference objects assigned to this subprogram chair
     * @return list of conference objects
     */
    public List<Conference> getMyAssignedConferences() {
        return this.myAssignedConferences;
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
	 * Subprogram chair specific method to return a list of conferences its been assigned
	 * @return The conference list appropriate to the user type.
	 */
    @Override
    public List<Conference> getConferenceList() {
        return super.myConferenceList;
    }
    
}
