
public class UI {
	private int myState;
	private String userType;
	public void changeState() {
			
		if (Controller.theState < 10) {
			setUserType("Author");
			displayHeader();
		} else {
			setUserType("SubProgramChair");
			displayHeader();
		}
	}
	
	private void setUserType(String theUserType) {
		userType = theUserType;
	}
	private void displayHeader() {
		System.out.println();
		
	}
	
	private void AuthorView() {
		
	}

}
