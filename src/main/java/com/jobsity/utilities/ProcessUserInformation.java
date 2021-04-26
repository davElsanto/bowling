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
                if (i < scores.size()) {
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
        for (Score score : userScore) {
            Score auxNext, auxNextNext, auxTemp;
            int total = 0;
            int totalPrev = index > 0 ? userScore.get(index - 1).getTotal() : 0;
            if (index < 9) {
                //strike #1
                //totalPrev = index > 1 ? userScore.get(index - 1).getTotal() : 0;
                auxTemp = userScore.get(index);
                auxNext = this.getNext(index + 1, userScore);
                auxNextNext = this.getNext(index + 2, userScore);
                if (score.isStrike()) {

                    //strike #2
                    if (auxNext.isStrike()) {
                        //strike #3
                        if (auxNextNext.isStrike()) {
                            auxTemp.setTotal(30 + totalPrev);

                            //userScore.set(index, auxTemp);
                        } else {
                            auxTemp.setTotal(20 + auxNextNext.getPinsValue1() + totalPrev);


                        }

                    } else {
                        auxTemp.setTotal(10 + auxNext.getPinsValue1() + auxNext.getPinsValue2() + totalPrev);

                    }

                } else if (score.isSpare()) {
                    auxTemp.setTotal(10 + auxNext.getPinsValue1() + totalPrev);
                } else {
                    auxTemp.setTotal(auxTemp.getPinsValue1() + auxTemp.getPinsValue2() + totalPrev);
                }

                userScore.set(index, auxTemp);
                index++;
            } else {

                auxTemp = userScore.get(index);
                auxNext = this.getNext(index + 1, userScore);
                auxNextNext = new Score(0,0,false,false,false,false,0);
                try {
                    auxNextNext = this.getNext(index + 2, userScore);
                }catch (IndexOutOfBoundsException e){

                }



                auxTemp.setTotal(auxTemp.getPinsValue1() + auxTemp.getPinsValue2() +
                        auxNext.getPinsValue1() + auxNext.getPinsValue2() +
                        auxNextNext.getPinsValue1() + auxNextNext.getPinsValue2() +
                        totalPrev);
                userScore.set(index, auxTemp);

                break;


            }


        }
    }

    public Score getNext(int index, ArrayList<Score> userScore) {
        return userScore.get(index);
    }

    public void getTotalByUser(ArrayList<Score> userScore, String name){
        StringBuilder sb = new StringBuilder();

        sb.append("Frame\t\t");

        for(int i = 1; i < 11; i++){
            sb.append(i + "\t\t");
        }
        sb.append("\n"+ name + "\n");
        sb.append("Pinfalls" + "\t");
        for(int i = 0; i < 10; i++){
            if(userScore.get(i).isStrike()){
                sb.append(" \tX\t");
            }else if(userScore.get(i).isSpare()){
                sb.append(userScore.get(i).getPinsValue1() + "\t/\t");
            }else if(userScore.get(i).isOpen()){
                if(userScore.get(i).getPinsValue1() == 0){
                    sb.append("-\t" + userScore.get(i).getPinsValue2()+ "\t");
                }else{
                    sb.append(userScore.get(i).getPinsValue1() + "\t-\t");
                }
            }else{
                if(userScore.get(i).getPinsValue1() == 0){
                    sb.append("F\t" + userScore.get(i).getPinsValue2()+ "\t");
                }else{
                    sb.append(userScore.get(i).getPinsValue1() + "\tF\t");
                }
            }

        }
        sb.append("\n");
        sb.append("Score" + "\t\t");
        for(int i = 0; i < 10; i++){
            sb.append(userScore.get(i).getTotal() + "\t\t");
        }
        sb.append("\n");

        System.out.println(sb.toString());

    }
}
