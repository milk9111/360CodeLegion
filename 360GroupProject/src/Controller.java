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
		
		switch (myCurrentState) {
		
		case AUTHOR:
			break;
		case REVIEWER:
			break;
		case SUBPROGRAM_CHAIR:
			break;
		}
	}

	@Override
	public void update(Observable o, Object arg) {

	}
}
