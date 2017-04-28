//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.*;

/**
 * Created by Josiah on 4/27/2017.
 */
public class ConferenceTest {

    @Test
    void testGetSubmissionDeadline() {
        Date submissionDeadline = new Date().;
        Date reviewDeadline = new Date();
        Conference tester = new Conference(submissionDeadline, reviewDeadline, new List<Reviewer>());
        asserEquals(submissionDeadline, tester.getSubmissionDeadline);


        Date submissionDeadline2 = new Date();
        Date reviewDeadline2 = new Date();
        tester = new Conference(submissionDeadline2, reviewDeadline2, new List<Reviewer>()
        );
        assertEquals(submissionDeadline2, tester.getSubmissionDeadline);
    }

    @Test
    void testGetReviewDeadline() {

        Date submissionDeadline = new Date();
        Date reviewDeadline = new Date();
        Conference tester = new Conference(submissionDeadline, reviewDeadline, new List<Reviewer>()
        );
        assertEquals(reviewDeadline, tester.getReviewDeadline);

        Date submissionDeadline = new Date();
        Date reviewDeadline2 = new Date();
        tester = new Conference(submissionDeadline, reviewDeadline2,
                );
        assertEquals(reviewDeadline2, tester.getReviewDeadline);

    }

    @Test
    void getPastReviewers() {

        Date submissionDeadline = Calendar.getTime();
        Date reviewDeadline = Calendar.getTime();
        Conference tester = new Conference(submissionDeadline, reviewDeadline, new ArrayList<Reviewer>());
        assertEquals(tester.getPastReviewers(), new ArrayList<Reviewer() >)
    }

    @Test
    void testForSubmitManuscriptAndGetManuscripts() {
        Conference tester = new Conference(Calendar.getInstance.addDays(CalendarDate, 1).getTime(), Calendar.getTime(),
                new List<Reviewer>());
        Manuscript testManuscript = new Manuscript();
        List<Manuscript> theManuscripts = new ArrayList<Manuscript>();
        theManuscripts.add(testManuscript);
        tester.submitManuscript(testManuscript);
        assertEquals(tester.getManuscripts, theManuscripts);

        Manuscript secondManuscript = new Manuscript();
        theManuscripts.add(secondManuscript);
        assertEquals(tester.getManuscripts, theManuscripts);
    }

    @Test
    void testForSubmitManuscriptDeadlineVerification() {
        Conference tester = new Conference(Calendar.getInstance.addDays(CalendarDate, -1).getTime(), Calendar.getTime(),
                new List<Reviewer>());
        Manuscript testManuscript = new Manuscript();
        List<Manuscript> theManuscripts = new ArrayList<Manuscript>();
        tester.submitManuscript(testManuscript);
        assertEquals(tester.getManuscripts, theManuscripts);

        Manuscript secondManuscript = new Manuscript();
        assertEquals(tester.getManuscripts, theManuscripts);
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
