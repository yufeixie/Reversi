package Game;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * This class contains the main method. This class handles input, output and the
 * running of the game.
 * 
 */
public class Game {

	private Player[] players = new Player[2];
	private Board board;

	public Game(Scanner input) {
		board = checkCustomBoard(input);
		players[0] = createNewPlayer(1, Piece.WHITE, input);
		players[1] = createNewPlayer(2, Piece.BLACK, input);
		board.display();
	}

	private Player createNewPlayer(int playerNumber, Piece square,
			Scanner input) {
		String name;
		System.out.println("Player " + playerNumber
				+ " please enter your name: ");
		name = input.nextLine();
		System.out.println("Hello " + name + ", you will be player "
				+ playerNumber + " and your piece is : " + square);
		return new Player(name, 2, square);
	}

	private void endGame() {
		System.out.println("Game Over!");
		int player1Score = players[0].getScore();
		int player2Score = players[1].getScore();

		if (player1Score > player2Score) {
			System.out.println("Player 1 wins with a score of " + player1Score);
		} else if (player1Score < player2Score) {
			System.out.println("Player 2 wins with a score of " + player2Score);
		} else {
			System.out.println("It's a draw!");
			System.out.println("The final score for both players is "
					+ player1Score);
		}
	}

	private Board checkCustomBoard(Scanner input) {
		String ownSize, customSize;
		int size = 0;

		System.out
				.println("Would you like to play with a custom board? (YES/NO)");
		ownSize = input.nextLine().toUpperCase();
		while (!(ownSize.equals("YES") || ownSize.equals("NO"))) {
			System.out.println("Please enter YES or NO.");
			ownSize = input.nextLine().toUpperCase();
		}
		if (ownSize.equals("YES")) {
			System.out.println("Enter the size of the board.");
			do {
				System.out.println("Please enter an integer greater than 2.");
				customSize = input.nextLine();
				try {
					size = Integer.parseInt(customSize);
				} catch (NumberFormatException e) {
					System.out.println(customSize + " is not a valid integer.");
				}
			} while (size <= 2);

			return new Board(size);
		}

		return new Board();
	}
	
	public void run(Scanner input) {
		int currentPlayer = 0;
		ArrayList<Direction> validDirections;
		Piece currentPiece;
		String nextCoordinate;
		int x, y;
		int scoreChange;

		Pair<Integer, Integer> coords = null;
		while (!board.gameOver()) {
			currentPiece = players[currentPlayer].getPiece();
			if (!board.validMoveExists(currentPiece)) {
				System.out.println("Player " + (currentPlayer + 1)
						+ " cannot move.");
				currentPlayer = ++currentPlayer % 2;
				currentPiece = players[currentPlayer].getPiece();
			}
			
			do {
				System.out.println("Player " + (currentPlayer + 1)
						+ ": Please enter your move in the format: letter number");

				nextCoordinate = input.next();
				x = 1 + (nextCoordinate.toUpperCase().charAt(0) - 'A');
				nextCoordinate = input.next();
				try {
					y = Integer.parseInt(nextCoordinate);
				} catch (NumberFormatException e) {
					System.out.println("You have not entered a valid set of coordinates.");
					y = -1;
				}
				coords = new Pair<Integer, Integer>(x, y);
				validDirections = board.checkMoves(coords, currentPiece);
			} while ((validDirections == null));
			
			scoreChange = board.makeMove(coords, currentPiece, validDirections);
			players[currentPlayer].adjustScore(scoreChange);
			currentPlayer = ++currentPlayer % 2;
			players[currentPlayer].adjustScore(-(--scoreChange));
			board.display();
		}
		endGame();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Othello");
		Scanner input = new Scanner(System.in);
		Game game = new Game(input);
		game.run(input);
		input.close();
	}

}
