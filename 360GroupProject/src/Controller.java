import java.util.Observable;
import java.util.Observer;
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

	//Objects we are adding in the system. We are saving them because we need persistence between states.
	private Conference currentConference;
	private Manuscript currenManuscript;

	
	
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
                        Manuscript submitting;
						if(pieces[0].equals("Submit Manuscript")){

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
	

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof String) {
			changeState ((String) arg1);
		}
	}
		
}
