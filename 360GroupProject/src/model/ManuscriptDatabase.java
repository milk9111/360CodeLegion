package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @author Ryan Tran
 *
 */
public class ManuscriptDatabase {

	private TreeMap<UUID, Manuscript> myManuscriptList;

	private final String MANUSCRIPT_SERIALIZED_PATH = "360GroupProject/src/model/serializedModel/";
	private final String MANUSCRIPT_FILE_PATHNAME = "manuscripts.ser";

	public void ManuscriptDatabase() {
		this.myManuscriptList = null;
	}

	public TreeMap<UUID, Manuscript> getAllManuscripts() {
		TreeMap<UUID, Manuscript> listToReturn = this.deserializeManuscriptList();
		return listToReturn;
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

}
