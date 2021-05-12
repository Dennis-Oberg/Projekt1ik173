package com.company;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class BookStore implements IBookStore {
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

    public Book[] getBooks() {

        CheckConnection();

        ArrayList<Book> tempList = new ArrayList<>();

        try {
            String query = "SELECT * FROM `dennis-1ik173vt21`.book";
            statement = conn.createStatement();

            System.out.println("Alla böcker");
            System.out.println("=====");
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) { //Print every existing row in artist table for all three columns
                tempList.add(new Book(resultSet.getLong(3), resultSet.getString(1)));
            }
        } catch (SQLException sqle) { //If connection fails
            sqle.printStackTrace();
        }
        Book[] books = new Book[tempList.size()];
        return tempList.toArray(books);
    }

    public Book getBookByTitle(String title) {

        Book tempBook = null;
        CheckConnection();

        try {
            String query = "SELECT title, author, isbn, copies FROM book WHERE title = ?";

            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, title);

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
            String query = "SELECT book.title, book.author, book.isbn, book.copies, borrowedBy.borrowedBy FROM book, borrowedby WHERE book.isbn = borrowedBy.isbn AND borrowedBy.borrowedBy = ?";

            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, memberId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tempList.add(new Book(resultSet.getLong(3), resultSet.getString(1),
                        resultSet.getInt(4), resultSet.getString(2), resultSet.getInt(5)));
            }

        } catch (SQLException sqle) { //If connection fails
            sqle.printStackTrace();
        }

        Book[] books = new Book[tempList.size()];
        return tempList.toArray(books);
    }

    public void updateBookInfo(Book book) {

    }

    public Book[] checkAvailability(Long isbn)
    {
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

    public void loanBook(Book book) {
        CheckConnection();

        try {
            preparedStatement = conn.prepareStatement("INSERT INTO borrowedby (isbn, borrowedBy, date) VALUES (?, ?, ?)");

            preparedStatement.setLong(1, book.getIsbn());
            preparedStatement.setInt(2, book.getBorrowedBy());
            preparedStatement.setDate(3, null);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println("Lyckades inte");
        }
    }

    public void returnBook(Book book) {
        CheckConnection();

        try {
            preparedStatement = conn.prepareStatement("DELETE FROM borrowedby WHERE isbn = ? AND borrowedBy.borrowedBy = ?");

            preparedStatement.setLong(1, book.getIsbn());
            preparedStatement.setInt(2, book.getBorrowedBy());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println("Lyckades inte");
        }
    }

    public boolean insertBook(long isbn, String title,String author,int copies)
    {
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
