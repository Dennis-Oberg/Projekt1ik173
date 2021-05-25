package com.company;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class BookStoreStub extends BookStore {

    ArrayList<Book> bookList = null;
    Borrowedby borrowedby;

    public BookStoreStub()  {
        this.bookList = new ArrayList<>();
    }

    public void addBook(Book bk) {
        this.bookList.add(bk);
    }

    public Book getBookByIsbn(int isbn)   {
        Book book = null;

        for (Book b: bookList){
            if (b.getIsbn() == isbn){
                book = b;
            }
        }
        return book;
    }

    public Book[] getBookByMember(int member)  {
        ArrayList<Book> bookArrayList = new ArrayList<>();

        for (Book book: bookList){
            if (book.getBorrowedBy() == member){
                bookArrayList.add(book);
            }
        }
        Book[] books = new Book[bookArrayList.size()];
        return bookArrayList.toArray(books);
    }

    public Book[] checkAvailability(Long isbn) {
        ArrayList<Book> bookArrayList = new ArrayList<>();

        for (Book book: bookList){
            if (book.getIsbn() == isbn){
                bookArrayList.add(book);
            }
        }

        Book[] books = new Book[bookArrayList.size()];
        return bookArrayList.toArray(books);
    }

    public void loanBook(Book book, User user) {
        CheckConnection();

        int copies = book.getCopy();

        if (copies>0) {
            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(today);
            cal.add(Calendar.DAY_OF_MONTH,15);
            Date returnDate = cal.getTime();

            book.setBorrowedBy(user.getIDCode());
            book.setCopy(book.getCopy()-1);
            book.setLoanDate(today);
            book.setReturnDate(returnDate);

            borrowedby = new Borrowedby();
            borrowedby.loanList.add(book);

        }

    }

    public void returnBook(Book book, User user) {
        if (book.getBorrowedBy() == user.getIDCode()) {

            borrowedby.loanList.remove(book);

            book.setCopy(book.getCopy()+1);
            book.setLoanDate(null);
            book.setReturnDate(null);
        }
    }

}
