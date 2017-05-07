import static org.junit.Assert.*;

import java.util.Observable;
import java.util.Observer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.Controller;

public class ControllerTest {

	private Controller myPracticeController;
	private ControllerTestHelper myControllerTestHelper;
	
	
	@Before
	public void setUp() throws Exception {
		myPracticeController = new Controller ();
		myControllerTestHelper = new ControllerTestHelper ();
		
		myPracticeController.addObserver(myControllerTestHelper);
		myControllerTestHelper.addObserver(myPracticeController);
	}
	
	
	@After
	public void tearDown() throws Exception {
		myPracticeController.deleteObservers();
		myControllerTestHelper.deleteObservers();
	}
	

	
	@Test
	public void startProgram_testStartProgramForChangeSet () {
		myPracticeController.startProgram();
		assertTrue("startProgram() setChanged and notified correctly.", myControllerTestHelper.showWasChanged());
	}
	
	
	@Test
	public void startProgram_testStartProgramForCorrectValuePassed () {
		myPracticeController.startProgram();
		assertEquals(myControllerTestHelper.showStateValue(), Controller.LOG_IN_STATE);
	}
	
	
	@Test
	public void changeState_testChangeStateForCorrectValuePassedFromObserver () {
		myPracticeController.setState(Controller.AUTHOR+Controller.LIST_CONFERENCE_VIEW);
		myControllerTestHelper.makeChange("List Conference View");
		
		assertEquals(myPracticeController.getState(), Controller.AUTHOR+Controller.USER_OPTIONS);
	}
	
	
	public class ControllerTestHelper extends Observable implements Observer {
		
		private boolean wasChanged;
		private int stateValue;
		
		public ControllerTestHelper () {
			reset();
		}
		
		public void makeChange (String theString) {
			setChanged();
			notifyObservers(theString);
		}
		
		public void reset () {
			wasChanged = false;
		}
		
		public boolean showWasChanged () {
			return wasChanged;
		}
		
		public int showStateValue () {
			return stateValue;
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			if (arg1 instanceof Integer) {
				wasChanged = true;
				stateValue = (Integer) arg1;
			}
		}
		
	}

}
