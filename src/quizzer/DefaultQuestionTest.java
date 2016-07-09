/**
 * 
 */
package quizzer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author mchenja
 *
 */
public class DefaultQuestionTest {
	Question q; 

	@Before
	public void setUp() throws Exception {
		q = new DefaultQuestion();
	}

	@Test
	public void testDefaultQuestion() {
		q = new DefaultQuestion();
		assertTrue(q.getID() == 0);
		assertTrue(q.getQuizID() == 0);
	}

	@Test
	public void testDefaultQuestionStringStringArray() {
		q = new DefaultQuestion("Question", "Answer 1", "Answer 2");
		assertTrue(q.getID() == 0);
		assertTrue(q.getQuizID() == 0);
		assertTrue(q.get)
	}

	@Test
	public void testSaveToDatabase() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testToJSON() {
		fail("Not yet implemented"); // TODO
	}

}
