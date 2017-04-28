import java.util.List;

public abstract class User {
	public String myName;
	public List<Conference> myConferenceList;
	public User(String theName, List<Conference> theConferenceList) {
		myName = theName;
		myConferenceList = theConferenceList;
	}
	public void setConferences(List<Conference> theConferenceList) {
		myConferenceList = theConferenceList;
	}
	abstract public List<Conference> getConfernceList();
	public void setName(String theName) {
		myName = theName;
	}
	public String getName() {
		return myName;
	}
}
