package client;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

import model.Conference;
import model.Reviewer;

public class Driver {

	public static void main(String[] args) {
		Controller fsm = new Controller ();
		UI consoleDisplay = new UI ();
		
		fsm.addObserver(consoleDisplay);
		consoleDisplay.addObserver(fsm);
		
		ArrayList<Reviewer> reviewers2 = new ArrayList<Reviewer>();
		reviewers2.add(new Reviewer("Brock Samson", null));
		reviewers2.add(new Reviewer("Mr. Meeseeks", null));
		reviewers2.add(new Reviewer("Bob Belcher", null));
		reviewers2.add(new Reviewer("Kyle Broflovski", null));
		Conference conference2 = new Conference("Annual Random Cartoon Characters Conference", new Date("5/9/2017"), new Date("7/15/2018"), reviewers2);
		
		ArrayList<Reviewer> reviewers1 = new ArrayList<Reviewer>();
		reviewers1.add(new Reviewer("Johnny Storm", null));
		reviewers1.add(new Reviewer("Reed Richards", null));
		reviewers1.add(new Reviewer("Susan Storm", null));
		reviewers1.add(new Reviewer("Ben Grimm", null));
		Conference conference1 = new Conference("National Heroes Without Borders Conference", new Date("5/9/2017"), new Date("5/9/2017"), reviewers1);
		
		
		fsm.setConference(conference2);
		fsm.setConference(conference1);
		//System.out.println(reviewers1);
		//System.out.println(reviewers2);

		
		fsm.startProgram ();
	}

}
