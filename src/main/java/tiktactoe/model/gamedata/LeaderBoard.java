package tiktactoe.model.gamedata;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A leaderboard class that records the all-time record of players
 */
public class LeaderBoard {

    private static final String LEADERBOARD_FILE = "resources/leaderboard.json";
    private Map<String, PlayerRecord> leaderBoard;

    /**
     * Constructor for leaderboard
     */
    public LeaderBoard() {
        leaderBoard = new HashMap<>();
    }

    /**
     * Reads from a json file and populates the leaderboard field member with items in the file
     */
    public void initializeLeaderBoard() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode node = objectMapper.readTree(new File(LEADERBOARD_FILE));
            for (JsonNode playerNode : node) {
                String username = playerNode.get("username").asText();
                int wins = playerNode.get("wins").asInt();
                int losses = playerNode.get("loss").asInt();
                int ties = playerNode.get("tie").asInt();

                PlayerRecord record = new PlayerRecord();
                record.initializeWins(wins);
                record.initializeLoss(losses);
                record.initializeTie(ties);
                leaderBoard.put(username, record);
            }
        } catch (IOException e) {
            System.out.println("Error reading leaderboard.json");
            System.out.println(e.getMessage());
        }
    }

    /**
     * After the end of a game session, the player's leaderboard are updated based on their performance of that game
     * session and stored in the leaderboard
     * @param gameRecords The records of a game session. Contains the username of the players and their record
     */
    public void updateLeaderBoard(Map<String, PlayerRecord> gameRecords) {
        System.out.println(gameRecords.size() + ": " + gameRecords.keySet());
        for (Map.Entry<String, PlayerRecord> entry : gameRecords.entrySet()) {
            String username = entry.getKey();
            PlayerRecord record = entry.getValue();
            if (leaderBoard.containsKey(username)) {
                int additionalWins = record.getWins();
                int additionalLoss = record.getLosses();
                int additionalTie = record.getTies();
                leaderBoard.get(username).updateWins(additionalWins);
                leaderBoard.get(username).updateLoss(additionalLoss);
                leaderBoard.get(username).updateTies(additionalTie);
            } else {
                leaderBoard.put(username, record);
            }
        }
    }

    /**
     * Converts the data from the leaderboard field member back into a json object via overwriting.
     * Since my json is a list of hashmaps, I need to pass a list of maps
     */
    public void updateJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> updatedLeaderBoard = new ArrayList<>();
        for (Map.Entry<String, PlayerRecord> entry : leaderBoard.entrySet()) {
            Map<String, Object> temp = new HashMap<>();
            String username = entry.getKey();
            int wins = entry.getValue().getWins();
            int losses = entry.getValue().getLosses();
            int ties = entry.getValue().getTies();
            temp.put("username", username);
            temp.put("wins", wins);
            temp.put("loss", losses);
            temp.put("tie", ties);
            updatedLeaderBoard.add(temp);
        }
        try {
            objectMapper.writeValue(new File(LEADERBOARD_FILE), updatedLeaderBoard);
        } catch (IOException e) {
            System.out.println("Error converting to JSON");
        }
    }

    /**
     * From the leaderboard, obtain the top five players to be displayed later
     * @return A hashmap where the key is the player and their value is their all-time records
     */
    public Map<String, PlayerRecord> getTopFiveLeaderBoard() {
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
        Comparator<Map.Entry<String, PlayerRecord>> compareWins = (
                o1,
                o2) ->
                        comparator.compare(o2.getValue().getWins(), o1.getValue().getWins());

        Map<String, PlayerRecord> topFiveLeaderBoard = leaderBoard
                .entrySet()
                .stream()
                .sorted(compareWins)
                .limit(5)
                .collect(Collectors
                    .toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) ->
                    e1, LinkedHashMap::new));
        return topFiveLeaderBoard;
    }
}
