import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author Ryan Tran
 * @date 4/29/17
 */
public class SubprogramChairTest {

    // fixtures
	private SubprogramChair mySubprogramChair;

	/**
	 * Initialize a subprogram chair
	 * @throws Exception
	 */
    @Before
    public void setUp() throws Exception {
        mySubprogramChair = new SubprogramChair("Marauder");
    }

    /**
     * Test method for {@link SubprogramChair#SubprogramChair(String)}
     */
    @Test
    public void testConstructor() {
        String testUsername = "Scion";
        SubprogramChair simpleChairObj = new SubprogramChair(testUsername);

        assertEquals(simpleChairObj.getName(), testUsername);
    }

    /**
     * Test method for {@link SubprogramChair#getMyAssignedConferences()}
     * Tests to see if the correct list of conferences assigned to the subprogram chair is returned.
     */
    @Test
    public void testGetMyAssignedConferences() {
    	// add a single conference to subprogram chair's assigned conferences
    	Date submissionDeadline = new Date();
    	Date reviewDeadline = new Date();
    	List<Reviewer> pastReviewerList = new ArrayList<Reviewer>();
    	Conference tempConference = new Conference(submissionDeadline, reviewDeadline, pastReviewerList);

    	mySubprogramChair.getMyAssignedConferences().add(tempConference);
    	
    	List<Conference> theAssignedConferences = mySubprogramChair.getMyAssignedConferences();
    	
    	// assert if the added conference is equivalent to the one in the current subprogram chair
    	assertTrue(theAssignedConferences.get(0) == tempConference);
    	assertEquals(theAssignedConferences.size(), 1);
    }

    /**
     * Test method for {@link SubprogramChair#getReviewers()}
     * Tests if list of all reviewers is returned from the method
     */
    @Test
    public void testGetReviewers() {
    	// initialize a temporary list of Reviewers
    	Reviewer tempReviewerA = new Reviewer("John Doe", new ArrayList<Conference>());
    	Reviewer tempReviewerB = new Reviewer("Jane Doe", new ArrayList<Conference>());
    	Reviewer tempReviewerC = new Reviewer("Jack Doe", new ArrayList<Conference>());
    	
    	List<Reviewer> tempReviewerList = new ArrayList<Reviewer>();
    	tempReviewerList.add(tempReviewerA);
    	tempReviewerList.add(tempReviewerB);
    	tempReviewerList.add(tempReviewerC);
    	
    	// Add same temp Reviewers to database
    	Database.addReviewerToReviewerList(tempReviewerA);
    	Database.addReviewerToReviewerList(tempReviewerB);
    	Database.addReviewerToReviewerList(tempReviewerC);
    	
    	// Call SubprogramChair#getReviewers() and test for equality between temp list of reviewers
    	List<Reviewer> listToBeTested = mySubprogramChair.getReviewers();
    	
    	for(int i = 0; i < Database.getReviewerListSize(); i++) {
    		// Using == to test Reviewer objects by reference rather than field values
    		assertTrue(listToBeTested.get(i) == tempReviewerList.get(i));
    	}
    	
    	// testing size of both lists
    	assertEquals(listToBeTested.size(), Database.getReviewerListSize());
    }

    /**
     * Test method for {@link SubprogramChair#getConferenceList()}
     * Tests if correct list of viewable conferences for the subprogram chair is returned
     */
    @Test
    public void testGetConfernceList() {
        List<Conference> conferenceListWithMoreThanOne = new ArrayList<Conference>();
        Conference tempConfA = new Conference(new Date(), new Date(), new ArrayList<Reviewer>());
        Conference tempConfB = new Conference(new Date(), new Date(), new ArrayList<Reviewer>());
        Conference tempConfC = new Conference(new Date(), new Date(), new ArrayList<Reviewer>());

        // initialize conference list to have multiple items
        conferenceListWithMoreThanOne.add(tempConfA);
        conferenceListWithMoreThanOne.add(tempConfB);
        conferenceListWithMoreThanOne.add(tempConfC);

        mySubprogramChair.setConferences(conferenceListWithMoreThanOne);

        assertEquals(mySubprogramChair.getConferenceList(), conferenceListWithMoreThanOne);
    }

}