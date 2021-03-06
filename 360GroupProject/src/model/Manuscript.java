package model;


import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import model.Reviewer;

/**
 * 
 * @author Connor Lundberg
 * @version 4/28/2017
 * 
 * This is the Manuscript class. It contains information about the
 * individual manuscript including author(s) and reviews.
 */
public class Manuscript implements Serializable {
	private UUID myID;
	private HashMap<Reviewer, String> myReviews;
	// TODO: Refactor to list of author UUIDs instead, maybe TreeMap of Author/User UUID, Authors
	//private ArrayList<Author> myAuthors;
	private ArrayList<UUID> myAuthorsIDs;
	private Date mySubmittedDate;
	private String myTitle;
	private File myFilePath;
	private UUID myConference;
	
	
	/**
	 * Simple constructor used for testing purposes.
	 * 
	 * @param theSubmittedDate The date submitted
	 * @param theReviews The reviews to assign
	 * @param theAuthors The authors for the manuscript
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	public Manuscript (String theTitle, Date theSubmittedDate, HashMap<Reviewer, String> theReviews,
			ArrayList<UUID> theAuthorsIDs,File theFilePath, Conference theConference) {
		myID = UUID.randomUUID();
		myReviews = (HashMap<Reviewer, String>) theReviews.clone();
		this.myAuthorsIDs = (ArrayList<UUID>) theAuthorsIDs.clone();
		mySubmittedDate = theSubmittedDate;
		myTitle = theTitle;
		myFilePath = theFilePath;
		this.myConference = theConference.getMyID();
	}
	
	public Manuscript(String theTitle, Date theSubmittedDate, ArrayList<UUID> theAuthorsIDs, Conference theConference) {
		myID = UUID.randomUUID();
		myTitle = theTitle;
		mySubmittedDate = theSubmittedDate;
		myAuthorsIDs = theAuthorsIDs;
		this.myConference = theConference.getMyID();
	}
	
	/**
	 * constructor that Automatically inits an author list and adds the given author to the list
	 * to set as the author list
	 * @param theTitle title of manuscript
	 * @param theSubmittedDate submission date
	 * @param theAuthor Author to add to author list for manuscript
	 */
	public Manuscript(String theTitle, Date theSubmittedDate, Author theAuthor, File theFilePath, Conference theConference) {
		myID = UUID.randomUUID();
		myTitle = theTitle;
		mySubmittedDate = theSubmittedDate;
		myFilePath = theFilePath;
		
		// init author list and add passed in author to list
		ArrayList<UUID> authorIDList = new ArrayList<UUID>();
		authorIDList.add(theAuthor.getMyID());
		myAuthorsIDs = authorIDList;
		
		
		this.myConference = theConference.getMyID();
	}
	
	
	/**
	 * Default constructor.
	 * 
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	public Manuscript () {
		myID = UUID.randomUUID();
		myReviews = new HashMap<Reviewer, String> ();
		myAuthorsIDs = new ArrayList<UUID> ();
		mySubmittedDate = new Date ();
		myFilePath = new File("");
		this.myConference = null;
	}
	
	/**
	 * Returns the manuscripts unique ID
	 * @return the UUID reprensenting the manuscript's ID
	 */
	public UUID getMyID() {
		return this.myID;
	}
	
	/**
	 * Returns an ArrayList of all the reviews attached to this
	 * manuscript.
	 * 
	 * @return All reviews for this manuscript
	 */
	public ArrayList<String> getReviews () {
		ArrayList<String> returnList = new ArrayList<String>();
		Set<Reviewer> reviewSet = myReviews.keySet();
		Iterator itr = reviewSet.iterator();
		while (itr.hasNext()) {
			returnList.add(myReviews.get(itr.next()));
		}
		
		return returnList;
	}
	
	
	/**
	 * Returns the ArrayList of Reviewers attached to this Manuscript.
	 * 
	 * @return The list of Reviewers. This can be empty, but never null.
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	public ArrayList<Reviewer> getReviewers () {
		ArrayList<Reviewer> returnList = new ArrayList<Reviewer>();
		Set<Reviewer> reviewSet = myReviews.keySet();
		Iterator itr = reviewSet.iterator();
		while (itr.hasNext()) {
			returnList.add((Reviewer) itr.next());
		}
		
		return returnList;
	}
	
	
	/**
	 * Sets the title to the String title passed.
	 * 
	 * @param theTitle The title String to set
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	public void setTitle (String theTitle) {
		myTitle = theTitle;
	}
	
	
	/**
	 * Returns an ArrayList of all authors attached to this
	 * manuscript.
	 * 
	 * @return All authors for this manuscript
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	public ArrayList<UUID> getAuthors () {
		return (ArrayList<UUID>) myAuthorsIDs.clone();
	}
	
	
	/**
	 * Sets myAuthors list.  Allows tester to use default constructor.
	 * 
	 * @author Connor Lundberg
	 * @version 5/1/2017
	 */
	public void setAuthors(List<UUID> theAuthors) {
		this.myAuthorsIDs = (ArrayList<UUID>) theAuthors;
	}
	
	
	/**
	 * Adds a single author to the Author list.
	 * 
	 * @param theAuthor The author to add to the list.
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	public void addAuthor(Author theAuthor) {
		this.myAuthorsIDs.add(theAuthor.getMyID());
	}
	
	
	/**
	 * Returns the date this manuscript was submitted.
	 * 
	 * @return The submitted date
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	public Date getSubmittedDate () {
		return (Date) mySubmittedDate.clone();
	}
	
	
	/**
	 * Sets the submitted date for the Manuscript. This is used on submission
	 * of the Manuscript only.
	 * 
	 * @param theSubmittedDate The new submitted date
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	public void setSubmittedDate (Date theSubmittedDate) {
		mySubmittedDate = theSubmittedDate;
	}
	
	
	/**
	 * Returns the title of this manuscript.
	 * 
	 * @return The manuscript title
	 * @author Connor Lundberg
	 * @version 5/6/2017
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
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	public void assignReviewer (final Reviewer theReviewer) {
		myReviews.put(theReviewer, "");
	}
	
	
	/**
	 * Adds a review to the review list.
	 * 
	 * @param theReviewer The Reviewer linked to the review given
	 * @param theReview The review to add
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	public void addReview (final Reviewer theReviewer, String theReview) {
		myReviews.replace(theReviewer, theReview);
	}
	
	
	public void setFilePath (File theNewFilePath) {
		myFilePath = theNewFilePath;
	}
	
	
	/**
	 * Submits a manuscript then returns it upon success.
	 * 
	 * @return A copy of the manuscript
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 
	public Manuscript submitManuscript () {
		return new Manuscript(this.myTitle, this.mySubmittedDate, this.myReviews, this.myAuthors);
	}*/
	

	/**
	 * this method returns a boolean indicating if the passed in author exists within the manuscript's
	 * list of authors or not.
	 * @param theAuthor the author to check if it exists within the manuscript's author's list..
	 * @return true if author exists in authors list, false otherwise.
	 */
	public boolean doesAuthorBelongToManuscript(Author theAuthor) {
		boolean authorBelongsToManuscript = false;
		
		for(UUID anAuthorID : this.myAuthorsIDs) {
			// System.out.println(theAuthor);
			if(anAuthorID.equals(theAuthor.getMyID())) {
				authorBelongsToManuscript = true;
			}
		}
		
		return authorBelongsToManuscript;
	}
	
	
	/**
	 * Set's the conference id for the manuscript to the given conf
	 * @param theConference
	 */
	public void setConference(Conference theConference) {
		this.myConference = theConference.getMyID();
	}
	
	/**
	 * Gets manuscript's Conference ID
	 * @return
	 */
	public UUID getConferenceID() {
		return this.myConference;
	}
}



















