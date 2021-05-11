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

        memberMenu();

    }

    public void memberMenu() {
        System.out.println("\nVälj ett alternativ:\n1.Låna bok\n2.Lämna tillbaka bok\n3.Säg upp medlemskap\n4.Visa lån\n0.Logga ut");
        System.out.print("\nVal: ");
        Scanner input = new Scanner(System.in);

        int in  = input.nextInt();
        memberOption(in);

    }

    public void memberOption(int option) {
        System.out.println("");
        if (option == 1) {
            loanByMember();
            memberMenu();

        } else if (option == 2) {
            returnBook();
            memberMenu();
        } else if (option == 3) {
            deleteAccount();
            AuthService auth = new AuthService();
            auth.start();

        } else if (option == 4) {
            viewLoans();

        } else if (option == 0) {
            AuthService auth = new AuthService();
            auth.start();
        } else {
            System.out.println("Inget giltigt val");
            memberMenu();
        }
    }

    public void loanByMember() {
        System.out.println("Ange ISBN för bok");
        System.out.print("ISBN: ");
        Scanner input = new Scanner(System.in);
        long isbn = input.nextLong();
        try {
            Book book = bManager.loan(isbn, user.getIDCode());
            System.out.println("Du har nu lånat " + book.getTitle());

        } catch (Exception inteBra) {
            System.out.println(inteBra.getMessage());
            memberMenu();
        }
    }

    public void returnBook() {
        Scanner input = new Scanner(System.in);
        System.out.println("Lämna tillbaka bok");
        System.out.print("Ange isbn: ");
        long isbn = input.nextLong();

        try {
             book = bManager.returnBook(isbn);
        }
        catch (Exception error){
            System.out.println(error.getMessage());
        }
    }

    public void deleteAccount() {
        System.out.println("Bekräfta (y/n)");

        Scanner scan = new Scanner(System.in);

        if (scan.next().equalsIgnoreCase("y")) {
            MemberManager mManager = new MemberManager(user);
            mManager.removeMember(user);
            //scan.close();
        } else{
            memberMenu();
        //       memberOption(memberMenu());
        scan.close();
        }

    }

    public void viewLoans() {
        for (Book b : bManager.getMemberLoans()
        ) {
            System.out.println(b.getTitle() + "\n");
        }
        memberMenu();
    }
}