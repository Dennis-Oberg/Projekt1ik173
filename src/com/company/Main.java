package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        System.out.println("Välkommen!\n");
        Menu.start();
        //ID 1234 för användare
        //ID 4321 för bibliotekarie
        //Lösen: 1234 för båda


        //System.out.println("Tryck 2 för att logga in");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()){
            case 1:
                registerUser();
            break;
            case 2:
              //  login(memberID);
        }


    }
    public void loginMessage() {
        Scanner input = new Scanner(System.in);
        System.out.print("Ange ID: ");
        int id = input.nextInt();

        System.out.print("Ange lösen:");
        int password = input.nextInt();
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

        Member member = new Member(returnRandom(), SSN, firstName, lastName, titel);

        System.out.println(member.IDCode);
        System.out.println(member.SSN);
        System.out.println(member.firstName);
        System.out.println(member.lastName);
    }

    static int returnRandom(){
        return (int) (Math.random() * 1000);
    }

}


