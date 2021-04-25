package com.jobsity.utilities;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ProcessUserInformation {

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

            if(!data.containsKey(userName)){
                values.add(userScore);
                data.put(userName, values);
            }else{
                values = data.get(userName);
                values.add(userScore);

                data.replace(userName, values);
            }
        }

        return data;
    }

}
