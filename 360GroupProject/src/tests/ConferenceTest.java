package tests;//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;


import org.junit.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Josiah on 4/27/2017.
 */
public class ConferenceTest {

    @Test
    void testGetSubmissionDeadline() {
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

    @Test
    void testGetReviewDeadline() {

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

    @Test
    void getPastReviewers() {

        Date submissionDeadline = Calendar.getInstance().getTime();
        Date reviewDeadline = Calendar.getInstance().getTime();
        Conference tester = new Conference(submissionDeadline, reviewDeadline, new ArrayList<Reviewer>());
        assertEquals(tester.getPastReviewers(), new ArrayList<Reviewer>());
    }


    @Test
    void testForSubmitManuscriptAndGetManuscripts() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        Conference tester = new Conference(c.getTime(), Calendar.getInstance().getTime(),
                new ArrayList<Reviewer>());
        Manuscript testManuscript = new Manuscript();
        List<Manuscript> theManuscripts = new ArrayList<Manuscript>();
        theManuscripts.add(testManuscript);
        tester.submitManuscript(testManuscript);
        assertEquals(tester.getManuscripts(), theManuscripts);

        Manuscript secondManuscript = new Manuscript();
        theManuscripts.add(secondManuscript);
        assertEquals(tester.getManuscripts(), theManuscripts);
    }

    @Test
    void testForSubmitManuscriptDeadlineVerification() {
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

}

/*
    Fields
		Manuscripts
		Submission Deadline
		Review Deadline
		Past Reviewers
	Methods
		getManuscript()
		getSubmissionDeadline()
		getReviewDeadline()
		getPastReviewers()
		submitManuscript()
		getManuscriptList()
		getReviewer()
 */
