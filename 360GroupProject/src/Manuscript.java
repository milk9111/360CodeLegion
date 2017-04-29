

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author Connor Lundberg
 * @version 4/28/2017
 * 
 * This is the Manuscript class. It contains information about the
 * individual manuscript including author(s) and reviews.
 */
public class Manuscript {
	private HashMap<Reviewer, String> myReviews;
	private ArrayList<Author> myAuthors;
	private Date mySubmittedDate;
	private String myTitle;
	
	
	/**
	 * Simple constructor used for testing purposes.
	 * 
	 * @param theSubmittedDate The date submitted
	 * @param theReviews The reviews to assign
	 * @param theAuthors The authors for the manuscript
	 */
	public Manuscript (String theTitle, Date theSubmittedDate, HashMap<Reviewer, String> theReviews, ArrayList<Author> theAuthors) {
		myReviews = (HashMap<Reviewer, String>) theReviews.clone();
		myAuthors = (ArrayList<Author>) theAuthors.clone();
		mySubmittedDate = theSubmittedDate;
		myTitle = theTitle;
	}
	
	
	/**
	 * Default constructor;
	 */
	public Manuscript () {
		myReviews = new HashMap<Reviewer, String> ();
		myAuthors = new ArrayList<Author> ();
		mySubmittedDate = new Date ();
	}
	
	
	/**
	 * Returns an ArrayList of all the reviews attached to this
	 * manuscript.
	 * 
	 * @return All reviews for this manuscript
	 */
	public ArrayList<String> getReviews () {
		return (ArrayList<String>) myReviews.values();
	}
	
	
	public ArrayList<Reviewer> getReviewers () {
		return (ArrayList<Reviewer>) myReviews.keySet();
	}
	
	
	/**
	 * Returns an ArrayList of all authors attached to this
	 * manuscript.
	 * 
	 * @return All authors for this manuscript
	 */
	public ArrayList<Author> getAuthors () {
		return (ArrayList<Author>) myAuthors.clone();
	}
	
	/**
	 * Sets myAuthors list.  Allows tester to use default constructor and add authors later.
	 */
	public void setAuthors(List<Author> theAuthors) {
		myAuthors = (ArrayList<Author>) theAuthors;
	}
	
	
	/**
	 * Returns the date this manuscript was submitted.
	 * 
	 * @return The submitted date
	 */
	public Date getSubmittedDate () {
		return (Date) mySubmittedDate.clone();
	}
	
	
	/**
	 * Returns the title of this manuscript.
	 * 
	 * @return The manuscript title
	 */
	public String getTitle () {
		return myTitle;
	}
	
	
	/**
	 * Adds a reviewer to this manuscript. This simply uses the
	 * put() method with theReviewer as the key and a default 
	 * empty string as the value.
	 * 
	 * @param theReviewer The reviewer to add
	 */
	public void assignReviewer (final Reviewer theReviewer) {
		myReviews.put(theReviewer, "");
	}
	
	
	/**
	 * Submits a manuscript then returns it upon success
	 * @param
	 * @return A copy of the manuscript
	 */
	public Manuscript submitManuscript () {
		return new Manuscript(this.myTitle, this.mySubmittedDate, this.myReviews, this.myAuthors);
	}
}



















