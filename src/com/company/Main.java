package com.company;
import java.util.Scanner;

public class Main  {

    public static void main(String[] args) {

        boolean end = false;
        int selection;
        Scanner input = new Scanner(System.in);


        System.out.println("Välkommen\n");
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
/*
                return();
                ngt.getAvailableBooks();
                user.lend();

 */

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

        }
    }
}
