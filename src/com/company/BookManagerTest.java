package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookManagerTest {


    @Test
    public void test_AddBook() {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123, 19991122, "Gustaf", "Thim", 3, 0, 0, null, false);
        BookManager bookManager = new BookManager(user, bookStoreStub);

        Book harry = new Book(1234, "Harry Potter", 2, "JK rowling");
        Book öl = new Book(1234, "Harry Potter", 2, "JK rowling");
        Book starköl = new Book(1234, "Harry Potter", 2, "JK rowling");

        bookManager.bStore.bookList.add(harry);
        bookManager.bStore.bookList.add(öl);
        bookManager.bStore.bookList.add(starköl);
        bookManager.addBook(1234, "Harry Potter", "JK rowling",3 );

        assertEquals(3, bookManager.bStore.bookList.size());
    }

}