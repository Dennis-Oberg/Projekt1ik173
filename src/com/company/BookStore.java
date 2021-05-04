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
            System.out.println("Connection failed\n");
        } else {
            System.out.println("Connection successful\n");
        }
    }

    public BookStore()  {
        this.bookList = new ArrayList<>();


        //Ska bort senare
        bookList.add(new Book(1234,"Harry Potter"));
        bookList.add(new Book(1235, "Nalle Puh"));

        bookList.add(new Book(1236, "Pippi"));
        bookList.add(new Book(1237, "Star wars"));
        bookList.add(new Book(1238, "Greta gris"));
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
            String query = "SELECT * FROM `dennis-1ik173vt21`.book WHERE `dennis-1ik173vt21`.book.isbn = ?";
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setLong(1,isbn);

            System.out.println("Böcker hämtade på ISBN");
            System.out.println("=====");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { //Print every existing row in artist table for all three columns
                tempList.add(new Book(resultSet.getLong(3),resultSet.getString(1)));
            }
        } catch (SQLException sqle) { //If connection fails
            sqle.printStackTrace();
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
