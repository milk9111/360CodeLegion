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
        Date submissionDeadline = new Date();
        Date reviewDeadline = new Date();
        Conference tester = new Conference(submissionDeadline, reviewDeadline,
                );
        asserEquals(submissionDeadline, tester.getSubmissionDeadline);


        Date submissionDeadline2 = new Date();
        Date reviewDeadline2 = new Date();
        tester = new Conference(submissionDeadline2, reviewDeadline2,
                );
        assertEquals(submissionDeadline2, tester.getSubmissionDeadline);
    }

    @Test
    void testGetReviewDeadline() {

        Date submissionDeadline = new Date();
        Date reviewDeadline = new Date();
        Conference tester = new Conference(submissionDeadline, reviewDeadline,
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

        Date submissionDeadline = new Date();
        Date reviewDeadline = new Date();
        Conference tester = new Conference(submissionDeadline, reviewDeadline, new ArrayList<Reviewer>()
                );
        assertEquals(tester.getPastReviewers(), new ArrayList<Reviewer()>)
    }

    @Test
    void testForSubmitManuscriptAndGetManuscripts() {
        Conference tester = new Conference(Calendar.getTime(), )
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
        Manuscript testManuscript = new Manuscript();
        List<Manuscript> theManuscripts = new ArrayList<Manuscript>();
        theManuscripts.add(testManuscript);
        tester.submitManuscript(testManuscript);
        assertEquals(tester.getManuscripts, theManuscripts);

        Manuscript secondManuscript = new Manuscript();
        theManuscripts.add(secondManuscript);
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
