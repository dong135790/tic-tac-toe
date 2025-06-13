import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import tiktactoe.model.gamedata.Board;
import tiktactoe.model.gamedata.GameEnum;
import tiktactoe.model.gamedata.Move;
import tiktactoe.model.player.EasyComputer;
import tiktactoe.model.player.HumanPlayer;

/**
 * Tests the methods in the easyComputer class
 */
public class TestEasyComputer {

    /**
     * Tests to see if the getRandom method returns a value within the bounds
     */
    @Test
    public void testGetRandom() {
        EasyComputer computer = new EasyComputer("EasyAi", GameEnum.X);
        Board board = new Board();
        board.initializeBoard();
        int random = computer.getRandom(board);
        boolean flag = false;
        if (random >= 0 && random < 10) {
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Tests to see if getMove will return the correct move given only one slot available
     */
    @Test
    public void testGetMove() {
        Board board = new Board();
        board.initializeBoard();
        HumanPlayer humanPlayer = new HumanPlayer("TikTacMaster", GameEnum.X);
        EasyComputer computer = new EasyComputer("EasyAi", GameEnum.O);
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.X));
        board.makeMove(new Move(0, 2, GameEnum.O));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 1, GameEnum.O));
        board.makeMove(new Move(1, 2, GameEnum.X));
        board.makeMove(new Move(2, 0, GameEnum.O));
        board.makeMove(new Move(2, 1, GameEnum.X));
        Move computerMove = computer.getMove(board);
        assertEquals(2, computerMove.getRow());
        assertEquals(2, computerMove.getCol());
    }

    /**
     * Tests to see if the move given is null if the board is full
     */
    @Test
    public void testGetMoveBoardFull() {
        Board board = new Board();
        board.initializeBoard();
        HumanPlayer humanPlayer = new HumanPlayer("TikTacMaster", GameEnum.X);
        EasyComputer computer = new EasyComputer("EasyAi", GameEnum.O);
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.X));
        board.makeMove(new Move(0, 2, GameEnum.O));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 1, GameEnum.O));
        board.makeMove(new Move(1, 2, GameEnum.X));
        board.makeMove(new Move(2, 0, GameEnum.O));
        board.makeMove(new Move(2, 1, GameEnum.X));
        board.makeMove(new Move(2, 2, GameEnum.O));
        assertNull(computer.getMove(board));
    }

    /**
     * Tests to see if the valid moves that can be placed is the correct number given a almost filled up board
     */
    @Test
    public void testGetMoveBoardHalfFull() {
        Board board = new Board();
        board.initializeBoard();
        board.makeMove(new Move(0, 0, GameEnum.O));
        board.makeMove(new Move(0, 1, GameEnum.X));
        board.makeMove(new Move(0, 2, GameEnum.O));
        board.makeMove(new Move(1, 0 , GameEnum.X));
        board.makeMove(new Move(1, 1, GameEnum.O));
        List<Move> moves = board.getValidMoves(GameEnum.X);
        assertEquals(4, moves.size());
    }

    /**
     * Tests to see how many moves are available for the Ai to choose from given an empty board
     */
    @Test
    public void tstGetMoveBoardEmpty() {
        Board board = new Board();
        board.initializeBoard();
        List<Move> moves = board.getValidMoves(GameEnum.X);
        assertEquals(9, moves.size());
    }

    /**
     * Tests to see if we can obtain the record of an EasyAi computer
     */
    @Test
    public void testGetRecord() {
        EasyComputer easyAi = new EasyComputer("EasyAi", GameEnum.O);
        assertEquals(0, easyAi.getRecord().getWins());
        assertEquals(0, easyAi.getRecord().getLosses());
        assertEquals(0, easyAi.getRecord().getTies());
    }
}
