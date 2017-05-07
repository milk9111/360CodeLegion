package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Subprogram chair class to handle all fields/methods of a subprogram chair.
 * Primary responsibilities of a subprogram chair is to assign manuscripts to reviewers to review
 * and to submit their recommendation for a manuscript to the program chair
 * @author Ryan Tran
 * @date 4/28/17
 * @version 1.0
 */
public class SubprogramChair extends User {

    /**
     * List manuscripts assigned to the subprogram chair
     */
    private List<Manuscript> myAssignedManuscripts;

    /**
     * List of conferences viewable to the subprogram chair
     */
    private List<Conference> myConferences;

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
        this.myAssignedManuscripts = new ArrayList<Manuscript>();
        this.myConferences = new ArrayList<Conference>();
        this.myAssignedConferences = new ArrayList<Conference>();
        super.myConferenceList = new ArrayList<Conference>();
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
