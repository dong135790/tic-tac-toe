package tiktactoe.controller;

import java.util.Map;

import tiktactoe.model.gamedata.GameState;
import tiktactoe.model.gamedata.LeaderBoard;
import tiktactoe.model.gamedata.Move;
import tiktactoe.model.gamedata.PlayerRecord;
import tiktactoe.model.player.ComputerPlayer;
import tiktactoe.model.player.HumanPlayer;
import tiktactoe.view.GuiMenu;
import tiktactoe.view.TicTacToeGui;

/**
 * Class that controls the logic behind gui application of tic-tac-toe
 */
public class GuiController {

    private GameState gameState;
    private TicTacToeGui gui;

    /**
     * Constructor for GuiController
     * @param gameState The current gameState
     * @param gui The gui application display
     */
    public GuiController(GameState gameState, TicTacToeGui gui) {
        this.gameState = gameState;
        this.gui = gui;
    }

    /**
     * Handles the logic if a user clicks on a slot on the tic-tac-toe board
     * @param row The row of the grid corresponding to the game board
     * @param col The column of the grid corresponding to the game board
     */
    public void clickSlotOnGrid(int row, int col) {
        if (this.gameState.getCurrentPlayer() instanceof HumanPlayer) {
            Move move = new Move(row, col, this.gameState.getCurrentPlayer().getSymbol());
            if (this.gameState.getBoard().isValidMove(move)) {
                this.gameState.getBoard().makeMove(move);
                gui.updateSlot(row, col, this.gameState.getCurrentPlayer().getSymbol());
            } else {
                return;
            }

            if (checkBoardWin()) {
                this.gameState.getCurrentPlayer().getRecord().addWin();
                this.gameState.getOpponentPlayer().getRecord().addLoss();
                gui.displayWinner();
            } else if (checkBoardTie()) {
                this.gameState.getCurrentPlayer().getRecord().addTies();
                this.gameState.getOpponentPlayer().getRecord().addTies();
                gui.displayTie();
            } else {
                switchPlayer();
                if (this.gameState.getCurrentPlayer() instanceof ComputerPlayer) {
                    computerPlayerMove();
                }
            }
        }
    }

    /**
     * Logic that handles the computer Ai move in the board game
     */
    public void computerPlayerMove() {
        ComputerPlayer computerPlayer = (ComputerPlayer) this.gameState.getCurrentPlayer();
        System.out.println("Computer Player Detected");
        Move move = computerPlayer.getMove(this.gameState.getBoard());
        this.gameState.getBoard().makeMove(move);
        this.gui.updateSlot(move.getRow(), move.getCol(), this.gameState.getCurrentPlayer().getSymbol());

        if (checkBoardWin()) {
            this.gameState.getCurrentPlayer().getRecord().addWin();
            this.gameState.getOpponentPlayer().getRecord().addLoss();
            gui.displayWinner();
        } else if (checkBoardTie()) {
            this.gameState.getCurrentPlayer().getRecord().addTies();
            this.gameState.getOpponentPlayer().getRecord().addTies();
            gui.displayTie();
        } else {
            switchPlayer();
        }
    }

    /**
     * Checks to see if a player has won based on the content of the board via gameState
     * @return True if someone has won. False otherwise
     */
    public boolean checkBoardWin() {
        boolean result = false;
        if (this.gameState.getBoard().checkWin(this.gameState.getPlayerOne())) {
            result = true;
        }
        if (this.gameState.getBoard().checkWin(this.gameState.getPlayerTwo())) {
            result = true;
        }
        return result;
    }

    /**
     * Checks to see if a player has tied based on the content of the board via gameState
     * @return True gameState ends with a tie. False otherwise.
     */
    public boolean checkBoardTie() {
        return this.gameState.getBoard().checkTie(this.gameState.getPlayerOne(), this.gameState.getPlayerTwo());
    }

    /**
     * Simply changes the current player after a player has made a move.
     */
    public void switchPlayer() {
        if (this.gameState.getCurrentPlayer() == this.gameState.getPlayerOne()) {
            this.gameState.setCurrentPlayer(this.gameState.getPlayerTwo());
        } else {
            this.gameState.setCurrentPlayer(this.gameState.getPlayerOne());
        }
    }

    /**
     * Helper method that updates the leaderboard and returns to the menu
     */
    public void exitGameSession() {
        // Updates the leaderboard based on session
        LeaderBoard leaderBoard = new LeaderBoard();
        leaderBoard.initializeLeaderBoard();
        gameState.setPlayerScoreBoard(gameState.getPlayerOne(), gameState.getPlayerTwo());
        Map<String, PlayerRecord> sessionInfo = this.gameState.getScoreBoard();
        leaderBoard.updateLeaderBoard(sessionInfo);
        leaderBoard.updateJsonFile();

        // Clears out of tic-tac-toe gui and opens menu options
        gui.dispose();
        this.gameState.getPlayerOne().getRecord().reset();
        GuiMenu menu = new GuiMenu();
        menu.menuOption((HumanPlayer) gameState.getPlayerOne());
    }
}
