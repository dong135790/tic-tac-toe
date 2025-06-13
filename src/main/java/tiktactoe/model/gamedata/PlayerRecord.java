package tiktactoe.model.gamedata;

/**
 * A class that stores a player's record given the current gaming session
 */
public class PlayerRecord {
    private int wins;
    private int losses;
    private int ties;

    /**
     * Constructor for player record. Initializes all the data members to 0
     */
    public PlayerRecord() {
        this.wins = 0;
        this.losses = 0;
        this.ties = 0;
    }

    /**
     * Obtains the wins
     * @return An int of the player's wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * Obtains the loss record
     * @return An int of the player's losses
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Obtains the tie record
     * @return An int of the player's tie
     */
    public int getTies() {
        return ties;
    }

    /**
     * Updates the wins by one
     */
    public void addWin() {
        this.wins++;
    }

    /**
     * Updates the loss by one
     */
    public void addLoss() {
        this.losses++;
    }

    /**
     * Updates the ties by one
     */
    public void addTies() {
        this.ties++;
    }

    /**
     * Sets the number of wins to whatever is provided
     * @param win The amount of wins
     */
    public void initializeWins(int win) {
        this.wins = win;
    }

    /**
     * Sets the number of losses to whatever is provided
     * @param loss Amount of losses
     */
    public void initializeLoss(int loss) {
        this.losses = loss;
    }

    /**
     * Sets the number of ties to whatever is provided
     * @param ties Amount of ties
     */
    public void initializeTie(int ties) {
        this.ties = ties;
    }

    /**
     * Updates the number of wins by adding up whatever number is provided
     * @param win Number of wins to be added
     */
    public void updateWins(int win) {
        this.wins += win;
    }

    /**
     * Updates the number of losses by adding up whatever number is provided
     * @param loss Number of losses to be added
     */
    public void updateLoss(int loss) {
        this.losses += loss;
    }

    /**
     * Updates the number of ties by adding up whatever number is provided
     * @param ties Number of ties to be added
     */
    public void updateTies(int ties) {
        this.ties += ties;
    }

    /**
     * Resets the player's record to 0 once again
     */
    public void reset() {
        this.wins = 0;
        this.losses = 0;
        this.ties = 0;
    }
}
