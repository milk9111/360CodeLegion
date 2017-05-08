package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Josiah on 4/27/2017.
 */
public class Conference implements Serializable {
    // Unique ID to identify the conference by
    private UUID myID;
    
    private String myName;

    // TODO: change to list of manuscript IDs, May only be needed for Program Chair User Stories
    //Contains the list of manuscripts which have been submitted to this conference.
    private List<Manuscript> myManuscripts;

    //Details the date by which submissions must occur
    private Date mySubmissionDeadline;

    //Details the deadline for reviews to be turned in
    private Date myReviewDeadline;

    // TODO: change to list of UUIDs
    //Contains the list of reviewers that have reviewed for this conference in the past.
    private List<Reviewer> myPastReviewers;

    /**
     * Constructor for a conference.
     * @param theSubmissionDeadline is the date by which submissions must occur
     * @param theReviewDeadline is the deadlines for reviews to be turned in
     * @param thePastReviewers is the list of past reviewers for this conference
     */
    public Conference(String theName, Date theSubmissionDeadline, Date theReviewDeadline,
                      List<Reviewer> thePastReviewers) {
    	this.myID = UUID.randomUUID();
        this.myManuscripts = new ArrayList<Manuscript>();
        this.myPastReviewers = thePastReviewers;
        this.myReviewDeadline = theReviewDeadline;
        this.mySubmissionDeadline = theSubmissionDeadline;
        this.myName = theName;
    }
    
    /**
     * Constructor for conference with no past reviewers
     * @param theName name of conference
     * @param theSubmissionDeadline date of which manuscript submissions are due
     * @param theReviewDeadline date of which reviews are due
     */
    public Conference(String theName, Date theSubmissionDeadline, Date theReviewDeadline) {
    	this.myID = UUID.randomUUID();
        this.myManuscripts = new ArrayList<Manuscript>();
        this.myReviewDeadline = theReviewDeadline;
        this.mySubmissionDeadline = theSubmissionDeadline;
        this.myName = theName;
    }

    /**
     * Gets the list of manuscripts.
     * @author Josiah Hopkins
     * @return the list of manuscripts that have been submitted to this conference so far.
     */
    public List<Manuscript> getManuscripts() {
        return myManuscripts;
    }
    
    public UUID getMyID() {
    	return myID;
    }

    /**
     * Gets the submission Deadline
     * @author Josiah Hopkins
     * @return The date of the submission Deadline
     */
    public Date getSubmissionDeadline() {
        return mySubmissionDeadline;
    }

    /**
     * Gets the review deadline
     * @author Josiah Hopkins
     * @return The date of the review deadline
     */
    public Date getReviewDeadline() {
        return myReviewDeadline;
    }

    /**
     * Gets the list of past reviewers
     * @author Josiah Hopkins
     * @return The list of past reviewers
     */
    public List<Reviewer> getPastReviewers() {
        return myPastReviewers;
    }

    /**
     * Attemps to submit a manuscript to the conference. The date must be before the submission deadline
     * @author Josiah Hopkins
     * @param theManuscript the manuscript which is trying to be submitted to the conference.
     */
    public void submitManuscript(Manuscript theManuscript) {
    	// TODO: change to add manuscript IDs isnead of objects
        if(isWithinSubmissionDeadline()) {
            myManuscripts.add(theManuscript);
           // System.out.println("Adding to manuscript list");
        } else{
            //System.out.println("You're past the date bro.");
        }
    }

    /**
     * validates whether we are within the submission deadline for a conference.
     * @return true if we are before the deadline, false otherwise.
     */
    private boolean isWithinSubmissionDeadline(){
        return mySubmissionDeadline.after(Calendar.getInstance().getTime());
    }

    /**
     * Validates whether we are within the review deadline for a conference
     * @return true if we are before the deadline, false otherwise.
     */
    private boolean isWithinReviewDeadline(){
        return myReviewDeadline.after(Calendar.getInstance().getTime());
    }
    
    public String getMyName() {
        return myName;
    }


}
