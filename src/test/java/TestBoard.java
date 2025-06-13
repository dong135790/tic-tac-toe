import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import tiktactoe.model.gamedata.Board;
import tiktactoe.model.gamedata.GameEnum;
import tiktactoe.model.gamedata.Move;
import tiktactoe.model.player.HumanPlayer;

/**
 * Tests the methods in the board class
 */
public class TestBoard {

    /**
     * Tests to see if the initialize board creates an empty board of chars
     */
    @Test
    public void testInitializeBoard() {
        Board board = new Board();
        board.initializeBoard();
        char[][] startBoard = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
        assertArrayEquals(startBoard, board.getBoard());
    }

    /**
     * Tests to see if the initialize board returns rows of 3 and columns of 3
     */
    @Test
    public void testGetBoard() {
        Board board = new Board();
        board.initializeBoard();
        assertEquals(3, board.getBoard().length);
        assertEquals(3, board.getBoard()[0].length);
    }

    /**
     * Tests to ensure that each move is added to the move history stack
     */
    @Test
    public void testAddToMoveHistory() {
        Board board = new Board();
        Move move1 = new Move(0, 0, GameEnum.O);
        Move move2 = new Move(3, 0, GameEnum.X);
        Move move3 = new Move(0, 1, GameEnum.O);
        Move move4 = new Move(3, 1, GameEnum.X);
        Move move5 = new Move(0, 2, GameEnum.O);
        ArrayList<GameEnum> listOfMoves = new ArrayList<>();

        board.addToMoveHistory(move1);
        board.addToMoveHistory(move2);
        board.addToMoveHistory(move3);
        board.addToMoveHistory(move4);
        board.addToMoveHistory(move5);

        listOfMoves.add(GameEnum.O);
        listOfMoves.add(GameEnum.X);
        listOfMoves.add(GameEnum.O);
        listOfMoves.add(GameEnum.X);
        listOfMoves.add(GameEnum.O);
        assertEquals(5, board.getMoveHistory().size());
        assertEquals("[O, X, O, X, O]", listOfMoves.toString());
    }

    /**
     * Tests the return of getMoveHistory when the history is empty
     */
    @Test
    public void testGetMoveHistoryEmpty() {
        Board board = new Board();
        board.initializeBoard();
        assertEquals(0, board.getMoveHistory().size());
    }

    /**
     * Checks to see if a move is valid given an empty board
     */
    @Test
    public void testIsValidMove() {
        Board board = new Board();
        board.initializeBoard();
        Move move = new Move(0, 0, GameEnum.O);
        assertTrue(board.isValidMove(move));
    }

    /**
     * Tests if move is invalid by testing the row location (out of bounds)
     */
    @Test
    public void testIsValidMoveRowError() {
        Board board = new Board();
        board.initializeBoard();
        Move move = new Move(3, 0, GameEnum.O);
        assertFalse(board.isValidMove(move));
    }

    /**
     * Tests if move is invalid by testing the column location (out of bounds)
     */
    @Test
    public void testIsValidMoveColError() {
        Board board = new Board();
        board.initializeBoard();
        Move move = new Move(0, 3, GameEnum.O);
        assertFalse(board.isValidMove(move));
    }

    /**
     * Tests if move is invalid by testing the row and column location (out of bounds)
     */
    @Test
    public void testIsValidMoveRowAndColError() {
        Board board = new Board();
        board.initializeBoard();
        Move move = new Move(3, 3, GameEnum.O);
        assertFalse(board.isValidMove(move));
    }

    /**
     * Test isValidMove by placing a move in an already filled slot
     */
    @Test
    public void testIsValidMoveSlotFull() {
        Board board = new Board();
        board.initializeBoard();
        Move move = new Move(0, 0, GameEnum.O);
        board.makeMove(move);
        Move move2 = new Move(0, 0, GameEnum.X);
        assertFalse(board.isValidMove(move2));
    }

    /**
     * Tests if the makeMove method correctly places a move on the board and adds to move history
     */
    @Test
    public void testMakeMove() {
        Board board = new Board();
        board.initializeBoard();
        Move move = new Move(0, 0, GameEnum.O);
        board.makeMove(move);
        assertEquals(1, board.getMoveHistory().size());
        assertEquals('O', board.getBoard()[0][0]);

    }

    /**
     * Tests if isBoardFull returns false if given an empty board
     */
    @Test
    public void testIsBoardFullEmpty() {
        Board board = new Board();
        board.initializeBoard();
        assertFalse(board.isBoardFull());
    }

    /**
     * Tests if isBoardFull returns true if given a full board
     */
    @Test
    public void testIsBoardFullFull() {
        Board board = new Board();
        board.initializeBoard();
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.O));
        board.makeMove(new Move(0, 2, GameEnum.X));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 1, GameEnum.X));
        board.makeMove(new Move(1, 2, GameEnum.O));
        board.makeMove(new Move(2, 0, GameEnum.X));
        board.makeMove(new Move(2, 1, GameEnum.X));
        board.makeMove(new Move(2, 2, GameEnum.O));
        assertEquals("[[O, O, X], [X, X, O], [X, X, O]]", Arrays.deepToString(board.getBoard()));
        assertTrue(board.isBoardFull());
    }

    /**
     * Tests the checkWin method by detecting if a player has won based on their symbol
     */
    @Test
    public void testCheckWin() {
        Board board = new Board();
        board.initializeBoard();
        HumanPlayer humanPlayer = new HumanPlayer("TikTacMaster", GameEnum.X);
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.O));
        board.makeMove(new Move(0, 2, GameEnum.X));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 1, GameEnum.X));
        board.makeMove(new Move(1, 2, GameEnum.O));
        board.makeMove(new Move(2, 0, GameEnum.X));
        board.makeMove(new Move(2, 1, GameEnum.X));
        board.makeMove(new Move(2, 2, GameEnum.O));
        assertTrue(board.checkWin(humanPlayer));
    }

    /**
     * Tests the checkTie method by detecting if both players cannot make a move and the game is not a win/loss
     */
    @Test
    public void testCheckTie() {
        Board board = new Board();
        board.initializeBoard();
        HumanPlayer humanPlayer = new HumanPlayer("TikTacMaster", GameEnum.X);
        HumanPlayer humanPlayer2 = new HumanPlayer("TikTacker", GameEnum.O);
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.O));
        board.makeMove(new Move(0, 2, GameEnum.X));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 1, GameEnum.X));
        board.makeMove(new Move(1, 2, GameEnum.O));
        board.makeMove(new Move(2, 2, GameEnum.X));
        board.makeMove(new Move(2, 1, GameEnum.X));
        board.makeMove(new Move(2, 0, GameEnum.O));
        assertTrue(board.checkTie(humanPlayer, humanPlayer2));
    }

    /**
     * Tests the get valid moves returns a list of valid moves by testing the size of the list returned
     */
    @Test
    public void testGetValidMoves() {
        Board board = new Board();
        board.initializeBoard();
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.O));
        board.makeMove(new Move(0, 2, GameEnum.X));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 1, GameEnum.X));
        board.makeMove(new Move(1, 2, GameEnum.O));
        List<Move> validMoves = board.getValidMoves(GameEnum.O);
        for (Move move : validMoves) {
            int row = move.getRow();
            int col = move.getCol();
            System.out.println("[" + row + "," + col + "]");
        }
        assertEquals(3, validMoves.size());
    }

    /**
     * Tests if getValidEdgeMoves returns a list of valid edge moves by testing the size of the list returned
     */
    @Test
    public void testGetValidEdgeMoves() {
        Board board = new Board();
        board.initializeBoard();
        board.makeMove(new Move(0, 1, GameEnum.O));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 1, GameEnum.X));
        board.makeMove(new Move(1, 2, GameEnum.O));
        board.makeMove(new Move(2, 1, GameEnum.X));
        assertEquals(4, board.getValidEdgeMoves(GameEnum.O).size());
    }

    /**
     * Tests if getRow returns the correct row on the board
     */
    @Test
    public void testGetRow() {
        Board board = new Board();
        board.initializeBoard();
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.O));
        board.makeMove(new Move(0, 2, GameEnum.X));
        assertEquals(3, board.getRow(0).size());
        assertEquals(GameEnum.O, board.getRow(0).get(0));
        assertEquals(GameEnum.O, board.getRow(0).get(1));
        assertEquals(GameEnum.X, board.getRow(0).get(2));
    }

    /**
     * Tests if getCol returns the correct column on the board
     */
    @Test
    public void testGetColumn() {
        Board board = new Board();
        board.initializeBoard();
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(1, 0, GameEnum.O));
        board.makeMove(new Move(2, 0, GameEnum.X));
        assertEquals(3, board.getCol(0).size());
        assertEquals(GameEnum.O, board.getCol(0).get(0));
        assertEquals(GameEnum.O, board.getCol(0).get(1));
        assertEquals(GameEnum.X, board.getCol(0).get(2));
    }

    /**
     * Tests if getLrDiagonal returns the correct slots on of the board from left to right
     */
    @Test
    public void testLrDiagonal() {
        Board board = new Board();
        board.initializeBoard();
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(1, 1, GameEnum.O));
        board.makeMove(new Move(2, 2, GameEnum.X));
        assertEquals(3, board.getLrDiagonal().size());
        assertEquals(GameEnum.O, board.getLrDiagonal().get(0));
        assertEquals(GameEnum.O, board.getLrDiagonal().get(1));
        assertEquals(GameEnum.X, board.getLrDiagonal().get(2));
    }

    /**
     * Tests if getRlDiagonal returns the correct slots on of the board from right to left
     */
    @Test
    public void testRlDiagonal() {
        Board board = new Board();
        board.initializeBoard();
        board.makeMove(new Move(0, 2, GameEnum.O));
        board.makeMove(new Move(1, 1, GameEnum.O));
        board.makeMove(new Move(2, 0, GameEnum.X));
        assertEquals(3, board.getRlDiagonal().size());
        assertEquals(GameEnum.O, board.getRlDiagonal().get(0));
        assertEquals(GameEnum.O, board.getRlDiagonal().get(1));
        assertEquals(GameEnum.X, board.getRlDiagonal().get(2));
    }

    /**
     * Tests if reset board actually resets the board by checking the move history
     */
    @Test
    public void testResetBoard() {
        Board board = new Board();
        board.initializeBoard();
        HumanPlayer humanPlayer = new HumanPlayer("TikTacMaster", GameEnum.X);
        HumanPlayer humanPlayer2 = new HumanPlayer("TikTacker", GameEnum.O);
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.O));
        board.makeMove(new Move(0, 2, GameEnum.X));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 1, GameEnum.X));
        board.makeMove(new Move(1, 2, GameEnum.O));
        board.makeMove(new Move(2, 2, GameEnum.X));
        board.makeMove(new Move(2, 1, GameEnum.X));
        board.makeMove(new Move(2, 0, GameEnum.O));
        assertEquals(9, board.getMoveHistory().size());
        board.resetBoard();
        assertEquals(0, board.getMoveHistory().size());
    }
}
