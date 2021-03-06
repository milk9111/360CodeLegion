package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @author Ryan Tran
 *
 */
public class ManuscriptDatabase implements Serializable {

	private TreeMap<UUID, Manuscript> myManuscriptList;

	/**
	 * serialized folder to prevent error from being thrown.
	 */
	private final String MANUSCRIPT_SERIALIZED_PATH = "/C:/serializedModel/";
	private final String MANUSCRIPT_FILE_PATHNAME = "manuscripts.ser";

	public void ManuscriptDatabase() {
		this.myManuscriptList = null;
	}

	public TreeMap<UUID, Manuscript> getAllManuscripts() {
		TreeMap<UUID, Manuscript> listToReturn = this.deserializeManuscriptList();
		return listToReturn;
	}
	
	/**
	 * This method will saved the passed in Manuscript to the Manuscript database.
	 * preconditions: Assumes that the Manuscript file already exists.
	 * @param theManuscript the Manuscript to save to the database
	 * @return The Manuscript if save is successful.
	 * else return null if failed
	 */
	public Manuscript saveManuscriptToDatabase(Manuscript theManuscript) {
		TreeMap<UUID, Manuscript> manuscriptList = deserializeManuscriptList();

		// pre conditions to check for uniqueness, limits, ect. are here
		manuscriptList.put(theManuscript.getMyID(), theManuscript);
		saveManuscriptListToDatabase(manuscriptList);

		return theManuscript;
	}
	
	
	/**
	 * Saves passed in Manuscript list to database 
	 * @param theManuscriptList the list to save to the database
	 */
	public void saveManuscriptListToDatabase(TreeMap<UUID, Manuscript> theManuscriptList) {
		// TODO: add better error handling to notify client of failure
	      try {
	         FileOutputStream fileOut = new FileOutputStream(MANUSCRIPT_SERIALIZED_PATH + MANUSCRIPT_FILE_PATHNAME);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(theManuscriptList);
	         out.close();
	         fileOut.close();
	         // System.out.printf("Serialized data is saved in " + Account_SERIALIZED_PATH + "accounts.ser");
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	}
	
	
	/**
	 * Deserialize the Manuscript list if it exists
	 * @return a TreeMap of the Manuscript list
	 */
	public TreeMap<UUID, Manuscript> deserializeManuscriptList() {
		TreeMap<UUID, Manuscript> manuscriptListToReturn = null;

		try {
			FileInputStream fileIn = new FileInputStream(MANUSCRIPT_SERIALIZED_PATH + MANUSCRIPT_FILE_PATHNAME);

			ObjectInputStream in = new ObjectInputStream(fileIn);
			manuscriptListToReturn = (TreeMap<UUID, Manuscript>) in.readObject();
			in.close();
			fileIn.close();

			// return nulls on error
		} catch(IOException i) {
			i.printStackTrace();
			return null;
		} catch(ClassNotFoundException c) {
			System.out.println("Manuscript List could not be found");
			c.printStackTrace();
			return null;
		}

		return manuscriptListToReturn;
	}
	
	/**
	 * Creates and serializes and empty treemap of manuscripts
	 */
	public void createEmptySerializedManuscriptList() {
		TreeMap<UUID, Manuscript> emptyManuscriptList = new TreeMap<UUID, Manuscript>();

		try {
			FileOutputStream fileOut = new FileOutputStream(MANUSCRIPT_SERIALIZED_PATH + MANUSCRIPT_FILE_PATHNAME);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(emptyManuscriptList);
			out.close();
			fileOut.close();
		}catch(IOException i) {
			i.printStackTrace();
		}
	}
	
	public ArrayList<Manuscript> getManuscriptsBelongingToAuthor(Author theAuthor) {
		ArrayList<Manuscript> returnListOfManuscripts = new ArrayList<Manuscript>();
		TreeMap<UUID, Manuscript> allManuscripts = this.getAllManuscripts();
		
		for (Manuscript manuToCompare : allManuscripts.values()) {
			if(manuToCompare.doesAuthorBelongToManuscript(theAuthor)) {
				returnListOfManuscripts.add(manuToCompare);
			}
		}
		
		return returnListOfManuscripts;
		
	}
	
	/**
	 * Returns a list of manuscripts belonging to the spc for a specific conference.
	 * returns null if no manuscripts in DB
	 * @param theSubChair
	 * @param theConference
	 * @return
	 */
	public ArrayList<Manuscript> getManuscriptsBelongingtoSubprogramChairForConference(SubprogramChair theSubChair, Conference theConference) {
		TreeMap<UUID, Manuscript> allManuscripts = this.getAllManuscripts();
		ArrayList<Manuscript> returnListOfManuscripts = new ArrayList<Manuscript>();
		
		if(allManuscripts.isEmpty()) {
			return null;
		}
		
		for(Manuscript aManu : allManuscripts.values()) {
			if(aManu.getConferenceID().equals(theConference.getMyID())) {
				returnListOfManuscripts.add(aManu);
			}
		}
		
		return returnListOfManuscripts;
	}

	/**
	 * This method will accept an account and save it to the serialized account list, updating
	 * the already existing account within the list.
	 * preconditions: Assumes the given account already exists within the list.
	 * @param theManuscript the updated Account object to be saved to the database
	 * @return An Account after it has been successfully saved to the database,
	 */
	public Manuscript updateAndSaveManuscriptToDatabase(Manuscript theManuscript) {
		if(myManuscriptList.containsKey(theManuscript.getMyID())) {
			TreeMap<UUID, Manuscript> conferenceList = deserializeManuscriptList();
			conferenceList.put(theManuscript.getMyID(), theManuscript);
			saveManuscriptListToDatabase(conferenceList);

			return conferenceList.get(theManuscript.getMyID());
		} else {
			return null;
		}
	}

}
