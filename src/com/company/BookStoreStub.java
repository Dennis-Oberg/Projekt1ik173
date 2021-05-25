package com.company;

import java.util.ArrayList;

public class BookStoreStub extends BookStore {

    ArrayList<Book> bookList = null;

    public BookStoreStub()  {
        this.bookList = new ArrayList<>();

        bookList.add(new Book(1234,"Harry Potter"));
        bookList.add(new Book(1235, "Nalle Puh"));

        bookList.add(new Book(1236, "Pippi"));
        bookList.add(new Book(1237, "Star wars"));
        bookList.add(new Book(1238, "Greta gris"));
    }

    public void addBook(Book bk) //Hjälpmetod för testning så att man enkelt kan lägga in böcker
    {
        this.bookList.add(bk);
    }

    public Book[] getBooks() {
        Book[] books = new Book[bookList.size()];
        return this.bookList.toArray(books);
    }
/*
    public Book[] getBookByTitle(String title) {
        Book tempBook = null;
        for (Book b: bookList
        ) {
            if (b.getTitle().equals(title))
            {
                tempBook = b;
            }
        }
        return tempBook;
    }

 */

    public Book[] getBookByIsbn(int isbn)   {
        ArrayList<Book> tempList = new ArrayList<>();

        for (Book book: bookList){
            if (book.getIsbn() == isbn){
                tempList.add(book);
            }
        }
        Book[] books = new Book[tempList.size()];
        return tempList.toArray(books);
    }

    public Book[] getBookByMember(int member)  {
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
