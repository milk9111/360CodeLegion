
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is representing an Author.
 * 
 * @author Casey Anderson
 * @version 1 
 *
 */
public class Author extends User {
	
	/**
	 * List of the current Reviews returned to the Author.
	 */
	private List<File> myReviewList;
	
	/**
	 * Map of the Manuscripts already submitted to each conference.
	 */
	private Map<Conference,List<Manuscript>> myManuscriptList;
	
	/**
	 * The user name for the Author.
	 */
	private String myUserName;
	
	/**
	 * Constructor for Author class.
	 * @param theUserName The Username of the Author.
	 */
	public Author(String theUserName) {
		myUserName = theUserName;
		myReviewList = new ArrayList<File>();
	}
	
	
	/**
	 * Method to add Review to the Author.
	 * @param theReview Review file to be added to Author.
	 */
	public void addReview(File theReview) {
		myReviewList.add(theReview);
	}
	
	/**
	 * Method to change the Authors username.
	 * @param theName The new username desired.
	 */
	public void setUserName(String theName) {
		myUserName = theName;
	}
	
	/**
	 * Method to return the current username.
	 * @return The user name associated with current Author.
	 */
	public String getUserName() {
		return myUserName;
	}
	
	/**
	 * Method to create a new Manuscript.
	 * @param theTitle The title of the manuscript to be created.
	 * @param theSubmittedDate The date the Manuscript is being submitted on.
	 * @param theReviews A map of Reviewers attached to manuscript.
	 * @param theAuthors A List of the Authors who are submitting the manuscript.
	 * @return The Manuscript containing all the new information.
	 */
	public Manuscript createManuscript(String theTitle, Date theSubmittedDate, HashMap<Reviewer, String> theReviews, ArrayList<Author> theAuthors) {
		return new Manuscript(theTitle, theSubmittedDate, theReviews, theAuthors) ;
	}
	
	/**
	 * Method to return Review list of Authors Manuscripts.
	 * @return A list of files containing Authors Reviews.
	 */
	public List<File> getReviewList() {
		return myReviewList;
	}
	
	/**
	 * Adds manuscript to a conference.
	 * @param theConference The conference that the manuscript is to be added to.
	 * @param theManuscript The manuscript to be added to Conference.
	 */
	public void addManuscript(Conference theConference, Manuscript theManuscript) {
		if (getNumberOfManuscriptsSubmitted(theConference) <= 5) {
			if (myManuscriptList.containsKey(theConference)) {
				myManuscriptList.get(theConference).add(theManuscript);
			} else {
				List<Manuscript> ManuscriptList = new ArrayList<Manuscript>();
				ManuscriptList.add(theManuscript);
				myManuscriptList.put(theConference, ManuscriptList);
			}
		} else {
			System.out.println("Already have 5 Manuscript submitted!");
		}
	}
	
	/**
	 * Method to return how many manuscripts Author has already submitted.
	 * @param theConference The Conference to be checked for how many manuscripts Author has already submitted.
	 * @return An Integer representing how many manuscripts the Author has already submitted.
	 */
	public int getNumberOfManuscriptsSubmitted(Conference theConference) {
		return myManuscriptList.get(theConference).size();
	}
	
	/**
	 * Method to return Conference list for Author.
	 * @return The available Conference list.
	 */
	@Override
	public List<Conference> getConferenceList() {
		return super.myConferenceList;
	}
	
}