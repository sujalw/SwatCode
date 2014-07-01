import java.util.Scanner;

/**
 * @author Sujal
 */
public class Player {
	char code;
	int score;

	public Player(char code) {
		this.code = code;
		score = 0;
	}

	public char getCode() {
		return code;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		assert score > 0;

		this.score = score;
	}

	public void addScore(int score) {
		assert score > 0;

		this.score += score;
	}

	public void reduceScore(int score) {
		assert score > 0 && score <= this.score;

		this.score -= score;
	}
}