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
	private List<Reviewer> myReviewerList;
	private SubprogramChair mySubprogramChair;

    @Before
    public void setUp() throws Exception {
        myReviewerList = new ArrayList<Reviewer>();
        mySubprogramChair = new SubprogramChair("Marauder");
    }

    /**
     * Test method for SubprogramChair constructor
     */
    @Test
    public void testConstructor() {
        String testUsername = "Scion";
        SubprogramChair simpleChairObj = new SubprogramChair(testUsername);

        assertEquals(simpleChairObj.getName(), testUsername);
    }
    @Test
    public void testGetMyAssignedConferences() {

    }

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