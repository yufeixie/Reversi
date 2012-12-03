package Game;

import java.util.ArrayList;

/**
 * 
 * Represents an Othello board of a variable size.
 * 
 */
public class Board implements GameBoard {

	Square[][] board;
	private final int DEFAULTSIZE = 8;
	private final int sizeOfBoard;

	public Board() {
		sizeOfBoard = DEFAULTSIZE;
		board = new Square[DEFAULTSIZE][DEFAULTSIZE];
		initialiseBoard();
	}

	public Board(int size) {
		sizeOfBoard = size;
		board = new Square[size][size];
		initialiseBoard();
	}

	/*
	 * The following method sets all squares on the board to be empty.
	 */
	private void initialiseBoard() {
		for (int i = 0; i < getSizeOfBoard(); i++) {
			for (int j = 0; j < getSizeOfBoard(); j++) {
				board[i][j] = new Square(Piece.EMPTY);
			}
		}
		setDefaultPieces();

	}

	/*
	 * The following method places the first four initial pieces
	 */
	private void setDefaultPieces() {
		int middleOfBoard = getSizeOfBoard() / 2;
		int middleOfBoardPlusOne = middleOfBoard + 1;

		setPiece(new Pair<Integer, Integer>(middleOfBoard, middleOfBoard),
				Piece.WHITE);
		setPiece(
				new Pair<Integer, Integer>(middleOfBoardPlusOne, middleOfBoard),
				Piece.BLACK);
		setPiece(
				new Pair<Integer, Integer>(middleOfBoard, middleOfBoardPlusOne),
				Piece.BLACK);
		setPiece(new Pair<Integer, Integer>(middleOfBoardPlusOne,
				middleOfBoardPlusOne), Piece.WHITE);
	}

	private void setPiece(Pair<Integer, Integer> coords, Piece piece) {
		board[coords.getFirst() - 1][coords.getSecond() - 1].setPiece(piece);

	}

	private Piece getColour(Pair<Integer, Integer> coords) {
		return board[coords.getFirst() - 1][coords.getSecond() - 1].getPiece();
	}

	private int getSizeOfBoard() {
		return sizeOfBoard;
	}

	private boolean isInBoundary(Pair<Integer, Integer> coords) {
		int column = coords.getFirst() - 1;
		int row = coords.getSecond() - 1;
		return column >= 0 && row >= 0 && column < getSizeOfBoard()
				&& row < getSizeOfBoard();
	}

	private boolean isValidMove(Pair<Integer, Integer> coords,
			Piece squareColour) {
		boolean found = false;
		if (isInBoundary(coords)) {
			if ((board[coords.getFirst() - 1][coords.getSecond() - 1])
					.getPiece().equals(Piece.EMPTY)) {
				for (Direction dir : Direction.values()) {
					if (checkLine(coords, squareColour, dir, found)) {
						return true;
					}

				}
				return false;
			}
		}
		return false;
	}

	/*
	 * Used to check whether a direction of travel would be valid by searching
	 * for the same coloured piece in the direction of travel after finding an
	 * opposite coloured piece.
	 */
	private boolean checkLine(Pair<Integer, Integer> coords,
			Piece squareColour, Direction dir, boolean found) {
		if (isInBoundary(getAdjacent(coords, dir))) {
			Piece nextColour = getColour(getAdjacent(coords, dir));
			if (squareColour == nextColour) {
				if (!found) {
					return false;
				}
				return true;
			} else if (squareColour.opposite() == nextColour) {
				return checkLine(getAdjacent(coords, dir), squareColour, dir,
						true);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/*
	 * Finds the next pair of coordinates in the current direction of travel.
	 */
	private Pair<Integer, Integer> getAdjacent(Pair<Integer, Integer> coords,
			Direction dir) {
		int x = coords.getFirst();
		int y = coords.getSecond();
		switch (dir) {
		case N:
			return new Pair<Integer, Integer>(x, --y);
		case NE:
			return new Pair<Integer, Integer>(++x, --y);
		case E:
			return new Pair<Integer, Integer>(++x, y);
		case SE:
			return new Pair<Integer, Integer>(++x, ++y);
		case S:
			return new Pair<Integer, Integer>(x, ++y);
		case SW:
			return new Pair<Integer, Integer>(--x, ++y);
		case W:
			return new Pair<Integer, Integer>(--x, y);
		case NW:
			return new Pair<Integer, Integer>(--x, --y);
		}
		return new Pair<Integer, Integer>(x, y);
	}

	/*
	 * Searches for a valid move for a given colour type.
	 */
	protected boolean validMoveExists(Piece squareColour) {
		for (int i = 1; i <= sizeOfBoard; i++) {
			for (int j = 1; j <= sizeOfBoard; j++) {
				if (isValidMove(new Pair<Integer, Integer>(i, j), squareColour)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void display() {
		System.out.print("  ");
		char letter = 'A';
		for (int i = 0; i < sizeOfBoard; i++) {
			System.out.print(letter++);
			System.out.print(' ');
		}
		System.out.println();
		System.out.print(" ");
		for (int i = 0; i < sizeOfBoard; i++) {
			System.out.print("--");
		}
		System.out.print("-");
		System.out.println();
		for (int j = 0; j < sizeOfBoard; j++) {
			System.out.print(j + 1);
			System.out.print('|');
			for (int i = 0; i < sizeOfBoard; i++) {
				System.out.print(board[i][j]);
				System.out.print('|');
			}
			System.out.println();
			System.out.print(" ");
			for (int k = 0; k < sizeOfBoard; k++) {
				System.out.print("--");
			}
			System.out.print("-");
			System.out.println();
		}
	}

	@Override
	public boolean gameOver() {
		return !(validMoveExists(Piece.BLACK) || validMoveExists(Piece.WHITE));

	}

	/*
	 * Checks for a valid Othello move. The resulting List will be null if the
	 * move is invalid.
	 */
	public ArrayList<Direction> checkMoves(Pair<Integer, Integer> coords,
			Piece squareColour) {
		boolean found = false;
		ArrayList<Direction> validDirections = null;
		if (isInBoundary(coords)) {
			if ((board[coords.getFirst() - 1][coords.getSecond() - 1])
					.getPiece().equals(Piece.EMPTY)) {
				for (Direction dir : Direction.values()) {
					if (checkLine(coords, squareColour, dir, found)) {
						if (validDirections == null) {
							validDirections = new ArrayList<Direction>();
						}
						validDirections.add(dir);
					}
				}
			}
		}
		return validDirections;
	}

	/*
	 * Switches pieces of the opposite colour to that of the player's colour.
	 * The resulting integer represents the score change of the players.
	 */
	private int switchRestOfPieces(Pair<Integer, Integer> coords, Piece colour,
			Direction dir) {
		if (board[coords.getFirst() - 1][coords.getSecond() - 1].getPiece() == colour) {
			return 0;
		}
		Pair<Integer, Integer> nextCoordinate = getAdjacent(coords, dir);
		board[coords.getFirst() - 1][coords.getSecond() - 1].setPiece(colour);
		return 1 + switchRestOfPieces(nextCoordinate, colour, dir);
	}
	
	/*
	 * Carries out the process of making a valid move.
	 */
	protected int makeMove(Pair<Integer, Integer> coords, Piece colour,
			ArrayList<Direction> validDirections) {
		int scoreChange = 1;
		board[coords.getFirst() - 1][coords.getSecond() - 1].setPiece(colour);
		Pair<Integer, Integer> nextCoordinate;
		for (Direction dir : validDirections) {
			nextCoordinate = getAdjacent(coords, dir);
			scoreChange += switchRestOfPieces(nextCoordinate, colour, dir);
		}
		return scoreChange;
	}
}
