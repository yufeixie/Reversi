package Game;

public class Piece {
	
	private Square piece;
	
	public Piece(Square piece)
	{
		this.piece = piece;
	}
	
	public Square getPiece()
	{
		return piece;
	}
	
	public void setPiece(Square piece)
	{
		this.piece = piece;
	}

}
