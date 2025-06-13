package tiktactoe.model.player;

import static tiktactoe.model.gamedata.GameEnum.NONE;

import java.util.List;

import tiktactoe.model.gamedata.Board;
import tiktactoe.model.gamedata.GameEnum;
import tiktactoe.model.gamedata.Move;

/**
 * An abstract class of Computer player that inherits from abstract class PlayerType
 */
public abstract class ComputerPlayer extends PlayerType {

    /**
     * Constructor for computer player that inherits via super
     * @param username The computer player's name
     * @param symbol The computer player's symbol
     */
    public ComputerPlayer(String username, GameEnum symbol) {
        super(username, symbol);
    }

    /**
     * Declared method that will obtain the computer player's move
     * @param board The board of the tic-tac-toe game in data representation
     * @return A Move object that represents the computer player's chosen move
     */
    public abstract Move getMove(Board board);

    /**
     * Given the board and the player's symbol. Will detect moves that needs to be blocked to prevent a loss
     * @param board The board of the tic-tac-toe game in data representation
     * @param symbol The computer player's symbol
     * @return A move object that represents a move that needs to be blocked to prevent a loss
     */
    public Move findBlockMove(Board board, GameEnum symbol) {
        GameEnum opponentSymbol;
        if (symbol == GameEnum.X) {
            opponentSymbol = GameEnum.O;
        } else {
            opponentSymbol = GameEnum.X;
        }
        // Check Each column
        for (int i = 0; i < 3; i++) {
            int opponentCount = 0;
            int ourCount = 0;
            int nullsFound = 0;
            // Each individual row's content in GameEnum
            List<GameEnum> columnContent = board.getCol(i);

            // Loop thru this content and look at gameEnums
            for (GameEnum c : columnContent) {
                if (c == opponentSymbol) {
                    opponentCount++;
                } else if (c == symbol) {
                    ourCount++;
                } else {
                    nullsFound++;
                }
            }
            // If there are more than 1 empty slot in this row, continue
            if (nullsFound == 1 && opponentCount == 2) {
                return new Move(columnContent.indexOf(NONE), i, symbol);
            }
        }
        for (int i = 0; i < 3; i++) {
            int opponentCount = 0;
            int ourCount = 0;
            int nullsFound = 0;
            List<GameEnum> rowContent = board.getRow(i);
            for (GameEnum c : rowContent) {
                if (c == opponentSymbol) {
                    opponentCount++;
                } else if (c == symbol) {
                    ourCount++;
                } else {
                    nullsFound++;
                }
            }
            if (nullsFound == 1 && opponentCount == 2) {
                return new Move(i, rowContent.indexOf(NONE), symbol);
            }
        }
        List<GameEnum> lrDiagonal = board.getLrDiagonal();
        int opponentCount = 0;
        int ourCount = 0;
        int nullsFound = 0;
        for (GameEnum c : lrDiagonal) {
            if (c == opponentSymbol) {
                opponentCount++;
            } else if (c == symbol) {
                ourCount++;
            } else {
                nullsFound++;
            }
        }
        if (nullsFound == 1 && opponentCount == 2) {
            return new Move(lrDiagonal.indexOf(NONE), lrDiagonal.indexOf(NONE), symbol);
        }
        List<GameEnum> rlDiagonal = board.getRlDiagonal();
        opponentCount = 0;
        ourCount = 0;
        nullsFound = 0;
        for (GameEnum c : rlDiagonal) {
            if (c == opponentSymbol) {
                opponentCount++;
            } else if (c == symbol) {
                ourCount++;
            } else {
                nullsFound++;
            }
        }
        if (nullsFound == 1 && opponentCount == 2) {
            return new Move(rlDiagonal.indexOf(NONE), 2 - rlDiagonal.indexOf(NONE) , symbol);
        }
        return null;
    }

    /**
     * Given the board and the player's symbol. Will detect moves that needs to be placed to result in a win
     * @param board The board of the tic-tac-toe game in data representation
     * @param symbol The computer player's symbol
     * @return A move object that represents a move that results in a win
     */
    public Move findWinningMove(Board board, GameEnum symbol) {
        GameEnum opponentSymbol;
        if (symbol == GameEnum.X) {
            opponentSymbol = GameEnum.O;
        } else {
            opponentSymbol = GameEnum.X;
        }
        // Check Each column
        for (int i = 0; i < 3; i++) {
            int opponentCount = 0;
            int ourCount = 0;
            int nullsFound = 0;
            // Each individual column's content in GameEnum
            List<GameEnum> rowContent = board.getCol(i);

            // Loop thru this content and look at gameEnums
            for (GameEnum c : rowContent) {
                if (c == opponentSymbol) {
                    opponentCount++;
                } else if (c == symbol) {
                    ourCount++;
                } else {
                    nullsFound++;
                }
            }
            // If there are more than 1 empty slot in this row, continue
            if (nullsFound == 1 && ourCount == 2) {
                // [X X NONE]
                return new Move(rowContent.indexOf(NONE), i, symbol);
            }
        }
        for (int i = 0; i < 3; i++) {
            int opponentCount = 0;
            int ourCount = 0;
            int nullsFound = 0;
            List<GameEnum> rowContent = board.getRow(i);
            for (GameEnum c : rowContent) {
                if (c == opponentSymbol) {
                    opponentCount++;
                } else if (c == symbol) {
                    ourCount++;
                } else {
                    nullsFound++;
                }
            }
            if (nullsFound == 1 && ourCount == 2) {
                return new Move(i, rowContent.indexOf(NONE), symbol);
            }
        }
        List<GameEnum> lrDiagonal = board.getLrDiagonal();
        int opponentCount = 0;
        int ourCount = 0;
        int nullsFound = 0;
        for (GameEnum c : lrDiagonal) {
            if (c == opponentSymbol) {
                opponentCount++;
            } else if (c == symbol) {
                ourCount++;
            } else {
                nullsFound++;
            }
        }
        if (nullsFound == 1 && ourCount == 2) {
            return new Move(lrDiagonal.indexOf(NONE), lrDiagonal.indexOf(NONE), symbol);
        }
        List<GameEnum> rlDiagonal = board.getRlDiagonal();

        opponentCount = 0;
        ourCount = 0;
        nullsFound = 0;
        for (GameEnum c : rlDiagonal) {
            if (c == opponentSymbol) {
                opponentCount++;
            } else if (c == symbol) {
                ourCount++;
            } else {
                nullsFound++;
            }
        }
        if (nullsFound == 1 && ourCount == 2) {
            // O O NONE
            return new Move(rlDiagonal.indexOf(NONE), 2 - rlDiagonal.indexOf(NONE) , symbol);
        }
        return null;
    }

    /**
     * Given the board and the player's symbol. Will determine if there is a move that can result in a win
     * @param board The board of the tic-tac-toe game in data representation
     * @param symbol The computer player's symbol
     * @return True if there is a possible winning move. False otherwise
     */
    public boolean possibleWinningMove(Board board, GameEnum symbol) {
        return findWinningMove(board, symbol) != null;
    }

    /**
     * Given the board and the player's symbol. Will determine if there is a move that can result in a loss
     * @param board The board of the tic-tac-toe game in data representation
     * @param symbol The computer player's symbol
     * @return True if there is a possible blocking move. False otherwise
     */
    public boolean possibleBlockingMove(Board board, GameEnum symbol) {
        return findBlockMove(board, symbol) != null;
    }
}
