package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AuthService {

    MemberStore mStore;

    int loginId;
    User loggedInUser;
    public AuthService(){

    }


    public AuthService(MemberStore newMStore) {
        this.mStore = newMStore;
    }

    public void start(){
        boolean isValidID = true;
        System.out.println("Välkommen!\n");

        do {
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("Ange ID för att logga in: ");
                int id = input.nextInt();
                mStore = new MemberStore();

                decideAuth(id);
            }
            catch (InputMismatchException inputMismatchException)
            {
                isValidID = false;
                System.out.println("Du måste ange ett numeriskt värde.");
            }
        }
        while (!isValidID);
    }

    public void decideAuth(int id) {
        boolean authorisation = mStore.login(id);
        if (authorisation){
            loggedInUser = mStore.getMemberById(id);

            if (loggedInUser.getTitel() == 5)
            {
                new Librarian(loggedInUser);
            }
            else
            {
                new Member(loggedInUser);

            }
        }
        else {
            System.out.println("\nError, fel lösenord och/eller användarnamn");
            System.out.println("Försök igen\n");
            start();
        }

    }


    public User getLoggedInMember() {
        return loggedInUser;
    }

    public User returnMember() {
        return loggedInUser;
    }

    public void displayMembers() {

        ArrayList<User> users = mStore.getMembers();

        for (User m : users
        ) {
            System.out.println(m.firstName);
        }

    }

    public void logout() {
        this.loggedInUser = null;
    }

    public User getMemberById(int id) {

        for (User m : mStore.getMembers()) {
            //Bytas ut mot databas??
            if (m.getIDCode() == id) {
                return m;
            }
        }
        return null;
    }


    void getCredentials() {

    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

}