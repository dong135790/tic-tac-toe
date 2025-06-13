import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import tiktactoe.model.gamedata.Board;
import tiktactoe.model.gamedata.GameEnum;
import tiktactoe.model.gamedata.GameState;
import tiktactoe.model.gamedata.Move;
import tiktactoe.model.gamedata.PlayerRecord;
import tiktactoe.model.player.HumanPlayer;
import tiktactoe.model.player.MediumComputer;

/**
 * Tests the methods declared in the gameState class
 */
public class TestGameState {

    /**
     * Tests whether we can obtain the correct information from playerOne
     */
    @Test
    public void testGetPlayerOne() {
        HumanPlayer humanPlayer = new HumanPlayer("TicTacMaster", GameEnum.O);
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        Board board = new Board();
        GameState gameState = new GameState(humanPlayer, mediumAi, board);
        assertEquals("TicTacMaster", gameState.getPlayerOne().getUsername());
        assertEquals(GameEnum.O, gameState.getPlayerOne().getSymbol());
    }

    /**
     * Tests whether we can obtain the correct information from playerTwo
     */
    @Test
    public void testGetPlayerTwo() {
        HumanPlayer humanPlayer = new HumanPlayer("TicTacMaster", GameEnum.O);
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        Board board = new Board();
        GameState gameState = new GameState(humanPlayer, mediumAi, board);
        assertEquals("MediumAi", gameState.getPlayerTwo().getUsername());
        assertEquals(GameEnum.X, gameState.getPlayerTwo().getSymbol());
    }

    /**
     * Tests if we can assign the current player
     */
    @Test
    public void testSetCurrentPlayer() {
        HumanPlayer humanPlayer = new HumanPlayer("TicTacMaster", GameEnum.O);
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        Board board = new Board();
        GameState gameState = new GameState(humanPlayer, mediumAi, board);
        gameState.setCurrentPlayer(humanPlayer);
        assertNotNull(gameState.getCurrentPlayer());
        assertEquals(humanPlayer, gameState.getCurrentPlayer());
    }

    /**
     * Makes sure getCurrentPlayer returns null if we have not assigned a current player
     */
    @Test
    public void testGetCurrentPlayerNull() {
        HumanPlayer humanPlayer = new HumanPlayer("TicTacMaster", GameEnum.O);
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        Board board = new Board();
        GameState gameState = new GameState(humanPlayer, mediumAi, board);
        assertNull(gameState.getCurrentPlayer());
    }

    /**
     * Makes sure getCurrentPlayer will give back a player given that current player is set
     */
    @Test
    public void testGetCurrentPlayerNotNull() {
        HumanPlayer humanPlayer = new HumanPlayer("TicTacMaster", GameEnum.O);
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        Board board = new Board();
        GameState gameState = new GameState(humanPlayer, mediumAi, board);
        gameState.setCurrentPlayer(humanPlayer);
        assertEquals(humanPlayer, gameState.getCurrentPlayer());
    }

    /**
     * Makes sure that we can get the opponent player
     */
    @Test
    public void testGetOpponentPlayer() {
        HumanPlayer humanPlayer = new HumanPlayer("TicTacMaster", GameEnum.O);
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        Board board = new Board();
        GameState gameState = new GameState(humanPlayer, mediumAi, board);
        gameState.setCurrentPlayer(humanPlayer);
        assertEquals(mediumAi, gameState.getOpponentPlayer());
    }

    /**
     * Makes sure that we can obtain the board from the gameState
     */
    @Test
    public void testGetBoard() {
        HumanPlayer humanPlayer = new HumanPlayer("TicTacMaster", GameEnum.O);
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        Board board = new Board();
        GameState gameState = new GameState(humanPlayer, mediumAi, board);
        assertEquals(board, gameState.getBoard());
    }

    /**
     * Tests to see if we can set the isPlaying to false in gameState
     */
    @Test
    public void testStopGame() {
        HumanPlayer humanPlayer = new HumanPlayer("TicTacMaster", GameEnum.O);
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        Board board = new Board();
        GameState gameState = new GameState(humanPlayer, mediumAi, board);
        gameState.stopGame();
        assertFalse(gameState.isCurrentlyPlaying());
    }

    /**
     * Tests to see if we can reset the board by calling resetGame
     */
    @Test
    public void testResetGame() {
        HumanPlayer humanPlayer = new HumanPlayer("TicTacMaster", GameEnum.O);
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        Board board = new Board();
        GameState gameState = new GameState(humanPlayer, mediumAi, board);
        board.makeMove(mediumAi.getMove(board));
        board.makeMove(new Move(0, 0, GameEnum.O));
        char[][] currentBoard = {
                {'O', ' ', ' '},
                {' ', 'X', ' '},
                {' ', ' ', ' '}
        };
        assertArrayEquals(gameState.getBoard().getBoard(), currentBoard);
        char[][] startBoard = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
        gameState.resetGame();
        assertArrayEquals(gameState.getBoard().getBoard(), startBoard);
    }

    /**
     * Makes sure we can populate the scoreboard session by the players and is initialized to 0
     */
    @Test
    public void testSetPlayerScoreBoard() {
        HumanPlayer humanPlayer = new HumanPlayer("TicTacMaster", GameEnum.O);
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        Board board = new Board();
        GameState gameState = new GameState(humanPlayer, mediumAi, board);
        gameState.setPlayerScoreBoard(humanPlayer, mediumAi);
        assertEquals(0, gameState.getPlayerScoreBoard(humanPlayer).getWins());
        assertEquals(0, gameState.getPlayerScoreBoard(humanPlayer).getLosses());
        assertEquals(0, gameState.getPlayerScoreBoard(humanPlayer).getTies());
    }

    /**
     * Makes sure we can obtain the player's scoreboard from the hashmap
     */
    @Test
    public void testGetPlayerScoreBoard() {
        HumanPlayer humanPlayer = new HumanPlayer("TicTacMaster", GameEnum.O);
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        Board board = new Board();
        GameState gameState = new GameState(humanPlayer, mediumAi, board);
        gameState.setPlayerScoreBoard(humanPlayer, mediumAi);
        PlayerRecord playerOneRecord = gameState.getPlayerScoreBoard(humanPlayer);
        PlayerRecord playerTwoRecord = gameState.getPlayerScoreBoard(mediumAi);
        assertEquals(0, playerOneRecord.getWins());
        assertEquals(0, playerOneRecord.getLosses());
        assertEquals(0, playerOneRecord.getTies());
        assertEquals(0, playerTwoRecord.getWins());
        assertEquals(0, playerTwoRecord.getLosses());
        assertEquals(0, playerTwoRecord.getTies());
    }

    /**
     * Makes sure the player's wins scoreboard are properly updated
     */
    @Test
    public void testSetPlayerScoreBoardAddLossesAndWins() {
        HumanPlayer humanPlayer = new HumanPlayer("TicTacMaster", GameEnum.O);
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        Board board = new Board();
        GameState gameState = new GameState(humanPlayer, mediumAi, board);
        gameState.setPlayerScoreBoard(humanPlayer, mediumAi);
        PlayerRecord playerOneRecord = gameState.getPlayerScoreBoard(humanPlayer);
        PlayerRecord playerTwoRecord = gameState.getPlayerScoreBoard(mediumAi);
        playerOneRecord.addWin();
        playerOneRecord.addWin();
        playerOneRecord.addWin();
        playerTwoRecord.addLoss();
        playerTwoRecord.addLoss();
        playerTwoRecord.addLoss();
        assertEquals(3, playerOneRecord.getWins());
        assertEquals(0, playerOneRecord.getLosses());
        assertEquals(0, playerOneRecord.getTies());
        assertEquals(0, playerTwoRecord.getWins());
        assertEquals(3, playerTwoRecord.getLosses());
        assertEquals(0, playerTwoRecord.getTies());
    }

    /**
     * Makes sure the ties are properly updated in the player's scoreboard
     */
    @Test
    public void testSetPlayerScoreBoardAddTies() {
        HumanPlayer humanPlayer = new HumanPlayer("TicTacMaster", GameEnum.O);
        MediumComputer mediumAi = new MediumComputer("MediumAi", GameEnum.X);
        Board board = new Board();
        GameState gameState = new GameState(humanPlayer, mediumAi, board);
        gameState.setPlayerScoreBoard(humanPlayer, mediumAi);
        PlayerRecord playerOneRecord = gameState.getPlayerScoreBoard(humanPlayer);
        PlayerRecord playerTwoRecord = gameState.getPlayerScoreBoard(mediumAi);
        playerOneRecord.addTies();
        playerOneRecord.addTies();
        playerOneRecord.addTies();
        playerTwoRecord.addTies();
        playerTwoRecord.addTies();
        playerTwoRecord.addTies();
        assertEquals(0, playerOneRecord.getWins());
        assertEquals(0, playerOneRecord.getLosses());
        assertEquals(3, playerOneRecord.getTies());
        assertEquals(0, playerTwoRecord.getWins());
        assertEquals(0, playerTwoRecord.getLosses());
        assertEquals(3, playerTwoRecord.getTies());
    }
}
