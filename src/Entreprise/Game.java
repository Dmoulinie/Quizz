package Entreprise;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import IHM.*;
import Data.*;


public class Game {
	boolean choixAffichage = true;
	private int score = 0;
	private long startTime;
	private long endTime;
	private long timeElapsed;
	private boolean done = false;
	private int nbreQuestions;
	Scanner sc = new Scanner(System.in);
	GameIHMGraphique gameIHM = new GameIHMGraphique();

	public Game() { // Constructeur par défaut
		this.choixAffichage = false;
		this.startTime = System.currentTimeMillis();
	}
	
	public Game(boolean choixAffichage) { // Constructeur avec choix de l'affichage
		this.choixAffichage = choixAffichage;
		this.startTime = System.currentTimeMillis();

	}
	
	public static void main(String[] args) throws FileNotFoundException, SQLException, IOException {
		Game game = new Game(true);
		game.start();
	}
	
	
	public void start() throws SQLException, FileNotFoundException, IOException {
		this.nbreQuestions = gameIHM.askNombreQuestions();
		try {
			ArrayList<Question> questions = generate();
			for (int i = 0; i < this.nbreQuestions; i++) {
				String userAnswer = gameIHM.askQuestionPays(questions.get(i).getPays());
				if (Verif.verifInputAnswerQuestion(userAnswer, questions.get(i))) {
					if (Verif.checkAnswer(userAnswer, questions.get(i))) {
						gameIHM.printMessageSuccess();
						score++;
					} else {
						gameIHM.printMessageWrongAnswer(questions.get(i).getVille());
					}
				} else {
					gameIHM.printMessageWrongAnswer(questions.get(i).getVille());
				}
			}
			done = true;
			endTime = System.currentTimeMillis();
			timeElapsed = endTime - startTime;
			displayResultats();
		} catch (IllegalArgumentException e) {
			done = false;
			System.out.println(e.getMessage());
		}

	}
	

public ArrayList<Question> generate() throws SQLException, FileNotFoundException, IOException {
	String[][] data = getData();
	int index = 0;
	ArrayList<Question> questions = new ArrayList<Question>();
	ArrayList<Integer> nbRandomAlreadyExists = new ArrayList<Integer>();

	if (nbreQuestions > data.length) {
		gameIHM.printMaxQuestionsExceeded(data.length);
	}
	nbRandomAlreadyExists.clear();
	Random random = new Random();
	while (index != nbreQuestions) {
		int nbRandom = random.nextInt(data.length);
		if (!nbRandomAlreadyExists.contains(nbRandom)) {
			questions.add(new Question(data[nbRandom][0], data[nbRandom][1]));
			nbRandomAlreadyExists.add(nbRandom);
			index++;
		}
		
	}
	return questions;
}

public String[][] getData() throws FileNotFoundException, IOException, SQLException {
	return new dbConnexion().getAllData();
}

	
	
	/**
	 * Affiche le temps utilisé par le joueur pour répondre aux questions
	 * @return : Texte du temps écoulé
	 */
	private int getTimeElapsed() {
		return getTimeElapsedInSeconds(timeElapsed);
	}
	
	private int getTimeElapsedInSeconds(long timeInMilliSeconds) {
		return (int) (timeInMilliSeconds / 1000);
	}

	/**
	 * Affiche le score final
	 */
	private int getScore() {
		return score;
	}
	
	/*data hibernate*/
	
	public void displayResultats() {
		if (done) {
			gameIHM.printResultats(this.score, nbreQuestions, getTimeElapsed());
		} else {
			gameIHM.printMessageNoResponses();
		}
	}
	
}
