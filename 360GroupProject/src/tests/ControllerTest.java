import static org.junit.Assert.*;

import java.util.Observable;
import java.util.Observer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.Controller;

public class ControllerTest {

	private Controller myPracticeController;
	private ControllerTestHelper myControllerTestHelper;
	
	
	@Before
	public void setUp() throws Exception {
		myPracticeController = new Controller ();
		myControllerTestHelper = new ControllerTestHelper ();
		
		myPracticeController.addObserver(myControllerTestHelper);
		myControllerTestHelper.addObserver(myPracticeController);
	}
	
	
	@After
	public void tearDown() throws Exception {
		myPracticeController.deleteObservers();
		myControllerTestHelper.deleteObservers();
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
