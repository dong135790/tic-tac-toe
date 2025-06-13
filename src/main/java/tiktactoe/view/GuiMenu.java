package tiktactoe.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tiktactoe.model.gamedata.Board;
import tiktactoe.model.gamedata.GameEnum;
import tiktactoe.model.gamedata.GameState;
import tiktactoe.model.gamedata.LeaderBoard;
import tiktactoe.model.gamedata.PlayerRecord;
import tiktactoe.model.player.EasyComputer;
import tiktactoe.model.player.HumanPlayer;
import tiktactoe.model.player.MediumComputer;
import tiktactoe.model.player.PlayerType;
/**
 * A class that asks for username and symbol. Also displays the menu aspect of the tic-tac-toe game
 */
public class GuiMenu extends JFrame {

    /**
     * Empty constructor.
     */
    public GuiMenu() {
    }

    /**
     * First method to be called when starting application. Allows the user to enter a username and select their
     * symbol via popup
     */
    public void getUserInfo() {
        String username = "";
        while (username.isEmpty()) {
            username = getUserName();
        }
        String symbolChoice = "";
        Object[] options = {"X", "O"};
        while (symbolChoice.isEmpty()) {
            symbolChoice = String.valueOf(JOptionPane.showOptionDialog(
                    null,
                    "Please select your symbol:",
                    "Symbol",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
            ));
        }
        GameEnum symbol;
        if (symbolChoice.equals("0")) {
            symbol = GameEnum.X;
        } else {
            symbol = GameEnum.O;
        }
        HumanPlayer player = new HumanPlayer(username, symbol);
        menuOption(player);
    }

    /**
     * A menu showing the options that the user can click on for their tic-tac-toe game.
     * Allows user to:
     * 1. Play vs human
     * 2. Play vs Computer
     * 3. View LeaderBoard
     * 4. Exit game
     * @param playerOne An object representing player One or user.
     */
    public void menuOption(HumanPlayer playerOne) {
        // Gets opponent symbol
        GameEnum opponentSymbol = getOpponentSymbol(playerOne);

        JFrame window = new JFrame();
        window.setTitle("TicTacToe Menu");
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
        window.setSize(300, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton vsHumanButton = createVsHumanButton(playerOne, opponentSymbol);
        JButton vsComputerButton = createVsComputerButton(playerOne, opponentSymbol);
        JButton leaderBoardButton = createLeaderBoardButton(playerOne);
        JButton exitGameButton = createExitButton();

        window.add(Box.createVerticalGlue());
        window.add(vsHumanButton);
        window.add(vsComputerButton);
        window.add(leaderBoardButton);
        window.add(exitGameButton);
        window.add(Box.createVerticalGlue());

        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);
    }

    /**
     * Helper method that utilizes a popup to ask the user to input their username
     * @return A string representation of the user's chosen username
     */
    public String getUserName() {
        String userName = JOptionPane.showInputDialog(null, "Please enter your name");
        if (validateUsername(userName)) {
            return userName;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username");
            return userName;
        }
    }

    /**
     * A helper method that validates if the username that the user inputted is valid or not. (Cannot be empty)
     * @param username The string that we want to check
     * @return True if the username is valid. False otherwise
     */
    public boolean validateUsername(String username) {
        return !username.isEmpty();
    }

    /**
     * Helper method to determine the symbol of the opponent depending on the symbol of player one
     * @param playerOne An object representation of player one
     * @return A gameEnum which stores the symbol of the opponent
     */
    public GameEnum getOpponentSymbol(PlayerType playerOne) {
        GameEnum opponentSymbol = null;
        if (playerOne.getSymbol() == GameEnum.X) {
            opponentSymbol = GameEnum.O;
        } else {
            opponentSymbol = GameEnum.X;
        }
        return opponentSymbol;
    }

    /**
     * One of the chosen options for the menu. Creates an instance of the opponent player object
     * @param opponentSymbol The symbol of the opponent
     * @return An object representing the opponent player
     */
    public PlayerType playVsHuman(GameEnum opponentSymbol, PlayerType playerOne) {
        System.out.println("Human game");
        PlayerType playerTwo = null;
        // Play vs human so ask for other human username
        String opponentName = "";
        while (opponentName.isEmpty() || opponentName.equals(playerOne.getUsername())) {
            opponentName = getOpponentUsername(playerOne);
        }
        playerTwo = new HumanPlayer(opponentName, opponentSymbol);
        return playerTwo;
    }

    /**
     * One of the chosen options for the menu. Creates an instance of the opponent player object (ComputerPlayer)
     * @param opponentSymbol The symbol of the opponent
     * @return An object representing the opponent player
     */
    public PlayerType playVsComputer(GameEnum opponentSymbol) {
        System.out.println("Computer game");
        PlayerType playerTwo = null;

        String[] difficulty = {"Medium", "Easy"};
        int difficultyChoice = JOptionPane.showOptionDialog(
                null,
                "Please select a computer difficulty",
                "Computer Difficulty",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                difficulty,
                difficulty[0]
        );
        if (difficultyChoice == 1) {
            System.out.println("Easy");
            playerTwo = new EasyComputer("EasyAi", opponentSymbol);
        } else {
            System.out.println("Medium");
            playerTwo = new MediumComputer("MediumAi", opponentSymbol);
        }
        return playerTwo;
    }

    /**
     * Creates the frame of the leaderboard gui.
     */
    public void displayLeaderBoard(PlayerType playerOne) {
        //Window
        JFrame leaderBoardFrame = new JFrame();
        leaderBoardFrame.setTitle("LeaderBoard");
        leaderBoardFrame.setLayout(new BorderLayout());

        // Items in my window
        JLabel topFiveLabel = new JLabel("Top Five Leaderboard");
        JPanel northPanel = new JPanel();
        northPanel.add(topFiveLabel);

        String topPlayers = getInfoFromLeaderBoard();
        JPanel centerPanel = new JPanel();
        JLabel data = new JLabel(topPlayers);
        centerPanel.add(data);

        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> {
            leaderBoardFrame.dispose();
            menuOption((HumanPlayer) playerOne);
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(exit);

        // Layout of items in windows
        leaderBoardFrame.add(northPanel, BorderLayout.NORTH);
        leaderBoardFrame.add(centerPanel, BorderLayout.CENTER);
        leaderBoardFrame.add(bottomPanel, BorderLayout.SOUTH);
        leaderBoardFrame.setSize(300, 500);
        leaderBoardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        leaderBoardFrame.setLocationRelativeTo(null);
        leaderBoardFrame.setResizable(false);
        leaderBoardFrame.setVisible(true);
    }

    /**
     * Helper method for displayLeaderBoard that obtains the necessary information from the json file and stores the
     * info as a string.
     * @return A string representation of the top five players in terms of wins.
     */
    public String getInfoFromLeaderBoard() {
        LeaderBoard leaderBoard = new LeaderBoard();
        leaderBoard.initializeLeaderBoard();
        String data = "<html><div>";
        int count = 1;
        Map<String, PlayerRecord> topFive = leaderBoard.getTopFiveLeaderBoard();
        System.out.println(topFive.size());
        for (Map.Entry<String, PlayerRecord> entry : topFive.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getWins());
            String username = entry.getKey();
            int win = entry.getValue().getWins();
            int loss = entry.getValue().getLosses();
            int tie = entry.getValue().getTies();
            data += "<div>"
                + count
                + ". " + username
                + "<br>" + "Wins: " + win + " Losses: " + loss + " Ties: " + tie + "<br>" + "<br>"
                + "</div>";
            count++;
        }
        data += "</div></html>";
        return data;
    }

    /**
     * Creates the button for playing against a human
     * @param playerOne An object representation of player one
     * @param opponentSymbol The symbol of the opponent
     * @return A JButton that is fully operational for playing against human
     */
    public JButton createVsHumanButton(PlayerType playerOne, GameEnum opponentSymbol) {
        JButton vsHumanButton = new JButton("Play vs Human");
        vsHumanButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        vsHumanButton.setPreferredSize(new Dimension(100, 40));

        vsHumanButton.addActionListener((e) -> {
            PlayerType playerTwo = playVsHuman(opponentSymbol, playerOne);
            Board board = new Board();
            GameState game = new GameState(playerOne, playerTwo, board);
            game.setPlayerScoreBoard(playerOne, playerTwo);
            game.setCurrentPlayer(playerOne);
            new TicTacToeGui(game);
        });
        return vsHumanButton;
    }

    /**
     * Creates the button for playing against a computer Ai
     * @param playerOne An object representation of player one
     * @param opponentSymbol The symbol of the opponent
     * @return A JButton that is fully operational for playing against computerAi
     */
    public JButton createVsComputerButton(PlayerType playerOne, GameEnum opponentSymbol) {
        JButton vsComputerButton = new JButton("Play vs Computer");
        vsComputerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        vsComputerButton.setPreferredSize(new Dimension(100, 40));
        vsComputerButton.addActionListener((e) -> {
            PlayerType playerTwo = playVsComputer(opponentSymbol);
            Board board = new Board();
            GameState game = new GameState(playerOne, playerTwo, board);
            game.setPlayerScoreBoard(playerOne, playerTwo);
            game.setCurrentPlayer(playerOne);
            new TicTacToeGui(game);
        });
        return vsComputerButton;
    }

    /**
     * Creates the button for displaying the leaderboard
     * @param playerOne An object representation of player one
     * @return A JButton that is fully operational for displaying the leaderboard
     */
    public JButton createLeaderBoardButton(PlayerType playerOne) {
        JButton leaderBoardButton = new JButton("View LeaderBoard");
        leaderBoardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderBoardButton.setPreferredSize(new Dimension(100, 40));
        leaderBoardButton.addActionListener((e) -> {
            displayLeaderBoard(playerOne);
        });
        return leaderBoardButton;
    }

    /**
     * Creates the button for exiting the game entirely
     * @return A JButton that is fully operational exiting the game
     */
    public JButton createExitButton() {
        JButton exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setPreferredSize(new Dimension(100, 40));
        exitButton.addActionListener((e) -> {
            exitGame();
        });
        return exitButton;
    }

    /**
     * Helper method that simply closes the application.
     */
    public void exitGame() {
        System.exit(0);
    }

    /**
     * Similar to getUsername, but ensures that if the username is the same as player one, it will display a message
     * @param playerOne Object representation of playerOne
     * @return String of player two's name
     */
    public String getOpponentUsername(PlayerType playerOne) {
        String userName = JOptionPane.showInputDialog(null, "Please enter your name");
        if (validateUsername(userName) && !userName.equals(playerOne.getUsername())) {
            return userName;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username");
            return userName;
        }
    }
}
