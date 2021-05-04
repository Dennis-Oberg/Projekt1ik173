package com.company;

import java.time.LocalDate;

public class Book {
    private long isbn;
    private String title;
    private boolean available;
    private LocalDate loanDate;
    private int borrowedBy;

    public int getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(int borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public Book(long isbn, String title){
        this.isbn = isbn;
        this.title = title;
        this.available = true;
        this.loanDate = LocalDate.now();
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
