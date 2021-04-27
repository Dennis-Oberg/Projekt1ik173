package com.company;

public class User {

    int IDCode;
    int SSN;
    String firstName;
    String lastName;
    int titel;
    int maxloans = 3;
    int current;
    String position;
    int strikes;
    AuthService aus = null;

    User(int idcode, int ssn, String firstname, String lastname, int titel){
        this.IDCode = idcode;
        this.SSN = ssn;
        this.firstName = firstname;
        this.lastName = lastname;
        this.titel = titel;

        aus = new AuthService();
        position = aus.getPosition;

        switch (type){
            case "PhD":
                possibleLoans = 7;
            case "Undergraduate":
                possibleLoans = 3;
        }

    }


    public void lend()
    {
        if (maxloans > current)
        {
            BookManager obj = new BookManager();
            obj.loan();
            current++;
        }
    }

}
