package com.jobsity.interview;

import com.jobsity.entities.Player;
import com.jobsity.utilities.InputReader;
import com.jobsity.utilities.ProcessUserInformation;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class BowlingGame {

    public static void main(String arg[]){

        InputReader userInput = new InputReader();
        ProcessUserInformation processUser = new ProcessUserInformation();

        System.out.println("Please, insert your bowling score:");

        ArrayList<String> userData = userInput.processUserInput();
        LinkedHashMap<String, ArrayList<String>> valuesByUser = processUser.generateUsersAndScores(userData);

        ArrayList<Player> players = new ArrayList<>();
        valuesByUser.forEach( (key, values) -> {
            Player player = new Player();
            player.setPlayerName(key);
            player.setPlayerScore(processUser.generateScore(values));
            players.add(player);
        });

        System.out.println(players);
    }
}
