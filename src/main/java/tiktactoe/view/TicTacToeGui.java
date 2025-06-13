package tiktactoe.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tiktactoe.controller.GuiController;
import tiktactoe.model.gamedata.GameEnum;
import tiktactoe.model.gamedata.GameState;
import tiktactoe.model.player.PlayerType;

/**
 * Class that displays the gui application for the tic-tac-toe game
 */
public class TicTacToeGui extends JFrame {
    private GameState gameState;
    private GuiController guiController;
    private JButton[][] buttons;
    private JPanel window;
    private JPanel playerOnePanel;
    private JPanel playerTwoPanel;
    private JPanel ticTacToeGrid;

    /**
     * Constructor for the board game gui
     * @param gameState An object that stores information about the current gameState.
     */
    public TicTacToeGui(GameState gameState) {
        this.gameState = gameState;
        this.guiController = new GuiController(gameState, this);
        this.buttons = new JButton[3][3];
        this.window = new JPanel();

        window.setLayout(new BorderLayout());
        setTitle("Tic Tac Toe");

        ticTacToeGrid = ticTacToeGrid();
        playerOnePanel = displayPlayerInfo(this.gameState.getPlayerOne());
        playerTwoPanel = displayPlayerInfo(this.gameState.getPlayerTwo());

        window.add(ticTacToeGrid, BorderLayout.CENTER);
        window.add(playerOnePanel, BorderLayout.WEST);
        window.add(playerTwoPanel, BorderLayout.EAST);

        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(window);
        setVisible(true);
    }

    /**
     * A method that creates the tic-tac-toe grid
     * @return A jpanel that represents the tic-tac-toe board
     */
    public JPanel ticTacToeGrid() {
        // Creates the grid of tic-tac-toe
        GridLayout gridLayout = new GridLayout(3, 3);
        // Surrounds the grid with a "JPanel component that is needed when we return"
        JPanel panel = new JPanel(gridLayout);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int row = i;
                int col = j;
                // Create a single button
                buttons[i][j] = new JButton();
                buttons[i][j].setText(" ");
                buttons[i][j].setBackground(Color.WHITE);
                // For each button, it will call a clickSlotOnGrid via event listener
                buttons[i][j].addActionListener(e -> guiController.clickSlotOnGrid(row, col));
                panel.add(buttons[i][j]);
            }
        }
        return panel;
    }

    /**
     * Method that updates the slot on the tic-tac-toe grid based on the symbol for user visual
     * @param row The row of the grid
     * @param col The column of the grid
     * @param symbol The symbol that will be displayed on the board
     */
    public void updateSlot(int row, int col, GameEnum symbol) {
        buttons[row][col].setText(symbol.toString());
    }

    /**
     * Displays a popup that tells the user that a player has won and asks to play again to continue the game session
     */
    public void displayWinner() {
        JOptionPane optionPane = new JOptionPane();
        int response = JOptionPane.showConfirmDialog(
                optionPane,
                gameState.getCurrentPlayer().getUsername() + " Wins! Would you like to play again?",
                "Winner",
                JOptionPane.YES_NO_OPTION
        );
        if (response == JOptionPane.YES_OPTION) {
            gameState.setPlayerScoreBoard(gameState.getPlayerOne(), gameState.getPlayerTwo());
            resetTicTacToeBoard();
            updatePlayerInfo();
            this.gameState.setCurrentPlayer(this.gameState.getPlayerOne());
        } else {
            guiController.exitGameSession();
        }
    }

    /**
     * Displays a popup that tells the user a tie has occurred and asks to play again to continue the game session
     */
    public void displayTie() {
        JOptionPane optionPane = new JOptionPane();
        int response = JOptionPane.showConfirmDialog(
                optionPane,
                "A Draw! No one Wins. Would you like to play again?",
                "Tie",
                JOptionPane.YES_NO_OPTION
        );
        if (response == JOptionPane.YES_OPTION) {
            gameState.setPlayerScoreBoard(gameState.getPlayerOne(), gameState.getPlayerTwo());
            resetTicTacToeBoard();
            updatePlayerInfo();
            this.gameState.setCurrentPlayer(this.gameState.getPlayerOne());
        } else {
            guiController.exitGameSession();
        }
    }

    /**
     * Method that resets the tic-tac-toe board to its initial state
     */
    public void resetTicTacToeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(" ");
                buttons[i][j].setBackground(Color.WHITE);
            }
        }
        this.gameState.getBoard().resetBoard();
        this.gameState.setCurrentPlayer(this.gameState.getCurrentPlayer());
    }

    /**
     * Method that creates a JPanel that stores the information about the player
     * @param player A PlayerType object that represents the player
     * @return A JPanel for the layout of the player info
     */
    public JPanel displayPlayerInfo(PlayerType player) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(150, 600));
        panel.setBorder(BorderFactory.createEmptyBorder(200, 10, 10, 10));
        JLabel top = new JLabel("Player: ");
        JLabel username = new JLabel(player.getUsername());
        JLabel win = new JLabel("Win: " + player.getRecord().getWins());
        JLabel loss = new JLabel("Loss: " + player.getRecord().getLosses());
        JLabel ties = new JLabel("Ties: " + player.getRecord().getTies());
        top.setAlignmentX(Component.CENTER_ALIGNMENT);
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        win.setAlignmentX(Component.CENTER_ALIGNMENT);
        loss.setAlignmentX(Component.CENTER_ALIGNMENT);
        ties.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(top);
        panel.add(username);
        panel.add(win);
        panel.add(loss);
        panel.add(ties);

        return panel;
    }

    /**
     * Refreshes the player panels after each game has been complete
     */
    public void updatePlayerInfo() {
        window.remove(playerOnePanel);
        window.remove(playerTwoPanel);

        playerOnePanel = displayPlayerInfo(this.gameState.getPlayerOne());
        playerTwoPanel = displayPlayerInfo(this.gameState.getPlayerTwo());

        window.add(playerOnePanel, BorderLayout.WEST);
        window.add(playerTwoPanel, BorderLayout.EAST);
    }
}
