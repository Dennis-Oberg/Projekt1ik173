package com.company;

import java.util.Scanner;

public class Menu {

    public static void start(){
        int authorisation = loginMessage();
        decideAuth(authorisation);

    }
    public static int loginMessage(){
        Scanner input = new Scanner(System.in);
        System.out.print("Ange ID: ");
        int id = input.nextInt();

        System.out.print("Ange lösen:");
        int password = input.nextInt();
        return verifyLogin(id, password);
    }

    public static int verifyLogin(int id, int password) {
        if (id == 1234 && password == 1234){
            return 1;
        }
        else if (id == 4321 && password == 1234){
            return 2;
        }
        else return 0;
    }

    public static void decideAuth(int authorisation){
        if (authorisation == 1){
            memberOption(memberMenu());
        }
        else if (authorisation == 2){
            librarianOption(librarianMenu());
        }
        else System.out.println("Ogiltig användare");
    }
    public static int memberMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("Välja alternativ:\n1.Låna bok\n2.Säg upp medlemskap");
        System.out.print("Val: ");

        return input.nextInt();
    }

    public static void memberOption(int option) {
        if (option == 1){
            System.out.println("Lägg till bok i system metod");
        }
        else if (option == 2){
            System.out.println("Säg upp medlemskap metod");
        }

    }

    public static int librarianMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("\nVälja alternativ:\n1.Lägg till bok i system\n2.Registrerar medlem\n3.Söka efter medlem\n4.Ta bort medlem" +
                "\n5.Lämna tillbak bok\n6.Låna bok");
        System.out.print("Val: ");
        return input.nextInt();
    }

    public static void librarianOption(int option) {
        System.out.println("");
        switch (option){
            case 1:
                System.out.println("Lägg till bok i system metod");
                break;
            case 2:
                System.out.println("Registrerar medlem metod");
                break;
            case 3:
                System.out.println("Söka efter medlem metod");
                break;
            case 4:
                System.out.println("Ta bort medlemm metod");
                break;
            case 5:
                System.out.println("Lämna tillbak bok metod");
                break;
            case 6:
                System.out.println("Låna bok metod");
                break;
            default:
                System.out.println("Inget giltig val\n");
                librarianMenu();
                break;
        }
    }

}
