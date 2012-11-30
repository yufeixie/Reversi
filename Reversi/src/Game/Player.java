package Game;

public class Player {
	
	private String name;
	private int score;
	private Piece piece;

	public Player(String name, int score, Piece piece)
	{
		this.name = name;
		this.score = score;
		this.piece = piece;
	}
	
	String getName()
	{
		return name;
	}
	
	int getScore()
	{
		return score;
	}
	
	Piece getPiece()
	{
		return piece;
	}
	
	void setScore(int change)
	{
		score += change;
	}
	
	@Override
	public String toString()
	{
		return null;
	}
}
