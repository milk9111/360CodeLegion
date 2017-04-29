package group3project;

import java.util.ArrayList;
import java.util.List;

/**
 * Reviewers class.
 * 
 * @author Morgan Blackmore
 * @version 4/27/17
 */
public class Reviewer {
	/** Constant: maximum number of reviews allowed for a reviewr. */
	private static final int MAX_REVIEWS = 8;
	/** Number of Reviews that have been assigned to this reviewer. */
	private int numReviews;
	/** List of the Assigned Manuscripts to this reviewers. */
	private List<Manuscript> assignedManuscriptList;
	
	/**
	 * Default constructor for Reviewers object.
	 * Initializes number of reviews and assigned manuscript list to zero.
	 */
	public Reviewer(){

		setNumReviews(0);
		
		setAssignedManuscriptList(new ArrayList<Manuscript>());
		
	}
	
	/**
	 * Assign a new manuscript to this reviewer.
	 * Adds the manuscript to this reviewer's list and increments the number of reviews.
	 * 
	 * @param theMan The Manuscript to be assigned
	 */
	public void assignManuscript(Manuscript theManuscript){
		if (isOverReviewLimit() == false) {
			if (isReviewerAnAuthor(theManuscript) == false) {
			assignedManuscriptList.add(theManuscript);
			setNumReviews(getNumReviews()+1);
			}
		} else {
			//return some error message
		}
	}
	
	/**
	 * Compares current value of numReviews with limit of MAX_REVIEWS
	 * 
	 * @return true if over limit, false otherwise
	 */
	private boolean isOverReviewLimit() {
		boolean isOver = false;
		if (getNumReviews() > MAX_REVIEWS) {
			isOver = true;
		} 
		
		return isOver;
	}
	
	/**
	 * Compares is the assigned reviewer is an author of this paper.
	 * 
	 * @return true if reviewer is an author, false otherwise.
	 */
	private boolean isReviewerAnAuthor(Manuscript theManuscript) {
		boolean isAuthor = false;
		//call manuscript getauthor method and compare to User getusername method 
		if ( ){ 
			
		}
		
	}
	


	public List<Manuscript> getAssignedManuscriptList() {
		return assignedManuscriptList;
	}

	public void setAssignedManuscriptList(List<Manuscript> assignedManuscriptList) {
		this.assignedManuscriptList = assignedManuscriptList;
	}

	public int getNumReviews() {
		return numReviews;
	}

	public void setNumReviews(int numReviews) {
		this.numReviews = numReviews;
	}
	
	
	
}
