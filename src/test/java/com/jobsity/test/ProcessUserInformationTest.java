package com.jobsity.test;

import com.jobsity.utilities.ProcessUserInformation;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ProcessUserInformationTest {

    @Test
    public void testGenerateUsersAndScores(){
        ProcessUserInformation pui = new ProcessUserInformation();
        ArrayList<String> userData = new ArrayList<>();
        userData.add("user1  1");
        userData.add("user1  9");
        userData.add("user2  10");

        LinkedHashMap<String, ArrayList<String>> expected = new LinkedHashMap<>();
        ArrayList<String> dataUser1 = new ArrayList<>();
        ArrayList<String> dataUser2 = new ArrayList<>();
        dataUser1.add("1");
        dataUser1.add("9");
        expected.put("user1", dataUser1);
        dataUser2.add("10");
        expected.put("user2", dataUser2);

        Assert.assertEquals(expected, pui.generateUsersAndScores(userData));
    }
}
