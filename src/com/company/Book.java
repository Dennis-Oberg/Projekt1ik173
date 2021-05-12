package com.company;

import java.time.LocalDate;

public class Book {
    private long isbn;
    private String title;
    private int copy;
    private String author;
    private int borrowedBy;

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

