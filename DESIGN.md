# Final Project Design

## Diagram
```mermaid
classDiagram
    class Model

    class PlayerType {
    <<Abstract>>
        +protected String username;
        +protected char symbol;
        +public abstract Move getMove(Board board)
        +public String getUsername();
    }
    
    class HumanPlayer {
        +private int undoAmount;
        +public Move getMove()
    }
    
    class ComputerPlayer {
    <<Abstract>>
        +public ComputerPlayer()
    }

    class EasyComputerPlayer {
        private Random;
        +public Move getMove()
    }

    class MediumComputerPlayer {
        +public Move getMove()
        +private Move findWinningMove(Board board, char symbol)
    }

    class HardComputerPlayer {
        +public Move getMove()
    }
    
    class Move {
        +private int row;
        +private int col;
        
        +public int getRow()
        +public int getCol()
    }

    class Board {
        +private final char[][];
        +private final Stack<Move> moveHistory;
        
        +public void makeMove(Move move, char symbol)
        +public boolean isValidMove(Move move)
        +public boolean undoLastMove()
        +public boolean getLastMove()
        +public boolean isSlotValid(Move move)
        +public boolean isBoardFull()
        +public boolean checkWin(char symbol)
        +public boolean checkTie()
        +public void reset()
    }
    
    class GameState {
        +private PlayerType player1;
        +private PlayerType player2;
        +private PlayerType currentPlayer;
        +private Board board;
        +private boolean currentlyPlaying;
        +private Map<String username, PlayerRecord record> scoreBoard;
        
        +public Board getBoard()
        +public Player getCurrentPlayer()
        +public Player getOpponent()
        +public void switchPlayer()
        +public Map<String, PlayerRecord> getScoreBoard()
        +public void stopGame()
        +public void resetGame()
    }
    
    class PlayerRecord {
        +private int wins;
        +private int losses;
        +private int ties;
        
        +public int getWins()
        +private int getLosses()
        +private int getTies()
    }
    
    class LeaderBoard {
        +private Map<String username, PlayerRecord record> leaderboardRecords;
        
        +public void displayTopThreeLeaderBoard()
    }
    
    class Conditions{
        <<Enum>>
        WIN
        LOSS
        TIE
        X
        O
    }
    
    class Builder {
        +private final String fileName;
        +public Map<String username, PlayerRecord record> createLeaderBoardFromJson(fileName)
        +public LeaderBoard buildLeaderBoard(Map<String username, PlayerRecord record>)
        +public void updateLeaderBoards()
    }

Model --|> PlayerType
Model --|> Move
Model --|> Conditions
Model --|> Board
Model --|> GameState
Model --|> LeaderBoard
Model --|> PlayerRecord
PlayerType --|> HumanPlayer
PlayerType --|> ComputerPlayer

ComputerPlayer --|> EasyComputerPlayer
ComputerPlayer --|> MediumComputerPlayer
ComputerPlayer --|> HardComputerPlayer
```
My model consists of the board, gameState, playerRecord, leaderboard, move, conditions, and PlayerType  
1) The board will check the conditions of the board itself and make operations on it such as making a move, checking if it is full, checking the conditions of the board (win/loss/tie) along with resetting the board after a game  
2) The gameState will focus on the players of the game, so it makes sure the players are in the game and the player's "turns" are swapped after each round.  
3) The player record will be created when gamestate is implemented to keep tract of each player's current win/loss/tie status during each gamestate. 
4) The player type will demonstrate inheritance in which they can be split into human players and computer players. The computer players are further split into at least 2 difficulties as of yet which are easy/medium (Maybe hard later)
5) The leaderboard will deal with the json file and update the json file according to the stats of each game session.

Controller
```mermaid
classDiagram
    class GameController {
        +private Timer timer;
        +public void startTimer(PlayerType player)
        +public HumanPlayer initializePlayer()
        +public String getOpponentType()
        +public String getComputerDifficulty()
        +public HumanPlayer createOpponentHuman(PlayerType opponent)
        +public ComputerPlayer createOpponentComputer(PlayerType opponent, String difficulty)
        +public void startGameComputer()
        +public void startGameHuman()
        +public void currentPlayerMove(Player player)
        +public void switchPlayer()
        +public void determineWin()
        +public void undo()
        +public void updateSessionResults()
        +public void updateLeaderBoard()
    }
```
My controller will only have one class, but it makes sure the game flows correctly when implemented by working with the view and the model.  
1) It will get the player's username and their chosen opponent type
2) Ensure the player's move gets placed on the board and is updated 
3) Ensure the player's turn gets swapped after each move 
4) Allows for the undo option (for computers, they will delay their choice by 10 seconds, so users will have a brief window to undo their move)
5) Checks for win/loss/tie conditions after each move and will display the board accordingly 
6) Updates game session win/loss/tie records for each player and asks to redo the game 
7) If not, results from game session will be stored into the leaderboard.

View
```mermaid
classDiagram
    class displayTicTacToe {
        +displayBoard(Board board, Move lastMove)
        +askUsername()
        +askPlayerType()
        +askDifficulty()
        +askNextMove()
        +displayVictor()
        +displayGameState()
        +displayLeaderBoard()
        +promptDifficulty()
    }
```
The view will simply display the contents of the board after each round while also display the print prompt of certain events. Currently set as terminal