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

	public void AccountDatabase() {
		this.myConferenceList = null;
	}

	public TreeMap<UUID, Conference> getAllConferences() {
		TreeMap<UUID, Conference> listToReturn = null;

		return null;
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
