package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Reviewers class.
 * 
 * 
 * @author Morgan Blackmore
 * @version 4/27/17
 */
public class Reviewer extends User implements Serializable {
	/** Constant: maximum number of reviews allowed for a reviewer. */
	private static final int MAX_REVIEWS = 8;

	
	/**
	 * List of assigned manuscripts, mapped to a conference id.
	 * key: conference id, value: list of manuscript ids for which the reviewer is assigned
	 */
	private TreeMap<UUID, HashSet<UUID>> myConferencesAndAssignedManuscriptsList;
	
	/**
	 * Constructor for Reviewer.
	 * 
	 * @author Morgan Blackmore
	 */
	public Reviewer(String theName, ArrayList<Conference> theConferenceList){
		super(theName, theConferenceList);
		myConferencesAndAssignedManuscriptsList = new TreeMap<UUID, HashSet<UUID>>();

		
	}
	
	public Reviewer(Manuscript theManuscript, Conference theConference) {
		super(theConference);
		this.myConferencesAndAssignedManuscriptsList = new TreeMap<UUID, HashSet<UUID>>();
		HashSet<UUID> listOfManuIDs = new HashSet<UUID>();
		listOfManuIDs.add(theManuscript.getMyID());
		this.myConferencesAndAssignedManuscriptsList.put(theConference.getMyID(), listOfManuIDs);
	}
	
	public Reviewer(Conference theConference) {
		super(theConference);
		this.myConferencesAndAssignedManuscriptsList = new TreeMap<UUID, HashSet<UUID>>();
		HashSet<UUID> listOfManuIDs = new HashSet<UUID>();
		this.myConferencesAndAssignedManuscriptsList.put(theConference.getMyID(), listOfManuIDs);
	}
	
	/**
	 * Assign a new manuscript to this reviewer.
	 * Checks for business rules.  Returns an error message if fails.  
	 * Should also throw some exception to the class that's calling it.
	 * Adds the manuscript to this reviewer's list.
	 * 
	 * @author Morgan Blackmore
	 * @param theMan The Manuscript to be assigned
	 * 
	 * @return boolean true if assigned successfully
	 */
	public boolean assignManuscript(Manuscript theManuscript){
		boolean wasAssigned = true;
		
		//Condition 1: check if Reviewer is a coauthor on this manuscript 
		//Condition 2: check if Reviewer is over review limit
		
		//separate these tests and throw exceptions
		//also need to add a check for if this reviewer is already assigned to this manuscript.
		if ((isReviewerAnAuthor(theManuscript) != true) || (isOverReviewLimit(theManuscript.getConferenceID()) == true)) {
			wasAssigned = false;			
		} else {
			if(isReviewerAssignedToConference(theManuscript.getConferenceID()) == false) {
				HashSet<UUID> currentManuList = this.myConferencesAndAssignedManuscriptsList.get(theManuscript.getConferenceID());
				// TODO: check if manuscript already exists within list
				currentManuList.add(theManuscript.getMyID());
				// save manuscript to DB
				new ManuscriptDatabase().saveManuscriptToDatabase(theManuscript);
				// add manuscript to associated conference of manuscript and save to DB, save account to DB as well
				this.myConferencesAndAssignedManuscriptsList.put(theManuscript.getConferenceID(), currentManuList);
				Account updatedAcct = new AccountDatabase().getAccountByReviewer(this);
				updatedAcct.setReviewer(this);
				wasAssigned = true;

			} else {
				HashSet<UUID> newManuList = new HashSet<UUID>();
				newManuList.add(theManuscript.getMyID());
				// save manuscript to DB
				new ManuscriptDatabase().saveManuscriptToDatabase(theManuscript);
				// add manuscript to associated conference of manuscript and save to DB, save account to DB as well
				this.myConferencesAndAssignedManuscriptsList.put(theManuscript.getConferenceID(), newManuList);
				Account updatedAcct = new AccountDatabase().getAccountByReviewer(this);
				updatedAcct.setReviewer(this);
				wasAssigned = true;

			}
		}
		
		return wasAssigned;
	}
	
	/**
	 * Compares size of assigned manuscript list with limit defined by MAX_REVIEWS
	 * @author Morgan Blackmore
	 * @return true if over limit.
	 */
	private boolean isOverReviewLimit(UUID theConferenceID) {
		// check to see if conference exists within reviewer list of confs or not
		if(this.isReviewerAssignedToConference(theConferenceID)) {
			return false;
		}

		boolean isOver = false;
		if (this.myConferencesAndAssignedManuscriptsList.get(theConferenceID).size() >= MAX_REVIEWS) {
			isOver = true;
		} 
		System.out.println(isOver);
		return isOver;
	}
	
	/**
	 * Compares if the assigned reviewer is an author of this paper.
	 * preconditions: Reviewer object in question is already saved to an account
	 * @return true if reviewer is an author.
	 */
	private boolean isReviewerAnAuthor(Manuscript theManuscript) {
		boolean isAuthor = false;
		TreeMap<UUID, Account> acctList = new AccountDatabase().deserializeAccountList();
		ArrayList<Account> validAccountList = new ArrayList<Account>();
		
		List<UUID> authorlist = theManuscript.getAuthors();
		
		for(Account anAcct : acctList.values()) {
			if (anAcct.getMyReviewer() != null) {
				if(anAcct.getMyReviewer().getMyID().equals(this.getMyID())) {
					validAccountList.add(anAcct);
				}
			}
		}

		for(Account anAcct : validAccountList) {
			if(theManuscript.doesAuthorBelongToManuscript(anAcct.getMyAuthor())) {
				isAuthor = true;
			}
		}


		return isAuthor;
		
	}
	
	public boolean isReviewerAssignedToConference(UUID theConfID) {
		return this.myConferencesAndAssignedManuscriptsList.containsKey(theConfID);
	}

	public TreeMap<UUID, HashSet<UUID>> getMyAssignedManuscriptsAndConferenceList() {
		return this.myConferencesAndAssignedManuscriptsList;
	}

	public void setAssignedManuscriptList(TreeMap<UUID, HashSet<UUID>> theList) {
		this.myConferencesAndAssignedManuscriptsList = theList;
	}



	@Override
	public List<Conference> getConferenceList() {

		return super.myConferenceList;
	}
	
	
	
}
