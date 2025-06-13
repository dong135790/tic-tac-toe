package tiktactoe.model.gamedata;

import java.util.HashMap;
import java.util.Map;

import tiktactoe.model.player.PlayerType;

/**
 * Stores the information of the current gameState
 */
public class GameState {
    private PlayerType player1;
    private PlayerType player2;
    private PlayerType currentPlayer;
    private Board board;
    private boolean currentlyPlaying;
    private Map<String, PlayerRecord> scoreBoard;

    /**
     * Constructor for GameState
     * @param player1 Player One
     * @param player2 Player Two
     * @param board The data representation of the board of the tic-tac-toe game
     */
    public GameState(PlayerType player1, PlayerType player2, Board board) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
        this.board.initializeBoard();
        this.currentPlayer = null;
        this.currentlyPlaying = true;
        this.scoreBoard = new HashMap<>();
    }

    /**
     * Obtains player one's object
     * @return The object of player One
     */
    public PlayerType getPlayerOne() {
        return player1;
    }

    /**
     * Obtains player two's object
     * @return The object of player two
     */
    public PlayerType getPlayerTwo() {
        return player2;
    }

    /**
     * Sets the current player between the two players
     * @param player The player who would be the current player
     */
    public void setCurrentPlayer(PlayerType player) {
        this.currentPlayer = player;
    }

    /**
     * Gets the current player that is playing
     * @return Object of the current player
     */
    public PlayerType getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets the opponent player that is about to go after the current player makes a move
     * @return Object of the opposing player
     */
    public PlayerType getOpponentPlayer() {
        if (currentPlayer == player1) {
            return player2;
        } else {
            return player1;
        }
    }

    /**
     * Obtains the data representation of the board game
     * @return The tic-tac-toe board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Sets the currentlyPlaying value to false
     */
    public void stopGame() {
        this.currentlyPlaying = false;
    }

    /**
     * Resets the gamestate
     */
    public void resetGame() {
        this.currentPlayer = null;
        this.currentlyPlaying = true;
        this.scoreBoard.clear();
        this.board.initializeBoard();
    }

    /**
     * Checks to see if the game is still ongoing
     * @return True if the game is still ongoing. False otherwise
     */
    public boolean isCurrentlyPlaying() {
        return currentlyPlaying;
    }

    /**
     * Initializes the scoreboard based on their record (Should be all 0 for win/loss/tie)
     * @param playerOne Object of Player One
     * @param playerTwo Object of Player Two
     */
    public void setPlayerScoreBoard(PlayerType playerOne, PlayerType playerTwo) {
        String playerOneName = playerOne.getUsername();
        String playerTwoName = playerTwo.getUsername();
        scoreBoard.put(playerOneName, playerOne.getRecord());
        scoreBoard.put(playerTwoName, playerTwo.getRecord());
    }

    /**
     * Returns a player's record of win/loss/tie
     * @param player Player Object that is used to obtain their record of the current gameState.
     * @return The PlayerRecord object that stores their win/loss/tie record of the current gameState.
     */
    public PlayerRecord getPlayerScoreBoard(PlayerType player) {
        return scoreBoard.get(player.getUsername());
    }

    /**
     * Returns the hashmap of the player and their game record for this game session
     * @return A map representation that stores a player as the key and their record of the current game session
     */
    public Map<String, PlayerRecord> getScoreBoard() {
        return scoreBoard;
    }
}
