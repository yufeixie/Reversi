package game;

public class Board {

	Piece[][] board;
	private final int DEFAULTSIZE = 8;
	private final int sizeOfBoard;

	public Board() {
		sizeOfBoard = DEFAULTSIZE;
		board = new Piece[DEFAULTSIZE][DEFAULTSIZE];
		initialiseBoard();
	}

	public Board(int size) {
		sizeOfBoard = size;
		board = new Piece[size][size];
		initialiseBoard();
	}

	private void initialiseBoard() {
		for (int i = 0; i < getSizeOfBoard(); i++) {
			for (int j = 0; j < getSizeOfBoard(); j++) {
				board[i][j] = new Piece(Square.EMPTY);
			}
		}
		//TODO other initialisation
		setDefaultPieces();

	}
	
	private void setDefaultPieces(){
		int middleOfBoard = getSizeOfBoard() / 2;
		int middleOfBoardPlusOne = (getSizeOfBoard() / 2) + 1;

		setPiece(middleOfBoard, middleOfBoard, Square.WHITE);
		setPiece(middleOfBoardPlusOne, middleOfBoard, Square.BLACK);
		setPiece(middleOfBoard, middleOfBoardPlusOne, Square.BLACK);
		setPiece(middleOfBoardPlusOne, middleOfBoardPlusOne, Square.WHITE);
	}

	public Piece getPiece(int x, int y) {
		return board[x - 1][y - 1];
	}
	
	public void setPiece(int x, int y, Square piece) {
		board[x - 1][y - 1].setPiece(piece);

	}

	private Square getColour(int x, int y) {
		return board[x - 1][y - 1].getPiece();
	}

	public Piece[][] getBoard() {
		return board;
	}

	public int getSizeOfBoard() {
		return sizeOfBoard;
	}

	private boolean isInBoundary(int x, int y) {
		int column = x - 1;
		int row = y - 1;
		return column >= 0 && row >= 0 && column < getSizeOfBoard()
				&& row < getSizeOfBoard();
	}

	public int countNoOfPieces(Square squareColour) {
		int numberOfSameColouredPieces = 0;
		for (int i = 0; i < sizeOfBoard; i++) {
			for (int j = 0; j < sizeOfBoard; j++) {
				if (board[i][j].getPiece() == squareColour) {
					numberOfSameColouredPieces++;
				}
			}
		}
		return numberOfSameColouredPieces;
	}
	
	//TODO
	public boolean isValidMove(int x, int y, Square squareColour) {
		boolean found = false;
		if(isInBoundary(x, y)) {
			if ((board[x-1][y-1]).getPiece().equals(Square.EMPTY)){
				for (Direction dir : Direction.values()) {
					if (checkLine(x,y,squareColour, dir, found)) {
						return true;
					}
				
				}
				return false;
			}
		}
		return false;
	}
	
	private boolean checkLine(int x, int y, Square squareColour, Direction dir, boolean found) {
		if (isInBoundary(getAdjacent(x, y, dir).getFirst(), getAdjacent(x, y, dir).getSecond())){
			Square nextColour = getColour(getAdjacent(x, y, dir).getFirst(), getAdjacent(x, y, dir).getSecond());
			if (squareColour == nextColour) {
				if (!found){
					return false;
				}
				return true;
			} else if (squareColour.opposite() == nextColour) { 
				return checkLine(getAdjacent(x, y, dir).getFirst(), getAdjacent(x, y, dir).getSecond(), squareColour, dir, true);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private Pair<Integer, Integer> getAdjacent(int x, int y, Direction dir) {
		switch (dir) {
        	case N:  return new Pair<Integer, Integer>(x, --y);
        	case NE: return new Pair<Integer, Integer>(++x, --y);
        	case E:  return new Pair<Integer, Integer>(++x, y);
        	case SE: return new Pair<Integer, Integer>(++x, ++y);
        	case S:  return new Pair<Integer, Integer>(x, ++y);
        	case SW: return new Pair<Integer, Integer>(--x, ++y);
        	case W:  return new Pair<Integer, Integer>(--x, y);
        	case NW: return new Pair<Integer, Integer>(--x, --y);
		}
		return new Pair<Integer, Integer>(x, y);
	}

	public boolean validMoveExists(Square squareColour) {
		boolean validMove = false;
		for (int i = 1; i <= sizeOfBoard; i++) {
			for (int j = 1; j <= sizeOfBoard; j++) {
				if (isValidMove(i, j, squareColour)) {
					return (validMove = true);
				}
			}
		}
		return validMove;
	}
	
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
}
