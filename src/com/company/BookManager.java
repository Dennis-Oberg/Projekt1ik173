package com.company;

import java.time.LocalDate;
import java.util.NoSuchElementException;

public class BookManager implements IBookManager {

    BookStore bStore;
    Book[] books;
    User user = null;


    public BookManager(BookStore bStore) {
        this.bStore = bStore;
    }


    public BookManager(User user) {
        this.bStore = new BookStore();
        this.user = user;
        books = getMemberLoans();
    }


    public BookManager(BookStore bStore, User user) {
        this.bStore = bStore;
        this.user = user;
        books = getMemberLoans();
    }

    public Book loan(long isbn) throws NoSuchElementException, NullPointerException { //kanske försöka ta bort memberID som in parameter
        Book tempBook = bStore.getBookByIsbn(isbn);
        if (memberLendStatus() && !user.suspended) {

            if (tempBook == null) {
                throw new NoSuchElementException("Ingen bok med ISBN finns");

            } else if (!checkAvailable(tempBook)) {
                throw new NullPointerException("Inga lediga böcker att låna ut");
            } else {
                tempBook.setBorrowedBy(user.getIDCode());
                bStore.loanBook(tempBook);

            }
            return tempBook;
        } else if (user.suspended) {
            throw new NoSuchElementException("Användaren är bannad :<");
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
        for (Book b: booklist
             ) {
            if (b.getBorrowedBy() == user.getIDCode())
            {
                tempBook.setBorrowedBy(user.getIDCode());
                bStore.returnBook(tempBook);
                System.out.println(tempBook.getTitle() + " har lämnats tillbaka"); //funkar ej, programmet går aldrig in hit?
            }
            else
            {
                System.out.println("Du har inte lånat denna boken");
            }
        }
        return tempBook;
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
        Book[] bookList = bStore.checkAvailability(book.getIsbn());

        return bookList.length < book.getCopy();
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