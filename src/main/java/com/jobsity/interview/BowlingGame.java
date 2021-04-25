package com.jobsity.interview;

import com.jobsity.utilities.InputReader;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    public static void main(String arg[]){
        InputReader userInput = new InputReader();

        System.out.println("Please, insert your bowling score:");

        ArrayList<String> userData = userInput.processUserInput();

        System.out.println(userData);
    }
}
