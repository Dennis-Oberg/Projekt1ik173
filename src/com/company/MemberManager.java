package com.company;

import java.util.Scanner;

public class MemberManager {

    User user = null;
    BookManager bookManager;
    MemberStore mStore;

    public MemberManager(User user, MemberStore memberStore) {
        this.user = user;
        this.mStore = memberStore;
    }

    public void removeMember(User user) {
        //Måste kolla om man har böcker lånade innan man kan ta bort. Om inte blir det fel i databas
        BookStore bookStore = new BookStore();
        bookManager = new BookManager(user, bookStore);
        int numberOfBooks = bookManager.numberOfBorrowedBooks();

        if (numberOfBooks > 0) {
            System.out.println("Behöver lämna tillbaks följande böcker innan medlem kan tas bort:");
            Book[] books = bookManager.memberLoans();
            for (Book book : books) {
                System.out.println(book.getTitle() + " (ISBN: " + book.getIsbn() + ")");

            }
        } else {
            mStore.removeMember(user);
        }
    }

    public User searchMember(int id) {
        user = mStore.getMemberById(id);
        return user;
    }

    public void addUser(int ssn, String fName, String lName, int title) {

        //Ska senare kolla om man varit medlem tidigare eller är bannad
        mStore.creatNewMember(ssn, fName, lName, title);
    }

}