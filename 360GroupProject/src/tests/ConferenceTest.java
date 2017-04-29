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
        assertEquals(DateUtils.truncate(submissionDeadline, Calendar.DAY), DateUtils.truncate(tester.getSubmissionDeadline(), Calendar.DAY));


        Date submissionDeadline2 = new Date();
        Date reviewDeadline2 = new Date();
        tester = new Conference(submissionDeadline2, reviewDeadline2, new List<Reviewer>()
        );
        assertEquals(DateUtils.truncate(submissionDeadline2, Calendar.DAY), DateUtils.truncate(tester.getSubmissionDeadline()), Calendar.DAY);
    }

    @Test
    void testGetReviewDeadline() {

        Date submissionDeadline = new Date();
        Date reviewDeadline = new Date();
        Conference tester = new Conference(submissionDeadline, reviewDeadline, new List<Reviewer>()
        );

        assertEquals(DateUtils.truncate(reviewDeadline, Calendar.DAY), DateUtils.truncate(tester.getReviewDeadline, Calendar.DAY);

        Date submissionDeadline = new Date();
        Date reviewDeadline2 = new Date();
        tester = new Conference(submissionDeadline, reviewDeadline2,
                );
        assertEquals(DateUtils.truncate(reviewDeadline2, Calendar.DAY), DateUtils.truncate(tester.getReviewDeadline, Calendar.DAY);

    }

    @Test
    void getPastReviewers() {

        Date submissionDeadline = Calendar.getInstance().getTime();
        Date reviewDeadline = Calendar.getInstance().getTime();
        Conference tester = new Conference(submissionDeadline, reviewDeadline, new ArrayList<Reviewer>());
        assertEquals(tester.getPastReviewers(), new ArrayList<Reviewer>())
    }

    @Test
    void testForSubmitManuscriptAndGetManuscripts() {
        Conference tester = new Conference(Calendar.getInstance().addDays(CalendarDate, 1).getTime(), Calendar.getInstance().getTime(),
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
        Conference tester = new Conference(Calendar.getInstance().addDays(CalendarDate, -1).getTime(), Calendar.getInstance().getTime(),
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
