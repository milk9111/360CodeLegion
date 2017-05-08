import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses(value = {AccountDatabaseTest.class, AccountTest.class, AuthorTest.class, 
		ConferenceDatabaseTest.class, ConferenceTest.class, ControllerTest.class, 
		ManuscriptDatabaseTest.class, ManuscriptTest.class, ReviewerTest.class, 
		SubprogramChairTest.class})
public class AutomatedTestSuite {}
