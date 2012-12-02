package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.Board;
import Game.Square;

public class RevTests {
	@Test
	public void testSetPiece() {
		Board gameBoard = new Board();

		gameBoard.setPiece(3, 4, Square.BLACK);

		assertEquals(Square.BLACK, gameBoard.getPiece(3, 4).getPiece());
	}

	@Test
	public void testValidMoves() {
		Board gameBoard = new Board();

		assertTrue(gameBoard.isValidMove(3, 4, Square.BLACK));
		assertTrue(gameBoard.isValidMove(4, 3, Square.BLACK));
		assertTrue(gameBoard.isValidMove(5, 6, Square.BLACK));
		assertFalse(gameBoard.isValidMove(3, 5, Square.BLACK));
		assertFalse(gameBoard.isValidMove(10, 5, Square.BLACK));
		assertFalse(gameBoard.isValidMove(4, 4, Square.BLACK));
	}

	@Test
	public void testCapture() {
		Board gameBoard = new Board();

		gameBoard.setPiece(3, 4, Square.BLACK);
		gameBoard.capturePieces(3, 4, Square.BLACK);

		assertEquals(Square.BLACK, gameBoard.getPiece(4, 4).getPiece());
	}

	@Test
	public void testCaptureSetSequence() {
		Board gameBoard = new Board();

		gameBoard.setPiece(4, 3, Square.BLACK);
		gameBoard.capturePieces(4, 3, Square.BLACK);

		assertEquals(Square.BLACK, gameBoard.getPiece(4, 4).getPiece());

		gameBoard.setPiece(3, 5, Square.WHITE);
		gameBoard.capturePieces(3, 5, Square.WHITE);

		assertEquals(Square.WHITE, gameBoard.getPiece(4, 5).getPiece());
	}

	@Test
	public void testNoValidMove() {
		Board gameBoard = new Board();
		gameBoard.setPiece(4, 4, Square.BLACK);
		gameBoard.setPiece(5, 5, Square.BLACK);
		assertFalse(gameBoard.validMoveExists(Square.WHITE));
	}


	@Test
	public void testHasValidMoves() {
		Board gameBoard = new Board(4);

		assertTrue(gameBoard.validMoveExists(Square.BLACK));
		assertTrue(gameBoard.validMoveExists(Square.WHITE));

		gameBoard.setPiece(2, 1, Square.BLACK);
		gameBoard.capturePieces(2, 1, Square.BLACK);
		gameBoard.setPiece(4, 3, Square.BLACK);
		gameBoard.capturePieces(4, 3, Square.BLACK);

		assertFalse(gameBoard.validMoveExists(Square.WHITE));
	}

}
