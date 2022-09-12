package IHM;

import javax.swing.JOptionPane;

public class GameIHMGraphique {

	

	
	public void printMessageSuccess() {
		JOptionPane.showMessageDialog(null, "Bonne réponse !");
	}
	
	public void printMessageWrongAnswer(String capitale) {
		JOptionPane.showMessageDialog(null, "Mauvaise réponse ! \n La capitale était : " + capitale);
	}
	
	public void printMaxQuestionsExceeded(int nbrQuestions) {
		JOptionPane.showMessageDialog(null, "Erreur ! On ne peut générer que " + nbrQuestions + " questions.");
	}
	
	
	/*-----------------RESULTATS--------------------*/
	
	public void printResultats(int score, int nbrQuestions, int timeElapsed) {
		JOptionPane.showMessageDialog(null,
				"Votre score final est de : " + score + " / " + nbrQuestions + "\n"
				+ "Il vous a fallu environ " + timeElapsed
				+ " secondes pour repondre aux " + nbrQuestions + " questions"
				);
	}
	
	public void printMessageNoResponses() {
		JOptionPane.showMessageDialog(null, "Vous n'avez rien répondu");
	}
	
	public int askNombreQuestions() {
		return Integer.parseInt(JOptionPane.showInputDialog(null, "Combien de question voulez vous ? "));
	}
	
	public String askQuestionPays(String pays) {
		return JOptionPane.showInputDialog(null,"Quelle est la capitale du pays : "+ pays);

	}
	
}
