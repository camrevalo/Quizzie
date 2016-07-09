/**
 * 
 */
package quizzer;

/**
 * @author mchenja
 *
 */
abstract class Question implements Saveable {
	protected Integer ID;
	protected Integer quizID;
	
	protected Question() {
		ID = 0;
		quizID = 0;
	}
	
	protected Question(Integer newID, Integer newQuizID) {
		ID = newID;
		quizID = newQuizID;
	}
	
	protected int getID() {
		return ID;
	}
	
	protected int getQuizID() {
		return quizID;
	}
}
