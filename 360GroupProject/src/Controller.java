import java.util.*;
import java.util.regex.Pattern;

import model.Author;
import model.Conference;
import model.Manuscript;
import model.Reviewer;
import model.User;

/**
 * The system controller that handles the different states of the 
 * program. It is the bridge between the UI and the Model. 
 * 
 * @author Connor Lundberg
 * @author Josiah Hopkins
 * @version 5/5/2017
 */
public class Controller extends Observable implements Observer {

	//View States
	public final int AUTHOR = 0;
	public final int REVIEWER = 10;
	public final int SUBPROGRAM_CHAIR = 20;
	
	//Action States
	public final int USER_OPTIONS = 0;
	public final int SUBMIT_MANUSCRIPT = 1;
	public final int LIST_MANUSCRIPT_VIEW = 2;
	public final int LIST_CONFERENCE_VIEW = 3;
	public final int ASSIGN_REVIEWER = 4;
	
	
	//Objects we are adding in the system. We are saving them because we need persistence between states.
	private int myCurrentState;

	private User myUser;

	private Conference myCurrentConference;
	private Manuscript myCurrentManuscript;
	private Reviewer myCurrentReviewer;
	

	
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
	 * @author Josiah Hopkins
	 * @version 5/5/2017
	 * @param theNextState The next state the program will be in.
	 */
	private void changeState (String theNextState) {
		String[] pieces = theNextState.split(",");
		
		switch ((myCurrentState / 10) * 10) {
			case AUTHOR:
				switch (myCurrentState % 10){
					case SUBMIT_MANUSCRIPT:
                        Manuscript manuscriptToSubmit;
						if(pieces[0].equals("Submit Manuscript")){
							manuscriptToSubmit = makeManuscript(pieces);
							
							try {
								((Author) myUser).addManuscript(myCurrentConference, manuscriptToSubmit);
							} catch (Exception e) {
								// TODO Auto-generated catch block
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
							
						}
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
                    case ASSIGN_REVIEWER:
						myCurrentReviewer = findReviewer(theNextState, myCurrentConference.getPastReviewers());
                        break;
                    case LIST_CONFERENCE_VIEW:
						//myCurrentConference =
                        break;
					case LIST_MANUSCRIPT_VIEW:

						break;
				}
				break;
		}
	}

	private Reviewer findReviewer(String theNextState, List<Reviewer> pastReviewers) {
		for(Reviewer r: pastReviewers){
			if(theNextState.contains(r.getUsername())){
				return r;
			}
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


























