package com.jobsity.entities;

import java.util.ArrayList;

public class Player {
    private String playerName;
    private ArrayList<Score> playerScore;

    public Player() {
    }

    public Player(String playerName, ArrayList<Score> playerScore) {
        this.playerName = playerName;
        this.playerScore = playerScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public ArrayList<Score> getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(ArrayList<Score> playerScore) {
        this.playerScore = playerScore;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", playerScore=" + playerScore.toString() +
                '}';
    }
}
