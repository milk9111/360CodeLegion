

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Connor Lundberg
 * @version 4/27/2017
 * 
 * This is the Manuscript class. It contains information about the
 * individual manuscript including author(s) and reviews.
 */
public class Manuscript {
	private HashMap<Reviewer, String> myReviews;
	private ArrayList<Author> myAuthors;
	
	
	/**
	 * Simple constructor used for testing purposes.
	 * 
	 * @param theReviews The reviews to assign
	 * @param theAuthors The authors for the manuscript
	 */
	public Manuscript (HashMap<Reviewer, String> theReviews, ArrayList<Author> theAuthors) {
		myReviews = (HashMap<Reviewer, String>) theReviews.clone();
		myAuthors = (ArrayList<Author>) theAuthors.clone();
	}
	
	
	/**
	 * Default constructor;
	 */
	public Manuscript () {
		myReviews = new HashMap<Reviewer, String> ();
		myAuthors = new ArrayList<Author> ();
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
	 * Returns a copy of the manuscript.
	 * 
	 * @return A copy of the manuscript
	 */
	public Manuscript submitManuscript () {
		return new Manuscript (this.myReviews, this.myAuthors);
	}
}



















