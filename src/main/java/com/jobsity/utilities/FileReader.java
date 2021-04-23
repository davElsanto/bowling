package com.jobsity.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public String getData(String filePath) {
        String data = "";

        StringBuilder sb = new StringBuilder();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath))){
            String values;
            while( (values = bufferedReader.readLine()) != null){
                sb.append(values);
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        data = sb.toString();
        return data;
    }


}
