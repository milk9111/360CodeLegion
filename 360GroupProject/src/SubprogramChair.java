import java.util.ArrayList;
import java.util.Date;

/**
 * Subprogram chair class to handle all fields/methods of a subprogram chair.
 * Primary responsibilities of a subprogram chair is to assign manuscripts to reviewers to review
 * and to submit their recommendation for a manuscript to the program chair
 * @author Ryan Tran
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
     * This method should return a single conference object
     * @return
     */
    public Conference getConference() {
        // TODO: verify what this method should do within the context of the subprogram chair
        return null;
    }

    /**
     * This method should return a list of conference objects assigned to this subprogram chair
     * @return list of conference objects
     */
    public List<Conference> getMyAssignedConferences() {
        return this.myAssignedConferences;
    }

    /**
     * This method should return a list of all reviewers to the subprogram chair
     * @return a list of Reviewer objects
     */
    public List<Reviewer> getReviewers() {
        // TODO: finish getReviewers method(may need to get list of persistent reviewers
        return null;
    }
}
