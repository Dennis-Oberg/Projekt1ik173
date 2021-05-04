package com.company;

import java.util.Scanner;

public class Menu {

    BookManager bm;
    AuthService as;

    public Menu(AuthService newAS, BookManager newBM)
    {
        this.as=newAS;
        this.bm=newBM;
        this.bm.member=this.as.returnMember();
    }

    public void start(){
        Boolean authorisation = loginMessage();
        decideAuth(authorisation);
    }

    public Boolean loginMessage(){
        bm.displayBooks();
        as.displayMembers();
        Scanner input = new Scanner(System.in);
        System.out.print("Ange ID: ");
        int id = input.nextInt();
        return as.login(id);
    }

    public void decideAuth(Boolean authorisation)    {
        if (authorisation){
            if (this.as.returnMember().isLibrarian)
            {
                librarianOption(librarianMenu());
            }
            else
            {
                memberOption(memberMenu());
            }

        }
        else if (!authorisation){
            System.out.println("Error, fel lösenord och/eller användarnamn");
        }

    }
    public static int memberMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("\nVälj ett alternativ:\n1.Låna bok\n2.Lämna tillbaka bok\n3.Säg upp medlemskap");
        System.out.print("Val: ");

        return input.nextInt();
    }

    public void memberOption(int option) {
        System.out.println("");
        if (option == 1){
            System.out.println("Lägg till bok i system metod");
        }
        else if (option == 2){
            System.out.println("Lämna tillbaka medlemskap metod");
        }
        else if (option == 3)
        {
            System.out.println("Säg upp medlemskap metod");
        }
        else {
            System.out.println("Inget giltigt val");
            memberOption(memberMenu());
        }
    }

    public static int librarianMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("\nVälj ett alternativ:\n1.Lägg till bok i system\n2.Registrerar medlem\n3.Söka efter medlem\n4.Ta bort medlem" +
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
                System.out.println("Ta bort medlem metod");
                break;
            case 5:
                System.out.println("Lämna tillbaka bok metod");
                break;
            case 6:
                System.out.println("Låna bok metod");
                break;
            default:
                System.out.println("Inget giltig val\n");
                librarianOption(librarianMenu());
                break;
        }
    }
}
