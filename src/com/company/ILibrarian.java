package com.company;

public interface ILibrarian {
    void addMember(int memberID);
    void removeMember(int memberID);
    void suspendMember(int memberID);
    Book provideBook(int isbn);


}
