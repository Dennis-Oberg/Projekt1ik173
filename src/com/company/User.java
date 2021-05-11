package com.company;

import java.math.BigInteger;
import java.util.ArrayList;

public class User {

    int IDCode;
    int SSN;
    String firstName;
    String lastName;

    int titel;
    int maxloans;
    int current;
    int strikes;
    int suspendedCount;
    boolean suspended;
    boolean suspendedOnce;
    boolean isLibrarian = false;
    ArrayList<Book> books;

    User() {

    }

    User(int idCode, int ssn, String firstname, String lastname, int titel) {
        this.IDCode = idCode;
        this.SSN = ssn;
        this.firstName = firstname;
        this.lastName = lastname;
        this.titel = titel;
        this.suspended = false;
        decideMax(titel);
        books = new ArrayList<>();
    }

    public int getIDCode() {
        return IDCode;
    }


    public int getMaxloans() {
        return maxloans;
    }

    public void setMaxloans(int maxloans) {
        this.maxloans = maxloans;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }


    public int getTitel() {
        return titel;
    }


    private void decideMax(int rank) {
        switch (rank) {
            case 1 -> this.setMaxloans(3);
            case 2 -> this.setMaxloans(5);
            case 3 -> this.setMaxloans(7);
            case 4 -> this.setMaxloans(10);
            case 5 -> this.isLibrarian = true;
            default -> System.out.println("Ogiltig rank");
        }
    }
}

