/**
 * 
 */
package quizzer;

/**
 * @author mchenja
 *
 */
public final class DefaultQuestion extends Question implements Saveable {
	private String question;
	private String[] answers;

	/**
	 * 
	 */
	public DefaultQuestion() {
		super();
		question = new String();
		answers = new String[5];
	}
	
	public DefaultQuestion(String newQuestion, String... newAnswers) {
		question = newQuestion;
		answers = newAnswers;
	}
	
	public boolean checkAnswer(String answer) {
		for (String s : answers) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see quizzer.Saveable#saveToDatabase(java.lang.String)
	 */
	@Override
	public void saveToDatabase(String username) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see quizzer.Saveable#toJSON()
	 */
	@Override
	public String toJSON() {
		// TODO Auto-generated method stub
		return null;
	}

}
