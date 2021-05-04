package com.company;

import java.sql.*;
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
            System.out.println("Connection failed");
        } else {
            System.out.println("Connection successful");
        }
    }

    public BookStore()  {
        this.bookList = new ArrayList<>();
    }

    public Book[] getBooks() {

        CheckConnection();

        try
        {
            String query = "SELECT * FROM `dennis-1ik173vt21`.test";
            statement = conn.createStatement();
            statement.executeQuery(query);

            System.out.println("Böcker");
            System.out.println("=====");
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) { //Print every existing row in artist table for all three columns
                this.bookList.add(new Book(resultSet.getInt(2),resultSet.getString(1)));
            }
        } catch (SQLException sqle) { //If connection fails
            sqle.printStackTrace();
        }

        Book[] books = new Book[bookList.size()];
        return this.bookList.toArray(books);
    }

    public Book getBookByTitle(String title) {
        Book tempBook = null;
        for (Book b: bookList
             ) {
            if (b.getTitle().equals(title))
            {
                 tempBook = b;
            }
        }
        return tempBook;
    }

    public Book[] getBookByIsbn(int isbn)   {
        ArrayList<Book> tempList = new ArrayList<>();

        for (Book book: bookList){
            if (book.getIsbn() == isbn){
                tempList.add(book);
            }
        }
        Book[] books = new Book[tempList.size()];
        return tempList.toArray(books);
    }

    public Book[] getBookByMember(int member)  {
        ArrayList<Book> tempList = new ArrayList<>();

        for (Book book: bookList){
            if (book.getBorrowedBy() == member){
                tempList.add(book);
            }
        }
        Book[] books = new Book[tempList.size()];
        return tempList.toArray(books);
    }
}
