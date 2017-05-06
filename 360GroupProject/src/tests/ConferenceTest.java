//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;


import org.junit.*;

import model.Conference;
import model.Manuscript;
import model.Reviewer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Josiah on 4/27/2017.
 * This class is for testing the Conference class
 */
public class ConferenceTest {

    /**
     * @author Josiah Hopkins
     * Test to verify that get submission date works properly
     */
    @Test
    public void testGetSubmissionDeadline_CorrectValues() {
        Date submissionDeadline = new Date();
        Date reviewDeadline = new Date();
        Conference tester = new Conference(submissionDeadline, reviewDeadline, new ArrayList<Reviewer>());
        assertEquals(submissionDeadline, tester.getSubmissionDeadline());


        Date submissionDeadline2 = new Date();
        Date reviewDeadline2 = new Date();
        tester = new Conference(submissionDeadline2, reviewDeadline2, new ArrayList<Reviewer>()
        );
        assertEquals(submissionDeadline2, tester.getSubmissionDeadline());
    }

    /**
     * @author Josiah Hopkins
     * Test to verify that get review deadline works properly
     */
    @Test
    public void testGetReviewDeadline_CorrectValues() {

        Date submissionDeadline = new Date();
        Date reviewDeadline = new Date();
        Conference tester = new Conference(submissionDeadline, reviewDeadline, new ArrayList<Reviewer>()
        );

        assertEquals(reviewDeadline, tester.getReviewDeadline());

        Date submissionDeadline2 = new Date();
        Date reviewDeadline2 = new Date();
        tester = new Conference(submissionDeadline2, reviewDeadline2, new ArrayList<Reviewer>());
        assertEquals(reviewDeadline2, tester.getReviewDeadline());

    }

    /**
     * @author Josiah Hopkins
     * Tests to make sure the get past reviewers works properly
     */
    @Test
    public void getPastReviewers_CorrectBlankList() {

        Date submissionDeadline = Calendar.getInstance().getTime();
        Date reviewDeadline = Calendar.getInstance().getTime();
        Conference tester = new Conference(submissionDeadline, reviewDeadline, new ArrayList<Reviewer>());
        assertEquals(tester.getPastReviewers(), new ArrayList<Reviewer>());
    }


    /**
     * @author Josiah Hopkins
     * Tests submit and get manuscript in valid cases.
     */
    @Test
    public void testForSubmitManuscriptAndGetManuscripts_CorrectValuesJustInTime() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        Conference tester = new Conference(c.getTime(), Calendar.getInstance().getTime(),
                new ArrayList<Reviewer>());
        Manuscript testManuscript = new Manuscript();
        List<Manuscript> theManuscripts = new ArrayList<Manuscript>();
        theManuscripts.add(testManuscript);
        tester.submitManuscript(testManuscript);
        assertEquals("First failure", tester.getManuscripts(), theManuscripts);

        Manuscript secondManuscript = new Manuscript();
        theManuscripts.add(secondManuscript);
        tester.submitManuscript(secondManuscript);
        assertTrue("Second failure", tester.getManuscripts().containsAll(theManuscripts));
    }

    /**
     * @author Josiah Hopkins
     * Tests submit and get manuscript in valid cases just before the deadline.
     */
    @Test
    public void testForSubmitManuscriptAndGetManuscripts_CorrectValuesWayBeforeDueDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 100);
        Conference tester = new Conference(c.getTime(), Calendar.getInstance().getTime(),
                new ArrayList<Reviewer>());
        Manuscript testManuscript = new Manuscript();
        List<Manuscript> theManuscripts = new ArrayList<Manuscript>();
        theManuscripts.add(testManuscript);
        tester.submitManuscript(testManuscript);
        assertEquals("First failure", tester.getManuscripts(), theManuscripts);

        Manuscript secondManuscript = new Manuscript();
        theManuscripts.add(secondManuscript);
        tester.submitManuscript(secondManuscript);
        assertTrue("Second failure", tester.getManuscripts().containsAll(theManuscripts));
    }

    /**
     * @author Josiah Hopkins
     * Tests to verify that when submitting a manuscript after the deadline it doesn't work.
     * This test is for business rule 1.b
     */
    @Test
    public void testForSubmitManuscript_InvalidJustAfterDeadline() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        Conference tester = new Conference(c.getTime(), Calendar.getInstance().getTime(),
                new ArrayList<Reviewer>());
        Manuscript testManuscript = new Manuscript();
        List<Manuscript> theManuscripts = new ArrayList<Manuscript>();
        tester.submitManuscript(testManuscript);
        assertEquals(tester.getManuscripts(), theManuscripts);

        Manuscript secondManuscript = new Manuscript();
        assertEquals(tester.getManuscripts(), theManuscripts);
    }


    /**
     * @author Josiah Hopkins
     * Tests to verify that when submitting a manuscript after the deadline it doesn't work.
     * This test is for business rule 1.b
     */
    @Test
    public void testForSubmitManuscript_InvalidFarAfterDeadline() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -100);
        Conference tester = new Conference(c.getTime(), Calendar.getInstance().getTime(),
                new ArrayList<Reviewer>());
        Manuscript testManuscript = new Manuscript();
        List<Manuscript> theManuscripts = new ArrayList<Manuscript>();
        tester.submitManuscript(testManuscript);
        assertEquals(tester.getManuscripts(), theManuscripts);

        Manuscript secondManuscript = new Manuscript();
        assertEquals(tester.getManuscripts(), theManuscripts);
    }

}
