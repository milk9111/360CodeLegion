import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ryan Tran.
 * @version 1.0
 * @date 4/28/17
 */
public class SubprogramChairTest {
    
	// fixtures
	private List<Reviewer> myReviewerList;
	private SubprogramChair mySubprogramChair;

    /**
     * Initialize the test fixture before each test.
     */
    @Before
    public void setUp() {
    	myReviewerList = new List<Reviewer>();
        mySubprogramChair = new SubprogramChair("Marauder");
    }


    /**
     * Test method for SubprogramChair constructor
     */
    @Test
    public void testConstructor() {
        String testUsername = "Scion";
        SubprogramChair simpleChairObj = new SubprogramChair(testUsername);

        AssertEquals(simpleChairObj.getName(), testUsername);
    }

    /**
     * Test method for SubprogramChair#getConferenceList
     */
    @Test
    public void testGetConferenceList() {
        List<Conference> conferenceListWithMoreThanOne = new ArrayList<Conference>();
        Conference tempConfA = new Conference(new Date(), new Date(), new List<Reviewer>());
        Conference tempConfB = new Conference(new Date(), new Date(), new List<Reviewer>());
        Conference tempConfC = new Conference(new Date(), new Date(), new List<Reviewer>());

        // initialize conference list to have multiple items
        conferenceListWithMoreThanOne.add(tempConfA);
        conferenceListWithMoreThanOne.add(tempConfB);
        conferenceListWithMoreThanOne.add(tempConfC);

        mySubprogramChair.setConferences(conferenceListWithMoreThanOne);

        AssertEquals(mySubprogramChair.getConfernceList(), conferenceListWithMoreThanOne);
    }
    /**
     * Test method for SubprogramChair#getReviewers()
     */
    @Test
    public void testGetReviewers() {}


}
