package com.company;

import java.util.Date;
import java.util.Locale;
import java.time.LocalDate;

public class BookManager implements IBookManager {

    BookStore bStore;

    Member member = null;

    public BookManager(BookStore bStore) {
        this.bStore = bStore;
    }

    public BookManager(BookStore bStore, Member member) {
        this.bStore = bStore;
        this.member = member;
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
        if (memberLendStatus() && !member.suspended) {
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
                        member.books.add(book);
                        member.current++;
                        System.out.println("Du har nu lånat " + book.getTitle());
                        break;
                    }
                }
            }
        } else if (member.suspended) {
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
        return bStore.getBookByMember(this.member.getIDCode());
    }

    public int numberOfBorrowedBooks() {
        return memberLoans().length;
    }

    public boolean memberLendStatus() { //Kolla upp om man kan låna mer böcker
        return member.getCurrent() < member.getMaxloans();
    }

    public void returnBook(long isbn) {
        Book[] books = bStore.getBookByIsbn(isbn);
        LocalDate currentDate = LocalDate.now();

        for (Book book : books) {

            if (currentDate.isAfter(book.getLoanDate().plusDays(15))) {

                if (member.strikes>2)
                {
                    if (member.suspendedOnce)
                    {
                        //ta bort userfan

                    }
                    else
                    {
                        member.setSuspendedOnce(true);
                    }
                }
                member.strikes++;
            }

            if (book.getBorrowedBy() == member.getIDCode()) {
                book.setAvailable(true);
                member.current--;
            } else System.out.println("Du har inte lånat denna bok");
        }
    }
    public void setMember(Member member) {
        this.member = member;
    }
}