package swat.reversi;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * @author Sujal
 */
public class ReversiGame {

	public static void main(String[] args) {
		new ReversiGame();
	}

	public ReversiGame() {
		reversiGamePlay();
	}

	public void reversiGamePlay() {
		Reversi reversi = new Reversi(4, 4);
		reversi.printBoard();

		boolean player1Chance = true;
		int row, column;
		boolean legalMove;
		boolean prevPlayerMadePass = false;

		Scanner scanner = new Scanner(System.in);

		while(! reversi.isFinished()) {
			if(player1Chance) {
				System.out.print("Player1: Enter X,Y position: ");
			} else {
				System.out.print("Player2: Enter X,Y position: ");
			}

			String inputLine = scanner.nextLine();

			if(isPass(inputLine)) {
				if(prevPlayerMadePass) {
					break;
				}

				prevPlayerMadePass = true;

				// toggle
				player1Chance = player1Chance ^ true;

				continue;
			} else {
				prevPlayerMadePass = false;
			}

			String[] input = inputLine.split(",");

			row = Integer.parseInt(input[0]);
			column = Integer.parseInt(input[1]);

			row--;
			column--;

			if(player1Chance) {
				legalMove = reversi.movePlayer1(row, column);
				player1Chance = false;
			} else {
				legalMove = reversi.movePlayer2(row, column);
				player1Chance = true;
			}

			if(legalMove) {
				reversi.printBoard();
				System.out.println("player 1 : " + reversi.getPlayer1Score());
				System.out.println("player 2 : " + reversi.getPlayer2Score());
				System.out.println();
			} else {
				System.out.println("Not a legal move.");
			}
		}

		System.out.println("Game finished !!!");
		System.out.println("Player 1 score = " + reversi.getPlayer1Score());
		System.out.println("Player 2 score = " + reversi.getPlayer2Score());
	}

	private boolean isPass(String inputLine) {
		return StringUtils.isEmpty(inputLine);
	}
}