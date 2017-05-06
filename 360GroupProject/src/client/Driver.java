package client;

public class Driver {

	public static void main(String[] args) {
		Controller fsm = new Controller ();
		UI consoleDisplay = new UI ();
		
		fsm.addObserver(consoleDisplay);
		consoleDisplay.addObserver(fsm);
		
		fsm.startProgram ();
	}

}
