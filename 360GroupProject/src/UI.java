import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class UI implements Observer{
	private int myState;
	private String userType;
	private String myUserName;
	private User myUser;
	private Scanner myScanner;
	private int userChoice;
	
	public UI() {
		myState = 0;
		userType = "";
		myUserName = "";
		userChoice = 0;
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
	
    private void start() {
    	boolean validUserName = false;
    	while (!validUserName) {
	    	System.out.println("Please enter user name to log in: ");
	    	myUserName = myScanner.next();
	    	if (myUser.isUserRegistered(myUserName)) {
	    		validUserName = true;
	    	} else {
	    		System.out.println("Invalid User Name!");
	    	}
    	}
    	
    }
    
	public void changeState() {
			
		if (Controller.theState < 10) {
			setUserType("Author");
			displayHeader();
		} else {
			setUserType("SubProgramChair");
			displayHeader();
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
		System.out.println("0 - View currently submitted Manuscripts.");
		System.out.println("1 - View current list of Conferences.");
		System.out.println("2 - Submit new Manuscript.");
	}
	
	private void AuthorListOfManuscriptsView() {
		System.out.println("List of currently submitted Manuscripts:");
		for (int i = 0; i < )
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
