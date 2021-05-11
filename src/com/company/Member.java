package com.company;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Member extends User {

    User user;
    Book book;
    BookManager bManager;

    public Member(User loggedinUser) {

        user = loggedinUser;
        bManager = new BookManager(user);

        //memberOption(memberMenu());
        memberMenu();

    }

    public void memberMenu() {
        System.out.println("\nVälj ett alternativ:\n1.Låna bok\n2.Lämna tillbaka bok\n3.Säg upp medlemskap\n4.Visa lån\n0.Logga ut");
        System.out.println("\nVal: ");
        Scanner input = new Scanner(System.in);


        int in  = input.nextInt();
        memberOption(in);
        /*
        try {



        } catch (InputMismatchException ine){
            ine.printStackTrace();

        }



         */
        //return input.nextInt();

    }

    public void memberOption(int option) {
        System.out.println("");
        if (option == 1) {
            loanByMember();
            // memberOption(memberMenu());
        } else if (option == 2) {
            returnBook();
            memberMenu();
        } else if (option == 3) {
            deleteAccount();
            AuthService auth = new AuthService();
            auth.start();
            //System.out.println("Säg upp medlemskap metod");
        } else if (option == 4) {
            viewLoans();
            //  memberOption(memberMenu());

        } else if (option == 0) {
            AuthService auth = new AuthService();
            auth.start();
        } else {
            System.out.println("Inget gitligt val");
            //  memberOption(memberMenu());

        }
    }

    public void loanByMember() {
        System.out.println("Ange ISBN för bok");
        System.out.print("ISBN: ");
        Scanner input = new Scanner(System.in);
        long ISBN = input.nextLong();
        bManager.loan(ISBN, user.getIDCode());
        //  memberOption(memberMenu());
        memberMenu();
    }

    public void returnBook() {
        Scanner input = new Scanner(System.in);
        System.out.println("Lämna tillbaka bok");
        System.out.print("Ange isbn: ");
        long isbn = input.nextLong();

        bManager.returnBook(isbn);
        input.close();
        //  memberOption(memberMenu());


    }

    public void deleteAccount() {
        System.out.println("Bekräfta (y/n)");

        Scanner scan = new Scanner(System.in);

        if (scan.next().equalsIgnoreCase("y")) {
            MemberManager mManager = new MemberManager(user);
            mManager.removeMember(user);
            scan.close();
        } else
            memberMenu();
        //       memberOption(memberMenu());
        scan.close();

    }

    public void viewLoans() {
        for (Book b : bManager.memberLoans()
        ) {
            System.out.println(b.getTitle() + "\n");
        }
        memberMenu();
    }
}