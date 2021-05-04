package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Menu m = new Menu(new AuthService(new MemberStore()), new BookManager(new BookStore()));
        m.start();


        //ID 1234 för användare
        //ID 4321 för bibliotekarie
        //Lösen: 1234 för båda


    }
}



