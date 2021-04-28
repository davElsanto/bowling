package com.jobsity.utilities;


import com.jobsity.entities.Score;

import java.util.ArrayList;

public class Validations {

    public Boolean isNumeric(String value){
        try{
            Integer.parseInt(value);
            return true;
        }catch (NumberFormatException nfe){
            return false;
        }
    }

    public Boolean isValueInRange(Integer value){
        return value >= 0 && value <= 10;
    }

    public Boolean isFoul(String value){
        return value.equals("f") || value.equals("F");
    }

    public Boolean hasEnoughEntries(ArrayList<Score> userScores){
        return userScores.size() > 10 && userScores.size() < 13;
    }

    public Boolean validateInput(String userScore){
        if(this.isNumeric(userScore)){
            int value = Integer.parseInt(userScore);
            if(!this.isValueInRange(value)) return false;
            return true;
        }

        if(!this.isFoul(userScore)) return false;
        return true;
    }

}
