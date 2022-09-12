package Entreprise;
public class Question {
	private String pays;
	private String ville;
	private int point;
/**
 * Constructeur de la classe Question
 * @param pays : Le pays de la question
 * @param ville : La capitale du pays de la question
 */
	public Question(String pays, String ville) {
		this.pays = pays;
		this.ville = ville;
		this.point = 2;
	}
	/**
	 * Constructeur de la classe Question
	 * @param pays : Le pays de la question
	 * @param ville : La capitale du pays de la question
	 * @param point : nombre de point
	 */
	public Question(String pays, String ville, int point) {
		this.pays = pays;
		this.ville = ville;
		this.point = point;
	}
/**
 * Permet de retourner le pays de la question
 * @return : le pays de la question
 */
	public String getPays() {
		return pays;
	}
/**
 * Permet de retourner la ville de la question
 * @return : la ville de la question
 */
	public String getVille() {
		return ville;
	}
	
}
