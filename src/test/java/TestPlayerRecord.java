import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tiktactoe.model.gamedata.PlayerRecord;

/**
 * Tests the methods in the Player Record class
 */
public class TestPlayerRecord {

    /**
     * Tests if we can obtain the wins of that player's records
     */
    @Test
    public void testGetWins() {
        PlayerRecord playerRecord = new PlayerRecord();
        assertEquals(0, playerRecord.getWins());
    }

    /**
     * Tests if we can obtain the loss of that player's records
     */
    @Test
    public void testGetLosses() {
        PlayerRecord playerRecord = new PlayerRecord();
        assertEquals(0, playerRecord.getLosses());
    }

    /**
     * Tests if we can obtain the ties of that player's records
     */
    @Test void testGetTies() {
        PlayerRecord playerRecord = new PlayerRecord();
        assertEquals(0, playerRecord.getTies());
    }

    /**
     * Tests if we can update the player's win record by one each function call
     */
    @Test
    public void testAddWins() {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.addWin();
        playerRecord.addWin();
        assertEquals(2, playerRecord.getWins());
    }

    /**
     * Tests if we can update the player's loss record by one each function call
     */
    @Test
    public void testAddLoss() {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.addLoss();
        playerRecord.addLoss();
        playerRecord.addLoss();
        assertEquals(3, playerRecord.getLosses());
    }

    /**
     * Tests if we can update the player's tie record by one each function call
     */
    @Test
    public void testAddTie() {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.addTies();
        assertEquals(1, playerRecord.getTies());
    }

    /**
     * Tests if we initialize the player's win record by a specific number
     */
    @Test
    public void testInitializeWins() {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.initializeWins(10);
        assertEquals(10, playerRecord.getWins());
    }

    /**
     * Tests if we initialize the player's loss record by a specific number
     */
    @Test
    public void testInitializeLosses() {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.initializeLoss(5);
        assertEquals(5, playerRecord.getLosses());
    }

    /**
     * Tests if we initialize the player's tie record by a specific number
     */
    @Test
    public void testInitializeTies() {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.initializeTie(10);
        assertEquals(10, playerRecord.getTies());
    }

    /**
     * Tests if we update a player's win number by adding onto it with a given number
     */
    @Test
    public void testUpdateWins() {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.addWin();
        playerRecord.updateWins(5);
        assertEquals(6, playerRecord.getWins());
    }

    /**
     * Tests if we update a player's loss number by adding onto it with a given number
     */
    @Test
    public void testUpdateLosses() {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.addLoss();
        playerRecord.updateLoss(8);
        assertEquals(9, playerRecord.getLosses());
    }

    /**
     * Tests if we update a player's tie number by adding onto it with a given number
     */
    @Test
    public void testUpdateTies() {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.addTies();
        playerRecord.updateTies(50);
        assertEquals(51, playerRecord.getTies());
    }

    /**
     * Tests if we can reset the player's records with a function call
     */
    @Test
    public void testReset() {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.addWin();
        playerRecord.addLoss();
        playerRecord.addTies();
        assertEquals(1, playerRecord.getTies());
        playerRecord.reset();
        assertEquals(0, playerRecord.getTies());
    }
}

