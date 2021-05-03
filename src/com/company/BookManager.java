package com.company;

import java.util.Date;
import java.util.Locale;
import java.time.LocalDate;

public class BookManager implements IBookManager {

    BookStore bStore = null;
    Member member = null;

    public BookManager(BookStore bStore)    {
        this.bStore = bStore;
    }

    public BookManager(BookStore bStore, Member member) {
        this.bStore = bStore;
        this.member = member;
    }

    public void loan(int isbn, int memberId) { //ska behållas
        if (memberLendStatus() && !member.suspended){
            Book[] books = bStore.getBookByIsbn(isbn);

            if (books.length == 0){
                System.out.println("Ingen bok med ISBN finns");
            }
            else if (!checkAvailable(books)){
                System.out.println("Inga lediga böcker att låna ut :(");
            }
            else {
                for (Book book : books) {
                    if (book.isAvailable()) {
                        book.setAvailable(false);
                        book.setLoanDate(LocalDate.now());
                        book.setBorrowedBy(memberId);
                        member.current ++;
                        System.out.println("Du har nu lånat " + book.getTitle());
                        break;
                    }
                }
            }
        }
        else if (member.suspended){
            System.out.println("Suspended");
        }
        else System.out.println("Max antal böcker lånade");
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
        Book[] memberBooks = bStore.getBookByMember(this.member.getIDCode());
        return memberBooks;
    }

    public int numberOfBorrowedBooks() {
        if (memberLoans() == null){
            return memberLoans().length;
        }
        else
            return member.getCurrent();
    }

    public boolean memberLendStatus() { //Kolla upp om man kan låna mer böcker
        if (member.getCurrent() >= member.getMaxloans())
            return false;
        else
            return true;
    }

    public void returnBook(int isbn) {
        Book[] books = bStore.getBookByIsbn(isbn);
        LocalDate currentDate = LocalDate.now();
        for (Book book: books){

            if (currentDate.isAfter(book.getLoanDate().plusDays(15)))
            {
                member.strikes++;
            }

            if (book.getBorrowedBy() == member.getIDCode()){
                book.setAvailable(true);
                member.current --;
                System.out.println(book.getTitle() + " har lämnats tillbak");
            }
        }
    }
}