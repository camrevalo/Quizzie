package quizzer;

/**
 * @author mchenja
 *
 */
interface Saveable {
	void saveToDatabase(String username);
	String toJSON();
}