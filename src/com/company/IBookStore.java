package com.company;

public interface IBookStore {

    Book[] getBooks();

    Book getBookByTitle(String title);

    Book getBookByIsbn(long isbn);

    Book[] getBookByMember (int member);

}
