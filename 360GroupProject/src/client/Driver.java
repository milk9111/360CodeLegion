package client;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import model.Account;
import model.AccountDatabase;
import model.Conference;
import model.ConferenceDatabase;
import model.Manuscript;
import model.ManuscriptDatabase;
import model.Reviewer;
import model.SubprogramChair;

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
		
		
		Manuscript manu1 = new Manuscript("Is Krypton Really Dead?", new Date(), null, conference1);
		Manuscript manu2 = new Manuscript("The Effects of Cartoon Violence on Adolescents", new Date(), null, conference2);
		
		AccountDatabase adb = new AccountDatabase();
		ManuscriptDatabase mdb = new ManuscriptDatabase();
		ConferenceDatabase cdb = new ConferenceDatabase();
		
		Account acc1 = new Account("tester1");
		//System.out.println(acc1.getMyID());
		Account acc2 = new Account("tester2");
		
		acc1.addSubprogramChairRoleToAccount(new SubprogramChair(conference1));
		acc2.addSubprogramChairRoleToAccount(new SubprogramChair(conference2));
		
		acc1.getMySubprogramChair().assignManuscriptToSubChair(manu1);
		acc2.getMySubprogramChair().assignManuscriptToSubChair(manu2);
		
		adb.saveNewAccountToDatabase(acc1);
		adb.saveNewAccountToDatabase(acc2);
		
		cdb.saveConferenceToDatabase(conference1);
		cdb.saveConferenceToDatabase(conference2);
		
		mdb.saveManuscriptToDatabase(manu1);
		mdb.saveManuscriptToDatabase(manu2);
		
		
		fsm.setConference(conference2);
		fsm.setConference(conference1);
		//System.out.println(reviewers1);
		//System.out.println(reviewers2);

		
		fsm.startProgram ();
	}

}
