package com.company;

public interface IBookManager {

    Book loan(long isbn);
    Book returnBook(long isbn);


}
