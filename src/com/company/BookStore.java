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

    public BookStore()  {
        this.bookList = new ArrayList<>();


        //Ska bort senare

    }

    public Book[] getBooks() {

        CheckConnection();

        ArrayList<Book> tempList = new ArrayList<>();

        try
        {
            String query = "SELECT * FROM `dennis-1ik173vt21`.book";
            statement = conn.createStatement();

            System.out.println("Alla böcker");
            System.out.println("=====");
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) { //Print every existing row in artist table for all three columns
                tempList.add(new Book(resultSet.getLong(3),resultSet.getString(1)));
            }
        } catch (SQLException sqle) { //If connection fails
            sqle.printStackTrace();
        }
        Book[] books = new Book[tempList.size()];
        return tempList.toArray(books);
    }

    public Book getBookByTitle(String title) {

        CheckConnection();

        Book placeholderBook = null;

        try
        {
            String query = "SELECT * FROM `dennis-1ik173vt21`.book WHERE `dennis-1ik173vt21`.book.title = ?";
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1,title);

            System.out.println("Bok hämtad efter titel");
            System.out.println("=====");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { //Print every existing row in artist table for all three columns
                placeholderBook = new Book(resultSet.getLong(3),resultSet.getString(1));
            }
            System.out.println(placeholderBook.getTitle() + " " + placeholderBook.getIsbn());
        } catch (SQLException sqle) { //If connection fails
            sqle.printStackTrace();
        }
        return placeholderBook;
    }

    public Book[] getBookByIsbn(long isbn)   {

        CheckConnection();
        ArrayList<Book> tempList = new ArrayList<>();

        try
        {
            String query = "SELECT title, copiesofbook.isbn, copy, isAvailable, borrowedBy FROM copiesofbook,book WHERE copiesofbook.isbn = ? AND book.isbn = ?";

            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setLong(1,isbn);
            preparedStatement.setLong(2,isbn);

            System.out.println("Böcker hämtade på ISBN");
            System.out.println("=====");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { //Print every existing row in artist table for all three columns
                tempList.add(new Book(resultSet.getLong(2),resultSet.getString(1),
                        resultSet.getInt(3),resultSet.getBoolean(4), resultSet.getInt(5)));


            }
        } catch (SQLException sqle) { //If connection fails
            sqle.printStackTrace();
        }

        Book[] books = new Book[tempList.size()];
        return tempList.toArray(books);
    }

    public Book[] getBookByMember(int memberId)  {

        CheckConnection();
        ArrayList<Book> tempList = new ArrayList<>();

        try
        {
            //String query = "SELECT * FROM copiesofbooks WHERE borrowedBy = ?";
            String query = "SELECT title, copiesofbook.isbn, copy, isAvailable, borrowedBy FROM copiesofbook,book WHERE copiesofbook.borrowedBy = ? AND copiesofbook.isbn = book.isbn";

            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1,memberId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                tempList.add(new Book(resultSet.getLong(2),resultSet.getString(1),resultSet.getInt(3), resultSet.getBoolean(4), resultSet.getInt(5)));
            }

        } catch (SQLException sqle) { //If connection fails
            sqle.printStackTrace();
        }

        Book[] books = new Book[tempList.size()];
        return tempList.toArray(books);
    }
    public void updateBookInfo(Book book) {

    }

    public void setBookStatus(Book book){
        CheckConnection();

        try {
              preparedStatement = conn.prepareStatement("UPDATE copiesofbook SET isAvailable = ?, borrowedBy = ? WHERE isbn = ? AND copy = ? AND date = CURRENT_DATE");

              preparedStatement.setBoolean(1, book.isAvailable());
              preparedStatement.setInt(2, book.getBorrowedBy());
              preparedStatement.setLong(3, book.getIsbn());
              preparedStatement.setInt(4, book.getCopy());

              preparedStatement.executeUpdate();
              System.out.println("Lyckades!");
          
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println("Lyckades inte");
        }
    }
  
}
