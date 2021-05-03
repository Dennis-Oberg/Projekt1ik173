package com.company;

import java.time.LocalDate;

public class Book {
    int isbn;
    String title;
    boolean available;
    LocalDate loanDate;
    int borrowedBy;

    public int getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(int borrowedBy) {
        this.borrowedBy = borrowedBy;
    }


    //int totalAvailable;

    public Book(int isbn, String title){
        this.isbn = isbn;
        this.title = title;
        this.available = true;
    }

    public int getIsbn() {
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }
}
