package client;

import java.util.*;
import java.util.regex.Pattern;

import model.*;

/**
 * The system controller that handles the different states of the 
 * program. It is the bridge between the UI and the Model. 
 * 
 * @author Connor Lundberg
 * @author Josiah Hopkins
 * @version 5/6/2017
 */
public class Controller extends Observable implements Observer {

	//View States
    public static final int LOG_IN_STATE = -2;
    public static final int CHOOSE_USER = -1;
	public static final int AUTHOR = 0;
	public static final int REVIEWER = 10;
	public static final int SUBPROGRAM_CHAIR = 20;
	
	//Action States
	public static final int USER_OPTIONS = 0;
	public static final int SUBMIT_MANUSCRIPT = 1;
	public static final int LIST_MANUSCRIPT_VIEW = 2;
	public static final int LIST_CONFERENCE_VIEW = 3;
	public static final int ASSIGN_REVIEWER = 4;
	public static final int LIST_ASSIGNED_REVIEWERS_VIEW = 5;

	
	//Objects we are adding in the system. We are saving them because we need persistence between states.
	private int myCurrentState;

	private Account myAccount;
	
	private AccountDatabase myAccountDatabase;
	private ManuscriptDatabase myManuscriptDatabase;
	public ConferenceDatabase myConferenceDatabase;

	private Conference myCurrentConference;
	private Manuscript myCurrentManuscript;
	private Reviewer myCurrentReviewer;
	

	/**
	 * The Controller constructor. This takes no arguments and sets all of
	 * the fields.
	 * 
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	public Controller () {
		myCurrentState = AUTHOR;
		myAccount = new Account(null);
		myCurrentConference = new Conference(null, null, null, null);
		myCurrentManuscript = new Manuscript();
		myCurrentReviewer = new Reviewer(null, null);
		myAccountDatabase = new AccountDatabase();
		myAccountDatabase.createEmptySerializedAccountList();
		myManuscriptDatabase = new ManuscriptDatabase();
		myManuscriptDatabase.createEmptySerializedManuscriptList();
		myConferenceDatabase = new ConferenceDatabase();
		myConferenceDatabase.createEmptySerializedConferenceList();
	}
	
	
	/**
	 * Call this method after instantiating the Controller
	 * to start the program. It sets myCurrentState to 0 and
	 * makes the UI object.
	 * 
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	public void startProgram () {
		myCurrentState = LOG_IN_STATE;
		setChanged();
		notifyObservers(myCurrentState);
	}
	
	
	/**
	 * This method is the finite state machine. It takes a integer 
	 * representing the next state to change to and passes information to
	 * the UI accordingly.
	 * 
	 * @author Connor Lundberg
	 * @author Josiah Hopkins
	 * @version 5/6/2017
	 * @param theNextState The next state the program will be in.
	 */
	private void changeState (String theNextState) {
//test print
		System.out.println("In controller changeState: " +theNextState);
		String[] pieces = theNextState.split(",");
		
		switch ((myCurrentState / 10) * 10) {
			case LOG_IN_STATE:
				myCurrentState = CHOOSE_USER;
				
				setChanged();
				notifyObservers(myCurrentState);
				break;
			case CHOOSE_USER:
				System.out.println("in choose user");
				switch (pieces[0]) {
					case "AUTHOR":
						System.out.println("in author");
						myCurrentState = AUTHOR;
						break;
					case "SUBPROGRAM_CHAIR":
						System.out.println("in subprogram chair");
						myCurrentState = SUBPROGRAM_CHAIR;
						break;
				}
				System.out.println("finished CHOOSE_USER");
				myCurrentState += LIST_CONFERENCE_VIEW;
				
				setChanged();
				notifyObservers(myCurrentState);
				break;
			case AUTHOR:
				//test print
				System.out.println("In controller changeState case Author");
				myCurrentState = AUTHOR;
				setChanged();
				notifyObservers(myCurrentState);
				
				switch (myCurrentState % 10){
					case SUBMIT_MANUSCRIPT:
                        Manuscript manuscriptToSubmit;
						if(pieces[0].equals("Submit Manuscript")){
							manuscriptToSubmit = makeManuscript(pieces);
							
							try {
								if (myAccount.doesAutorAssociatedWithConferenceExist(myCurrentConference)) {
									(myAccount.getAuthorAssociatedWithConference(myCurrentConference)).addManuscript(myCurrentConference, manuscriptToSubmit);
								} else {
									myAccount.addAuthorRoleToAccount(new Author(myAccount.getMyUsername(), myCurrentConference));
									(myAccount.getAuthorAssociatedWithConference(myCurrentConference)).addManuscript(myCurrentConference, manuscriptToSubmit);
								}
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							myCurrentConference.submitManuscript(manuscriptToSubmit);
							
							myCurrentState = AUTHOR + LIST_MANUSCRIPT_VIEW;
							setChanged();
							notifyObservers(myCurrentState);
                        }

						break;
					case LIST_MANUSCRIPT_VIEW:
						if (pieces[0].equals("List Manuscript View")) {
							myCurrentState = AUTHOR + USER_OPTIONS;
							setChanged();
							notifyObservers(myCurrentState);
						}
						break;
					case LIST_CONFERENCE_VIEW:
						if (pieces[0].equals("List Conference View")) {
							myCurrentConference = findConference(theNextState, myAccount.getMySubprogramChair().getConferenceList());
							
							myCurrentState = AUTHOR + USER_OPTIONS;
							setChanged();
							notifyObservers(myCurrentState);
						}
						break;
					case USER_OPTIONS:
						switch (pieces[0]) {
                    	case "Submit Manuscript":
                    		myCurrentState = AUTHOR + SUBMIT_MANUSCRIPT;
                    		break;
                    	case "Go Back":
                    		myCurrentState = AUTHOR + LIST_CONFERENCE_VIEW;
                    		break;
						}
						
						setChanged();
						notifyObservers(myCurrentState);
						break;
				}
				
				break;
			case REVIEWER:
				switch (myCurrentState % 10){

				}
				break;
			case SUBPROGRAM_CHAIR:
				
				switch (myCurrentState % 10){
                    case ASSIGN_REVIEWER:
						myCurrentReviewer = findReviewer(theNextState, myCurrentConference.getPastReviewers());

                        myCurrentState = SUBPROGRAM_CHAIR + LIST_ASSIGNED_REVIEWERS_VIEW;
                        setChanged();
						notifyObservers(myCurrentState);
                        break;
                    case LIST_CONFERENCE_VIEW:
						myCurrentConference = findConference(theNextState, myAccount.getMySubprogramChair().getConferenceList());
						
						myCurrentState = SUBPROGRAM_CHAIR + USER_OPTIONS;
						setChanged();
						notifyObservers(myCurrentState);
                        break;
					case LIST_MANUSCRIPT_VIEW:
						myCurrentState = SUBPROGRAM_CHAIR + USER_OPTIONS;
                    	
						setChanged();
						notifyObservers(myCurrentState);
						break;
                    case LIST_ASSIGNED_REVIEWERS_VIEW:
                    	myCurrentState = SUBPROGRAM_CHAIR + USER_OPTIONS;
                    	
						setChanged();
						notifyObservers(myCurrentState);
                        break;
                    case USER_OPTIONS:
                    	switch (pieces[0]) {
	                    	case "Assign Reviewer":
	                    		myCurrentState = SUBPROGRAM_CHAIR + ASSIGN_REVIEWER;
	                    		break;
	                    	case "Go Back":
	                    		myCurrentState = SUBPROGRAM_CHAIR + LIST_CONFERENCE_VIEW;
	                    		break;
                    	}
                    	
                    	setChanged();
						notifyObservers(myCurrentState);
                    	break;
				}
				break;
		}
	}
	
	
	/**
	 * Sets the current state to the passed int value. Used for testing
	 * purposes only.
	 * 
	 * @param theNewState The new state to set
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	public void setState (int theNewState) {
		myCurrentState = theNewState;
	}
	
	
	/**
	 * Returns the current int state. Used for testing purposes only.
	 * 
	 * @return The current state
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	public int getState () {
		return myCurrentState;
	}

	
	/**
	 * Finds the Conference title referenced within theNextState inside the Conference list that
	 * is retrieved from the Subprogram Chair.
	 * 
	 * @param theNextState The String used to pull the Conference title from
	 * @param conferenceList The list of Conferences to check against
	 * @return The Conference, otherwise null.
	 * @author Josiah Hopkins
	 * @version 5/6/2017
	 */
    private Conference findConference(String theNextState, List<Conference> conferenceList) {
	    for(Conference c: conferenceList){
	        if(theNextState.contains(c.getMyName())){
	            return c;
            }
        }
        return null;
    }

    
    /**
	 * Finds the Reviewer name/UID referenced within theNextState inside the past Reviewers list that
	 * is retrieved from the current Conference.
	 * 
	 * @param theNextState The String used to pull the Reviewer name/UID from
	 * @param pastReviewers The list of Reviewers to check against
	 * @return The Reviewer, otherwise null.
	 * @author Josiah Hopkins
	 * @version 5/6/2017
	 */
    private Reviewer findReviewer(String theNextState, List<Reviewer> pastReviewers) {
		for(Reviewer r: pastReviewers){
			if(theNextState.contains(r.getUsername())){
				return r;
			}
		}
		return null;
	}


	/**
	 * Makes the Manuscript to submit. This is just a helper method for clarity of
	 * FSM.
	 * 
	 * @param thePieces The parsed String array
	 * @return The new Manuscript
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	private Manuscript makeManuscript (String[] thePieces) {
		Manuscript returnManuscript = new Manuscript();
		
		returnManuscript.setTitle(thePieces[1]);
		returnManuscript.setSubmittedDate(new Date(thePieces[2]));
		
		//Adds the remaining Authors in the list.
		for (int i = 3; i < thePieces.length; i++) {
			returnManuscript.addAuthor(new Author(thePieces[i], myCurrentConference));
		}
		
		return returnManuscript;
	}
	
	
	/**
	 * Sets the new Account. This checks if theNewAccount is a valid Account within
	 * the AccountDatabase. If so, then it will set the current Account to theNewAccount,
	 * otherwise it will add theNewAccount to the AccountDatabase.
	 * 
	 * @param theNewAccount The new Account to set.
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	private void setAccount (Account theNewAccount) {
		if (myAccountDatabase.isUsernameInListValid(myAccountDatabase.getAllAccounts(), theNewAccount.getMyUsername())) {
			myAccount = theNewAccount;
		} else {
			myAccountDatabase.saveAccountToDatabase(theNewAccount);
			myAccount = theNewAccount;
		}
	}
	
	
	/**
	 * Sets the new Conference. This checks if theNewConference is a valid Conference within
	 * the ConferenceDatabase. If so, then it will set the current Conference to theNewConference,
	 * otherwise it will add theNewConference to the ConferenceDatabase. *A new Conference should
	 * not be added in any case other than with the Program Chair.
	 * 
	 * @param theNewConference The new Conference to set.
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	private void setConference (Conference theNewConference) {
		if (myConferenceDatabase.isConferenceInListUnique(myConferenceDatabase.getAllConferences(), theNewConference)) {
			myCurrentConference = theNewConference;
		} else {
			myConferenceDatabase.saveConferenceToDatabase(theNewConference);
			myCurrentConference = theNewConference;
		}
	}


	/**
	 * Used to talk to the UI while staying decoupled.
	 * 
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof String) {
			changeState ((String) arg1);
		} else if (arg1 instanceof Account) {
			setAccount((Account) arg1);
		} else if (arg1 instanceof Conference) {
			setConference((Conference) arg1);
		}
	}
		
}


























