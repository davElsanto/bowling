package com.jobsity.utilities;

import java.util.ArrayList;
import java.util.Scanner;

public class InputReader {

    public InputReader() {
    }

    public ArrayList<String> processUserInput(){

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> userLines = new ArrayList<>();
        String inputLine;

        while(scanner.hasNextLine()){

            inputLine = scanner.nextLine();
            if(inputLine.isEmpty()) break;

            userLines.add(inputLine);
        }

        scanner.close();

        return userLines;
    }
}
