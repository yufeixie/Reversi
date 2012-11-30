package Game;

public enum Square {
	EMPTY {
		@Override
	    public String toString() {
	        return " ";
	    }
	},
	 
	BLACK {
		@Override
	    public String toString() {
	        return "X";
	    }
	},
	WHITE {
		@Override
		public String toString(){
			return "O";
		}
	}
}
