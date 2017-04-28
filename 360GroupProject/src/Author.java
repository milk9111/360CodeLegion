
import java.io.File;
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
	private int myManuscriptCount;
	
	private List<File> myReviewList;
	
	private Conference myConference;
	
	
	
	private Map<Conference,List<Manuscript>> myManuscriptList;
	
	public Author(String theName, List<Conference> theConferenceList, int theManusriptCount) {
		super(theName, theConferenceList);
		myManuscriptCount = theManusriptCount;
	}
	
	public void setManuscriptCount(int theManusriptCount) {
		myManuscriptCount = theManusriptCount;
	}
	
	public int getManuscriptCount() {
		return myManuscriptCount;
	}
	
	public void setReview(List<File> theReviewList) {
		myReviewList = theReviewList;
	}
	
	public List<File> getReview() {
		return myReviewList;
	}
	
	public void setConference(Conference theConference) {
		myConference = theConference;
	}
	
	public Conference getConference() {
		return myConference;
	}
	
	public void addManuscript(Conference theConference, List<Manuscript> theManuscript) {
		if (myManuscriptList.containsKey(theConference)) {
			
		}
		myManuscriptList.put(theConference, theManuscript);
	}
	
	public int getNumberOfManuscriptsSubmitted(Conference theConference) {
		return myManuscriptList.get(theConference).size();
	}

	@Override
	public List<Conference> getConfernceList() {
		
		return super.myConferenceList;
	}
	
}