
public class Controller {
	//View States
	private final int AUTHOR = 0;
	private final int REVIEWER = 10;
	private final int SUBPROGRAM_CHAIR = 20;
	
	//Action States
	private final int SUBMITTING = 1;
	
	
	private int myCurrentState;
	
	
	private void changeState (int theNextState) {
		myCurrentState = theNextState;
		switch (myCurrentState) {
		
		}
	}
}
