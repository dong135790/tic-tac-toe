package tiktactoe.model.player;

import java.util.List;
import java.util.Random;

import tiktactoe.model.gamedata.Board;
import tiktactoe.model.gamedata.GameEnum;
import tiktactoe.model.gamedata.Move;

/**
 * A class of the medium computer Ai that extends from the ComputerPlayer class
 */
public class MediumComputer extends ComputerPlayer {

    /**
     * Constructor for the medium computer
     * @param username Username for medium computer
     * @param symbol Symbol for medium computer
     */
    public MediumComputer(String username, GameEnum symbol) {
        super(username, symbol);
    }

    /**
     * Overrides and defines the getMove method declared in ComputerPlayer.
     * This checks the board for potential moves and gets that move depending on win/loss conditions and priority pieces
     * @param board The tic-tac-toe board in data representation
     * @return The move object that represents the move that the computer wants to place
     */
    @Override
    public Move getMove(Board board) {
        List<Move> validMoves = board.getValidMoves(this.getSymbol());
        if (possibleWinningMove(board, this.getSymbol())) {
            return this.findWinningMove(board, this.getSymbol());
        }
        if (possibleBlockingMove(board, this.getSymbol())) {
            return this.findBlockMove(board, this.getSymbol());
        }
        // Prioritize center
        boolean centerPiece = validMoves
                .stream()
                .anyMatch(m -> m.getRow() == 1 && m.getCol() == 1);
        if (centerPiece) {
            for (Move move : validMoves) {
                if (move.getRow() == 1 && move.getCol() == 1) {
                    return move;
                }
            }
        }
        // Prioritize Edge
        List<Move> validEdgeMoves = board.getValidEdgeMoves(this.getSymbol());
        if (!validEdgeMoves.isEmpty()) {
            Random randomMove = new Random();
            return validEdgeMoves.get(randomMove.nextInt(validEdgeMoves.size()));
        // Random move
        } else {
            Random randomMove = new Random();
            if (validMoves.isEmpty()) {
                System.out.println("No valid moves found");
                return null;
            }
            return validMoves.get(randomMove.nextInt(validMoves.size()));
        }
    }
}
