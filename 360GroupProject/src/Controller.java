import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * The system controller that handles the different states of the 
 * program. It is the bridge between the UI and the Model. 
 * 
 * @author Connor Lundberg
 * @version 5/5/2017
 */
public class Controller extends Observable implements Observer {

	//View States
	public final int AUTHOR = 0;
	public final int REVIEWER = 10;
	public final int SUBPROGRAM_CHAIR = 20;
	
	//Action States
	public final int SUBMIT_MANUSCRIPT = 1;
	public final int LIST_MANUSCRIPT_VIEW = 2;
	public final int LIST_CONFERENCE_VIEW = 3;
	public final int ASSIGN_REVIEWER = 4;
	
	
	private int myCurrentState;
	private User myUser;
	private Conference myConference;
	
	
	/**
	 * Call this method after instantiating the Controller
	 * to start the program. It sets myCurrentState to 0 and
	 * makes the UI object.
	 * 
	 * @author Connor Lundberg
	 * @version 5/6/2017
	 */
	public void startProgram () {
		myCurrentState = AUTHOR;
		changeState (null);
	}
	
	
	/**
	 * This method is the finite state machine. It takes a integer 
	 * representing the next state to change to and passes information to
	 * the UI accordingly.
	 * 
	 * @author Connor Lundberg
	 * @version 5/5/2017
	 * @param theNextState The next state the program will be in.
	 */
	private void changeState (String theNextState) {
		
		switch ((myCurrentState / 10) * 10) {
			case AUTHOR:
				Manuscript manuscriptToAdd = new Manuscript();
				manuscriptToAdd.setTitle();
				manuscriptToAdd.addAuthor();
				((Author) myUser).addManuscript(myConference, new Manuscript());
				myConference.submitManuscript(theManuscript);
				break;
			case REVIEWER:
				break;
			case SUBPROGRAM_CHAIR:
				
				break;
		}
	}
	
	
	private Manuscript makeManuscript (String theStringToParse) {
		Manuscript returnManuscript = new Manuscript();
		Scanner stringScan = new Scanner (theStringToParse);
		
		Pattern delimiterPattern = stringScan.delimiter();
		stringScan.findInLine(delimiterPattern);
		
		return returnManuscript;
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof String) {
			changeState ((String) arg1);
		}
	}
		
}


























