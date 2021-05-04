package com.company;

public class MemberManager {
    BookStore bStore = null;
    Member member = null;

    public MemberManager(BookStore bStore, Member member){ //skick in ny bookstore samt medlem gjort med konstruktorn
        this.bStore = bStore;
        this.member = member;
    }
    public MemberManager(Member member){
        this.member = member;
    }
/*
    public Book[] memberLoans(){
        Book[] memberBooks = bStore.getBookByMember(this.member.getIDCode());

        return memberBooks;
    }
    public int numberOfBorrowedBooks(){
        return memberLoans().length;
    }

 */
}
