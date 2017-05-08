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

		
	}
	
	public Reviewer(Manuscript theManuscript, Conference theConference) {
		super(theConference);
		this.myConferencesAndAssignedManuscriptsList = new TreeMap<UUID, HashSet<UUID>>();
		HashSet<UUID> listOfManuIDs = new HashSet<UUID>();
		listOfManuIDs.add(theManuscript.getMyID());
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
		if ((isReviewerAnAuthor(theManuscript) == true) || (isOverReviewLimit() == true) ) {
			wasAssigned = false;			

		} else {
			myAssignedManuscriptList.add(theManuscript);
		}
		
		return wasAssigned;
	}
	
	/**
	 * Compares size of assigned manuscript list with limit defined by MAX_REVIEWS
	 * @author Morgan Blackmore
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

		TreeMap<UUID, Account> acctList = new AccountDatabase().deserializeAccountList();
		ArrayList<Account> validAccountList = new ArrayList<Account>();
		
		
		String reviewerName = getUsername();
		List<UUID> authorlist = theManuscript.getAuthors();
		
		for(Account anAcct : acctList.values()) {
			if(anAcct.getMyReviewer().getMyID().equals(this.getMyID())) {
				validAccountList.add(anAcct);
			}
		}
		
		for(Account anAcct : validAccountList) {
			if(theManuscript.doesAuthorBelongToManuscript(anAcct.getMyAuthor())) {
				isAuthor = true;
			}
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
	public List<Conference> getConferenceList() {

		return super.myConferenceList;
	}
	
	
	
}
