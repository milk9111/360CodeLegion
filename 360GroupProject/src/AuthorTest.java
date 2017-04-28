import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Fight
 *
 */
public class AuthorTest {

	private Author myAuthor;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myAuthor = new Author(0);
	}

	/**
	 * Test method for {@link Author#Author(int)}.
	 */
	@Test
	public void testAuthor() {
		assertNotNull("myAuthor was not instantiated!", myAuthor);
        assertEquals("myAuthor Manuscripts should be zero!", 0, myAuthor.getManuscriptCount());
	}

	/**
	 * Test method for {@link Author#setManuscriptCount(int)}.
	 */
	@Test
	public void testSetManuscriptCount() {
		myAuthor.setManuscriptCount(2);
		assertEquals("myAuthor Manuscripts should be two!", 2, myAuthor.getManuscriptCount());
	}

	/**
	 * Test method for {@link Author#setReview(java.io.File)}.
	 */
	@Test
	public void testSetReview() {
		File file = new File("./test.txt");
		myAuthor.setReview(file);
		assertEquals("myAuthor Review should be test.txt!", file, myAuthor.getReview());
	}

	/**
	 * Test method for {@link Author#setConference(Conference)}.
	 */
	@Test
	public void testSetConference() {
		//fail("Not yet implemented");
	}

}
