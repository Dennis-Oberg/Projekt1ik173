package com.company;

import java.util.ArrayList;

public class BookStore implements IBookStore {
    ArrayList<Book> bookList = null;

    public BookStore(){
        this.bookList = new ArrayList<>();
    }

    public Book[] getBooks(){
        return new Book[0];
        //select * from Books
    }

    public Book getBookByTitle(String title)
    {
        //kod mot databas som hämtar en Book
        return new Book(123, "ost");
    }
    public Book[] getBookByIsbn(int isbn){
        ArrayList<Book> tempList = new ArrayList<>();

        for (Book book: bookList){
            if (book.getIsbn() == isbn){
                tempList.add(book);
            }
        }
        Book[] books = new Book[tempList.size()];
        return tempList.toArray(books);
    }
    public Book[] getBookByMember (int member){
        ArrayList<Book> tempList = new ArrayList<>();

        for (Book book: bookList){
            if (book.getBorrowedBy() == member){
                tempList.add(book);
            }
        }
        Book[] books = new Book[tempList.size()];
        return tempList.toArray(books);
    }
}
