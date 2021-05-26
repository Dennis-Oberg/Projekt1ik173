package com.company;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BookManagerTest {


    @Test
    public void test_single_loan () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);
        Book[] expectedBookList = new Book[]{new Book(1234,"Harry Potter",3, "JK rowling")};

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);

        bookManager.loan(1234);

        assertEquals(expectedBookList.length, bookManager.getMemberLoans().length);
    }

    @Test
    public void test_multiple_loan () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);
        Book[] expectedBookList = new Book[]{new Book(1234,"Harry Potter",3, "JK rowling"), new Book(1235,"Harry Potter 2",3, "JK rowling")};

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);
        bookManager.addBook(1235,"Harry Potter 2","JK rowling", 3);

        bookManager.loan(1234);
        bookManager.loan(1235);

        assertEquals(expectedBookList.length, bookManager.getMemberLoans().length);
    }

    @Test
    public void test_sameBook_loan () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);
        Book[] expectedBookList = new Book[]{new Book(1234,"Harry Potter",3, "JK rowling")};

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);

        bookManager.loan(1234);
        bookManager.loan(1234);

        assertEquals(expectedBookList.length, bookManager.getMemberLoans().length);
    }

    @Test
    public void test_single_returnBook () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);
        Book[] expectedBookList = new Book[]{};

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);

        bookManager.loan(1234);

        bookManager.returnBook(1234);

        assertEquals(expectedBookList.length, bookManager.getMemberLoans().length);
    }

    @Test
    public void test_multiple_returnBook () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);
        Book[] expectedBookList = new Book[]{};

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);
        bookManager.addBook(1235,"Harry Potter 2","JK rowling", 3);

        bookManager.loan(1234);
        bookManager.loan(1235);

        bookManager.returnBook(1234);
        bookManager.returnBook(1235);

        assertEquals(expectedBookList.length, bookManager.getMemberLoans().length);
    }

    @Test
    public void test_wrong_returnBook () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);
        bookManager.addBook(1235,"Harry Potter 2","JK rowling", 3);

        bookManager.loan(1234);

        assertThrows(NullPointerException.class, () -> bookManager.returnBook(1235));
    }

    @Test
    public void test_single_overDueLoan () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);

        bookManager.loan(1234);

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH,16);
        Date overDueDate = cal.getTime();

        assertTrue(bookManager.overDueLoan(1234, overDueDate));
    }

    @Test
    public void test_multiple_overDueLoan () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);

        bookManager.loan(1234);

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH,16);
        Date overDueDate = cal.getTime();

        assertTrue(bookManager.overDueLoan(1234, overDueDate));
    }

    @Test
    public void test_single_addBook () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);

        assertEquals("Harry Potter", bookManager.getBook(1234).getTitle());
    }

    @Test
    public void test_multiple_addBook () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);
        bookManager.addBook(1235,"Harry Potter 2","JK rowling", 3);

        assertEquals("Harry Potter", bookManager.getBook(1234).getTitle());
        assertEquals("Harry Potter 2", bookManager.getBook(1235).getTitle());
    }

    @Test
    public void test_same_addBook () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);

        assertThrows(IllegalArgumentException.class, () -> bookManager.addBook(1234,"Harry Potter 2","JK rowling", 3));

    }

    @Test
    public void test_notAvailable_checkAvailable () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);

        bookManager.addBook(1234,"Harry Potter","JK rowling", 0);


        assertFalse(bookManager.checkAvailable(new Book(1234,"Harry Potter")));
    }

    @Test
    public void test_available_checkAvailable () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);


        assertTrue(bookManager.checkAvailable(new Book(1234,"Harry Potter", 3, "JK rowling")));
    }

    @Test
    public void test_single_getMemberLoans () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);
        Book[] expectedBookList = new Book[]{new Book(1234,"Harry Potter",3, "JK rowling")};

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);

        bookManager.loan(1234);

        Book[] actualBookList = bookManager.getMemberLoans();

        assertEquals(expectedBookList[0].getTitle(), actualBookList[0].getTitle());
    }

    @Test
    public void test_multiple_getMemberLoans () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);
        Book[] expectedBookList = new Book[]{
                new Book(1234,"Harry Potter",3, "JK rowling"),
                new Book(1235,"Harry Potter 2",3, "JK rowling"),
                new Book(1236,"Harry Potter 3",3, "JK rowling")
        };

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);
        bookManager.addBook(1235,"Harry Potter 2","JK rowling", 3);
        bookManager.addBook(1236,"Harry Potter 3","JK rowling", 3);

        bookManager.loan(1234);
        bookManager.loan(1235);
        bookManager.loan(1236);

        Book[] actualBookList = bookManager.getMemberLoans();

        assertEquals(expectedBookList.length, actualBookList.length);
    }

    @Test
    public void test_wrongLoans_getMemberLoans () { //funkar inte då programmet stannar p.g.a en exception som kastas om man försöker låna fler böcker än vad som tillåts
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",1,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);
        Book[] expectedBookList = new Book[]{
                new Book(1234,"Harry Potter",3, "JK rowling"),
                new Book(1235,"Harry Potter 2",3, "JK rowling"),
                new Book(1236,"Harry Potter 3",3, "JK rowling")
        };

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);
        bookManager.addBook(1235,"Harry Potter 2","JK rowling", 3);
        bookManager.addBook(1236,"Harry Potter 3","JK rowling", 3);
        bookManager.addBook(1237,"Harry Potter 3","JK rowling", 3);
        bookManager.addBook(1238,"Harry Potter 3","JK rowling", 3);

        bookManager.loan(1234);
        bookManager.loan(1235);
        bookManager.loan(1236);
        bookManager.loan(1237);

        Book[] actualBookList = bookManager.getMemberLoans();

        assertEquals(expectedBookList.length, actualBookList.length);
    }

    @Test
    public void test_single_getMemberLoansForLibrarian () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);
        Book[] expectedBookList = new Book[]{new Book(1234,"Harry Potter",3, "JK rowling")};

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);

        bookManager.loan(1234);

        Book[] actualBookList = bookManager.getMemberLoansForLibrarian(user);

        assertEquals(expectedBookList[0].getTitle(), actualBookList[0].getTitle());
    }

    @Test
    public void test_multiple_getMemberLoansForLibrarian () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);
        Book[] expectedBookList = new Book[]{
                new Book(1234,"Harry Potter",3, "JK rowling"),
                new Book(1235,"Harry Potter",3, "JK rowling"),
                new Book(1236,"Harry Potter",3, "JK rowling")
        };

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);
        bookManager.addBook(1235,"Harry Potter 2","JK rowling", 3);
        bookManager.addBook(1236,"Harry Potter 3","JK rowling", 3);

        bookManager.loan(1234);
        bookManager.loan(1235);
        bookManager.loan(1236);

        Book[] actualBookList = bookManager.getMemberLoansForLibrarian(user);

        assertEquals(expectedBookList.length, actualBookList.length);
    }

    @Test
    public void test_wrongLoans_getMemberLoansForLibrarian () { //funkar inte då programmet stannar p.g.a en exception som kastas om man försöker låna fler böcker än vad som tillåts
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",1,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);
        Book[] expectedBookList = new Book[]{
                new Book(1234,"Harry Potter",3, "JK rowling"),
                new Book(1235,"Harry Potter",3, "JK rowling"),
                new Book(1236,"Harry Potter",3, "JK rowling")
        };

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);
        bookManager.addBook(1235,"Harry Potter 2","JK rowling", 3);
        bookManager.addBook(1236,"Harry Potter 3","JK rowling", 3);
        bookManager.addBook(1237,"Harry Potter 4","JK rowling", 3);
        bookManager.addBook(1238,"Harry Potter 5","JK rowling", 3);

        bookManager.loan(1234);
        bookManager.loan(1235);
        bookManager.loan(1236);
        bookManager.loan(1237);
        bookManager.loan(1238);

        Book[] actualBookList = bookManager.getMemberLoansForLibrarian(user);

        assertEquals(expectedBookList.length, actualBookList.length);
    }

    @Test
    public void test_single_numberOfBorrowedBooks () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);

        bookManager.loan(1234);

        assertEquals(1, bookManager.numberOfBorrowedBooks());
    }

    @Test
    public void test_multiple_numberOfBorrowedBooks () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123,19991122,"Gustaf","Thim",3,0,0,null,false);
        BookManager bookManager = new BookManager(user,bookStoreStub);

        bookManager.addBook(1234,"Harry Potter","JK rowling", 3);
        bookManager.addBook(1235,"Harry Potter","JK rowling", 3);
        bookManager.addBook(1236,"Harry Potter","JK rowling", 3);

        bookManager.loan(1234);
        bookManager.loan(1235);
        bookManager.loan(1236);

        assertEquals(3, bookManager.numberOfBorrowedBooks());
    }

    @Test
    public void test_maxLoans_memberLendStatus () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123, 19991122, "Gustaf", "Thim", 1, 0, 0, null, false);
        BookManager bookManager = new BookManager(user, bookStoreStub);

        bookManager.addBook(1, "Harry Potter", "JK rowling", 3);
        bookManager.addBook(2, "Harry Potter 2", "JK rowling", 3);
        bookManager.addBook(3, "Harry Potter 3", "JK rowling", 3);

        bookManager.loan(1);
        bookManager.loan(2);
        bookManager.loan(3);

        assertFalse(bookManager.memberLendStatus());
    }

    @Test
    public void test_noLoans_memberLendStatus () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123, 19991122, "Gustaf", "Thim", 1, 0, 0, null, false);
        BookManager bookManager = new BookManager(user, bookStoreStub);

        bookManager.addBook(1, "Harry Potter", "JK rowling", 3);
        bookManager.addBook(2, "Harry Potter 2", "JK rowling", 3);
        bookManager.addBook(3, "Harry Potter 3", "JK rowling", 3);

        assertTrue(bookManager.memberLendStatus());
    }

    @Test
    public void test_teacherStatus_memberLendStatus () {
        BookStoreStub bookStoreStub = new BookStoreStub();
        User user = new User(123, 19991122, "Gustaf", "Thim", 4, 0, 0, null, false);
        BookManager bookManager = new BookManager(user, bookStoreStub);

        bookManager.addBook(1, "Harry Potter", "JK rowling", 3);
        bookManager.addBook(2, "Harry Potter 2", "JK rowling", 3);
        bookManager.addBook(3, "Harry Potter 3", "JK rowling", 3);
        bookManager.addBook(4, "Harry Potter 4", "JK rowling", 3);
        bookManager.addBook(5, "Harry Potter 5", "JK rowling", 3);
        bookManager.addBook(6, "Harry Potter 6", "JK rowling", 3);
        bookManager.addBook(7, "Harry Potter 7", "JK rowling", 3);
        bookManager.addBook(8, "Harry Potter 8", "JK rowling", 3);
        bookManager.addBook(9, "Harry Potter 9", "JK rowling", 3);

        bookManager.loan(1);
        bookManager.loan(2);
        bookManager.loan(3);
        bookManager.loan(4);
        bookManager.loan(5);
        bookManager.loan(6);
        bookManager.loan(7);
        bookManager.loan(8);
        bookManager.loan(9);

        assertTrue(bookManager.memberLendStatus());
    }

}