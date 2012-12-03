package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.*;

public class RevTests {
	
	
	/*
	 * Piece Tests
	 */
	@Test
	public void testPieceStrings(){
		Piece square = Piece.BLACK;
		assertTrue(square.toString().equals("X"));
		square = Piece.WHITE;
		assertTrue(square.toString().equals("O"));
		square = Piece.EMPTY;
		assertTrue(square.toString().equals(" "));
	}
	
	@Test
	public void testPieceOpposites(){
		Piece square = Piece.BLACK;
		assertEquals(square.opposite(), Piece.WHITE);
		square = Piece.WHITE;
		assertEquals(square.opposite(), Piece.BLACK);
		square = Piece.EMPTY;
		assertEquals(square.opposite(), Piece.EMPTY);
	}
	
	/*
	 * Square Tests
	 */
	@Test
	public void testGetPieceAndInitialisation(){
		Square piece;
		
		piece = new Square(Piece.BLACK);
		assertEquals(piece.getPiece(), Piece.BLACK);
		piece = new Square(Piece.WHITE);
		assertEquals(piece.getPiece(), Piece.WHITE);
		piece = new Square(Piece.EMPTY);
		assertEquals(piece.getPiece(), Piece.EMPTY);
	}
	
	@Test
	public void testSetPiece(){
		Square piece;
		
		piece = new Square(Piece.BLACK);
		piece.setPiece(Piece.WHITE);
		assertEquals(piece.getPiece(), Piece.WHITE);
		
		piece.setPiece(Piece.EMPTY);
		assertEquals(piece.getPiece(), Piece.EMPTY);
		
		piece.setPiece(Piece.BLACK);
		assertEquals(piece.getPiece(), Piece.BLACK);
		
		piece.setPiece(Piece.WHITE);
		assertEquals(piece.getPiece(), Piece.WHITE);
		
		piece.setPiece(Piece.BLACK);
		assertEquals(piece.getPiece(), Piece.BLACK);
		
		piece.setPiece(Piece.EMPTY);
		assertEquals(piece.getPiece(), Piece.EMPTY);
		
		piece.setPiece(Piece.WHITE);
		assertEquals(piece.getPiece(), Piece.WHITE);
	}
	
	/*
	 * Player Tests
	 */
	public void testPlayerInitialisation(){
		String name = "playerName";
		int score = 2;
		Piece square = Piece.BLACK;
		Player player = new Player(name, score, square);
		assertTrue(name.equals(player.toString()));
		assertEquals(score, player.getScore());
		assertEquals(square, player.getPiece());
	}
	
	public void testPlayerScoreChange(){
		int score = 2;
		int adjustment = 0;
		Player player = new Player("name", score, Piece.BLACK);
		
		player.adjustScore(adjustment);
		score += adjustment;
		assertEquals(score, player.getScore());
		
		adjustment = 12;
		player.adjustScore(adjustment);
		score += adjustment;
		assertEquals(score, player.getScore());
		
		adjustment = -7;
		player.adjustScore(adjustment);
		score += adjustment;
		assertEquals(score, player.getScore());	
	}
	
	@Test
	public void testBoardCreation() {
		Board gameBoard = new Board();
		
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (1, 1), Piece.BLACK));
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (1, 1), Piece.WHITE));
		
		
		assertNotNull(gameBoard.checkMoves(new Pair<Integer, Integer> (3, 4), Piece.BLACK));
		assertNotNull(gameBoard.checkMoves(new Pair<Integer, Integer> (4, 3), Piece.BLACK));
		assertNotNull(gameBoard.checkMoves(new Pair<Integer, Integer> (5, 6), Piece.BLACK));
		assertNotNull(gameBoard.checkMoves(new Pair<Integer, Integer> (6, 5), Piece.BLACK));
		
		assertNotNull(gameBoard.checkMoves(new Pair<Integer, Integer> (3, 5), Piece.WHITE));
		assertNotNull(gameBoard.checkMoves(new Pair<Integer, Integer> (5, 3), Piece.WHITE));
		assertNotNull(gameBoard.checkMoves(new Pair<Integer, Integer> (4, 6), Piece.WHITE));
		assertNotNull(gameBoard.checkMoves(new Pair<Integer, Integer> (6, 4), Piece.WHITE));
		
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (3, 3), Piece.WHITE));
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (3, 3), Piece.BLACK));
		
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (3, 6), Piece.WHITE));
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (3, 6), Piece.BLACK));
		
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (6, 3), Piece.WHITE));
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (6, 3), Piece.BLACK));
		
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (6, 6), Piece.WHITE));
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (6, 6), Piece.BLACK));
		
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (4, 4), Piece.WHITE));
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (4, 4), Piece.BLACK));
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (4, 5), Piece.WHITE));
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (4, 5), Piece.BLACK));
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (5, 4), Piece.WHITE));
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (5, 4), Piece.BLACK));
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (5, 5), Piece.WHITE));
		assertNull(gameBoard.checkMoves(new Pair<Integer, Integer> (5, 5), Piece.BLACK));
		
	}

}
