package client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javafx.beans.InvalidationListener;
import model.User;

/**
 * 
 * @author Casey Anderson, Morgan Blackmore
 * @version 5/6/17 1:45pm
 */

public class UI extends Observable implements Observer{


	//View States
	public final int AUTHOR = 0;
	public final int REVIEWER = 10;
	public final int SUBPROGRAM_CHAIR = 20;

	//Action States
	public final int SUBMIT_MANUSCRIPT = 1;
	public final int LIST_MANUSCRIPT_VIEW = 2;
	public final int LIST_CONFERENCE_VIEW = 3;
	public final int ASSIGN_REVIEWER = 4;

	private int myState;
	private String myUserType;
	private String myUserName;
	private User myUser;
	private Scanner myScanner;

	//	private int myUserChoice;


	private String myUserChoice;

	public UI() {
		myState = 0;
		myUserType = "";
		myUserName = "";
		myUserChoice = "";
		myScanner = new Scanner(System.in);
	}

	/**
	 * The start point for the program.
	 * 
	 * @param theArgs command line arguments - ignored
	 */
	public static void main(final String[] theArgs) {
		new UI().start();
	}
	
	/**
	 * Login prompt.  Displays prompt and scans input, compares to list of existing users.
	 * If valid, updates this UI user fields and proceeds to top menuL chooseUserTypeMenuView 
	 * 
	 * @author Casey Anderson, Morgan Blackmore
	 * 
	 */
	private void start() {
		
		boolean validUserName = false;
		
		while (!validUserName) {
			
			System.out.println("Please enter user name to log in: ");
			myUserName = myScanner.next();
//			if (myUser.isUserRegistered(myUserName)) {
			
			if (true) { //temporary block while we don't have a User registry to check
				validUserName = true;
				chooseUserTypeMenuView();
			} 
			
			else {
				
				System.out.println("Invalid User Name!");
			
			}
		}

	}

	/**
	 * Top menu from login.  
	 * User chooses how they want to proceed which notifies Observer with string
	 * 
	 * @author Morgan Blackmore
	 * 
	 */
	private void chooseUserTypeMenuView() {

		System.out.println("\nChoose what type of user you are");
		System.out.println("1 - Author");
		System.out.println("2 - SubProgram Chair");

		if (myScanner.next() == "1") {
			
			notifyObservers("AUTHOR");
			
		}
		
		else if(myScanner.next() == "2"){
			
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

		if (theState < 10) {
			
			setUserType("Author");
			displayHeader();
			
			switch (theState) {
			
			case AUTHOR:
				AuthorView();
				break;
			
			case SUBMIT_MANUSCRIPT:
				AuthorManuscriptSubmissionView();
				break;
				
			case LIST_MANUSCRIPT_VIEW:
				AuthorListOfManuscriptsView();
				break;
			
			case LIST_CONFERENCE_VIEW:
				ListOfConferenceView();
				break;
			
			}
		} 
		
		else if (theState >= 20){
			
			setUserType("SubProgram Chair");
			displayHeader();
			
			switch (theState % 20) {
			
			case (0):
				subProgramChairView();
				break;
				
			}
			
			//add switch statement
			subProgramChairConferenceView();
			subProgramChairAssignReviewerView(); 
			subProgramChairManuscriptsView();
			
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
	 * Method to diplay header on every page so user can see their myUserName and myUserType.
	 * 
	 * @author Casey Anderson
	 */
	private void displayHeader() {
		
		System.out.println(myUserName + " - " + myUserType);
		System.out.println();

	}

	/**
	 * Method to display the current Author option to the user gather their selection and send it to the Controller.
	 * 
	 * @author Casey Anderson
	 */
	private void AuthorView() {
		
		System.out.println("Author Main Page:");
		System.out.println("1 - Submit new Manuscript.");
		System.out.println("2 - View currently submitted Manuscripts.");
		System.out.println("3 - View current list of Conferences.");
		myUserChoice = myScanner.next();
		
		if (myUserChoice.equals("1")) {
			notifyObservers("SUBMIT_MANUSCRIPT"); 
		}
		
		else if (myUserChoice.equals("2")) {
			notifyObservers("LIST_MANUSCRIPT_VIEW"); 
		}
		
		else if (myUserChoice.equals("3")) {
			notifyObservers("LIST_CONFERENCE_VIEW"); 
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
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now();
		String moreAuthors = "";
		List<String> listOfAuthors = new ArrayList<String>();
		String ManuscriptFilePath;
		System.out.println("Manuscript Submission Page");
		System.out.println("Please enter title of Manuscript: ");
		manuscriptTile = myScanner.next();
		
		while (!moreAuthors.equals("0")) {
			
			System.out.println("Please Enter name of Author or CoAuthor for Manuscript or \"0\" when done: ");
			moreAuthors = myScanner.next();
			
			if (!moreAuthors.equals("0")) {
				listOfAuthors.add(myScanner.next());
			} 
			
		}
		
		System.out.println("Please Enter file path of Manuscript: ");
		ManuscriptFilePath = myScanner.next();	
		
		for (int i = 0; i < listOfAuthors.size(); i++) {
			
			authorList += listOfAuthors.get(i) + ",";
			
		}
		
		notifyObservers("Submit Manuscript," + manuscriptTile + "," + ManuscriptFilePath + "," + dtf.format(localDate) + "," + authorList); 


	}

	/**
	 * Method to display all of the current Authors Manuscripts they have aready submitted.
	 * 
	 * @author Casey Anderson
	 */
	private void AuthorListOfManuscriptsView() {
		System.out.println("Manuscript List Page");
		//		for (int i = 0; i < )
	}

	private void ListOfConferenceView() { //Gets list from database and displays.  DB isn't ready yet.

	}

	/**
	 * Display SubProgramChair top menu
	 * 
	 * @author Morgan Blackmore
	 */
	private void subProgramChairView(){
		System.out.println("SubProgram Chair Options");
		System.out.println("1 - Assign a Reviewer");
		System.out.println("2 - Select a Conference");
		System.out.println("3 - Select a Manuscript");
		myUserChoice = myScanner.next();
		
		switch (myUserChoice){
		case ("1"):
			notifyObservers("ASSIGN_REVIEWER"); 
		break;
		case ("2"):
			notifyObservers("LIST_CONFERENCE_VIEW"); 
		break;
		case ("3"):
			notifyObservers("LIST_MANUSCRIPT_VIEW"); 
		break;
		}


	}

	private void subProgramChairManuscriptsView() {
		//make a call to database to get myAssignedManuscripts list from SPC
		//display index + 1 and Manuscript title 
		//will take the users input (a digit) and get the title at that index, then send the title as a string to controller 

	}

	/**
	 * Calls db to get conference list for subprogramChair
	 */
	private void subProgramChairConferenceView() {
		//make a call to database to get myAssignedConferences for SPC
		//display index and conference title
		//will take the users input (a digit) and get the title at that index, then send the title as a string to controller
		//Should take user to next menu-Conference View 

	}
	
	/**
	 * This method is redundant from a back-end POV, it just forces a user to select from a conference
	 */
	private void subProgramChairAssignReviewerView() {
		//make a call to database to get myAssignedConferences for SPC
		//display index and conference title

		

	}



	public void removeListener(InvalidationListener listener) {
		
	}
	
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
