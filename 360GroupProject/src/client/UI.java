package client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.UUID;

import model.Account;
import model.Conference;
import model.ConferenceDatabase;
import model.Manuscript;
import model.ManuscriptDatabase;

/**
 * 
 * @author Casey Anderson, Morgan Blackmore
 * @version 5/6/17 1:45pm
 */

public class UI extends Observable implements Observer{

	//Action States
	public static final String NOTIFY_CONTROLLER_TO_CHANGE_TO_AUTHOR_CONFERENCE_LIST_VIEW = "LIST_CONFERENCE_VIEW";
	public static final String NOTIFY_CONTROLLER_TO_CHANGE_TO_LOGGIN_VIEW = "LOG_IN_STATE";
	public static final String NOTIFY_CONTROLLER_TO_CHANGE_TO_AUTHOR_SUBMIT_MANUSCRIPT_VIEW = "SUBMIT_MANUSCRIPT";
	public static final String NOTIFY_CONTROLLER_TO_CHANGE_TO_AUTHOR_MANUSCRIPT_LIST_VIEW = "LIST_MANUSCRIPT_VIEW";
	public static final String NOTIFY_CONTROLLER_TO_CHANGE_TO_AUTHOR_MAIN_VIEW = "AUTHOR";

	private String myUserType;
	private String myUserName;
	private Scanner myScanner;
	private String myUserChoice;
	private Account myAccount;

	public UI() {
		
		myUserType = "";
		myUserName = "";
		myUserChoice = "";
		myScanner = new Scanner(System.in);
		
	}

	/**
	 * Login prompt.  Displays prompt and scans input, compares to list of existing users.
	 * If valid, updates this UI user fields and proceeds to top menuL chooseUserTypeMenuView 
	 * 
	 * @author Casey Anderson, Morgan Blackmore
	 * 
	 */
	private void login() {
		
		System.out.println("Loggin Page:");
		System.out.print("\nPlease enter user name to log in: ");
		myUserName = myScanner.next();
		System.out.println();
		myAccount = new Account(myUserName);
		setChanged();
		notifyObservers(myAccount);
		setChanged();
		notifyObservers(NOTIFY_CONTROLLER_TO_CHANGE_TO_LOGGIN_VIEW);

	}

	/**
	 * Top menu from login.  
	 * User chooses how they want to proceed which notifies Observer with string
	 * 
	 * @author Morgan Blackmore
	 * 
	 */
	private void chooseUserTypeMenuView() {

		System.out.println("User Type Page:");
		System.out.println("\nChoose what type of user you are");
		System.out.println("1 - Author");
		System.out.println("2 - SubProgram Chair");
		System.out.print("Please enter choice: ");
		myUserChoice = myScanner.next();

		if (myUserChoice.equals("1")) {
			
			setChanged();
			notifyObservers(NOTIFY_CONTROLLER_TO_CHANGE_TO_AUTHOR_MAIN_VIEW);

		}

		else if (myUserChoice.equals("2")){
			
			setChanged();
			notifyObservers("SUBPROGRAM_CHAIR"); 

		} 

		else {

			System.out.println("Invalid choice, please select from the options displayed");
			chooseUserTypeMenuView();

		}   	

	}

	/**
	 * Change state method to change view displayed to user based off of what the theState received from the Controller.
	 * 
	 * @param theState The current state of the Controller for UI to display.
	 * @author Casey Anderson, Morgan Blackmore
	 * 
	 */
	public void changeState(int theState) {

		if (theState == Controller.LOG_IN_STATE){
			login();
		}

		else if (theState == Controller.CHOOSE_USER) {
			
			chooseUserTypeMenuView();
		
		}

		else if (theState == Controller.FAIL_AUTHOR_HAS_TO_MANY_MANUSCRIPTS) {
			
			authorHasToManySubmittedManuscriptsErrorView();
		
		}

		else if (theState == Controller.FAIL_SUBMITED_PAST_DEADLINE) {
			
			ManuscriptDeadLinePastErrorView();
		
		}

		else if (theState == Controller.FAIL_REVIEWER_IS_AUTHOR_ON_MANUSCRIPT) {
			
			ReviewerIsAuthorErrorView();
		
		}

		else if (theState >=0 && theState < 10) {

			setUserType(NOTIFY_CONTROLLER_TO_CHANGE_TO_AUTHOR_MAIN_VIEW);
			displayHeader();

			switch (theState) {

			case Controller.AUTHOR:

				AuthorView();
				break;

			case Controller.SUBMIT_MANUSCRIPT:

				AuthorManuscriptSubmissionView();
				break;

			case Controller.LIST_MANUSCRIPT_VIEW:

				AuthorListOfManuscriptsView();
				break;

			case Controller.LIST_CONFERENCE_VIEW:
				
				ListOfConferenceView();
				break;

			}
		} 

		else if (theState >= 20){

			setUserType("SubProgram Chair");
			displayHeader();

			switch (theState % 20) {


			case (Controller.SUBPROGRAM_CHAIR):

			subProgramChairView();
			break;

			case (Controller.LIST_CONFERENCE_VIEW):
				subProgramChairConferenceView();
				

			break;

			case (Controller.ASSIGN_REVIEWER):

			subProgramChairAssignReviewerView(); 
			break;

			case(Controller.LIST_MANUSCRIPT_VIEW):
				subProgramChairManuscriptsView();
			break;

			}	

		}
	}

	/**
	 * Method to set myUserType to theUserType of the user.
	 * @param theUserType
	 * @author Casey Anderson
	 */
	private void setUserType(String theUserType) {

		myUserType = theUserType;

	}

	/**
	 * Method to display header on every page so user can see their myUserName and myUserType.
	 * 
	 * @author Casey Anderson
	 */
	private void displayHeader() {

		System.out.println();
		System.out.println(myUserName + " - " + myUserType);

	}

	/**
	 * Method to display the current Author option to the user gather their selection and send it to the Controller.
	 * 
	 * @author Casey Anderson
	 */
	private void AuthorView() {

		System.out.println("Author Main Page:\n");
		System.out.println("1 - Submit new Manuscript.");
		System.out.println("2 - View currently submitted Manuscripts.");
		System.out.println("3 - View current list of Conferences.");
		System.out.print("Please enter choice: ");
		myUserChoice = myScanner.next();

		if (myUserChoice.equals("1")) {

			setChanged();
			notifyObservers(NOTIFY_CONTROLLER_TO_CHANGE_TO_AUTHOR_SUBMIT_MANUSCRIPT_VIEW); 

		}

		else if (myUserChoice.equals("2")) {

			setChanged();
			notifyObservers(NOTIFY_CONTROLLER_TO_CHANGE_TO_AUTHOR_MANUSCRIPT_LIST_VIEW); 

		}

		else if (myUserChoice.equals("3")) {

			setChanged();
			notifyObservers(NOTIFY_CONTROLLER_TO_CHANGE_TO_AUTHOR_CONFERENCE_LIST_VIEW); 

		}

		else {

			System.out.println("Invalid choice, please select from the options displayed");
			AuthorView();

		}

	}

	/**
	 * Method to display the Manuscript submission page to user and gather information from user to submit their
	 * Manuscript and send collected information to the Controller.
	 * 
	 * @author Casey Anderson
	 */
	private void AuthorManuscriptSubmissionView() {

		String manuscriptTile;
		String authorList = "";
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		//LocalDate localDate = LocalDate.now();
		String moreAuthors = "";
		List<String> listOfAuthors = new ArrayList<String>();
		String ManuscriptFilePath;
		System.out.println("Manuscript Submission Page: \n");
		System.out.print("Please enter title of Manuscript: ");
		manuscriptTile = myScanner.next();

		while (!moreAuthors.equals("0")) {

			System.out.print("Please Enter name of Author or CoAuthor for Manuscript or \"0\" when done: ");
			moreAuthors = myScanner.next();

			if (!moreAuthors.equals("0")) {

				listOfAuthors.add(moreAuthors);

			} 

		}

		System.out.print("Please Enter file path of Manuscript: ");
		ManuscriptFilePath = myScanner.next();	

		for (int i = 0; i < listOfAuthors.size(); i++) {

			authorList += listOfAuthors.get(i) + ",";

		}

		setChanged();
		notifyObservers(NOTIFY_CONTROLLER_TO_CHANGE_TO_AUTHOR_SUBMIT_MANUSCRIPT_VIEW + "," + manuscriptTile + "," + ManuscriptFilePath + "," + new Date() + "," + authorList); 


	}

	/**
	 * Method to Display Submit Maunscript Failure to User and let them decide if they want to go back
	 * to the Author main page or the manusscript submission page.
	 * 
	 * @author Casey Anderson
	 */
	private void authorHasToManySubmittedManuscriptsErrorView() {

		System.out.println("Sorry one of the current Authors has the maximum allowed number of manuscripts");
		System.out.println("1 - to go back to Manuscript Submission Page");
		System.out.println("2 - to go back to Author Main Page");
		myUserChoice = myScanner.next();

		if (myUserChoice.equals("1")) {

			setChanged();
			notifyObservers(NOTIFY_CONTROLLER_TO_CHANGE_TO_AUTHOR_SUBMIT_MANUSCRIPT_VIEW);

		}

		else if (myUserChoice.equals("2")) {

			setChanged();
			notifyObservers(NOTIFY_CONTROLLER_TO_CHANGE_TO_AUTHOR_MAIN_VIEW);

		}

		else {

			System.out.println("Invalid choice, please select from the options displayed");
			authorHasToManySubmittedManuscriptsErrorView();

		}

	}

	/**
	 * Method to Display to the User that the Manuscript submission failed due to the Manuscript
	 * Dead line being expired.
	 * 
	 * @author Casey Anderson
	 */
	private void ManuscriptDeadLinePastErrorView() {

		System.out.println("Sorry The Manuscript Dead Line has already past");
		System.out.println("1 - to go back to Author Main Page");
		myUserChoice = myScanner.next();

		if (myUserChoice.equals("1")) {

			setChanged();
			notifyObservers(NOTIFY_CONTROLLER_TO_CHANGE_TO_AUTHOR_MAIN_VIEW);

		}

		else {

			System.out.println("Invalid choice, please select from the options displayed");
			ManuscriptDeadLinePastErrorView();

		}

	}

	/**
	 * Method to Display to the User that the Manuscript submission failed due to the Manuscript
	 * Dead line being expired.
	 * 
	 * @author Casey Anderson
	 */
	private void ReviewerIsAuthorErrorView() {

		System.out.println("Reviewer Not Assigned - because Reviewer is Author on Manuscript being assigned");
		System.out.println("1 - to go back to Assign a Reviewer Page");
		System.out.println("2 - to go back to SubProgram Chair Main Page");	
		myUserChoice = myScanner.next();

		if (myUserChoice.equals("1")) {

			setChanged();
			notifyObservers("ASSIGN_REVIEWER");

		}

		else if (myUserChoice.equals("2")) {

			setChanged();
			notifyObservers("SUBPROGRAM_CHAIR");

		}

		else {

			System.out.println("Invalid choice, please select from the options displayed");
			ReviewerIsAuthorErrorView();

		}

	}

	/**
	 * Method to display all of the current Authors Manuscripts they have already submitted.
	 * 
	 * @author Casey Anderson
	 */
	private void AuthorListOfManuscriptsView() {
		
		//int manuscriptChoice;
		System.out.println("Manuscript List Page: ");
		System.out.println(myAccount.getMyID());
		System.out.println(myAccount.getMyAuthor());
		ArrayList<Manuscript> manuscriptList = new ManuscriptDatabase().getManuscriptsBelongingToAuthor(myAccount.getMyAuthor());
		
		System.out.println("Number of Manuscripts submitted - " + manuscriptList.size());
		for (int i = 0; i < manuscriptList.size(); i++) {
			
			System.out.println("" + (i + 1) + ". " + manuscriptList.get(i).getTitle());
			
		}
		
		System.out.println("Please enter \"1\" to go back to Authors main page");
		System.out.print("Please enter choice: ");
		myUserChoice = myScanner.next();
		
		if (myUserChoice.equals("1")) {
			setChanged();
			notifyObservers(NOTIFY_CONTROLLER_TO_CHANGE_TO_AUTHOR_MAIN_VIEW);
		}
		
		else {
			
			System.out.println("Invalid choice, please select from the options displayed");
			AuthorListOfManuscriptsView();
			
		}
		
		//manuscriptChoice = myScanner.nextInt();
		//setChanged();
		//notifyObservers(manuscriptList.get(manuscriptChoice - 1));
		
	}

	/**
	 * Method to display all of the current Conferences for user to select from and pass to Controller.
	 * 
	 * @author Casey Anderson
	 */
	private void ListOfConferenceView() { 

		int conferenceChoice;
		System.out.println("Conference List Page:\n");
		TreeMap<UUID, Conference> conferenceMap = new ConferenceDatabase().getAllConferences();
		Conference[] listOfConferences = conferenceMap.values().toArray(new Conference[conferenceMap.values().size()]);

		for (int i = 0; i < listOfConferences.length; i++) {

			System.out.println("" + (i + 1) + " - " + listOfConferences[i].getMyName());
			
		}
		
		System.out.print("Please enter choice: ");
		
		while (!myScanner.hasNextInt()) {
			
			myScanner.next();
			System.out.println("Invalid choice, please select from the options displayed");
			System.out.print("Please enter choice: ");
			
		}
		
		conferenceChoice = myScanner.nextInt();
		
		while (conferenceChoice < 1 || conferenceChoice > listOfConferences.length) {
			
			System.out.println("Invalid choice, please select from the options displayed");
			System.out.print("Please enter choice: ");
			
			while (!myScanner.hasNextInt()) {
				
				myScanner.next();
				System.out.println("Invalid choice, please select from the options displayed");
				System.out.print("Please enter choice: ");
				
			}
			
			conferenceChoice = myScanner.nextInt();
			
		}
		
		setChanged();
		notifyObservers(listOfConferences[conferenceChoice - 1]);
		setChanged();
		notifyObservers(NOTIFY_CONTROLLER_TO_CHANGE_TO_AUTHOR_CONFERENCE_LIST_VIEW + "," + listOfConferences[conferenceChoice - 1].getMyName());

	}

	/**
	 * Display SubProgramChair top menu
	 * 
	 * @author Morgan Blackmore
	 */
	private void subProgramChairView() {
		
		System.out.println("SubProgram Chair Page:\n");
		System.out.println("1 - Assign a Reviewer");
		System.out.println("2 - Select a Conference");
		System.out.println("3 - Select a Manuscript");
		myUserChoice = myScanner.next();

		switch (myUserChoice) {
		
		case ("1"):
			
			setChanged();
			notifyObservers("ASSIGN_REVIEWER"); 
			break;
			
		case ("2"):
			
			setChanged();
			notifyObservers(NOTIFY_CONTROLLER_TO_CHANGE_TO_AUTHOR_CONFERENCE_LIST_VIEW); 
			break;
		
		case ("3"):
			setChanged();
			notifyObservers(NOTIFY_CONTROLLER_TO_CHANGE_TO_AUTHOR_MANUSCRIPT_LIST_VIEW); 
			break;
			
		}

	}

	private void subProgramChairManuscriptsView() {
		ArrayList<Manuscript> manuscriptList = new ManuscriptDatabase().getManuscriptsBelongingToAuthor(myAccount.getMyAuthor());
		for (int i = 0; i < manuscriptList.size(); i++) {
			
			System.out.println("" + (i + 1) + " - " + manuscriptList.get(i).getTitle());
			
		}
		//make a call to database to get myAssignedManuscripts list from SPC
		//display index + 1 and Manuscript title 
		//will take the users input (a digit) and get the title at that index, then send the title as a string to controller 

	}

	/**
	 * Calls db to get conference list for subprogramChair
	 */
	private void subProgramChairConferenceView() {
		System.out.println("To assign a reviewer, first select a conference from the list below:\n");
		
		ListOfConferenceView();
		//make a call to database to get myAssignedConferences for SPC
		//display index and conference title
		//will take the users input (a digit) and get the title at that index, then send the title as a string to controller
		//Should take user to next menu-Conference View 

	}

	/**
	 * Walks User through the process of selecting conference, manuscript, and reviewer then sends info to controller
	 */
	private void subProgramChairAssignReviewerView() {
		//make a call to database to get myAssignedConferences for SPC
		//display index and conference title
		//take users input and store it
		//call database to get manuscripts for the stored conference
		//display manuscripts
		//take manuscript and store it
		//call database for list of reviewers
		//display reviewers
		//take reviewer and store it
		//send stored values to controller

	}

	/**
	 * Used to talk to the Controller while staying decoupled.
	 * 
	 * @author Casey Anderson
	 */
	@Override
	public void update(Observable arg0, Object theArg) {

		if (theArg instanceof Integer) {
			changeState((int) theArg);
		}

	}

}
