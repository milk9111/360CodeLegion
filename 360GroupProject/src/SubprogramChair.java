import java.util.ArrayList;
import java.util.Date;
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

    // List of manuscripts assigned to the subprogram chair
    private List<Manuscript> myAssignedManuscripts;

    /**
     * Constructor for the subprogram chair
     */
    public SubprogramChair(String theUsername, List<Conference> theConferenceList) {
    	super(theUsername, theConferenceList);
        this.myAssignedManuscripts = new ArrayList<Manuscript>();
    }

    /**
     * This method should return a list of conference objects assigned to this subprogram chair
     * @return list of conference objects
     */
    public List<Conference> getMyAssignedConferences() {
        return this.myConferences;
    }

    /**
     * This method should return a list of all reviewers in the system
     * @return a list of Reviewer objects
     */
    public List<Reviewer> getReviewers() {
    	// This method currently relies on persistent data to get the global list of reviewers
        return null;
    }
    
}
