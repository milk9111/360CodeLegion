import org.junit.Before;
import org.junit.Test;

import model.Conference;
import model.Database;
import model.Reviewer;
import model.SubprogramChair;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import model.Author;
import model.Conference;
import model.Database;
import model.Manuscript;
import model.Reviewer;
import model.SubprogramChair;
import model.User;

import static org.junit.Assert.*;

/**
 * @author Ryan Tran
 * @date 4/29/17
 */
public class SubprogramChairTest {

    // fixtures
	private SubprogramChair mySubprogramChair;
	private Conference myConference;

	/**
	 * Initialize a subprogram chair
	 * @throws Exception
	 */
    @Before
    public void setUp() throws Exception {
    	myConference = new Conference("Hydrogen Analysis", new Date(), new Date());
        mySubprogramChair = new SubprogramChair("Marauder", myConference);
        Database.clearDatabase();
    }


    /**
     * Test method for {@link SubprogramChair#getReviewers()}
     * Tests if list of all reviewers is returned from the method
     * @author Ryan Tran
     */
    @Test
    public void getReviewers_forReviewerListWithMoreThanOneItem_shouldReturnList() {
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
    		assertTrue("Individual Reviewer in list should be equal to temp list's Reviewer",
    				listToBeTested.get(i) == tempReviewerList.get(i));
    	}
    	
    	// testing size of both lists
    	assertEquals("Returned Reviewer list should be equal in size to database list",
    			listToBeTested.size(), Database.getReviewerListSize());
    }
    
    
    /**
     * Test method for {@link SubprogramChair#getReviewers()}
     * Tests if list there are 0 reviewers in the reviewers list
     * @author Ryan Tran
     */
    @Test
    public void getReviewers_forEmptyReviewerList_shouldReturnEmptyList() {
    	SubprogramChair chairWithNoReviewers = new SubprogramChair("Jack", myConference);
    	List<Reviewer> listToBeTested = chairWithNoReviewers.getReviewers();
        	
    	assertEquals("Reviewer list size should be equal to one from database",
    			listToBeTested.size(), Database.getReviewerListSize());
    	assertEquals("Reviewer list size should be 0",
    			listToBeTested.size(), 0);
    }


    /**
     * Test method for {@link SubprogramChair#getConferenceList()}
     * Tests if correct list of viewable conferences for the subprogram chair is returned
     * @author Ryan Tran
     */
    @Test
    public void getConferenceList_forListWithMoreThanOne_shouldReturnConferenceList() {
        List<Conference> conferenceListWithMoreThanOne = new ArrayList<Conference>();
        Conference tempConfA = new Conference("Science of memory", new Date(), new Date(), new ArrayList<Reviewer>());
        Conference tempConfB = new Conference("Research on machine learning", new Date(), new Date(), new ArrayList<Reviewer>());
        Conference tempConfC = new Conference("Genetic Algorithms", new Date(), new Date(), new ArrayList<Reviewer>());

        // initialize conference list to have multiple items
        conferenceListWithMoreThanOne.add(tempConfA);
        conferenceListWithMoreThanOne.add(tempConfB);
        conferenceListWithMoreThanOne.add(tempConfC);

        mySubprogramChair.setConferences(conferenceListWithMoreThanOne);

        assertEquals("Conference list should be returned with correct 3 items",
        		mySubprogramChair.getConferenceList(), conferenceListWithMoreThanOne);
    }

    /**
     * Test method for {@link SubprogramChair#getConferenceList()}
     * Tests list of conferences is correct when subprogram chair has 0 viewable conferences
     * @author Ryan Tran
     */
    @Test
    public void getConferenceList_forEmptyList_shouldReturnEmptyList() {
        List<Conference> testConferenceList = mySubprogramChair.getConferenceList();

        assertTrue("Conference list should be an instance of List<Conference>",
        			testConferenceList instanceof List<?>);

        assertEquals("Conference list should have a size of 0 when empty",
        			 testConferenceList.size(), 0);
    }

}