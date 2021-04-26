package com.jobsity.utilities;

import com.jobsity.entities.Score;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ListIterator;

public class ProcessUserInformation {

    Validations validations = new Validations();

    public LinkedHashMap<String, ArrayList<String>> generateUsersAndScores(ArrayList<String> userData) {
        LinkedHashMap<String, ArrayList<String>> data = new LinkedHashMap<>();

        String[] lineText = new String[2];
        String userName;
        String userScore;

        for (String line : userData) {
            lineText = line
                    .replaceAll("\\s+", " ")
                    .split(" ");
            userName = lineText[0];
            userScore = lineText[1];

            ArrayList<String> values = new ArrayList<>();

            if (!data.containsKey(userName)) {
                values.add(userScore);
                data.put(userName, values);
            } else {
                values = data.get(userName);
                values.add(userScore);

                data.replace(userName, values);
            }
        }

        return data;
    }

    public ArrayList<Score> generateScore(ArrayList<String> scores) {

        StringBuilder sb = new StringBuilder();

        ArrayList<Score> userScores = new ArrayList<>();

        for (int i = 0; i < scores.size(); i++) {

            if (scores.get(i).equals("10")) {
                //strike
                this.addScoreToUser(0, 10, "strike", userScores);
            } else {
                if (i + 1 < scores.size()) {
                    //numbers
                    int auxTotal = 0;
                    if (validations.isNumeric(scores.get(i)) && validations.isNumeric(scores.get(i + 1))) {
                        auxTotal = Integer.parseInt(scores.get(i)) + Integer.parseInt(scores.get(i + 1));
                        if (auxTotal == 10) {
                            //spare
                            this.addScoreToUser(Integer.parseInt(scores.get(i)), Integer.parseInt(scores.get(i + 1)), "spare", userScores);
                        } else {
                            //open
                            this.addScoreToUser(Integer.parseInt(scores.get(i)), Integer.parseInt(scores.get(i + 1)), "open", userScores);
                        }
                    } else {
                        //es foul
                        if (!validations.isNumeric(scores.get(i))) {
                            this.addScoreToUser(0, Integer.parseInt(scores.get(i + 1)), "foul", userScores);
                        } else {
                            this.addScoreToUser(Integer.parseInt(scores.get(i)), 0, "foul", userScores);
                        }
                    }
                    i++;
                }
            }
        }

        return userScores;
    }

    public void addScoreToUser(int value1, int value2, String flag, ArrayList<Score> scoreUser) {
        Score score = new Score(0, 0, false, false, false, false);
        switch (flag) {
            case "strike":
                score.setPinsValue2(10);
                score.setStrike(true);
                break;
            case "spare":
                score.setPinsValue1(value1);
                score.setPinsValue2(value2);
                score.setSpare(true);
                break;
            case "foul":
                score.setPinsValue1(value1);
                score.setPinsValue2(value2);
                score.setFoul(true);
                break;
            case "open":
                score.setPinsValue1(value1);
                score.setPinsValue2(value2);
                score.setOpen(true);
                break;
        }

        scoreUser.add(score);

    }

}
