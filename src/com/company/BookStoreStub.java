package com.company;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class BookStoreStub extends BookStore {

    ArrayList<Book> bookList;
    Borrowedby borrowedby;

    public BookStoreStub() {
        this.bookList = new ArrayList<>();
        this.borrowedby = new Borrowedby();
    }

    public boolean insertBook(long isbn, String title, String author, int copies) {
        Book book = new Book(isbn, title, copies, author);
        this.bookList.add(book);
        return true;
    }


    public Book getBookByIsbn(long isbn) {

        Book book = null;

        for (Book b : bookList) {
            if (b.getIsbn() == isbn) {
                book = b;
            }
        }
        return book;
    }

    public Book[] getBookByMember(int member) {
        ArrayList<Book> bookArrayList = new ArrayList<>();

        for (Book book : borrowedby.loanList) {

            bookArrayList.add(book);
        }
        Book[] books = new Book[bookArrayList.size()];
        return bookArrayList.toArray(books);
    }

    public Book[] checkAvailability(Long isbn) {
        ArrayList<Book> bookArrayList = new ArrayList<>();

        for (Book book : bookList) {
            if (book.getIsbn() == isbn) {
                bookArrayList.add(book);
            }
        }

        Book[] books = new Book[bookArrayList.size()];
        return bookArrayList.toArray(books);
    }

    public void loanBook(Book book, User user) {

        int copies = book.getCopy();


        if (copies > 0) {

            for (Book b : bookList
            ) {
                if (b.getIsbn() == book.getIsbn()) {
                    Date today = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(today);
                    cal.add(Calendar.DAY_OF_MONTH, 15);
                    Date returnDate = cal.getTime();


                    b.setBorrowedBy(user.getIDCode());
                    b.setCopy(book.getCopy() - 1);
                    b.setLoanDate(today);
                    b.setReturnDate(returnDate);

                    borrowedby.loanList.add(b);
                }
            }
        }
    }


    public void returnBook(Book book, User user) {
        if (book.getBorrowedBy() == user.getIDCode()) {

            for (Book b : bookList
            ) {
                if (b.getIsbn() == book.getIsbn()) {
                    borrowedby.loanList.remove(book);

                    b.setCopy(book.getCopy() + 1);
                    b.setLoanDate(null);
                    b.setReturnDate(null);
                }
            }

        }
    }
}
