package com.company;

public interface IBookManager {

    public Book loan(long isbn, int memberId);

    public Book returnBook(long isbn);


}
