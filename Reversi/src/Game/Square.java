package Game;

/**
 * 
 * This represents a square on the game board
 *
 */
public class Square {
	
	private Piece piece;
	
	public Square(Piece piece)
	{
		this.piece = piece;
	}
	
	public Piece getPiece()
	{
		return piece;
	}
	
	public void setPiece(Piece piece)
	{
		this.piece = piece;
	}
	
	public String toString(){
		return piece.toString();
	}

}
