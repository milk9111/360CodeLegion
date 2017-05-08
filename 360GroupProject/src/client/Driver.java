package client;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import model.Account;
import model.AccountDatabase;
import model.Author;
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
		
		Conference confPastSubmissionDate = new Conference("Conference of Days Gone Past", new Date("4/9/2017"), new Date("4/9/2017"), reviewers1);
		
		Account author1 = new Account("busrule_1b");
		author1.addAuthorRoleToAccount(new Author("Kal-El", conference1));
		Account author2 = new Account("Mr. Mackey");
		author2.addAuthorRoleToAccount(new Author("Mr. Mackey", conference2));
		
		ArrayList<UUID> authorList1 = new ArrayList<UUID>();
		authorList1.add(author1.getMyAuthor().getMyID());
		
		ArrayList<UUID> authorList2 = new ArrayList<UUID>();
		authorList2.add(author2.getMyAuthor().getMyID());
		
		Manuscript manu1 = new Manuscript("Is Krypton Really Dead?", new Date(), authorList1, conference1);
		Manuscript manu2 = new Manuscript("The Effects of Cartoon Violence on Adolescent Pitbulls", new Date(), authorList2, conference2);
		
		AccountDatabase adb = new AccountDatabase();
		ManuscriptDatabase mdb = new ManuscriptDatabase();
		ConferenceDatabase cdb = new ConferenceDatabase();
		
		//Need to sign into one of the these accounts in order to present subprogram chair.
		Account acc1 = new Account("tester1");
		Account acc2 = new Account("tester2");
		
		acc1.addSubprogramChairRoleToAccount(new SubprogramChair(conference1));
		acc2.addSubprogramChairRoleToAccount(new SubprogramChair(conference2));
		
		acc1.getMySubprogramChair().assignManuscriptToSubChair(manu1);
		acc2.getMySubprogramChair().assignManuscriptToSubChair(manu2);
		
		adb.saveNewAccountToDatabase(acc1);
		adb.saveNewAccountToDatabase(acc2);
		adb.saveNewAccountToDatabase(author1);
		adb.saveNewAccountToDatabase(author2);
		
		cdb.saveConferenceToDatabase(conference1);
		cdb.saveConferenceToDatabase(conference2);
		cdb.saveConferenceToDatabase(confPastSubmissionDate);
		
		mdb.saveManuscriptToDatabase(manu1);
		mdb.saveManuscriptToDatabase(manu2);
		
		
		fsm.setConference(conference2);
		fsm.setConference(conference1);
		//System.out.println(reviewers1);
		//System.out.println(reviewers2);
		
		// BusinessRule 1B: An author is limited to 5 manuscript submissions as author or co-author per conference
		
		// using same conference for all rows of bus rule
		Conference conf_1b = 
				new Conference("Conference of business rule 1b", new Date("5/9/2017"), new Date("7/15/2018"));
		
		cdb.saveConferenceToDatabase(conf_1b);


		// bus rule accounts
		Account busRule_1b_1 = new Account("busRule1b_1");
		adb.saveNewAccountToDatabase(busRule_1b_1);
		busRule_1b_1.addAuthorRoleToAccount(new Author("busRule1b_1", conf_1b));
		Account busRule_1b_2 = new Account("busRule1b_2");
		adb.saveNewAccountToDatabase(busRule_1b_2);
		Account busRule_1b_3 = new Account("busRule1b_3");
		adb.saveNewAccountToDatabase(busRule_1b_3);
		Account busRule_1b_4 = new Account("busRule1b_4");
		adb.saveNewAccountToDatabase(busRule_1b_4);
		Account busRule_1b_5 = new Account("busRule1b_5");
		adb.saveNewAccountToDatabase(busRule_1b_5);
		Account busRule_1b_6 = new Account("busRule1b_6");
		adb.saveNewAccountToDatabase(busRule_1b_6);
		
		// init and save author roles
		busRule_1b_2.addAuthorRoleToAccount(new Author(conf_1b));
		busRule_1b_3.addAuthorRoleToAccount(new Author(conf_1b));
		busRule_1b_4.addAuthorRoleToAccount(new Author(conf_1b));
		busRule_1b_5.addAuthorRoleToAccount(new Author(conf_1b));
		busRule_1b_6.addAuthorRoleToAccount(new Author(conf_1b));
		
		// list of authors
		ArrayList<UUID> authList_busRule_1b = new ArrayList<UUID>();
		authList_busRule_1b.add(busRule_1b_1.getMyID());
		authList_busRule_1b.add(busRule_1b_2.getMyID());
		authList_busRule_1b.add(busRule_1b_3.getMyID());

		Manuscript manu_1b_1 = new Manuscript("Is Krypton Really Hope?", new Date(), authorList1, conf_1b);
		mdb.saveManuscriptToDatabase(manu_1b_1);
		Manuscript manu_1b_2 = new Manuscript("Is Red Really Dead?", new Date(), authorList1, conf_1b);
		mdb.saveManuscriptToDatabase(manu_1b_2);
		Manuscript manu_1b_3 = new Manuscript("Is Green Really Shed?", new Date(), authorList1, conf_1b);
		mdb.saveManuscriptToDatabase(manu_1b_3);
//		Manuscript manu_1b_4 = new Manuscript("Is Mead Really Kead?", new Date(), authorList1, conf_1b);
//		mdb.saveManuscriptToDatabase(manu_1b_4);

		// bus rule 1B row 1:
		
		try {
			busRule_1b_1.getMyAuthor().addManuscript(conf_1b, manu_1b_1);
			conf_1b.submitManuscript(manu_1b_1);
			busRule_1b_1.getMyAuthor().addManuscript(conf_1b, manu_1b_2);
			conf_1b.submitManuscript(manu_1b_2);
			busRule_1b_1.getMyAuthor().addManuscript(conf_1b, manu_1b_3);
			conf_1b.submitManuscript(manu_1b_3);
//			busRule_1b_1.getMyAuthor().addManuscript(conf_1b, manu_1b_4);
//			conf_1b.submitManuscript(manu_1b_4);
			adb.updateAndSaveAccountToDatabase(busRule_1b_1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(Account theAcct : adb.getAllAccounts().values()) {
			//System.out.println(theAcct.getMyUsername());
			if(theAcct.getMyAuthor() != null) {
				//System.out.println(theAcct.getMyAuthor().getNumberOfManuscriptsSubmitted(conf_1b));
			}
		}
		
		fsm.startProgram ();
	}

}
