package game;

import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name;
		Scanner input = new Scanner(System.in);
		System.out.println("Reversi");
		Board board = checkCustomBoard(input);
		//System.out.println("Player 1, enter your name:");
		//name = input.nextLine();
		//System.out.println("Player 2, enter your name:");
		//name = input.nextLine();
		board.display();
		
		System.out.println(board.isValidMove(3, 4, Square.BLACK));

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


}
