package com.company;

public interface IBookManager {

    public void loan(int isbn, int memberId);

    public void returnBook(int isbn);


}
