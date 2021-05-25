package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Main {
    private static Logger logger = LogManager.getLogger(Member.class.getName());
    public static void main(String[] args) {

        logger.info("hello");
        new AuthService().start();




        //ID 1234 för användare
        //ID 4321 för bibliotekarie
        //Lösen: 1234 för båda


    }
}

