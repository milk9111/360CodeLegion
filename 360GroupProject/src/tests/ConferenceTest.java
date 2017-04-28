import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * Created by Josiah on 4/27/2017.
 */
public class ConferenceTest {

    private Conference tester;

    @Test
    void getManuscripts() {

    }

    @Test
    void getSubmissionDeadline() {

    }

    @Test
    void getReviewDeadline() {

    }

    @Test
    void getPastReviewers() {

    }

    @Test
    void submitManuscript() {

    }


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Date submissionDeadline = new Date();
        Date reviewDeadline = new Date();
        tester = new Conference(submissionDeadline, reviewDeadline,
                );
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
