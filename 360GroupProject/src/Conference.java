import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Josiah on 4/27/2017.
 */
public class Conference {

    private List<Manuscript> myManuscripts;
    private Date mySubmissionDeadline;
    private Date myReviewDeadline;
    private List<Reviewer> myPastReviewers;

    public Conference(Date theSubmissionDeadline, Date theReviewDeadline,
                        List<Reviewer> thePastReviewers){
        this.myManuscripts = new ArrayList<Manuscript>();
        this.myPastReviewers = thePastReviewers;
        this.myReviewDeadline = theReviewDeadline;
        this.mySubmissionDeadline = theSubmissionDeadline;
    }

    public List<Manuscript> getManuscripts(){
        return myManuscripts;
    }

    public Date getSubmissionDeadline(){
        return mySubmissionDeadline;
    }

    public Date getReviewDeadline(){
        return myReviewDeadline;
    }

    public List<Reviewer> getPastReviewers() {
        return myPastReviewers;
    }

    public void submitManuscript(Manuscript theManuscript){
        myManuscripts.add(theManuscript);
    }
}
