package com.jobsity.utilities;


public class Validations {

    public Boolean isNumeric(String value){
        try{
            Integer.parseInt(value);
            return true;
        }catch (NumberFormatException nfe){
            //nfe.printStackTrace();
            return false;
        }
    }

    public Boolean isValueInRange(Integer value){
        return value >= 0 || value <= 10;
    }

    public Boolean isFoul(String value){
        return value.equals("f") || value.equals("F");
    }


}
