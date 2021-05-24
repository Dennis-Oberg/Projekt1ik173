package com.company;

import java.time.LocalDate;
import java.util.Date;

public class Book {
    private long isbn;
    private String title;
    private int copy;
    private String author;
    private int borrowedBy;
    private Date loanDate;
    private Date returnDate;

    public int getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(int borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public Book(long isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public Book(long isbn, int borrowedBy) {
        this.isbn = isbn;
        this.borrowedBy = borrowedBy;
    }

    public Book(long isbn, String title, int copy, String author, int borrowedBy) {
        this.isbn = isbn;
        this.title = title;
        this.copy = copy;
        this.borrowedBy = borrowedBy;
        this.author = author;
    }

    public Book(long isbn, String title, int copy, String author, int borrowedBy, Date loanDate, Date returnDate) {
        this.isbn = isbn;
        this.title = title;
        this.copy = copy;
        this.borrowedBy = borrowedBy;
        this.author = author;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }

    public String getAuthor() {
        return author;
    }
}

