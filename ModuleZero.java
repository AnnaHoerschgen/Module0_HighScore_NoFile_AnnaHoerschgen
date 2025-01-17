/*
 * Name: Anna Hoerschgen
 * Date: January 16th, 2025
 * Filename: Main.java
 * Assignment: Module 0 - High Scores
 */

// Imports
import java.util.Arrays;

public class ModuleZero {
    public static void main(String[] args) {
        // part 1 - Create a list of default players
        Player[] playerList = new Player [11];
        playerList = createPlayers(playerList);

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

    // method that creates the players
    public static Player[] createPlayers(Player[] players) {
        String[] names = {"AAA", "BBB", "CCC", "DDD", "EEE", "FFF", "GGG", "HHH", "III", "JJJ"};
        int[] scores = {742, 698, 735, 620, 780, 512, 770, 695, 745, 728};

        for (int i = 0; i < names.length; i++) {
            // Initialize each Player object before using it
            players[i] = new Player();
            
            // set the values
            players[i].setPlayerName(names[i]);
            players[i].setHighScore(scores[i]);
        }
        return players;
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
    private String name = "";
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