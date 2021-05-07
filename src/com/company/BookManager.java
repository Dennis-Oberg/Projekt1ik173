package com.company;

import java.time.LocalDate;

public class BookManager implements IBookManager {

    BookStore bStore;

    User user = null;

    public BookManager(BookStore bStore) {
        this.bStore = bStore;
    }

    public BookManager(BookStore bStore, User user) {
        this.bStore = bStore;
        this.user = user;
    }

    public void displayBooks()
    {
        for (Book b: bStore.getBooks()
             ) {
            System.out.println(b.getTitle() + " " + b.getIsbn());
        }

        long newISBN = 978958111805L;

        for (Book b: bStore.getBookByIsbn(newISBN)
        ) {
            System.out.println(b.getTitle() + " " + b.getIsbn());
        }

        System.out.println();
        bStore.getBookByTitle("Matematik 1");
    }

    public void loan(long isbn, int memberId) { //ska behållas
        if (memberLendStatus() && !user.suspended) {
            Book[] books = bStore.getBookByIsbn(isbn);

            if (books.length == 0) {
                System.out.println("Ingen bok med ISBN finns");
            } else if (!checkAvailable(books)) {
                System.out.println("Inga lediga böcker att låna ut");
            } else {
                for (Book book : books) {
                    if (book.isAvailable()) {
                        book.setAvailable(false);
                        book.setLoanDate(LocalDate.now());
                        book.setBorrowedBy(memberId);
                        bStore.setBookStatus(book);
                        //user.books.add(book);
                        //user.current++;
                        System.out.println("Du har nu lånat " + book.getTitle());
                        break;
                    }
                }
            }
        } else if (user.suspended) {
            System.out.println("Suspended");
        } else System.out.println("Max antal böcker lånade");
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

    public Book[] memberLoans() {
        return bStore.getBookByMember(this.user.getIDCode());
    }

    public int numberOfBorrowedBooks() {
        return memberLoans().length;
    }

    public boolean memberLendStatus() { //Kolla upp om man kan låna mer böcker
        user.setCurrent(numberOfBorrowedBooks());
        return user.getCurrent() < user.getMaxloans();
    }

    public void returnBook(long isbn) {
        Book[] books = bStore.getBookByIsbn(isbn);
        LocalDate currentDate = LocalDate.now();

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
            }
        }
    }
    public void setMember(User user) {
        this.user = user;
    }
}