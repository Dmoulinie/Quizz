package Entreprise;

public class Verif {

	
	public static boolean verifInputNbreQuestion(String userInput) {
		if (isNumeric(userInput)) {
			return true;
		}
		return false;
	}
	
	public static boolean verifInputAnswerQuestion(String userAnswer, Question question) {
		if (userAnswer != null && userAnswer != "" && userAnswer.length() != 0){
			return true;
		}
		return false;
		
	}
	
	public static boolean checkAnswer(String userAnswer, Question question) {
		if (userAnswer.equalsIgnoreCase(question.getVille())) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	public static boolean isNumeric(String str) {
		  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
		}
}
