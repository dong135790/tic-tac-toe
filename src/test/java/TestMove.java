import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tiktactoe.model.gamedata.GameEnum;
import tiktactoe.model.gamedata.Move;

/**
 * Tests the Move class methods
 */
public class TestMove {

    /**
     * Tests if we can obtain the row position of the move
     */
    @Test
    public void testGetRow() {
        Move move = new Move(2, 1, GameEnum.O);
        assertEquals(2, move.getRow());
    }

    /**
     * Tests if we can obtain the column position of the move
     */
    @Test
    public void testGetColumn() {
        Move move = new Move(2, 1, GameEnum.O);
        assertEquals(1, move.getCol());
    }

    /**
     * Tests if we can obtain the player's symbol as a char
     */
    @Test
    public void testGetPlayerSymbol() {
        Move move = new Move(2, 1, GameEnum.O);
        assertEquals('O', move.getPlayerSymbolAsChar());
    }
}
