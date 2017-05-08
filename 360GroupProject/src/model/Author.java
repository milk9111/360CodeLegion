package model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import model.Conference;
import model.Manuscript;
import model.Reviewer;
import model.User;


/**
 * This class is representing an Author user type with all the functionality they are entitled.
 * 
 * @author Casey Anderson, Ryan Tran
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
	
	/**
	 * Map of the Manuscripts already submitted to each conference.
	 * Key: Conference ID, HashSet: List of Manuscripts author has submitted to that conference
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
		//System.out.println("author contructor entered");
		//System.out.println(this.getMyAssociatedConference());
		//System.out.println(super.getMyAssociatedConference());
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
	public Manuscript createManuscript(String theTitle, Date theSubmittedDate, HashMap<Reviewer, String> theReviews, ArrayList<UUID> theAuthorsIDs) {
		
		return new Manuscript(theTitle, theSubmittedDate, theReviews, theAuthorsIDs, new File("")) ;
	
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
	 * WILL SAVE/UPDATE accounts that are authors of the manuscript to be saved
	 * @param theConference The conference that the manuscript is to be added to.
	 * @param theManuscript The manuscript to be added to Conference.
	 * @throws illegalArgumentException
	 */
	public void addManuscript(Conference theConference, Manuscript theManuscript, TreeMap<UUID, Account> theAccountList) throws Exception {	
		ArrayList<Account> acctsWithAuthorsBelongingToConf = new ArrayList<Account>();
		
		if (!isAuthorAtManuscriptLimit(theConference, theManuscript, theAccountList)) {
			
			// iterate through all accounts and create a list of accounts with authors connected to the
			// passed in conference
			for(Account theAcct : theAccountList.values()) {
				// check if an account has an author associated with the passed in conference
				// if true, add that account to a relevant list
				if(theAcct.getMyAuthorList().containsKey(theConference.getMyID())) {
					acctsWithAuthorsBelongingToConf.add(theAcct);
				}
			}
			
			// Iterate through each Author attached to the manuscript to be submitted
			for (int i = 0; i < theManuscript.getAuthors().size(); i++) {			
				
				// If the author already has manuscripts in general, already submitted to the passed in conference
				// parameter then the new manuscript to be submitted will be appended onto that list with the conference
				// as the key
				for(Account aAcct : acctsWithAuthorsBelongingToConf) {
					Author authorOfAcct = (Author) aAcct.getMyAuthorList().get(theConference.getMyID());

					// checks if the current iteration's account's author matches a manuscript author
					if(theManuscript.getAuthors().contains(authorOfAcct.getMyID())) {

						// after confirming that the account's author is included within the manuscript's list of authors
						// Check if the author has already submitted any manuscripts to the passed in conference.
						// If they have, then append the submitted manuscript to the end of their manuscript list
						// where the current conference is the key
						if(authorOfAcct.getManuscriptMap().containsKey(theConference.getMyID())) {
							// Adds the manuscript's ID to..
							// the account -> author role linked to conference argument ->
							// author's manuscript map -> manuscript mapping with conference argument as key -> hashset of
							// given argument's manuscripts 
							authorOfAcct.getManuscriptMap().get(theConference.getMyID()).add(theManuscript.getMyID());
							
							// update account with changed author
							aAcct.addAuthorRoleToAccount(authorOfAcct);
							
							System.out.println(aAcct);
							
							// save Account to database
							new AccountDatabase().updateAndSaveAccountToDatabase(aAcct);
							
							// TODO: Save manuscript ID to conference as well
						} else {
							HashSet<UUID> manuHashList = new HashSet<UUID>();
							manuHashList.add(theManuscript.getMyID());
							authorOfAcct.getManuscriptMap().put(theConference.getMyID(), manuHashList);
						}
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
		
		return myManuscriptList.get(theConference.getMyID()).size();
	
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
	private boolean isAuthorAtManuscriptLimit(Conference theConference, Manuscript theManuscript,
											  TreeMap<UUID, Account> theGlobalAccountList) {
		
		boolean authorAlreadyHasMaxManuscripts = false;
		
		for(Account acctToCompare : theGlobalAccountList.values()) {
			Author currentAcctAuthor = acctToCompare.getAuthorAssociatedWithConference(theConference);
			
			// if associated author to conference is not found then break out of loop;
			if(currentAcctAuthor == null) {
				continue;
			}
			
			// Checks to see if the manuscript contains an author id equivalent to the current iteration's account's 
			// author associated with the conference parameter id
			if(theManuscript.getAuthors().contains(currentAcctAuthor.getMyID())) {
				
				if(currentAcctAuthor.getNumberOfManuscriptsSubmitted(theConference) >= MAX_MANUSCRIPT_ALLOWED) { 
					authorAlreadyHasMaxManuscripts = true;
				}
			}
		}
		
		return authorAlreadyHasMaxManuscripts;
	
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