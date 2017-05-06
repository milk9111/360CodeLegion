import java.util.Date;
import java.util.Observable;
import java.util.Observer;
<<<<<<< HEAD
import java.util.Scanner;
import java.util.regex.Pattern;

=======
>>>>>>> master
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
	
	
	//Objects we are adding in the system. We are saving them because we need persistence between states.
	private int myCurrentState;
	private User myUser;
	private Conference myConference;
	private Manuscript myCurrentManuscript;
	

	
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
		String[] pieces = theNextState.split(",");
		
		switch ((myCurrentState / 10) * 10) {
			case AUTHOR:
				switch (myCurrentState % 10){
					case SUBMIT_MANUSCRIPT:
                        Manuscript manuscriptToSubmit = new Manuscript();
						if(pieces[0].equals("Submit Manuscript")){
							
							
							manuscriptToSubmit.setSubmittedDate(new Date(pieces[2]));
							
							((Author) myUser).addManuscript(myConference, manuscriptToSubmit);
                        }

						break;
					case LIST_MANUSCRIPT_VIEW:

						break;
					case LIST_CONFERENCE_VIEW:

						break;
				}
				
				break;
			case REVIEWER:
				switch (myCurrentState % 10){

				}
				break;
			case SUBPROGRAM_CHAIR:
				
				switch (myCurrentState % 10){

				}
				break;
		}
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
			returnManuscript.addAuthor(new Author(thePieces[i]));
		}
		
		return returnManuscript;
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof String) {
			changeState ((String) arg1);
		}
	}
		
}


























