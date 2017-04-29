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

        assertEquals(simpleChairObj.getName(), testUsername);
    }
    @Test
    public void testGetMyAssignedConferences() {

    }

    @Test
    public void testGetReviewers() throws Exception {
    }

    @Test
    public void testGetConfernceList() {
        List<Conference> conferenceListWithMoreThanOne = new ArrayList<Conference>();
        Conference tempConfA = new Conference(new Date(), new Date(), new List<Reviewer>());
        Conference tempConfB = new Conference(new Date(), new Date(), new List<Reviewer>());
        Conference tempConfC = new Conference(new Date(), new Date(), new List<Reviewer>());

        // initialize conference list to have multiple items
        conferenceListWithMoreThanOne.add(tempConfA);
        conferenceListWithMoreThanOne.add(tempConfB);
        conferenceListWithMoreThanOne.add(tempConfC);

        mySubprogramChair.setConferences(conferenceListWithMoreThanOne);

        assertEquals(mySubprogramChair.getConferenceList(), conferenceListWithMoreThanOne);
    }

}