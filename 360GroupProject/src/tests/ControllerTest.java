import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.Controller;
import model.Conference;
import model.ConferenceDatabase;
import model.Reviewer;


/**
 * All tests for the System Controller.
 * 
 * @author Connor Lundberg
 * @version 5/6/2017
 *
 */
public class ControllerTest {

	private Controller myPracticeController;
	private ControllerTestHelper myControllerTestHelper;
	private ArrayList<Reviewer> myTestReviewersListHeroes;
	private ArrayList<Reviewer> myTestReviewersListCartoons;
	private Conference myHeroesConference;
	private Conference myCartoonConference;
	
	
	/**
	 * Creates the Test Controller and the Helper class along with adding them as
	 * Observers to each other.
	 * 
	 * @throws Exception
	 * @author Connor Lundberg
	 * @version 5/7/2017
	 */
	@Before
	public void setUp() throws Exception {
		myPracticeController = new Controller ();
		myControllerTestHelper = new ControllerTestHelper ();
		
		myTestReviewersListHeroes = new ArrayList<Reviewer>();
		myTestReviewersListHeroes.add(new Reviewer("Johnny Storm", null));
		myTestReviewersListHeroes.add(new Reviewer("Reed Richards", null));
		myTestReviewersListHeroes.add(new Reviewer("Susan Storm", null));
		myTestReviewersListHeroes.add(new Reviewer("Ben Grimm", null));
		myHeroesConference = new Conference("National Heroes Without Borders Conference", new Date("5/7/2017"), new Date("5/8/2017"), myTestReviewersListHeroes);
		
		myTestReviewersListCartoons = new ArrayList<Reviewer>();
		myTestReviewersListCartoons.add(new Reviewer("Brock Samson", null));
		myTestReviewersListCartoons.add(new Reviewer("Mr. Meeseeks", null));
		myTestReviewersListCartoons.add(new Reviewer("Bob Belcher", null));
		myTestReviewersListCartoons.add(new Reviewer("Kyle Broflovski", null));
		myCartoonConference = new Conference("Annual Random Cartoon Characters Conference", new Date("5/3/2017"), new Date("7/15/2018"));
		
		myPracticeController.setConference(myHeroesConference);
		myPracticeController.setConference(myCartoonConference);
		
		
		myPracticeController.addObserver(myControllerTestHelper);
		myControllerTestHelper.addObserver(myPracticeController);
	}
	
	
	/**
	 * Removes the Observers from each other so there are no duplicates.
	 * 
	 * @throws Exception
	 * @author Connor Lundberg
	 * @version 5/7/2017
	 */
	@After
	public void tearDown() throws Exception {
		myPracticeController.deleteObservers();
		myControllerTestHelper.deleteObservers();
	}
	

	/**
	 * Tests the startProgram method to make sure that it is actually starting
	 * everything off correctly. It's simple, but very important.
	 * 
	 * @author Connor Lundberg
	 * @version 5/7/2017
	 */
	@Test
	public void startProgram_testStartProgramForChangeSet () {
		myPracticeController.startProgram();
		assertTrue("startProgram() setChanged and notified correctly.", myControllerTestHelper.showWasChanged());
	}
	
	
	/**
	 * Tests the startProgram method for the correct value passed to UI.
	 * 
	 * @author Connor Lundberg
	 * @version 5/7/2017
	 */
	@Test
	public void startProgram_testStartProgramForCorrectValuePassed () {
		myPracticeController.startProgram();
		assertEquals(myControllerTestHelper.showStateValue(), Controller.LOG_IN_STATE);
	}
	
	
	/**
	 * Tests the changeState method for correct value received from UI.
	 * 
	 * @author Connor Lundberg
	 * @version 5/7/2017
	 */
	@Test
	public void changeState_testChangeStateForCorrectValuePassedFromObserver () {
		myPracticeController.setState(Controller.AUTHOR+Controller.LIST_CONFERENCE_VIEW);
		myControllerTestHelper.makeChange("List Conference View");
		
		assertEquals(myPracticeController.getState(), Controller.AUTHOR+Controller.USER_OPTIONS);
	}
	
	
	@Test
	public void changeState_testChangeStateForCorrectAuthorConferenceListAndStateChange () {
		myPracticeController.setState(Controller.AUTHOR+Controller.LIST_CONFERENCE_VIEW);
		assertTrue("myControllerTestHelper.viewConferences did not contain heroesConference", myControllerTestHelper.viewConferences().contains(myHeroesConference.getMyName()));
		assertTrue("myControllerTestHelper.viewConferences did not contain cartoonConference", myControllerTestHelper.viewConferences().contains(myCartoonConference.getMyName()));
	
		myControllerTestHelper.makeChange("List Conference View");
		assertEquals(myPracticeController.getState(), Controller.AUTHOR+Controller.USER_OPTIONS);
	}
	
	
	@Test
	public void changeState_testChangeStateForCorrectAuthorUserOptionsStateChangeWithSubmitManuscript () {
		myPracticeController.setState(Controller.AUTHOR+Controller.USER_OPTIONS);
		myControllerTestHelper.makeChange("SUBMIT_MANUSCRIPT");
		System.out.println(myPracticeController.getState() + ", " + Controller.AUTHOR+Controller.SUBMIT_MANUSCRIPT);
		assertEquals(myPracticeController.getState(), Controller.AUTHOR+Controller.SUBMIT_MANUSCRIPT);
	}
	
	
	@Test
	public void changeState_testChangeStateForCorrectAuthorUserOptionsStateChangeWithGoBack () {
		myPracticeController.setState(Controller.AUTHOR+Controller.USER_OPTIONS);
		myControllerTestHelper.makeChange("Go Back");
		assertEquals(myPracticeController.getState(), Controller.AUTHOR+Controller.LIST_CONFERENCE_VIEW);
	}
	
	
	
	/**
	 * A helper class to act as a stand-in for the UI.
	 * 
	 * @author Connor Lundberg
	 * @version 5/7/2017
	 *
	 */
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
		
		public String viewConferences () {
			TreeMap<UUID, Conference> conferenceMap = new ConferenceDatabase().getAllConferences();
			Conference[] listOfConferences = conferenceMap.values().toArray(new Conference[conferenceMap.values().size()]);
			
			String returnString = "";
			for (Conference singleConference : listOfConferences) {
				returnString += singleConference.getMyName() + " ";
			}
			
			return returnString;
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
