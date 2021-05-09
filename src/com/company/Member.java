package com.company;

import java.util.Scanner;

public class Member extends User{

    User user;
    BookManager bManager;

    public Member(AuthService loggedinUser){

        user = loggedinUser.getLoggedInMember();
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
        }
        else if (option == 2){
            System.out.println("Lämna tillbaka bok metod");
        }
        else if (option == 3){
            System.out.println("Säg upp medlemskap metod");
        }
        else if (option == 0){
            Menu menu = new Menu();
            menu.start();
        }
        else {
            System.out.println("Inget gitligt val");
            memberOption(memberMenu());
        }
    }

    public void loanByMember(){
        System.out.println("Ange ISBN för bok");
        System.out.print("ISBN: ");
        Scanner input = new Scanner(System.in);
        int ISBN = input.nextInt();
        bManager.loan(ISBN, user.getIDCode());
        memberOption(memberMenu());
    }

}
