package tiktactoe.model.player;

import tiktactoe.model.gamedata.GameEnum;

/**
 * Human player class that inherits from PlayerType
 */
public class HumanPlayer extends PlayerType {

    /**
     * Constructor for humanPlayer
     * @param username The player's username
     * @param symbol The player's symbol
     */
    public HumanPlayer(String username, GameEnum symbol) {
        super(username, symbol);
    }
}
