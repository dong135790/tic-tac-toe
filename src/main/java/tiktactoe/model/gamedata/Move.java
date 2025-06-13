package tiktactoe.model.gamedata;

/**
 * Class that represents a player's move
 */
public class Move {
    private int row;
    private int col;
    private GameEnum playerSymbol;

    /**
     * Constructor for the move class
     * @param row The row position of the move
     * @param col The column position of the move
     * @param playerSymbol The symbol of the player making the move
     */
    public Move(int row, int col, GameEnum playerSymbol) {
        this.row = row;
        this.col = col;
        this.playerSymbol = playerSymbol;
    }

    /**
     * Obtains the row of the move
     * @return An int representation of the row number
     */
    public int getRow() {
        return row;
    }

    /**
     * Obtains the column of the move
     * @return An int representation of the column number
     */
    public int getCol() {
        return col;
    }

    /**
     * Converts the player's symbol to a char
     * @return A char representation of the player's symbol
     */
    public char getPlayerSymbolAsChar() {
        String symbol = playerSymbol.toString();
        return symbol.charAt(0);
    }
}
