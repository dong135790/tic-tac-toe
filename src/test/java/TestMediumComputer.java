import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tiktactoe.model.gamedata.Board;
import tiktactoe.model.gamedata.GameEnum;
import tiktactoe.model.gamedata.Move;
import tiktactoe.model.player.MediumComputer;

/**
 * Tests the methods of the medium computer class
 */
public class TestMediumComputer {

    /**
     * Tests if we can find the correct winning move
     */
    @Test
    public void testFindWinningMove() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        MediumComputer mediumAi2 = new MediumComputer("SecondOne", GameEnum.O);
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.X));
        board.makeMove(new Move(0, 2, GameEnum.O));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 2, GameEnum.O));
        board.makeMove(new Move(1, 1, GameEnum.X));
        board.makeMove(new Move(2, 0, GameEnum.O));
        Move winForAi = mediumAi.findWinningMove(board, GameEnum.X);
        assertEquals(2, winForAi.getRow());
        assertEquals(1, winForAi.getCol());
        assertEquals('X', winForAi.getPlayerSymbolAsChar());

        Move winFor2nd = mediumAi2.findWinningMove(board, GameEnum.O);
        assertEquals(2, winFor2nd.getRow());
        assertEquals(2, winFor2nd.getCol());
        assertEquals('O', winFor2nd.getPlayerSymbolAsChar());
    }

    /**
     * Tests if we can find no winning moves given an empty board
     */
    @Test
    public void testFindWinningMoveNone() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        assertNull(mediumAi.findWinningMove(board, GameEnum.X));
    }

    /**
     * Tests if we can find a blocking move based on the column position
     */
    @Test
    public void testFindBlockMoveCol() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        MediumComputer mediumAi2 = new MediumComputer("SecondOne", GameEnum.O);
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.X));
        board.makeMove(new Move(0, 2, GameEnum.O));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 2, GameEnum.O));
        board.makeMove(new Move(1, 1, GameEnum.X));
        board.makeMove(new Move(2, 0, GameEnum.O));
        board.makeMove(new Move(2, 0, GameEnum.O));
        board.makeMove(new Move(2, 0, GameEnum.O));
        Move blockForMediumAi = mediumAi.findBlockMove(board, GameEnum.X);
        assertEquals(2, blockForMediumAi.getRow());
        assertEquals(2, blockForMediumAi.getCol());
    }

    /**
     * Tests if we can find a winning move based on the row position
     */
    @Test
    public void testFindWinningMoveRow() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi2 = new MediumComputer("SecondOne", GameEnum.O);
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.X));
        board.makeMove(new Move(0, 2, GameEnum.O));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 1, GameEnum.O));
        board.makeMove(new Move(1, 2, GameEnum.O));
        board.makeMove(new Move(2, 0, GameEnum.X));
        board.makeMove(new Move(2, 2, GameEnum.X));
        Move winningRow = mediumAi2.findWinningMove(board, GameEnum.X);
        assertEquals(2, winningRow.getRow());
        assertEquals(1, winningRow.getCol());
    }

    /**
     * Tests if we can find a winning move based on the left right diagonal positions of the board
     */
    @Test
    public void testFindWinningMoveLeftRightDiagonal() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi2 = new MediumComputer("SecondOne", GameEnum.X);
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.X));
        board.makeMove(new Move(0, 2, GameEnum.O));
        board.makeMove(new Move(1, 0, GameEnum.X));
        board.makeMove(new Move(1, 1, GameEnum.O));
        board.makeMove(new Move(1, 2, GameEnum.O));
        board.makeMove(new Move(2, 0, GameEnum.X));
        board.makeMove(new Move(2, 1, GameEnum.X));
        Move winningMove = mediumAi2.findWinningMove(board, GameEnum.O);
        assertEquals(2, winningMove.getRow());
        assertEquals(2, winningMove.getCol());
        assertEquals('O', winningMove.getPlayerSymbolAsChar());
    }

    /**
     * Tests if we can find the winning move based on the right left diagonal positions of the board
     */
    @Test
    public void testFindWinningMoveRightLeftDiagonal() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi2 = new MediumComputer("SecondOne", GameEnum.X);
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.X));
        board.makeMove(new Move(0, 2, GameEnum.O));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 1, GameEnum.O));
        board.makeMove(new Move(1, 2, GameEnum.O));
        board.makeMove(new Move(2, 1, GameEnum.X));
        board.makeMove(new Move(2, 2, GameEnum.X));
        Move winningMove = mediumAi2.findWinningMove(board, GameEnum.O);
        assertEquals(2, winningMove.getRow());
        assertEquals(0, winningMove.getCol());
        assertEquals('O', winningMove.getPlayerSymbolAsChar());
    }

    /**
     * Tests if we can find a blocking move based on the row position
     */
    @Test
    public void testFindBlockMoveRow() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi2 = new MediumComputer("SecondOne", GameEnum.O);
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.X));
        board.makeMove(new Move(0, 2, GameEnum.O));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 1, GameEnum.O));
        board.makeMove(new Move(1, 2, GameEnum.O));
        board.makeMove(new Move(2, 0, GameEnum.X));
        board.makeMove(new Move(2, 2, GameEnum.X));
        Move blockRow = mediumAi2.findBlockMove(board, GameEnum.O);
        assertEquals(2, blockRow.getRow());
        assertEquals(1, blockRow.getCol());
    }

    /**
     * Tests if we can find a blocking move based on the left right diagonal positions of the board
     */
    @Test
    public void testFindBlockMoveLeftRightDiagonal() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi2 = new MediumComputer("SecondOne", GameEnum.X);
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.X));
        board.makeMove(new Move(0, 2, GameEnum.O));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 1, GameEnum.O));
        board.makeMove(new Move(1, 2, GameEnum.O));
        board.makeMove(new Move(2, 0, GameEnum.X));
        board.makeMove(new Move(2, 1, GameEnum.X));
        Move blockRow = mediumAi2.findBlockMove(board, GameEnum.O);
        assertEquals(2, blockRow.getRow());
        assertEquals(2, blockRow.getCol());
    }

    /**
     * Tests if we can find a blocking move based on the right left diagonal positions of the board
     */
    @Test
    public void testFindBlockMoveRightLeftDiagonal() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi2 = new MediumComputer("SecondOne", GameEnum.X);
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.X));
        board.makeMove(new Move(0, 2, GameEnum.O));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 1, GameEnum.O));
        board.makeMove(new Move(1, 2, GameEnum.O));
        board.makeMove(new Move(2, 1, GameEnum.X));
        board.makeMove(new Move(2, 2, GameEnum.X));
        Move blockRow = mediumAi2.findBlockMove(board, GameEnum.O);
        assertEquals(2, blockRow.getRow());
        assertEquals(0, blockRow.getCol());
    }

    /**
     * Tests if blocking moves are none if there are no blocking moves available
     */
    @Test
    public void testFindBlockingMoveNone() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        assertNull(mediumAi.findBlockMove(board, GameEnum.X));
    }

    /**
     * Tests to see if the computer Ai finds a center move given an empty board
     */
    @Test
    public void testGetMoveCenter() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        Move centerMove = mediumAi.getMove(board);
        assertEquals(1, centerMove.getRow());
        assertEquals(1, centerMove.getCol());
    }

    /**
     * Tests to see if the Ai finds an edge move based on the positions of the board
     */
    @Test
    public void testGetMoveEdge() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        board.makeMove(new Move(1, 1, GameEnum.O));
        assertEquals(4, board.getValidEdgeMoves(GameEnum.X).size());
        int row = mediumAi.getMove(board).getRow();
        int col = mediumAi.getMove(board).getCol();
        assertTrue(row == 0 && col == 0
                        || row == 0 && col == 2
                        || row == 2 && col == 0
                        || row == 2 && col == 2,
                "Choices are [0,0], [0,2], [2,0], [2,2]"
        );
    }

    /**
     * Tests to see if the Ai can detect a winning move
     */
    @Test
    public void testGetMoveWinning() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        MediumComputer mediumAi2 = new MediumComputer("SecondOne", GameEnum.O);
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.X));
        board.makeMove(new Move(0, 2, GameEnum.O));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 2, GameEnum.O));
        board.makeMove(new Move(1, 1, GameEnum.X));
        board.makeMove(new Move(2, 0, GameEnum.O));
        Move winning = mediumAi.findWinningMove(board, GameEnum.X);
        assertEquals(2, winning.getRow());
        assertEquals(1, winning.getCol());
        assertEquals('X', winning.getPlayerSymbolAsChar());
    }

    /**
     * Tests to see if the Ai can find a blocking move
     */
    @Test
    public void testGetMoveBlocking() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.X));
        board.makeMove(new Move(0, 2, GameEnum.O));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 2, GameEnum.O));
        board.makeMove(new Move(1, 1, GameEnum.X));
        board.makeMove(new Move(2, 0, GameEnum.O));
        Move blockForMediumAi = mediumAi.findBlockMove(board, GameEnum.X);
        assertEquals(2, blockForMediumAi.getRow());
        assertEquals(2, blockForMediumAi.getCol());
    }

    /**
     * Tests the boolean functions to see if they are able to find a winning move
     */
    @Test
    public void testPossibleWinningMoveFalse() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        assertFalse(mediumAi.possibleWinningMove(board, GameEnum.X));
    }

    /**
     * Tests the boolean functions to see if they are able to find a winning move
     */
    @Test
    public void testPossibleWinningMoveTrue() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.X));
        board.makeMove(new Move(0, 2, GameEnum.O));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 2, GameEnum.O));
        board.makeMove(new Move(1, 1, GameEnum.X));
        board.makeMove(new Move(2, 0, GameEnum.O));
        assertTrue(mediumAi.possibleWinningMove(board, GameEnum.X));
    }

    /**
     * Tests the boolean functions to see if they are able to find a blocking move
     */
    @Test
    public void testPossibleBlockingMoveFalse() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        assertFalse(mediumAi.possibleBlockingMove(board, GameEnum.X));
    }

    /**
     * Tests the boolean functions to see if they are able to find a blocking move
     */
    @Test
    public void testPossibleBlockingMoveTrue() {
        Board board = new Board();
        board.initializeBoard();
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        assertFalse(mediumAi.possibleBlockingMove(board, GameEnum.X));
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.X));
        board.makeMove(new Move(0, 2, GameEnum.O));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 2, GameEnum.O));
        board.makeMove(new Move(1, 1, GameEnum.X));
        board.makeMove(new Move(2, 0, GameEnum.O));
        assertTrue(mediumAi.possibleBlockingMove(board, GameEnum.X));
    }

    /**
     * Tests to see if we can obtain the medium Ai's game record for that gaming session
     */
    @Test
    public void testGetRecord() {
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.O);
        assertEquals(0, mediumAi.getRecord().getWins());
        assertEquals(0, mediumAi.getRecord().getLosses());
        assertEquals(0, mediumAi.getRecord().getTies());
    }
}
