package com.company;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

public class GenerateID {

    public GenerateID() {

    }

    private static int generateID()
    {   Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int random = rand.nextInt(10);
            sb.append(random);
        }
        return Integer.parseInt(sb.toString());
    }

    public static int returnID(ArrayList<Integer> intArray)
    {
        int newID = generateID();
        for (int i: intArray
             ) {
            if (newID == i)
            {
                 returnID(intArray);
            }
        }
        System.out.println("Generated ID: " + newID);
        return newID;
    }

}
