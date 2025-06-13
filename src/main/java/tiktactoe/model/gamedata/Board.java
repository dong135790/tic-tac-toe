package tiktactoe.model.gamedata;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import tiktactoe.model.player.PlayerType;

/**
 * Class that controls the data movement of the tic-tac-toe board
 */
public class Board {
    private char[][] board;
    private Stack<Move> moveHistory;

    /**
     * Constructor for the tic-tac-toe board
     */
    public Board() {
        this.board = new char[3][3];
        this.moveHistory = new Stack<>();
    }

    /**
     * Initializes each position of the board to be an empty char
     */
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Obtains the board no matter what the gameState is.
     * @return A 2D array of char that represents the content of the board game
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * Resets the board to be all empty chars and clears the move history
     */
    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        moveHistory.clear();
    }

    /**
     * Whenever a player makes a move, this function should be called. Simply adds a Move object to our move
     * history stack
     * @param move An object representing a player's move (Contains row, col, player Symbol)
     */
    public void addToMoveHistory(Move move) {
        moveHistory.push(move);
    }

    /**
     * Obtains the move history of the current game session
     * @return A stack that consists of player's move history
     */
    public Stack<Move> getMoveHistory() {
        return moveHistory;
    }

    /**
     * Checks whether a move is valid based on the contents of the board
     * @param move A move that a player wants to place on the board game
     * @return True if that move is valid in the board. False otherwise
     */
    public boolean isValidMove(Move move) {
        int row = move.getRow();
        int col = move.getCol();
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            return board[row][col] == ' ';
        }
        return false;
    }

    /**
     * Adds a move to the move history and updates the board based on the position of the move and the
     * player's symbol.
     * @param move An object representing a player's move (Contains row, col, player Symbol)
     */
    public void makeMove(Move move) {
        addToMoveHistory(move);
        int row = move.getRow();
        int col = move.getCol();
        board[row][col] = move.getPlayerSymbolAsChar();
    }

    /**
     * Checks to see if each position is filled on the board game
     * @return True if the board is full. False otherwise
     */
    public boolean isBoardFull() {
        int size = getMoveHistory().size();
        return size >= 9;
    }

    /**
     * Given a player, check to see if that player has won based on their symbol and the current state of the board game
     * @param player A player object
     * @return True if player has won. False otherwise.
     */
    public boolean checkWin(PlayerType player) {
        char[][] currentBoard = this.getBoard();
        char symbol = player.getSymbolAsChar();
        if (
                currentBoard[0][0] == symbol
                && currentBoard[0][1] == symbol
                && currentBoard[0][2] == symbol
        ) {
            return true;
        } else if (
                currentBoard[1][0] == symbol
                && currentBoard[1][1] == symbol
                && currentBoard[1][2] == symbol
        ) {
            return true;
        } else if (
                currentBoard[2][0] == symbol
                && currentBoard[2][1] == symbol
                && currentBoard[2][2] == symbol
        ) {
            return true;
        } else if (
                currentBoard[0][0] == symbol
                && currentBoard[1][0] == symbol
                && currentBoard[2][0] == symbol
        ) {
            return true;
        } else if (
                currentBoard[0][1] == symbol
                && currentBoard[1][1] == symbol
                && currentBoard[2][1] == symbol
        ) {
            return true;
        } else if (
                currentBoard[0][2] == symbol
                && currentBoard[1][2] == symbol
                && currentBoard[2][2] == symbol
        ) {
            return true;
        } else if (
                currentBoard[0][0] == symbol
                && currentBoard[1][1] == symbol
                && currentBoard[2][2] == symbol
        ) {
            return true;
        } else if (
                currentBoard[0][2] == symbol
                && currentBoard[1][1] == symbol
                && currentBoard[2][0] == symbol
        ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks to see if the game state ends with a tie.
     * @param player1 PlayerType object of player one
     * @param player2 PlayerType object of player two
     * @return True if the game ends with a draw. False otherwise.
     */
    public boolean checkTie(PlayerType player1, PlayerType player2) {
        return isBoardFull() && !checkWin(player1) && !checkWin(player2);
    }

    /**
     * Based on the board positions, determine which moves are valid and returns a list of possible moves that are still
     * available
     * @param symbol The symbol of the player that wants to check if their move is valid
     * @return A list of possible moves that are still available
     */
    public List<Move> getValidMoves(GameEnum symbol) {
        List<Move> validMoves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    validMoves.add(new Move(i, j, symbol));
                }
            }
        }
        return validMoves;
    }

    /**
     * Gets the possible edge moves that are still possible to play based on the state of the game
     * @param symbol The symbol of the player that wants to find possible edge moves
     * @return A list of possible edge moves still available
     */
    public List<Move> getValidEdgeMoves(GameEnum symbol) {
        List<Move> allValidMoves = this.getValidMoves(symbol);
        List<Move> edgeMoves = new ArrayList<>();
        for (Move move : allValidMoves) {
            int row = move.getRow();
            int col = move.getCol();
            if (row == 0 && col == 0
                    || row == 0 && col == 2
                    || row == 2 && col == 0
                    || row == 2 && col == 2
            ) {
                edgeMoves.add(move);
            }
        }
        return edgeMoves;
    }

    /**
     * Based on the position given, returns that row of the board
     * @param i Position of the row (0-2 -> represents row 1 to row 3)
     * @return A list of GameEnums of the given game state. Possible Enums are (O, X, None <- for empty)
     */
    public List<GameEnum> getRow(int i) {
        List<GameEnum> rowOne = new ArrayList<>();
        char[][] currentBoard = this.getBoard();
        for (int j = 0; j < 3; j++) {
            rowOne.add(GameEnum.fromChar(currentBoard[i][j]));
        }
        return rowOne;

    }
    /**
     * Based on the position given, returns that column of the board
     * @param j Position of the column (0-2 -> represents column 1 to column 3)
     * @return A list of GameEnums of the given game state. Possible Enums are (O, X, None <- for empty)
     */
    public List<GameEnum> getCol(int j) {
        List<GameEnum> colOne = new ArrayList<>();
        char[][] currentBoard = this.getBoard();
        for (int i = 0; i < 3; i++) {
            colOne.add(GameEnum.fromChar(currentBoard[i][j]));
        }
        return colOne;
    }

    /**
     * Gets the GameEnum/symbol of board[0][0], board[1][1], board[2][2] as a list
     * @return A list of GameEnums of the given game state. Possible Enums are (O, X, None <- for empty)
     */
    public List<GameEnum> getLrDiagonal() {
        List<GameEnum> lrDiagonal = new ArrayList<>();
        char[][] currentBoard = this.getBoard();
        int j = 0;
        for (int i = 0; i < 3; i++) {
            lrDiagonal.add(GameEnum.fromChar(currentBoard[i][j]));
            j++;
        }
        return lrDiagonal;
    }

    /**
     * Gets the GameEnum/symbol of board[0][2], board[1][1], board[2][0] as a list
     * @return A list of GameEnums of the given game state. Possible Enums are (O, X, None <- for empty)
     */
    public List<GameEnum> getRlDiagonal() {
        List<GameEnum> rlDiagonal = new ArrayList<>();
        char[][] currentBoard = this.getBoard();
        int i = 0;
        for (int j = 2; j >= 0; j--) {
            rlDiagonal.add(GameEnum.fromChar(currentBoard[i][j]));
            i++;
        }
        return rlDiagonal;
    }
}
