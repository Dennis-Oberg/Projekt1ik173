package com.company;

public interface IBookManager {

    public void loan(long isbn, int memberId);

    public void returnBook(long isbn);


}
