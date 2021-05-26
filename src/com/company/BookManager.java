package com.company;

import java.time.LocalDate;
import java.util.Date;
import java.util.NoSuchElementException;

public class BookManager {

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
                tempBook.setBorrowedBy(user.getIDCode());
                bStore.loanBook(tempBook);
            }
            return tempBook;
        } else
            throw new NoSuchElementException("Max antal böcker lånade");
    }

    public Book returnBook(long isbn) throws NullPointerException  {
        Book tempBook = bStore.getBookByIsbn(isbn);
        Book[] booklist = bStore.getBookByMember(user.getIDCode());

        if (tempBook == null)
        {
            throw new NullPointerException("Fanns ingen bok med valt ISBN");
        }
        else {
            for (Book b: booklist) {
                if (b.getIsbn() == isbn){
                    b.setBorrowedBy(user.getIDCode());
                    bStore.returnBook(b);
                    System.out.println(b.getTitle() + " har lämnats tillbaka");
                    return b;
                }
             }
            throw new NullPointerException("Du har inte lånat denna boken");
        }
    }

    public boolean overDueLoan(long isbn) {
        Book[] booklist = bStore.getBookByMember(user.getIDCode());
        for (Book b: booklist) {
            if (b.getIsbn() == isbn){
                long millis = System.currentTimeMillis();
                Date date = new Date(millis);
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

    public boolean addBook(long isbn, String title,String author,int copies)
    {
        if (bStore.insertBook(isbn,title,author,copies))
        {
            return true;
        }
        return false;
    }

    public boolean checkAvailable(Book book) {
        //Book[] bookList = bStore.checkAvailability(book.getIsbn());

        if (book.getCopy() == 0) {
            return false;
        }

        else
        return book.getCopy() > 0;
    }

    public void borrowedBy(int memberId) {

    }

    public Book[] getMemberLoans() {
        return bStore.getBookByMember(this.user.getIDCode());
    }

    public Book[] getMemberLoansForLibrarian(User newUser) {
        return bStore.getBookByMember(newUser.getIDCode());
    }

    public Book[] memberLoans() {
        return books;
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