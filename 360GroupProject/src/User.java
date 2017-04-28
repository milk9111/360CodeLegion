import java.util.List;

public abstract class User {
	public String myName;
	public List<Conference> myConferences;
	public User(String theName, List<Conference> theConferences) {
		myName = theName;
		myConferences = theConferences;
	}
	public void setConferences(List<Conference> theConferences) {
		myConferences = theConferences;
	}
	public List<Conference> getConfernces() {
		return myConferences;
	}
	public void setName(String theName) {
		myName = theName;
	}
	public String getName() {
		return myName;
	}
}
