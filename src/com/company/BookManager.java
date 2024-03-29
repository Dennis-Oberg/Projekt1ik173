package com.company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Date;
import java.util.NoSuchElementException;

public class BookManager {

    private static final Logger logger = LogManager.getLogger(BookManager.class.getName());


    BookStore bStore;
    Book[] books;
    User user;

    public BookManager(User user, BookStore bStore) {
        this.bStore = bStore;
        this.user = user;
        books = getMemberLoans();
    }

    public Book loan(long isbn) throws NoSuchElementException, NullPointerException {
        Book tempBook = bStore.getBookByIsbn(isbn);
        if (memberLendStatus()) {

            if (tempBook == null) {
                throw new NoSuchElementException("Ingen bok med ISBN finns");

            } else if (!checkAvailable(tempBook)) {
                throw new NullPointerException("Inga lediga böcker att låna ut");
            } else {

                bStore.loanBook(tempBook, user);
            }
            return tempBook;
        } else
            throw new NoSuchElementException("Max antal böcker lånade");
    }

    public Book returnBook(long isbn) throws NullPointerException {
        Book tempBook = bStore.getBookByIsbn(isbn);
        Book[] booklist = bStore.getBookByMember(user.getIDCode());

        if (tempBook == null) {
            throw new NullPointerException("Fanns ingen bok med valt ISBN");
        } else {
            for (Book b : booklist) {
                if (b.getIsbn() == isbn) {
                    b.setBorrowedBy(user.getIDCode());
                    bStore.returnBook(b, user);
                    System.out.println(b.getTitle() + " har lämnats tillbaka");
                    return b;
                }
            }
            throw new NullPointerException("Du har inte lånat denna boken");
        }
    }

    public boolean overDueLoan(long isbn, Date date) {
        Book[] booklist = bStore.getBookByMember(user.getIDCode());

        for (Book b: booklist) {
            if (b.getIsbn() == isbn){
                if (date.after(b.getReturnDate())) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean addBook(long isbn, String title, String author, int copies) {
        return bStore.insertBook(isbn, title, author, copies);
    }

    public boolean checkAvailable(Book book) {
        //Book[] bookList = bStore.checkAvailability(book.getIsbn());

        if (book.getCopy() == 0) {
            return false;
        }

        else
        return book.getCopy() > 0;
    }

    public Book getBook(long isbn) {
        return bStore.getBookByIsbn(isbn);
    }

    public Book[] getMemberLoans() {
        return bStore.getBookByMember(this.user.getIDCode());
    }

    public Book[] getMemberLoansForLibrarian(User newUser) {
        return bStore.getBookByMember(newUser.getIDCode());
    }

    public int numberOfBorrowedBooks() {
        return getMemberLoans().length;
    }

    public boolean memberLendStatus() { //Kolla upp om man kan låna mer böcker
        user.setCurrent(numberOfBorrowedBooks());

        return user.getCurrent() < user.getMaxloans();
    }

    public void setMember(User user) {
        this.user = user;
    }
}