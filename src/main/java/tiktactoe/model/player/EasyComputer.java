package tiktactoe.model.player;

import java.util.List;
import java.util.Random;

import tiktactoe.model.gamedata.Board;
import tiktactoe.model.gamedata.GameEnum;
import tiktactoe.model.gamedata.Move;

/**
 * Easy computer class that inherits from computer player abstract class
 */
public class EasyComputer extends ComputerPlayer {
    private Random random;

    /**
     * Constructor for easyComputer
     * @param username The username for easy computer
     * @param symbol The easy computer's symbol
     */
    public EasyComputer(String username, GameEnum symbol) {
        super(username, symbol);
        random = new Random();
    }

    /**
     * Obtains a random move based on the valid moves on the board.
     * @param board The tic-tac-toe board in data representation
     * @return A random int that is in the bounds of valid moves.
     */
    public int getRandom(Board board) {
        List<Move> validMoves = board.getValidMoves(this.getSymbol());
        int size = validMoves.size();
        return random.nextInt(size);
    }

    /**
     * Overrides and defines the getMove method declared in ComputerPlayer.
     * This checks the board for potential moves and places a move randomly
     * @param board The tic-tac-toe board in data representation
     * @return The move object that represents the move that the computer wants to place
     */
    @Override
    public Move getMove(Board board) {
        List<Move> validMoves = board.getValidMoves(this.getSymbol());
        if (validMoves.isEmpty()) {
            return null;
        }
        return validMoves.get(this.getRandom(board));
    }
}
