package com.company;

import java.util.Scanner;

public class MemberManager {

    User user = null;
    BookManager bookManager;
    MemberStore mStore;

    public MemberManager(){ //skick in ny bookstore samt medlem gjort med konstruktorn
        mStore = new MemberStore();
    }

    public void removeMember(User user){
        //Måste kolla om man har böcker lånade innan man kan ta bort. Om inte blir det fel i databas
        bookManager = new BookManager(user);
        int numberOfBooks = bookManager.numberOfBorrowedBooks();

        if (numberOfBooks > 0){
            System.out.println("Behöver lämna tillbaks följande böcker innan medlem kan tas bort:");
            Book[] books = bookManager.memberLoans();
            for (Book book : books) {
                System.out.println(book.getTitle()  + " (ISBN: " + book.getIsbn() + ")");

            }
        }
        else {mStore.removeMember(user.getIDCode());}



    }

    public User searchMember(int id){
        user = mStore.getMemberById(id);
        return user;
    }

    public void addUser(int ssn, String fName, String lName, int title){

        //Ska senare kolla om man varit medlem tidigare eller är bannad
        mStore.creatNewMember(ssn, fName, lName, title);
    }

    public MemberManager(User user){
        this.user = user;
        mStore = new MemberStore();
    }

    public void banMember(User user) {
        mStore.moveToBannedMember(user);
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
