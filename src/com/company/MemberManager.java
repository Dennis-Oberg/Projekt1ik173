package com.company;

import java.util.Scanner;

public class MemberManager {
    BookStore bStore = null;
    User user = null;
    MemberStore mStore;

    public MemberManager(){ //skick in ny bookstore samt medlem gjort med konstruktorn
        mStore = new MemberStore();
    }

    public void removeMember(){

    }

    public void searchForMember(){

    }

    public void addUser(int id, int ssn, String fName, String lName, int title){


        mStore.creatNewMember(id, ssn, fName, lName, title);
    }

    public MemberManager(User user){
        this.user = user;
    }
/*
    public Book[] memberLoans(){
        Book[] memberBooks = bStore.getBookByMember(this.user.getIDCode());

        return memberBooks;
    }
    public int numberOfBorrowedBooks(){
        return memberLoans().length;
    }

 */
}
