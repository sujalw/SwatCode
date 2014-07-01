/**
 * @author Sujal
 */
public class Reversi {
	char[][] board;
	int noOfRows;
	int noOfColumns;

	private final char player1Code = 'X';
	private final char player2Code = 'O';
	private final char blank = ' ';

	Player player1;
	Player player2;

	public Reversi(int noOfRows, int noOfColumns) {

		assert noOfRows>=2 && noOfColumns>=2;

		this.noOfRows = noOfRows;
		this.noOfColumns = noOfColumns;

		initializePlayers();
		initializeBoard();
	}

	private void initializeBoard() {
		board = new char[noOfRows][noOfColumns];

		initializeBoardWithBlank();
		placeInitialPlayerPieces();
	}

	private void placeInitialPlayerPieces() {
		int centerRow = (noOfRows / 2) - 1;
		int centerColumn = (noOfColumns / 2) - 1;

		board[centerRow][centerColumn] = player1Code;
		board[centerRow][centerColumn+1] = player2Code;
		board[centerRow+1][centerColumn] = player2Code;
		board[centerRow+1][centerColumn+1] = player1Code;

		player1.setScore(2);
		player2.setScore(2);
	}

	private void initializeBoardWithBlank() {
		for(int r=0 ; r<noOfRows ; r++) {
			for(int c=0 ; c<noOfColumns ; c++) {
				board[r][c] = blank;
			}
		}
	}

	private void initializePlayers() {
		player1 = new Player(player1Code);
		player2 = new Player(player2Code);
	}

	public void printBoard() {
		// print label row
		for(int c=0 ; c<=noOfColumns ; c++) {
			System.out.printf("%3d", c);
		}
		System.out.println();

		for(int r=1 ; r<=noOfRows ; r++) {
			for(int c=0 ; c<=noOfColumns ; c++) {
				if(c == 0) {
					System.out.printf("%3d", r);
				} else {
					System.out.printf("%3c", board[r-1][c-1]);
				}
			}
			System.out.println();
		}
	}

	public boolean movePlayer1(int row, int col) {
		return isLegalMoveThenFlip(row, col, player1);
	}

	public boolean movePlayer2(int row, int col) {
		return isLegalMoveThenFlip(row, col, player2);
	}

	public boolean isLegalMoveThenFlip(int row, int col, Player player) {
		if(row<0 || row>=noOfRows || col<0 || col>=noOfColumns || !isBlank(row, col)) {
			return false;
		}

		return isLegalInRowThenFlip(row, col, player)
				|| isLegalInColumnThenFlip(row, col, player)
				|| isLegalInDiagonalThenFlip(row, col, player);
	}

	private boolean isLegalInRowThenFlip(int row, int col, Player player) {
		return isLegalOnLeftOfCurrentPositionThenFlip(row, col, player)
				|| isLegalOnRightOfCurrentPositionThenFlip(row, col, player);
	}

	private boolean isLegalOnRightOfCurrentPositionThenFlip(int row, int col, Player player) {
		int noOfOpponentPieces = 0;
		boolean foundOtherEnd = false;
		int otherEndColumn = -1;

		if(! isLastColumn(col)) {
			for(int c=col+1 ; c<noOfColumns ; c++) {
				if(isBlank(row, c)) {
					return false;
				}

				if(board[row][c] == player.getCode()) {
					foundOtherEnd = true;
					otherEndColumn = c;
					break;
				}

				noOfOpponentPieces++;
			}

			if(foundOtherEnd && noOfOpponentPieces > 0) {
				flipOpponentPiecesInRowWithinBounds(row, col, otherEndColumn, player);
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	private boolean isLegalOnLeftOfCurrentPositionThenFlip(int row, int col, Player player) {
		int noOfOpponentPieces = 0;
		boolean foundOtherEnd = false;
		int otherEndColumn = -1;

		if(! isFirstColumn(col)) {
			for(int c=col-1 ; c>=0 ; c--) {
				if(isBlank(row, c)) {
					return false;
				}

				if(board[row][c] == player.getCode()) {
					foundOtherEnd = true;
					otherEndColumn = c;
					break;
				}

				noOfOpponentPieces++;
			}

			if(foundOtherEnd && noOfOpponentPieces > 0) {
				flipOpponentPiecesInRowWithinBounds(row, col, otherEndColumn, player);
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	private void flipOpponentPiecesInRowWithinBounds(int row, int column1, int column2, Player currentPlayer) {
		int startColumn = Math.min(column1, column2);
		int endColumn = Math.max(column1, column2);

		for(int column=startColumn ; column<=endColumn ; column++) {
			board[row][column] = currentPlayer.getCode();
		}

		int scoreDeltaCurrentPlayer = endColumn - startColumn;
		currentPlayer.addScore(scoreDeltaCurrentPlayer);

		// ignoring other end index
		int scoreDeltaOpponent = scoreDeltaCurrentPlayer - 1;
		Player opponent = getOpponentPlayer(currentPlayer);
		opponent.reduceScore(scoreDeltaOpponent);
	}

	private Player getOpponentPlayer(Player currentPlayer) {
		if(isPlayer1(currentPlayer)) {
			return player2;
		}

		return player1;
	}

	private boolean isPlayer1(Player currentPlayer) {
		return currentPlayer.getCode() == player1.getCode();
	}

	private boolean isLegalInColumnThenFlip(int row, int col, Player player) {
		return isLegalAboveCurrentPosition(row, col, player)
				|| isLegalBelowCurrentPosition(row, col, player);
	}

	private boolean isLegalAboveCurrentPosition(int row, int col, Player player) {
		int noOfOpponentPieces = 0;
		boolean foundOtherEnd = false;
		int otherEndRow = -1;

		if(! isFirstRow(row)) {
			for(int r=row-1 ; r>=0 ; r--) {
				if(isBlank(r, col)) {
					return false;
				}

				if(board[r][col] == player.getCode()) {
					foundOtherEnd = true;
					otherEndRow = r;
					break;
				}

				noOfOpponentPieces++;
			}

			if(foundOtherEnd && noOfOpponentPieces>0) {
				flipOpponentPiecesInColumnWithinBounds(col, row, otherEndRow, player);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private boolean isLegalBelowCurrentPosition(int row, int col, Player player) {
		int noOfOpponentPieces = 0;
		boolean foundOtherEnd = false;
		int otherEndRow = -1;

		if(! isLastRow(row)) {
			for(int r=row+1 ; r<noOfRows ; r++) {
				if(isBlank(r, col)) {
					return false;
				}

				if(board[r][col] == player.getCode()) {
					foundOtherEnd = true;
					otherEndRow = r;
					break;
				}

				noOfOpponentPieces++;
			}

			if(foundOtherEnd && noOfOpponentPieces>0) {
				flipOpponentPiecesInColumnWithinBounds(col, row, otherEndRow, player);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private void flipOpponentPiecesInColumnWithinBounds(int col, int row1, int row2, Player currentPlayer) {

		int startRow = Math.min(row1, row2);
		int endRow = Math.max(row1, row2);

		for(int row=startRow ; row<=endRow ; row++) {
			board[row][col] = currentPlayer.getCode();
		}

		int scoreDeltaCurrentPlayer = endRow - startRow;
		currentPlayer.addScore(scoreDeltaCurrentPlayer);

		// excluding new other end
		int scoreDeltaOpponent = scoreDeltaCurrentPlayer - 1;
		Player opponent = getOpponentPlayer(currentPlayer);
		opponent.reduceScore(scoreDeltaOpponent);
	}

	private boolean isLegalInDiagonalThenFlip(int currentRow, int currentColumn, Player currentPlayer) {
		return isLegalInPrimaryDiagonalThenFlip(currentRow, currentColumn, currentPlayer)
				|| isLegalInSecondaryDiagonalThenFlip(currentRow, currentColumn, currentPlayer);
	}

	private boolean isLegalInPrimaryDiagonalThenFlip(int currentRow, int currentColumn, Player currentPlayer) {
		return isLegalAboveInPrimaryDiagonalThenFlip(currentRow, currentColumn, currentPlayer)
				|| isLegalBelowInPrimaryDiagonalThenFlip(currentRow, currentColumn, currentPlayer);
	}

	private boolean isLegalAboveInPrimaryDiagonalThenFlip(int currentRow, int currentColumn, Player currentPlayer) {
		int noOfOpponentPieces = 0;
		boolean foundOtherEnd = false;

		int otherEndRow = -1;
		int otherEndColumn = -1;

		if(!isFirstRow(currentRow) || !isFirstColumn(currentColumn)) {
			int row = currentRow - 1;
			int column = currentColumn - 1;

			while(row>=0 && column>=0) {
				if(isBlank(row, column)) {
					return false;
				}

				if(board[row][column] == currentPlayer.getCode()) {
					foundOtherEnd = true;
					otherEndRow = row;
					otherEndColumn = column;

					break;
				}

				noOfOpponentPieces++;
				row--;
				column--;
			}

			if(foundOtherEnd && noOfOpponentPieces>0) {
				flipOpponentPiecesInPrimaryDiagonalWithinBounds(currentRow, currentColumn, otherEndRow, otherEndColumn, currentPlayer);
				updatePlayerScores(currentRow, currentColumn, otherEndRow, otherEndColumn, currentPlayer);

				return true;
			}

			return false;
		} else {
			return false;
		}
	}

	private boolean isLegalBelowInPrimaryDiagonalThenFlip(int currentRow, int currentColumn, Player currentPlayer) {
		int noOfOpponentPieces = 0;
		boolean foundOtherEnd = false;

		int otherEndRow = -1;
		int otherEndColumn = -1;

		if(!isLastRow(currentRow) || !isLastColumn(currentColumn)) {
			int row = currentRow + 1;
			int column = currentColumn + 1;

			while(row<noOfRows && column<noOfColumns) {
				if(isBlank(row, column)) {
					return false;
				}

				if(board[row][column] == currentPlayer.getCode()) {
					foundOtherEnd = true;
					otherEndRow = row;
					otherEndColumn = column;

					break;
				}

				noOfOpponentPieces++;
				row++;
				column++;
			}

			if(foundOtherEnd && noOfOpponentPieces>0) {
				flipOpponentPiecesInPrimaryDiagonalWithinBounds(currentRow, currentColumn, otherEndRow, otherEndColumn, currentPlayer);
				updatePlayerScores(currentRow, currentColumn, otherEndRow, otherEndColumn, currentPlayer);

				return true;
			}

			return false;
		} else {
			return false;
		}
	}

	private boolean isLegalInSecondaryDiagonalThenFlip(int currentRow, int currentColumn, Player currentPlayer) {
		return isLegalAboveInSecondaryDiagonalThenFlip(currentRow, currentColumn, currentPlayer)
				|| isLegalBelowInSecondaryDiagonalThenFlip(currentRow, currentColumn, currentPlayer);
	}

	private boolean isLegalAboveInSecondaryDiagonalThenFlip(int currentRow, int currentColumn, Player currentPlayer) {
		int noOfOpponentPieces = 0;
		boolean foundOtherEnd = false;

		int otherEndRow = -1;
		int otherEndColumn = -1;

		if(!isFirstRow(currentRow) && !isLastColumn(currentColumn)) {
			int row = currentRow - 1;
			int column = currentColumn + 1;

			while(row>=0 && column<noOfColumns) {
				if(isBlank(row, column)) {
					return false;
				}

				if(board[row][column] == currentPlayer.getCode()) {
					foundOtherEnd = true;
					otherEndRow = row;
					otherEndColumn = column;

					break;
				}

				noOfOpponentPieces++;
				row--;
				column++;
			}

			if(foundOtherEnd && noOfOpponentPieces>0) {
				flipOpponentPiecesInSecondaryDiagonalWithinBounds(currentRow, currentColumn, otherEndRow, otherEndColumn, currentPlayer);
				updatePlayerScores(currentRow, currentColumn, otherEndRow, otherEndColumn, currentPlayer);

				return true;
			}

			return false;
		}

		return false;
	}

	private boolean isLegalBelowInSecondaryDiagonalThenFlip(int currentRow, int currentColumn, Player currentPlayer) {
		int noOfOpponentPieces = 0;
		boolean foundOtherEnd = false;

		int otherEndRow = -1;
		int otherEndColumn = -1;

		if(!isLastRow(currentRow) && !isFirstColumn(currentColumn)) {
			int row = currentRow + 1;
			int column = currentColumn - 1;

			while(row<noOfRows && column>=0) {
				if(isBlank(row, column)) {
					return false;
				}

				if(board[row][column] == currentPlayer.getCode()) {
					foundOtherEnd = true;
					otherEndRow = row;
					otherEndColumn = column;

					break;
				}

				noOfOpponentPieces++;
				row++;
				column--;
			}

			if(foundOtherEnd && noOfOpponentPieces>0) {
				flipOpponentPiecesInSecondaryDiagonalWithinBounds(currentRow, currentColumn, otherEndRow, otherEndColumn, currentPlayer);
				updatePlayerScores(currentRow, currentColumn, otherEndRow, otherEndColumn, currentPlayer);

				return true;
			}

			return false;
		}

		return false;
	}

	private void flipOpponentPiecesInPrimaryDiagonalWithinBounds(int row1, int column1, int row2, int column2, Player currentPlayer) {
		int startRow = Math.min(row1, row2);
		int endRow = Math.max(row1, row2);
		int startColumn = Math.min(column1, column2);
		int endColumn = Math.max(column1, column2);

		for(int row=startRow, column=startColumn ; row<=endRow && column<=endColumn ; row++, column--) {
			board[row][column] = currentPlayer.getCode();
		}
	}

	private void flipOpponentPiecesInSecondaryDiagonalWithinBounds(int row1, int column1, int row2, int column2, Player currentPlayer) {
		int startRow = Math.min(row1, row2);
		int startColumn = Math.max(column1, column2);
		int endRow = Math.max(row1, row2);
		int endColumn = Math.min(column1, column2);

		for(int row=startRow, column=startColumn ; row<=endRow && column>=endColumn ; row++, column--) {
			board[row][column] = currentPlayer.getCode();
		}
	}

	private void updatePlayerScores(int row1, int column1, int row2, int column2, Player currentPlayer) {
		int rowsDiff = Math.abs(row1 - row2);
		int columnsDiff = Math.abs(column1 - column2);
		int scoreDelta = Math.max(rowsDiff, columnsDiff);

		currentPlayer.addScore(scoreDelta);

		Player opponent = getOpponentPlayer(currentPlayer);
		opponent.reduceScore(scoreDelta - 1);
	}

	public boolean isFinished() {
		int totalBlankSpacesLeft = noOfRows*noOfColumns - player1.getScore() - player2.getScore();

		return totalBlankSpacesLeft == 0;
	}

	public int getPlayer1Score() {
		return player1.getScore();
	}

	public int getPlayer2Score() {
		return player2.getScore();
	}

	private boolean isFirstRow(int row) {
		return row == 0;
	}

	private boolean isLastRow(int row) {
		return row == noOfRows-1;
	}

	private boolean isFirstColumn(int col) {
		return col == 0;
	}

	private boolean isLastColumn(int col) {
		return col == noOfColumns-1;
	}

	private boolean isBlank(int row, int col) {
		return board[row][col] == blank;
	}
}