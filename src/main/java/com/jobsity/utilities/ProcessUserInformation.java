package com.jobsity.utilities;

import com.jobsity.entities.Score;
import com.jobsity.enums.Bowling;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ProcessUserInformation {

    Validations validations = new Validations();

    public LinkedHashMap<String, ArrayList<String>> generateUsersAndScores(ArrayList<String> userData) {

        LinkedHashMap<String, ArrayList<String>> data = new LinkedHashMap<>();

        String[] lineText;
        String userName;
        String userScore;

        for (String line : userData) {

            lineText = line
                    .replaceAll("\\s+", " ")
                    .split(" ");

            userName = lineText[0];
            userScore = validations.validateInput(lineText[1]) ?
                    lineText[1] :
                    String.valueOf(Bowling.ERROR_SCORE);

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

        ArrayList<Score> userScores = new ArrayList<>();

        for (int index = 0; index < scores.size(); index++) {

            if (scores.get(index).equals("10")) {
                //strike
                this.addScoreToUser(0, 10, "strike", userScores);
            } else {
                if (index < scores.size()) {

                    boolean isNumberValue = validations.isNumeric(scores.get(index));
                    boolean isNumberValueNext = index + 1 >= scores.size() ?
                            false :
                            validations.isNumeric(scores.get(index + 1));

                    int auxUserScore = isNumberValue ?
                            Integer.parseInt(scores.get(index)) : 0;
                    int auxUserScoreNext = isNumberValueNext ?
                            Integer.parseInt(scores.get(index + 1)) : 0;

                    String flag;

                    if (isNumberValue && isNumberValueNext)
                        if (auxUserScore + auxUserScoreNext == 10) flag = "spare";
                        else flag = "open";
                    else flag = !isNumberValueNext ? "open" : "foul";

                    this.addScoreToUser(auxUserScore, auxUserScoreNext, flag, userScores);

                    index++;

                }
            }
        }

        return userScores;
    }

    public void addScoreToUser(int value1, int value2, String flag, ArrayList<Score> scoreUser) {
        Score score = new Score(0, 0, false, false, false, false, 0);
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

    public void setTotalByUser(ArrayList<Score> userScore) {
        int index = 0;

        if (!validations.hasEnoughEntries(userScore))
            this.addScoresEmptyEntries(userScore);

        for (Score score : userScore) {

            Score auxNext, auxNextNext, auxTemp;
            int total;
            int totalPrev = index > 0 ? userScore.get(index - 1).getTotal() : 0;
            if (index < 9) {
                //strike #1
                auxTemp = userScore.get(index);
                auxNext = this.getNext(index + 1, userScore);
                auxNextNext = this.getNext(index + 2, userScore);

                if (score.isStrike()) {

                    //strike #2
                    if (auxNext.isStrike()) {
                        //strike #3
                        if (auxNextNext.isStrike()) total = Bowling.STRIKE_3 + totalPrev;
                        else total = Bowling.STRIKE_2 + auxNextNext.getPinsValue1() + totalPrev;

                    } else total = Bowling.STRIKE_1 + auxNext.getPinsValue1() + auxNext.getPinsValue2() + totalPrev;

                } else if (score.isSpare()) {
                    total = Bowling.STRIKE_1 + auxNext.getPinsValue1() + totalPrev;
                } else {
                    total = auxTemp.getPinsValue1() + auxTemp.getPinsValue2() + totalPrev;
                }

                auxTemp.setTotal(total);
                userScore.set(index, auxTemp);
                index++;

            } else {
                //get last values, manually
                auxTemp = userScore.get(index);
                auxNext = this.getNext(index + 1, userScore);
                auxNextNext = new Score(0, 0, false, false, false, false, 0);

                if (userScore.size() == 12)
                    auxNextNext = this.getNext(index + 2, userScore);

                auxTemp.setTotal(
                        auxTemp.getPinsValue1() + auxTemp.getPinsValue2() +
                        auxNext.getPinsValue1() + auxNext.getPinsValue2() +
                        auxNextNext.getPinsValue1() + auxNextNext.getPinsValue2() +
                        totalPrev);

                userScore.set(index, auxTemp);

                break;


            }


        }
    }

    public void addScoresEmptyEntries(ArrayList<Score> userScore) {
        Score score = new Score(0, 0, false, false, false, true, 0);
        while (userScore.size() < 11) userScore.add(score);
    }

    public Score getNext(int index, ArrayList<Score> userScore) {
        return userScore.get(index);
    }

    public void getTotalByUser(ArrayList<Score> userScore, String name) {

        StringBuilder sb = new StringBuilder();

        sb.append("Frame\t\t");

        for (int i = 1; i < 11; i++)
            sb.append(i + "\t\t");

        sb.append("\n" + name + "\nPinfalls" + "\t");

        for (int i = 0; i < userScore.size(); i++) {

            int pinsValue1 = userScore.get(i).getPinsValue1();
            int pinsValue2 = userScore.get(i).getPinsValue2();

            if (userScore.get(i).isStrike()) sb.append(" \tX\t");
            else if (userScore.get(i).isSpare()) sb.append(pinsValue1 + "\t/\t");
            else if (userScore.get(i).isOpen()) {

                if (pinsValue1 == 0) sb.append("-\t" + pinsValue2 + "\t");
                else if (pinsValue2 == 0) sb.append(pinsValue1 + "\t-\t");
                else sb.append(pinsValue1 + "\t" + pinsValue2 + "\t");

            } else {

                if (pinsValue1 == 0) sb.append("F\t" + pinsValue2 + "\t");
                else sb.append(pinsValue1 + "\tF\t");

            }

        }

        sb.append("\n").append("Score" + "\t\t");

        for (int i = 0; i < 10; i++) {
            sb.append(userScore.get(i).getTotal() + "\t\t");
        }

        sb.append("\n");

        System.out.println(sb.toString());

    }
}
