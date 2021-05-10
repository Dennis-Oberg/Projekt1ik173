package com.company;

import java.util.Scanner;

public class Member extends User{

    User user;
    Book book;
    BookManager bManager;

    public Member(User loggedinUser){

        user = loggedinUser;
        bManager = new BookManager(user);

        memberOption(memberMenu());

    }

    public int memberMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("\nVälj ett alternativ:\n1.Låna bok\n2.Lämna tillbaka bok\n3.Säg upp medlemskap\n0.Logga ut");
        System.out.print("Val: ");

        return input.nextInt();
    }

    public void memberOption(int option) {
        System.out.println("");
        if (option == 1){
            loanByMember();
            memberOption(memberMenu());
        }
        else if (option == 2){
            returnBook();
            memberOption(memberMenu());
        }
        else if (option == 3){
            deleteAccount();
            AuthService auth = new AuthService();
            auth.start();
            //System.out.println("Säg upp medlemskap metod");
        }
        else if (option == 0){
            AuthService auth = new AuthService();
            auth.start();
        }
        else {
            System.out.println("Inget gitligt val");
            memberOption(memberMenu());
        }
    }

    public void loanByMember() {
        System.out.println("Ange ISBN för bok");
        System.out.print("ISBN: ");
        Scanner input = new Scanner(System.in);
        int isbn = input.nextInt();
        try {
            book = bManager.loan(isbn, user.getIDCode());
            System.out.println("Du har nu lånat " + book.getTitle());

        } catch (Exception inteBra) {
            System.out.println(inteBra.getMessage());
            memberOption(memberMenu());
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

    public void deleteAccount(){
        MemberManager mManager = new MemberManager(user);
        mManager.removeMember(user);
    }

}
