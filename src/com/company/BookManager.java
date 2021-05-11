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

    public Book loan(long isbn, int memberId) throws NoSuchElementException, NullPointerException { //kanske försöka ta bort memberID som in parameter
        Book tempBook = null;
        if (memberLendStatus() && !user.suspended) {
            Book[] books = bStore.getBookByIsbn(isbn);

            if (books.length == 0) {
                throw new NoSuchElementException("Ingen bok med ISBN finns");
                //System.out.println("Ingen bok med ISBN finns");
            } else if (!checkAvailable(books)) {
                throw new NullPointerException("Inga lediga böcker att låna ut");
                //System.out.println("Inga lediga böcker att låna ut"); //MÅSTE FIXAS
            } else {
                for (Book book : books) {
                    if (book.isAvailable()) {
                        LocalDate currentDate = LocalDate.now();
                        book.setAvailable(false);
                        book.setLoanDate(LocalDate.now());
                        book.setBorrowedBy(memberId);
                        bStore.setBookStatus(book);
                        //user.books.add(book);
                        //user.current++;
                        System.out.println("Du har nu lånat " + book.getTitle());
                        tempBook = book;
                        break;
                    }
                }
            }
            return tempBook;
        } else if (user.suspended) {
            throw new NoSuchElementException("Användaren är bannad :<");
        } else
        throw new NoSuchElementException("Max antal böcker lånade");
    }

    public boolean checkAvailable(Book[] books) {

        for (Book book : books) {
            if (book.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    public void borrowedBy(int memberId) {

    }

    public Book[] getMemberLoans() {
        return bStore.getBookByMember(this.user.getIDCode());
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

    public Book returnBook(long isbn) throws NullPointerException  {
        Book[] books = bStore.getBookByIsbn(isbn);
        LocalDate currentDate = LocalDate.now();
        if (books.length == 0)
            throw new NullPointerException("Fel ISBN");
        else {
            for (Book book : books) {
        /*
            if (currentDate.isAfter(book.getLoanDate().plusDays(15))) {
                if (user.strikes>2)
                {
                    if (user.suspendedOnce)
                    {
                        //ta bort userfan
                    }
                    else
                    {
                        user.setSuspendedOnce(true);
                    }
                }
                user.strikes++;
            }
         */

                if (book.getBorrowedBy() == user.getIDCode()) {
                    book.setAvailable(true);
                    book.setBorrowedBy(0);
                    //user.current--;
                    bStore.setBookStatus(book);
                    System.out.println(book.getTitle() + " har lämnats tillbaka");
                    return book;
                }
            }
            throw new NullPointerException("Du har inga aktiva lån på denna bok");
        }
    }
    public void setMember(User user) {
        this.user = user;
    }
}