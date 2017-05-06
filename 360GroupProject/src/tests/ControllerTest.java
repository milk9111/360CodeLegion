import static org.junit.Assert.*;

import java.util.Observable;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;

public class ControllerTest {

	private Controller myPracticeController;
	private ControllerTestHelper myControllerTestHelper;
	
	@Before
	public void setUp() throws Exception {
		myPracticeController = new Controller ();
		myControllerTestHelper = new ControllerTestHelper ();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	
	
	public class ControllerTestHelper extends Observable implements Observer {

		@Override
		public void update(Observable arg0, Object arg1) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
