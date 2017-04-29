import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Josiah on 4/27/2017.
 */
public class Conference {

    private List<Manuscript> myManuscripts;
    private Date mySubmissionDeadline;
    private Date myReviewDeadline;
    private List<Reviewer> myPastReviewers;

    public Conference(Date theSubmissionDeadline, Date theReviewDeadline,
                      List<Reviewer> thePastReviewers) {
        this.myManuscripts = new ArrayList<Manuscript>();
        this.myPastReviewers = thePastReviewers;
        this.myReviewDeadline = theReviewDeadline;
        this.mySubmissionDeadline = theSubmissionDeadline;
    }

    public List<Manuscript> getManuscripts() {
        return myManuscripts;
    }

    public Date getSubmissionDeadline() {
        return mySubmissionDeadline;
    }

    public Date getReviewDeadline() {
        return myReviewDeadline;
    }

    public List<Reviewer> getPastReviewers() {
        return myPastReviewers;
    }

    public void submitManuscript(Manuscript theManuscript) {
        if(isWithinSubmissionDeadline()) {
            myManuscripts.add(theManuscript);
        } else{
            System.out.println("You're past the date bro.");
        }
    }

    private boolean isWithinSubmissionDeadline(){
        return mySubmissionDeadline.after(Calendar.getInstance().getTime());
    }

    private boolean isWithinReviewDeadline(){
        return myReviewDeadline.after(Calendar.getInstance().getTime());
    }
}
