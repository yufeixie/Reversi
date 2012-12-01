package Game;

public enum Square {
	EMPTY {
		@Override
	    public String toString() {
	        return " ";
	    }
		
		@Override
		public Square opposite(){
			return EMPTY;
		}
	},
	 
	BLACK {
		@Override
	    public String toString() {
	        return "X";
	    }
		
		@Override
		public Square opposite(){
			return WHITE;
		}
	},
	WHITE {
		@Override
		public String toString(){
			return "O";
		}
		
		@Override
		public Square opposite(){
			return BLACK;
		}
	};

	public Square opposite() {
		return null;
	}


}
