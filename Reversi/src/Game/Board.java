package Game;

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
	
	private void setPiece(int x, int y, Square piece) {
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
		for (Direction dir : Direction.values()) {
			if (checkLine(x,y,squareColour, dir)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkLine(int x, int y, Square squareColour, Direction dir) {
		Square nextColour = getColour(getAdjacentX(x, dir), getAdjacentY(y, dir));
		if (squareColour == Square.BLACK){
			switch (nextColour) {
	            case BLACK: return true;
	            case EMPTY: return false;
	            case WHITE: checkLine(getAdjacentX(x, dir), getAdjacentY(y, dir), nextColour, dir);
	        }
		} else {
			switch (nextColour) {
            	case WHITE: return true;
            	case EMPTY: return false;
            	case BLACK: checkLine(getAdjacentX(x, dir), getAdjacentY(y, dir), nextColour, dir);
			}	
		}	
		return false;
	}

	private int getAdjacentY(int y, Direction dir) {
		switch (dir) {
        	case N:  return y++;
        	case NE: return y++;
        	case E:  return y;
        	case SE: return y--;
        	case S:  return y--;
        	case SW: return y--;
        	case W:  return y;
        	case NW: return y++;
		}
		return y;
	}

	private int getAdjacentX(int x, Direction dir) {
		switch (dir) {
        	case N:  return x;
        	case NE: return x++;
			case E:  return x++;
			case SE: return x++;
			case S:  return x;
			case SW: return x--;
			case W:  return x--;
			case NW: return x--;
		}
		return x;
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
}
