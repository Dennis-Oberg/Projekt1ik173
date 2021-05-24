package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AuthService {

    MemberStore mStore;
    BookStore bStore;

    User loggedInUser;

    public AuthService() {

    }


    public AuthService(MemberStore newMStore) {
        this.mStore = newMStore;
    }


    public void start() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Välkommen!\n");

        try {
            System.out.print("Ange ID för att logga in: ");
            int id = scan.nextInt();
            mStore = new MemberStore();

            decideAuth(id);
            scan.close();
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("Du måste ange ett numeriskt värde.");
            start();
        }
    }
    public void decideAuth(int id) {
        boolean authorisation = mStore.login(id);
        if (authorisation) {
            loggedInUser = mStore.getMemberById(id);

            if (loggedInUser.getTitle() == 5) {
                new Librarian(loggedInUser, new BookManager(loggedInUser, bStore), new MemberManager(loggedInUser, mStore));
            } else if (checkSuspension(loggedInUser)) {
                new Member(loggedInUser, bStore);
            }
        } else {
            System.out.println("\nError, Du är bannlyst eller har skrivit in fel lösenord och/eller användarnamn");
            System.out.println("Försök igen\n");
            start();
        }
    }

    public boolean checkSuspension(User loggedInUser)
    {
        if (loggedInUser.getStrikes() == 3)
        {
            mStore.moveToBannedMember(loggedInUser);
            mStore.removeMember(loggedInUser);
            return false;
        }
        return true;
    }
}