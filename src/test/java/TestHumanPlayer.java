import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tiktactoe.model.gamedata.GameEnum;
import tiktactoe.model.player.HumanPlayer;

/**
 * Tests the methods of the abstract class PlayerType mainly since Human player does not have any methods defined
 */
public class TestHumanPlayer {

    /**
     * Tests if we can obtain the username of the human player
     */
    @Test
    public void testGetUsername() {
        HumanPlayer humanPlayer = new HumanPlayer("TikTacMaster", GameEnum.O);
        assertEquals("TikTacMaster", humanPlayer.getUsername());
    }

    /**
     * Tests if we can obtain the player's symbol as a char
     */
    @Test
    public void testGetSymbolAsChar() {
        HumanPlayer humanPlayer = new HumanPlayer("TikTacMaster", GameEnum.O);
        assertEquals('O', humanPlayer.getSymbolAsChar());
    }

    /**
     * Tests if we can obtain the player's symbol as a GameEnum
     */
    @Test
    public void testGetSymbol() {
        HumanPlayer humanPlayer = new HumanPlayer("TikTacMaster", GameEnum.O);
        assertEquals(GameEnum.O, humanPlayer.getSymbol());
    }

    /**
     * Tests if we can obtain the player's record based on that game session
     */
    @Test
    public void testGetRecord() {
        HumanPlayer humanPlayer = new HumanPlayer("TikTacMaster", GameEnum.O);
        assertEquals(0, humanPlayer.getRecord().getWins());
        assertEquals(0, humanPlayer.getRecord().getLosses());
        assertEquals(0, humanPlayer.getRecord().getTies());
    }
}
