package tiktactoe.model.player;

import tiktactoe.model.gamedata.GameEnum;
import tiktactoe.model.gamedata.PlayerRecord;

/**
 * An abstract class that contains multiple methods that are shared among all Player types
 */
public abstract class PlayerType {
    protected String username;
    protected GameEnum symbol;
    protected PlayerRecord record;

    /**
     * Constructor that requires each subclass to require a name and their corresponding symbol
     * @param name The username of the player
     * @param symbol The player's symbol (X or O)
     */
    public PlayerType(String name, GameEnum symbol) {
        this.username = name;
        this.symbol = symbol;
        this.record = new PlayerRecord();
    }

    /**
     * Obtains the username from the data member
     * @return A string representation of the player's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Converts the GameEnum symbol into a char
     * @return A char representation for a player's symbol
     */
    public char getSymbolAsChar() {
        String symbol = this.symbol.toString();
        return symbol.charAt(0);
    }

    /**
     * Obtains the player's game symbol as an enum
     * @return An enum of the player's symbol
     */
    public GameEnum getSymbol() {
        return symbol;
    }

    /**
     * Obtains the player's record of that gaming session
     * @return An object of a player's PlayerRecord
     */
    public PlayerRecord getRecord() {
        return record;
    }
}
