package com.company;

public class BookStore implements IBookStore {
    public Book[] getBooks(){
        return new Book[0];
        //select * from Books
    }

    public Book getBookByTitle(String title)
    {
        //kod mot databas som hämtar en Book
        return new Book(123, "ost");
    }
}
