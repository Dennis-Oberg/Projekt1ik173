package com.company;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookStoreTest {

    @Test
    public void test_lendBook(){
        BookStore store = new BookStore();
        BookManager cut = new BookManager(store);

        store.bookList.add(new Book(1234,"Harry potter"));
        store.bookList.add(new Book(1234,"Harry potter"));
        store.bookList.add(new Book(1234,"Harry potter"));
        store.bookList.add(new Book(1235,"Pippi"));

        cut.loan(1234);
        cut.loan(1234);
        cut.loan(1234);
        cut.loan(1234);
        cut.loan(1235);
        cut.loan(1235);
        cut.loan(1236);
    }
    @Test
    public void test_medlem(){
        BookStore store = new BookStore();
        Member member = new Member(0001, 1,"Kurt", "Olsson", 1);
        BookManager cut = new BookManager(store,member);

        store.bookList.add(new Book(1234,"Harry potter"));
        store.bookList.add(new Book(1234,"Harry potter"));
        store.bookList.add(new Book(1234,"Harry potter"));

        store.bookList.add(new Book(1235,"Nalle Puh"));
        //store.bookList.get(3).available = false;
        store.bookList.add(new Book(1236,"Pippi"));
        store.bookList.add(new Book(1237,"Star wars"));
        store.bookList.add(new Book(1238,"Greta gris"));
       // member.suspended = true;

        cut.loan(1239,0001);
        cut.loan(1234,0001);
        cut.loan(1235,0001);
        cut.loan(1236,0001);
        cut.loan(1238,0001);
        cut.loan(1237,0001);

        System.out.println(store.bookList.get(3).getLoanDate());
        //store.bookList.get(2).setBorrowedBy(0001);
        System.out.println(store.bookList.get(6).available);

        System.out.println(cut.numberOfBorrowedBooks());

        cut.returnBook(1238);
        System.out.println(store.bookList.get(6).available);

        System.out.println(cut.numberOfBorrowedBooks());
    }
}