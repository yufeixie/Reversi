package game;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int currentPlayer = 0;
		ArrayList<Direction>validDirections;
		Square currentPiece;
		int x, y, scoreChange;
		String nextCoordinate;
		Scanner input = new Scanner(System.in);
		System.out.println("Reversi");
		Player[] players = new Player[2];
		Board board = checkCustomBoard(input);
		players[0] = createNewPlayer(1, new Piece(Square.WHITE), input);
		players[1] = createNewPlayer(2, new Piece(Square.BLACK), input);
		System.out.println("Press any key.");
		input.nextLine();
		board.display();
		
		
		while(!board.gameOver()){
			
			currentPiece = players[currentPlayer].getPiece().getPiece();
			
			if (!board.validMoveExists(currentPiece)){
				System.out.println("Player " + (currentPlayer + 1) + " cannot move.");
				currentPlayer = ++currentPlayer%2;
				currentPiece = players[currentPlayer].getPiece().getPiece();
			}
			
			do {				
				System.out.println("Player " + (currentPlayer + 1) + ": Please enter your move in the format: letter number");
								
				nextCoordinate = input.next();
				x = 1 + (nextCoordinate.toUpperCase().charAt(0) - 'A');
				nextCoordinate = input.next();
				y = Integer.parseInt(nextCoordinate);
				
				validDirections = board.checkMoves(x, y, currentPiece);
			} while((validDirections == null));
	
			scoreChange = board.makeMove(x, y, currentPiece, validDirections);
			players[currentPlayer].setScore(scoreChange);
			currentPlayer = ++currentPlayer%2;
			players[currentPlayer].setScore(-(--scoreChange));
			board.display();			
		}
		System.out.println("Game Over!");
		int player1Score = players[0].getScore();
		int player2Score = players[1].getScore();
		
		if (player1Score > player2Score){
			System.out.println("Player 1 wins with a score of " + player1Score);
		} else if (player1Score < player2Score){
			System.out.println("Player 2 wins with a score of " + player2Score);
		} else {
			System.out.println("It's a draw!");
			System.out.println("The final score for both players is " + player1Score);
		}
		input.close();
	}

	private static Board checkCustomBoard(Scanner input) {
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

	private static Player createNewPlayer(int playerNumber, Piece piece,
			Scanner input) {
		String name;
		System.out.println("Player " + playerNumber
				+ " please enter your name: ");
		name = input.nextLine();
		System.out.println("Hello " + name + ", you will be player "
				+ playerNumber + " and your piece is : " + piece.getPiece());
		return new Player(name, 2, piece);
	}


}
