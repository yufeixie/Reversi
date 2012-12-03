package Game;

/**
 * 
 * An enumerated type to model the piece on a square of the board. This can be a
 * coloured piece or an empty piece, representing when a squared has not had a
 * piece placed on it.
 * 
 * The opposite method returns what would be interpreted as the opposite of a
 * piece.
 * 
 */
public enum Piece {

	EMPTY {
		@Override
		public String toString() {
			return " ";
		}

		@Override
		public Piece opposite() {
			return EMPTY;
		}
	},

	BLACK {
		@Override
		public String toString() {
			return "X";
		}

		@Override
		public Piece opposite() {
			return WHITE;
		}
	},
	WHITE {
		@Override
		public String toString() {
			return "O";
		}

		@Override
		public Piece opposite() {
			return BLACK;
		}
	};

	public Piece opposite() {
		return null;
	}

}
