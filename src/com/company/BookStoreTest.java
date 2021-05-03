package com.company;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BookStoreTest {

    @Test
    public void test_lendBook() {
        BookStore store = new BookStore();
        Member member = new Member(1, 1, "Denis", "Öberg", 4);
        Member member1 = new Member(2, 1, "Gustaf", "Öberg", 4);

        BookManager cut = new BookManager(store, member);
        BookManager cut1 = new BookManager(store, member1);


        store.bookList.add(new Book(1234, "Harry potter"));
        store.bookList.add(new Book(1234, "Harry potter"));
        store.bookList.add(new Book(1234, "Harry potter"));
        store.bookList.add(new Book(1235, "Pippi"));

        cut.loan(1234, 1);
     //   cut.loan(1234, 1);
        cut.loan(1234, 1);
        cut.loan(1235, 1);


        cut1.loan(1234, 2);





    }

    @Test
    public void test_medlem() {
        BookStore store = new BookStore();
        Member member = new Member(1, 1, "Kurt", "Olsson", 1);
        BookManager cut = new BookManager(store, member);

        store.bookList.add(new Book(1234, "Harry potter"));
        store.bookList.add(new Book(1234, "Harry potter"));
        store.bookList.add(new Book(1234, "Harry potter"));

        store.bookList.add(new Book(1235, "Nalle Puh"));
        //store.bookList.get(3).available = false;
        store.bookList.add(new Book(1236, "Pippi"));
        store.bookList.add(new Book(1237, "Star wars"));
        store.bookList.add(new Book(1238, "Greta gris"));
        // member.suspended = true;

        cut.loan(1239, 1);
        cut.loan(1234, 1);
        cut.loan(1235, 1);
        cut.loan(1236, 1);
        cut.loan(1238, 1);
        cut.loan(1237, 1);

        System.out.println(store.bookList.get(3).getLoanDate());
        //store.bookList.get(2).setBorrowedBy(0001);
        System.out.println(store.bookList.get(6).isAvailable());

        System.out.println(cut.numberOfBorrowedBooks());

        cut.returnBook(1238);
        System.out.println(store.bookList.get(6).isAvailable());

        System.out.println(cut.numberOfBorrowedBooks());
    }
}