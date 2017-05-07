package model;

import java.io.File;
import java.io.Serializable;
import java.util.*;

import model.Conference;
import model.Manuscript;
import model.Reviewer;
import model.User;


/**
 * This class is representing an Author user type with all the functionality they are entitled.
 * 
 * @author Casey Anderson
 * @version 1 
 *
 */
public class Author extends User implements Serializable {
	
	/**
	 * The maximum amount of Manuscripts a Author is allowed to submit per Conference.
	 */
	public static final int MAX_MANUSCRIPT_ALLOWED = 5;
	
	/**
	 * List of the current Reviews returned to the Author.
	 */
	private ArrayList<File> myReviewList;
	
	// TODO: refactor this to be conference IDs and Manuscript IDs
	/**
	 * Map of the Manuscripts already submitted to each conference.
	 */
	private Map<UUID,HashSet<UUID>> myManuscriptList;
	
	/**
	 * Constructor for Author class.
	 * @param theUserName The Username of the Author.
	 */
	public Author(String theUserName, Conference theConference) {		
		super(theUserName, theConference);
		this.myReviewList = new ArrayList<File>();
		myManuscriptList = new HashMap<UUID,HashSet<UUID>>();
	
	}
	
	/**
	 * basic constructor for an author, a conference must be provided for
	 * the author to be under.
	 * Users with no roles will have this created for them if they choose to submit a manuscript.
	 * @param theConference the conference to associate with the created author
	 */
	public Author(Conference theConference) {
		super(theConference);
		this.myReviewList = new ArrayList<File>();
		myManuscriptList = new HashMap<UUID,HashSet<UUID>>();
	}
	
	/**
	 * Method to add Review to the Author theReview must be a PDF file.
	 * @param theReview Review file to be added to Author.
	 */
	public void addReview(File theReview) {
		
		myReviewList.add(theReview);
	
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
		
		return new Manuscript(theTitle, theSubmittedDate, theReviews, theAuthors, new File("")) ;
	
	}
	
	/**
	 * Method to return Review list of Authors Manuscripts.
	 * @return A list of files containing Authors Reviews.
	 */
	public ArrayList<File> getReviewList() {
		
		return myReviewList;
	
	}
	
	/**
	 * Method to return a list of Manuscripts for this Author.
	 * @param theConference The Conference to get the list of Manuscripts from.
	 * @return A ArrayList of Manuscripts the Author has already submitted.
	 */
	public HashSet<UUID> getManuscript(Conference theConference) {
		
		return myManuscriptList.get(theConference.getMyID());
	
	}
	
	/**
	 * Adds manuscript to a conference.
	 * @param theConference The conference that the manuscript is to be added to.
	 * @param theManuscript The manuscript to be added to Conference.
	 * @throws illegalArgumentException
	 */
	public void addManuscript(Conference theConference, Manuscript theManuscript) throws Exception {	
		
		if (!isAuthorAtManuscriptLimit(theConference, theManuscript)) {
			
			for (int i = 0; i < theManuscript.getAuthors().size(); i++) {			
				
				if (theManuscript.getAuthors().get(i).getManuscriptMap().containsKey(theConference)) {
					
					theManuscript.getAuthors().get(i).getManuscriptMap().get(theConference).add(theManuscript.getMyID());
				
				} else {
					
					HashSet<UUID> ManuscriptList = new HashSet<>();
					ManuscriptList.add(theManuscript.getMyID());
					if(theManuscript.getAuthors().get(i).getManuscriptMap() == null) {
						theManuscript.getAuthors().get(i).getManuscriptMap().put(theConference.getMyID(), ManuscriptList);
					}
				}
			}
			
		} else {
			
			throw new IllegalArgumentException("Author or CoAuthor already has " + MAX_MANUSCRIPT_ALLOWED + " Manuscripts");
			
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
	 * Private helper method to return the internal map of Manuscripts and Conferences they are submitted to.
	 * @return A Map with the value of A List of Manuscripts and the key the Conference they are submitted to.
	 */
	private Map<UUID,HashSet<UUID>> getManuscriptMap() {
		
		return myManuscriptList;
		
	}
	
	/**
	 * A helper method to determine if any Author that is attached to the Manuscript has already submitted 5 Manuscripts.
	 * @param theConference The Conference that the Manuscript is being submitted to.
	 * @param theManuscript The Manuscript which is trying to be submitted.
	 * @return A boolean Value indicating if the any Author has Already submitted their limit of Manuscripts.
	 */
	private boolean isAuthorAtManuscriptLimit(Conference theConference, Manuscript theManuscript) {
		
		boolean auhorAlreadyHas5Manuscripts = false;
		
		for (int i = 0; i < theManuscript.getAuthors().size(); i++) {
			
			if (theManuscript.getAuthors().get(i).getManuscriptMap().containsKey(theConference)) {
				
				if (theManuscript.getAuthors().get(i).getNumberOfManuscriptsSubmitted(theConference) >= MAX_MANUSCRIPT_ALLOWED) {
					
					auhorAlreadyHas5Manuscripts = true;
				
				}
			
			}
		
		}
		
		return auhorAlreadyHas5Manuscripts;
	
	}

	/**
	 * This will return a list of conferences the author can view/take actions upon
	 * @return a list of viewable/actionable conferences relevant to the author
	 */
	@Override
	public List<Conference> getConferenceList() {
		
		return super.myConferenceList;
	
	}

}