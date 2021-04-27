package com.company;

public class TestUsers {
    int possibleLoans;
    String position;

    public TestUsers(String type) {
        position = type;

        switch (type){
            case "PhD":
                possibleLoans = 7;
            case "Undergraduate":
                possibleLoans = 3;
        }
    }


    void confirmUser(int type) {
    }

}
