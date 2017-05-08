package model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
	public HashSet<UUID> getManuscriptsAssociatedWithConference(Conference theConference) {
		
		return myManuscriptList.get(theConference.getMyID());
	
	}
	
	/**
	 * Adds manuscript to a conference.
	 * WILL SAVE/UPDATE accounts that are authors of the manuscript to be saved
	 * @param theConference The conference that the manuscript is to be added to.
	 * @param theManuscript The manuscript to be added to Conference.
	 * @throws illegalArgumentException
	 */
	public void addManuscript(Conference theConference, Manuscript theManuscript) throws Exception {	
		AccountDatabase theAccountDatabase = new AccountDatabase();
		ManuscriptDatabase theManuscriptDatabase = new ManuscriptDatabase();
		TreeMap<UUID, Account> theAccountList = theAccountDatabase.deserializeAccountList();
		
		if (!isAuthorAtManuscriptLimit(theConference, theManuscript, theAccountList)) {
			// After confirming none of the authors(author and coauthors) are at the max limit
			
			ArrayList<Account> validAccts = getAllAccountsWithAuthorsBelongingToManuscript(new ArrayList<Account>(theAccountList.values()), theManuscript);
			
			// iterate through all valid accoutns and check for an existing conference key
			for(Account anAcct : validAccts) {
				if(anAcct.getMyAuthor().doesConferenceKeyExistForMyManuscriptList(theConference)) {
					
					System.out.println("Saving Manuscript to DB and Updating Account in DB...");
					anAcct.getMyAuthor().addManuscriptExistingConferenceToManuscriptList(theManuscript, theConference);
					theAccountDatabase.updateAndSaveAccountToDatabase(anAcct);
					theManuscriptDatabase.saveManuscriptToDatabase(theManuscript);
				} else {
					// if author does NOT have the given conference as an existing key then make a new one
					anAcct.getMyAuthor().addManuscriptWithNewConferenceToManuscriptList(theManuscript, theConference);
					new ManuscriptDatabase().saveManuscriptToDatabase(theManuscript);
				}
			}

			} else {
			
			throw new IllegalArgumentException("Author or CoAuthor already has " + MAX_MANUSCRIPT_ALLOWED + " Manuscripts");
			
		}
		
	}
	
	
	/**
	 * Returns a list of accounts that are an author of the given manuscript's list of authors
	 * @param theAccountList
	 * @param theManuscript
	 * @return
	 */
	public ArrayList<Account> getAllAccountsWithAuthorsBelongingToManuscript(ArrayList<Account> theAccountList,
			Manuscript theManuscript) {
		
		ArrayList<Account> returnList = new ArrayList<Account>();
		
		for(Account anAcct : theAccountList) {
			if(theManuscript.doesAuthorBelongToManuscript(anAcct.getMyAuthor())) {
				returnList.add(anAcct);
			}
		}
		
		return returnList;
	}
	
	/**
	 * Method to return how many manuscripts Author has already submitted.
	 * @param theConference The Conference to be checked for how many manuscripts Author has already submitted.
	 * @return An Integer representing how many manuscripts the Author has already submitted.
	 */
	public int getNumberOfManuscriptsSubmitted(Conference theConference) {
		int returnSize = 0;
		if(myManuscriptList.get(theConference.getMyID()) != null) {
			returnSize = myManuscriptList.get(theConference.getMyID()).size();
		}

		return returnSize;
	
	}
	
	/**
	 * Returns a boolean indicating whether or not the manuscript list for the author already has a conference key
	 * matching the passed in one.
	 * @param theConference
	 * @return
	 */
	public boolean doesConferenceKeyExistForMyManuscriptList(Conference theConference) {
		boolean doesKeyExist = false;
		
		if(this.myManuscriptList.containsKey(theConference.getMyID())) {
			doesKeyExist = true;
		}
		
		return doesKeyExist;
	}
	
	
	/**
	 * Adds a conference key/manuscript to a new key/value pair for the author's manuscript list
	 * @param theManuscript
	 * @param theConference
	 */
	public void addManuscriptWithNewConferenceToManuscriptList(Manuscript theManuscript, Conference theConference) {
		HashSet<UUID> manuList = new HashSet<UUID>();
		manuList.add(theManuscript.getMyID());

		this.myManuscriptList.put(theConference.getMyID(), manuList);
	}
	
	/**
	 * Adds a Conference Key/Manuscript to an existing conference key/manuscript list pair
	 * @param theManuscript
	 * @param theConference
	 */
	public void addManuscriptExistingConferenceToManuscriptList(Manuscript theManuscript, Conference theConference) {
		// add new manuscript to existing list of manuscript ids
		HashSet<UUID> manuList = this.myManuscriptList.get(theConference.getMyID());
		manuList.add(theManuscript.getMyID());
		
		// add newly changed manuscript list to existing conference key/manuscript list pair
		this.myManuscriptList.put(theConference.getMyID(), manuList);
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
			Author currentAcctAuthor = acctToCompare.getMyAuthor();
			
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
	 * This will return a list of conferences IDs the author can view/take actions upon
	 * @return a list of viewable/actionable conferences relevant to the author
	 */
	public Set<UUID> getMyListOfConferenceIDs() {
		return this.myManuscriptList.keySet();
	}
	
	
	/**
	 * checks whether or not the passed in conference has manuscripts turned into it by the author
	 * @param theConference
	 * @return
	 */
	public boolean isConferenceAssociatedWithAuthor(Conference theConference) {
		return this.myManuscriptList.containsKey(theConference.getMyID());
	}

	/**
	 * DO NOT USE.
	 */
	@Override
	public List<Conference> getConferenceList() {
		// TODO Auto-generated method stub
		return null;
	}

}