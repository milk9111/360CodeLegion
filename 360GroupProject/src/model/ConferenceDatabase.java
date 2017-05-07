package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @author Ryan Tran
 *
 */
public class ConferenceDatabase {

	private TreeMap<UUID, Conference> myConferenceList;

	private final String CONFERENCE_SERIALIZED_PATH = "360GroupProject/src/model/serializedModel/";
	private final String CONFERENCE_FILE_PATHNAME = "conferences.ser";

	public void ConferenceDatabase() {
		this.myConferenceList = null;
	}

	public TreeMap<UUID, Conference> getAllConferences() {
		TreeMap<UUID, Conference> listToReturn = this.deserializeConferenceList();
		return listToReturn;
	}


	/**
	 * This method will saved the passed in Conference to the conference database.
	 * preconditions: Assumes that the conference file already exists.
	 * @param theConference the conference to save to the database
	 * @return The conference if save is successful.
	 * else return null if failed
	 */
	public Conference saveConferenceToDatabase(Conference theConference) {
		TreeMap<UUID, Conference> conferenceList = deserializeConferenceList();

		boolean isConferenceUnique = isConferenceInListUnique(conferenceList, theConference);
		if(isConferenceUnique) {
			conferenceList.put(theConference.getMyID(), theConference);
			saveConferenceListToDatabase(conferenceList);

		} else {
			System.out.println("Username is invalid");
		}

		return null;
	}

	/**
	 * This method will accept an account and save it to the serialized account list, updating
	 * the already existing account within the list.
	 * preconditions: Assumes the given account already exists within the list.
	 * @param theAccount the updated Account object to be saved to the database
	 * @return An Account after it has been successfully saved to the database,
	 */
	public Conference updateAndSaveConferenceToDatabase(Conference theConference) {
		if(myConferenceList.containsKey(theConference.getMyID())) {
			TreeMap<UUID, Conference> conferenceList = deserializeConferenceList();
			conferenceList.put(theConference.getMyID(), theConference);
			saveConferenceListToDatabase(conferenceList);

			return conferenceList.get(theConference.getMyID());
		} else {
			return null;
		}
	}
	
	/**
	 * Saves passed in Conference list to database 
	 * @param theConferenceList the list to save to the database
	 */
	public void saveConferenceListToDatabase(TreeMap<UUID, Conference> theConferenceList) {
		// TODO: add better error handling to notify client of failure
	      try {
	         FileOutputStream fileOut = new FileOutputStream(CONFERENCE_SERIALIZED_PATH + CONFERENCE_FILE_PATHNAME);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(theConferenceList);
	         out.close();
	         fileOut.close();
	         // System.out.printf("Serialized data is saved in " + Account_SERIALIZED_PATH + "accounts.ser");
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	}
	
	
	/**
	 * Checks the given conference against the given list to see if it's conference name is unique
	 * preconditions: conference list is not-null, conference is not-null and has a name
	 * @param theConfList the conference list to check if the given conference is unique against
	 * @param theConference The conference who's name we are checking for uniqueness
	 * @return true of conference name is unique relative to list, false otherwise
	 */
	public boolean isConferenceInListUnique(TreeMap<UUID, Conference> theConfList, Conference theConference) {
		boolean conferenceIsUnique = true;

		for (Conference aConfToCompare : theConfList.values()) {
			if (theConference.getMyName().equals(aConfToCompare.getMyName())) {
				conferenceIsUnique = false;
			}
		}

		return conferenceIsUnique;
	}

	/**
	 * Deserialize the conference list if it exists
	 * @return a TreeMap of the conference list
	 */
	public TreeMap<UUID, Conference> deserializeConferenceList() {
		TreeMap<UUID, Conference> conferenceListToReturn = null;

		try {
			FileInputStream fileIn = new FileInputStream(CONFERENCE_SERIALIZED_PATH + CONFERENCE_FILE_PATHNAME);

			ObjectInputStream in = new ObjectInputStream(fileIn);
			conferenceListToReturn = (TreeMap<UUID, Conference>) in.readObject();
			in.close();
			fileIn.close();

			// return nulls on error
		} catch(IOException i) {
			i.printStackTrace();
			return null;
		} catch(ClassNotFoundException c) {
			System.out.println("Conference List could not be found");
			c.printStackTrace();
			return null;
		}

		return conferenceListToReturn;
	}

	public void createEmptySerializedConferenceList() {
		TreeMap<UUID, Conference> emptyConferenceList = new TreeMap<UUID, Conference>();

		try {
			FileOutputStream fileOut = new FileOutputStream(CONFERENCE_SERIALIZED_PATH + CONFERENCE_FILE_PATHNAME);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(emptyConferenceList);
			out.close();
			fileOut.close();
		}catch(IOException i) {
			i.printStackTrace();
		}
	}



}
