package com.company;

public interface IBookManager {

    Book loan(long isbn, int memberId);
    Book returnBook(long isbn);


}
