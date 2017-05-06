
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

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
	private String userType;
	private String myUserName;
	private User myUser;
	private Scanner myScanner;
	private int myUserChoice;
	
	public UI() {
		myState = 0;
		userType = "";
		myUserName = "";
		myUserChoice = 0;
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
     * @author Casey, Anderson Morgan Blackmore
     * 
     */
	
    private void start() {
    	boolean validUserName = false;
    	while (!validUserName) {
	    	System.out.println("Please enter user name to log in: ");
	    	myUserName = myScanner.next();
	    	if (myUser.isUserRegistered(myUserName)) {
	    		validUserName = true;
	    		
	    		chooseUserTypeMenuView();
	    	} else {
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
    	if (myScanner.next()== "1") {
    		notifyObservers("AUTHOR"); 
    	} else if(myScanner.next()=="2"){
    		notifyObservers("SUBPROGRAM_CHAIR"); 
      	} else {
      		System.out.println("Invalid choice, please select from the options displayed");
      		chooseUserTypeMenuView();
      	}
    	
    	
    	
    }
    
	public void changeState(int theState) {
			
		if (theState < 10) {
			setUserType("Author");
			displayHeader();
			switch (theState) {
			case 1:
				AuthorManuscriptSubmissionView();
			case 2:
				AuthorListOfManuscriptsView();
			case 3:
				ListOfConferenceView();
			}
		} else {
			setUserType("SubProgramChair");
			displayHeader();
			
			subProgramChairView();
		}
	}
	
	
	private void setUserType(String theUserType) {
		userType = theUserType;
	}
	private void displayHeader() {
		System.out.println(myUserName + " - " + userType);
		System.out.println();
		
	}
	
	private void AuthorView() {
		System.out.println("Author Options:");
		System.out.println("1 - Submit new Manuscript.");
		System.out.println("2 - View currently submitted Manuscripts.");
		System.out.println("3 - View current list of Conferences.");	
		while (!myScanner.hasNextInt() || (myUserChoice < 1 || myUserChoice > 3)) {
			myUserChoice = myScanner.nextInt();
		}
		
	}
	
	private void AuthorManuscriptSubmissionView() {
		String manuscriptTile;
		int moreAuthors = 1;
		List<String> listOfAuthors = new ArrayList<String>();
		String ManuscriptFilePath;
		System.out.println("Manuscript submission:");
		System.out.println("Please Enter Title of Manuscript: ");
		manuscriptTile = myScanner.next();
		while (moreAuthors != 0) {
			System.out.println("Please Enter name of Author or CoAuthor for Manuscript or \"0\" when done: ");
			if (myScanner.hasNextInt()) {
				moreAuthors = myScanner.nextInt();
			} else {
				listOfAuthors.add(myScanner.next());
			}
		}
		System.out.println("Please Enter file path of Manuscript: ");
		ManuscriptFilePath = myScanner.next();	
		
	}
	
	private void AuthorListOfManuscriptsView() {
		System.out.println("List of currently submitted Manuscripts:");
		for (int i = 0; i < )
	}
	
	private void ListOfConferenceView() { //how know which conference list to display?
		
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
//		while (!myScanner.hasNextInt() || (myUserChoice < 1 || myUserChoice > 3)) {
			myUserChoice = myScanner.nextInt();
//		}
		switch (myUserChoice){
			case (1):
				notifyObservers(); //add notify message
				break;
			case (2):
				notifyObservers(); //add notify message
				break;
			case (3):
				notifyObservers(); //add notify message
				break;
		}
				

		}
	private void myManuscriptsView() {
		
	}
	
	private void assignReviewerView() {
		System.out.println("Assign Reviewer Options");
		System.out.println("First, Choose a conference");
		ListOfConferenceView();//display conference list
		
	}
	
	

	@Override
	public void removeListener(InvalidationListener listener) {


		public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
