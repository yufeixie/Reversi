package Game;

/**
 * 
 * Represents a player of Othello.
 * The player carries their score and piece.
 *
 */
public class Player implements GamePlayer{
	
	private final String name;
	private int score;
	private final Piece piece;

	public Player(String name, int score, Piece piece)
	{
		this.name = name;
		this.score = score;
		this.piece = piece;
	}
	
	@Override
	public int getScore()
	{
		return score;
	}
	
	public Piece getPiece()
	{
		return piece;
	}
	
	public void adjustScore(int change)
	{
		score += change;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
