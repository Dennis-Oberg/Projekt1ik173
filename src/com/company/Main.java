package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


/*
        MemberStore mStore = new MemberStore();
        ArrayList<Member> list = new ArrayList<>();
        list.add(new Member(1,123,"Tobias", "Wendel",1));
        AuthService authService = new AuthService();
        mStore.memberList = list;
        authService.mStore = mStore;
        Menu m = new Menu(authService, new BookManager(new BookStore()));

 */
        Menu menu = new Menu();
        menu.start();

        //ID 1234 för användare
        //ID 4321 för bibliotekarie
        //Lösen: 1234 för båda


    }
}



