package com.company;

import java.util.Date;

public class BorrowedBook {
    private String title;

    private int ID;
    private String ISBN;
    private Date start_date;
    private Date end_date;




    public BorrowedBook(String title, int ID, String ISBN, Date start_date, Date end_date) {
        this.title = title;
        this.ID = ID;
        this.ISBN = ISBN;
        this.start_date = start_date;
        this.end_date = end_date;
    }



}
