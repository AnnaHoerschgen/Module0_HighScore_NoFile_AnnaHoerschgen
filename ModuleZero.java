/*
 * Name: Anna Hoerschgen
 * Date: January 16th, 2025
 * Filename: Main.java
 * Assignment: Module 0 - High Scores from a File
 */

// Imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ModuleZero {
    public static void main(String[] args) {
        // part 1 - load all the players into a list
        String currentPath = System.getProperty("user.dir"); // file path
        Player[] playerList = readPlayersFile(currentPath); // put all the players into a list

        // part 2 - Display all 10 high scores
        printPlayers(playerList);

        // part 3 - Add a new player to the list
        playerList[10] = new Player(); // Initialize the 11th player
        playerList[10].setPlayerName("SBI");
        playerList[10].setHighScore(645);

        // part 4 - Sort the list by score, descending, and then print the list again
        playerList = playerSort(playerList);
        printPlayers(playerList);

        // part 5 - Drop the last score in the list (#11)
        playerList[10] = null;

        // part 6 - Re-display all 10 high scores
        printPlayers(Arrays.copyOf(playerList, 10)); // Only display the top 10
    }

    // readPlayersFile method - reads a file named "Players.csv" at the given filepath
    public static Player[] readPlayersFile(String filepath) {
        BufferedReader br = null;
        try {
            Player[] players = new Player[11]; // Initialize the array for 11 players

            br = new BufferedReader(new FileReader(filepath + "/Players.csv"));
            String line;

            int playerIndex = 0;
            while ((line = br.readLine()) != null && playerIndex < players.length) {
                // Initialize each Player object before using it
                players[playerIndex] = new Player();

                // Parse the line
                String[] values = line.split(",");
                players[playerIndex].setPlayerName(values[0]);
                players[playerIndex].setHighScore(Integer.parseInt(values[1]));
                playerIndex++;
            }
            br.close();
            return players;

        } catch (IOException e) {
            System.out.println("An error happened while reading the file!");
            return new Player[1]; // Return a single empty player for safety
        }
    }

    // printPlayers method
    public static void printPlayers(Player[] list) {
        System.out.println("\t\tPlayer Name\tHigh Score");
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) { // Avoid null entries
                System.out.println(
                    "#"
                    + (i + 1)
                    + "\t\t"
                    + list[i].getPlayerName()
                    + "\t\t"
                    + list[i].getHighScore()
                );
            }
        }
        System.out.println();
    }

    // playerSort method
    public static Player[] playerSort(Player[] list) {
        int n = list.length;
        boolean swapped;

        // Perform n-1 passes through the array
        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            // Compare adjacent elements
            for (int j = 0; j < n - 1 - i; j++) {
                if (list[j + 1] != null && list[j].getHighScore() < list[j + 1].getHighScore()) {
                    // Swap if elements are in the wrong order (descending)
                    Player temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;

                    swapped = true;
                }
            }

            // If no elements were swapped, the array is already sorted
            if (!swapped) break;
        }
        return list;
    }
}

// Player class
class Player {
    private String name;
    private int highScore = 0;

    public int getHighScore() {
        return this.highScore;
    }

    public String getPlayerName() {
        return this.name;
    }

    public void setHighScore(int score) {
        this.highScore = score;
    }

    public void setPlayerName(String name) {
        this.name = name;
    }
}