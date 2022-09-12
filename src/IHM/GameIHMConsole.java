package IHM;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class GameIHMConsole {
	static boolean choixAffichage = true;
	Scanner sc = new Scanner(System.in);

	
	public void printMessageSuccess() {
		JOptionPane.showMessageDialog(null, "Bonne réponse !");
	}
	public void printMessageWrongAnswer(String capitale) {
		JOptionPane.showMessageDialog(null, "Mauvaise réponse ! \n La capitale était : " + capitale);
	}
	
	public void printMaxQuestionsExceeded(int nbrQuestions) {
		JOptionPane.showMessageDialog(null, "Erreur ! On ne peu générer que " + nbrQuestions + " questions.");
	}
	
	
	
	public void printResultats(int score, int nbrQuestions, int timeElapsed) {
		System.out.println(
				"Votre score final est de : " + score + " / " + nbrQuestions + "\n"
				+ "Il vous a fallu environ " + timeElapsed
				+ " secondes pour repondre aux " + nbrQuestions + " questions"
				);
	}
	public void printMessageNoResponses() {
		System.out.println("Vous n'avez rien répondu");
	}
	
	public int askNombreQuestions() {
		System.out.println("Combien de question voulez vous ?");
		return sc.nextInt();
	}
	
	public String askQuestionPays(String pays) {
		System.out.println("Quelle est la capitale du pays : " + pays);
		return sc.nextLine();

	}
}
