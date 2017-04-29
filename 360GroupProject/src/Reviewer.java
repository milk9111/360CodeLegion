

import java.util.ArrayList;
import java.util.List;

/**
 * Reviewers class.
 * 
 * @author Morgan Blackmore
 * @version 4/27/17
 */
public class Reviewer extends User {
	/** Constant: maximum number of reviews allowed for a reviewer. */
	private static final int MAX_REVIEWS = 8;

	/** List of the Assigned Manuscripts to this reviewers. */
	private List<Manuscript> myAssignedManuscriptList;
	
	/**
	 * Constructor for Reviewer.
	 */
	public Reviewer(String theName, List<Conference> theConferenceList){
		super(theName, theConferenceList);
		this.myAssignedManuscriptList = new ArrayList<Manuscript>();
		
	}
	
	/**
	 * Constructor for Reviewer with assigned manustripts list.
	 */
	public Reviewer(String theName, List<Conference> theConferenceList, List<Manuscript> theManuscripts){
		super(theName, theConferenceList);
		this.myAssignedManuscriptList = theManuscripts;
		
	}
	
	/**
	 * Assign a new manuscript to this reviewer.
	 * Checks for business rules.  Returns an error message if fails.  
	 * Should also throw some exception to the class that's calling it.
	 * Adds the manuscript to this reviewer's list.
	 * 
	 * @param theMan The Manuscript to be assigned
	 */
	public void assignManuscript(Manuscript theManuscript){
		//Condition 1: check if Reviewer is over review limit
		if (isOverReviewLimit() == false) {
			//Condition 2: check if Reviewer is a coauthor on this manuscript 
			if (isReviewerAnAuthor(theManuscript) == false) {
				
				myAssignedManuscriptList.add(theManuscript);
			
			}
		} else {
			//return some error message
		}
	}
	
	/**
	 * Compares size of assigned manuscript list with limit defined by MAX_REVIEWS
	 * 
	 * @return true if over limit.
	 */
	private boolean isOverReviewLimit() {
		boolean isOver = false;
		if (myAssignedManuscriptList.size() >= MAX_REVIEWS) {
			isOver = true;
		} 
		
		return isOver;
	}
	
	/**
	 * Compares if the assigned reviewer is an author of this paper.
	 * 
	 * @return true if reviewer is an author.
	 */
	private boolean isReviewerAnAuthor(Manuscript theManuscript) {
		boolean isAuthor = false;

		String reviwerName = getName();
		List<Author> authorlist = theManuscript.getAuthors();
		
		for (Author coauthor : authorlist){ 
			if (coauthor.equals(reviwerName));
				isAuthor = true;
		}
		
		return isAuthor;
		
	}
	


	public List<Manuscript> getAssignedManuscriptList() {
		return myAssignedManuscriptList;
	}

	public void setAssignedManuscriptList(List<Manuscript> theAssignedManuscriptList) {
		this.myAssignedManuscriptList = theAssignedManuscriptList;
	}



	@Override
	public List<Conference> getConfernceList() {

		return super.myConferenceList;
	}
	
	
	
}
