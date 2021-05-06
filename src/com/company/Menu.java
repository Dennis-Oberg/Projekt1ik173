package com.company;

import java.awt.event.KeyListener;
import java.util.Scanner;

public class Menu {
    Member member;
    BookStore bStore;
    BookManager bManager;
    AuthService auth;
    MemberStore mStore;

    public Menu(AuthService newAS, BookManager newBM)
    {
        this.auth=newAS;
        this.bManager=newBM;
        this.bManager.member=this.auth.returnMember();

    }

    public void start(){
        System.out.println("Välkommen!\n");

        loginMessage();
        decideAuth();
    }


    public void loginMessage(){
        /*
        bManager.displayBooks(); //placeholder som hämtar böcker från DB
        auth.displayMembers(); //placeholder som hämtar alla medlemmar
        */
        Scanner input = new Scanner(System.in);
        System.out.print("Ange ID för att logga in: ");
        int id = input.nextInt();

        auth.setLoginId(id);

    }

    public void decideAuth() {
        boolean authorisation = auth.login(auth.getLoginId());
        if (authorisation) {

            bStore = new BookStore();
            member = auth.getLoggedInMember();
            bManager = new BookManager(bStore, member);
            mStore = new MemberStore();
            System.out.println("Välkommen " + member.firstName);
            if (this.auth.getLoggedInMember().getTitel() == 5) {
                librarianOption(librarianMenu());
            } else {

                memberOption(memberMenu());
            }
        } else {
            System.out.println("Error, fel lösenord och/eller användarnamn");
        }
    }

    public int memberMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("\nVälj ett alternativ:\n1.Låna bok\n2.Lämna tillbaka bok\n3.Säg upp medlemskap\n4.Visa tillgängliga böcker\n0.Logga ut");
        System.out.print("Val: ");

        return input.nextInt();
    }

    public void memberOption(int option) {
        System.out.println();
        if (option == 1){
            loanByMember();
        }
        else if (option == 2)
        {
            System.out.println("Lämna tillbaka bok metod");
        }
        else if (option == 3){
            System.out.println("Säg upp medlemskap metod");
        }
        else if (option == 4){
            bManager.displayBooks();
        }
        else if (option == 0 ){
            System.exit(0);
        }
        else {
            System.out.println("Inget giltigt val");
            memberOption(memberMenu());
        }
    }

    public int librarianMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nVälj ett alternativ:\n1.Lägg till bok i system\n2.Registrerar medlem\n3.Söka efter medlem\n4.Ta bort medlem" +
                "\n5.Lämna tillbak bok\n6.Låna bok\n0.Logga ut");
        System.out.print("Val: ");
        return input.nextInt();
    }

    public void librarianOption(int option) {
        System.out.println("");
        switch (option){
            case 0:
                start();
                break;
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
                loanByLibrerian();
                break;
            default:
                System.out.println("Inget giltig val\n");
                librarianOption(librarianMenu());
                break;
        }
    }

    public void loanByMember(){
        System.out.println("Ange ISBN för bok");
        System.out.print("ISBN: ");
        Scanner input = new Scanner(System.in);
        int ISBN = input.nextInt();
        bManager.loan(ISBN, member.getIDCode());
        memberOption(memberMenu());
    }
    public void loanByLibrerian(){
        System.out.println("Ange medlems ID för den som ska låna bok: ");
        System.out.print("ID: ");
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();

        member = mStore.getMemberById(id);
        if (member.getIDCode() == id){
            bManager.setMember(member);
            System.out.println("Ange ISBN för bok");
            System.out.print("ISBN: ");
            int isbn = input.nextInt();

            System.out.println("");
            bManager.loan(isbn,member.getIDCode());

            librarianOption(librarianMenu());
        }
        else {
            librarianOption(librarianMenu());
        }
    }
}
