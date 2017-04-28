
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is representing an Author.
 * 
 * @author Casey Anderson
 * @version 1 
 *
 */
public class Author extends User{
	
	private List<File> myReviewList;
	
	private Map<Conference,List<Manuscript>> myManuscriptList;
	
	public Author(String theName, List<Conference> theConferenceList) {
		super(theName, theConferenceList);
	}
	
	public void setReview(List<File> theReviewList) {
		myReviewList = theReviewList;
	}
	
	public Manuscript createManuscript(String theTitle, Date theSubmittedDate, HashMap<Reviewer, String> theReviews, ArrayList<Author> theAuthors) {
		return new Manuscript(theTitle, theSubmittedDate, theReviews, theAuthors) ;
	}
	
	public List<File> getReview() {
		return myReviewList;
	}
	
	public void addManuscript(Conference theConference, Manuscript theManuscript) {
		if (getNumberOfManuscriptsSubmitted(theConference) <= 5) {
			if (myManuscriptList.containsKey(theConference)) {
				myManuscriptList.get(theConference).add(theManuscript);
			} else {
				List<Manuscript> ManuscriptList = new ArrayList<Manuscript>();
				ManuscriptList.add(theManuscript);
				myManuscriptList.put(theConference, ManuscriptList);
			}
		} else {
			System.out.println("Already have 5 Manuscript submitted!");
		}
	}
	
	public int getNumberOfManuscriptsSubmitted(Conference theConference) {
		return myManuscriptList.get(theConference).size();
	}

	@Override
	public List<Conference> getConfernceList() {
		
		return super.myConferenceList;
	}
	
}