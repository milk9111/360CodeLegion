
import java.io.File;

/**
 * This class is representing an Author.
 * 
 * @author Casey Anderson
 * @version 1 
 *
 */
public class Author {
	private int myManuscriptCount;
	
	private File myReview;
	
	private Conference myConference;
	
	public Author(int theManusriptCount) {
		myManuscriptCount = theManusriptCount;
	}
	
	public void setManuscriptCount(int theManusriptCount) {
		myManuscriptCount = theManusriptCount;
	}
	
	public int getManuscriptCount() {
		return myManuscriptCount;
	}
	
	public void setReview(int theReview) {
		myReview = theReview;
	}
	
	public void getReview() {
		return myReview;
	}
	
	public void setConference(Conference theConference) {
		myConference = theReview;
	}
	
	public void getConference() {
		return myConference;
	}
}