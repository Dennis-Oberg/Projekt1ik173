package com.company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class BookStore {
    private static final Logger logger = LogManager.getLogger(BookStore.class.getName());

    ArrayList<Book> bookList = null;

    Connection conn;
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;

    public void CheckConnection() {
        conn = SQLConnection.DbConnector();
        if (conn == null) {
            System.out.println("Connection failed\n");
        } else {
            //System.out.println("Connection successful\n");
        }
    }

    public BookStore() {
        this.bookList = new ArrayList<>();
        //Ska bort senare
    }

    public Book getBookByIsbn(long isbn) {

        Book tempBook = null;
        CheckConnection();

        try {
            String query = "SELECT title, author, isbn, copies FROM book WHERE isbn = ?";

            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setLong(1, isbn);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tempBook = new Book(resultSet.getLong(3), resultSet.getString(1),
                        resultSet.getInt(4), resultSet.getString(2), 0);
            }
        } catch (SQLException sqle) { //If connection fails
            sqle.printStackTrace();
        }

        return tempBook;
    }

    public Book[] getBookByMember(int memberId) {

        CheckConnection();
        ArrayList<Book> tempList = new ArrayList<>();

        try {
            String query = "SELECT book.title, book.author, book.isbn, book.copies, borrowedBy.borrowedBy, borrowedBy.date, borrowedBy.returndate FROM book, borrowedby WHERE book.isbn = borrowedBy.isbn AND borrowedBy.borrowedBy = ?";

            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, memberId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tempList.add(new Book(resultSet.getLong(3), resultSet.getString(1),
                        resultSet.getInt(4), resultSet.getString(2), resultSet.getInt(5), resultSet.getDate(6), resultSet.getDate(7)));
            }

        } catch (SQLException sqle) { //If connection fails
            sqle.printStackTrace();
        }

        Book[] books = new Book[tempList.size()];
        return tempList.toArray(books);
    }

    public Book[] checkAvailability(Long isbn) {
        CheckConnection();
        ArrayList<Book> tempList = new ArrayList<>();

        try {
            String query = "SELECT isbn, borrowedBy FROM borrowedby WHERE isbn = ?";

            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setLong(1, isbn);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tempList.add(new Book(resultSet.getLong(1), resultSet.getInt(2)));
            }

        } catch (SQLException sqle) { //If connection fails
            sqle.printStackTrace();
        }

        Book[] books = new Book[tempList.size()];
        return tempList.toArray(books);
    }

    public void loanBook(Book book, User user) {
        CheckConnection();
        int copies = 0;

        try {
            String query = "SELECT copies FROM book WHERE isbn=?";
            PreparedStatement newPS = conn.prepareStatement(query);
            newPS.setLong(1, book.getIsbn());
            resultSet = newPS.executeQuery();

            while (resultSet.next()) {
                copies = resultSet.getInt(1);
            }

            if (copies > 0) {
                try {

                    long millis = System.currentTimeMillis();
                    Date today = new Date(millis);
                PreparedStatement newPreparedStatement = conn.prepareStatement("UPDATE book SET copies = copies-1 WHERE isbn=?");
                preparedStatement = conn.prepareStatement("INSERT INTO borrowedby (isbn, borrowedBy, date, returndate) VALUES (?, ?, ?, DATE_ADD(CURRENT_DATE, INTERVAL 15 DAY))");
                preparedStatement.setLong(1, book.getIsbn());
                preparedStatement.setInt(2, user.getIDCode());
                preparedStatement.setDate(3, today);
                newPreparedStatement.setLong(1, book.getIsbn());

                preparedStatement.executeUpdate();
                newPreparedStatement.executeUpdate();
                book.setBorrowedBy(user.getIDCode());
                System.out.println("Du har nu lånat " + book.getTitle());
                System.out.println("kopior kvar: " + "" + (copies-1));
                }
                catch (SQLException e) {

                    System.out.println(e.getErrorCode());
                    System.out.println("Lyckades inte");
                }
            } else {
                System.out.println("Det finns inga kopior kvar");
            }

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println("Lyckades inte");
        }
    }

    public void returnBook(Book book, User user) {
        CheckConnection();
        try {
            if (checkAvailability(book.getIsbn()).length > 0) {
                PreparedStatement newPreparedStatement = conn.prepareStatement("UPDATE book SET copies = copies+1 WHERE isbn=?");
                preparedStatement = conn.prepareStatement("DELETE FROM borrowedby WHERE isbn = ? AND borrowedBy.borrowedBy = ?");

                preparedStatement.setLong(1, book.getIsbn());
                preparedStatement.setInt(2, user.getIDCode());
                newPreparedStatement.setLong(1, book.getIsbn());

                preparedStatement.executeUpdate();
                newPreparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println("Lyckades inte");
        }
    }

    public boolean insertBook(long isbn, String title, String author, int copies) {
        CheckConnection();

        boolean inserted;

        try {
            preparedStatement = conn.prepareStatement("INSERT INTO book (title, author, isbn, copies) VALUES (?,?,?,?)");

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setLong(3, isbn);
            preparedStatement.setInt(4, copies);

            preparedStatement.executeUpdate();
            inserted = true;

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println("Lyckades inte");
            inserted = false;
        }
        return inserted;
    }
}
