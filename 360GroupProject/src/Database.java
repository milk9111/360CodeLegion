import java.util.ArrayList;
import java.util.List;

/**
 * This class will be used to query for system wide, model agnostic data.
 * In the future, once we establish an implementation of data persistency this model
 * will be refactored.
 */

/**
 * @author Ryan Tran
 * @version 1.0
 * @date 4/29/17
 */
public class Database {

	/**
	 * Global list of all available reviewers in the system
	 */
	static private List<Reviewer> myReviewers = new ArrayList<Reviewer>();
	
	/**
	 * This method will return the global list of all reviewers in the system
	 * @return list of all possible reviewers
	 */
	static public List<Reviewer> getReviewersList() {
		return myReviewers;
	}
	
}
