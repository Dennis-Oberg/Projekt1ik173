package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        System.out.println("Välkommen");
        System.out.println("Tryck 1 för att registrera en användare");
        System.out.println("Tryck 2 för att logga in");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()){
            case 1:
                registerUser();
            break;
            case 2:
              //  login(memberID);

                return();
                ngt.getAvailableBooks();
                user.lend();

        }

    }

    static void registerUser(){

        int SSN;
        String firstName;
        String lastName;
        int titel;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ange social: ");
        SSN = scanner.nextInt();

        System.out.print("Ange förnamn: ");
        firstName = scanner.next();

        System.out.print("Ange efternamn: ");
        lastName = scanner.next();

        System.out.println("Titel\n0 = undergraduate \n1 = postgraduate");
        titel = scanner.nextInt();

        User user = new User(returnRandom(), SSN, firstName, lastName, titel);

        System.out.println(user.IDCode);
        System.out.println(user.SSN);
        System.out.println(user.firstName);
        System.out.println(user.lastName);
    }

    static int returnRandom(){
        return (int) (Math.random() * 1000);
    }

}


