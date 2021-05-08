package com.company;

import java.sql.*;
import java.util.ArrayList;

public class MemberStore implements IMemberStore {

     User currentUser;
    Connection conn = SQLConnection.DbConnector();
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;

    public MemberStore(){
        //userList = new ArrayList<>();


        //ska bort senare
        /*
        userList.add(new User(1234,1,"Tobias", "Wendel", 1));
        userList.add(new User(1235,1,"Tobias", "Wendel", 2));
        userList.add(new User(1236,1,"Tobias", "Wendel", 4));
        userList.add(new User(4321,1,"Tobias", "Wendel", 5));

         */
    }


/*
    public void CheckConnection() { // for testing. gets kind of cluttery
        conn = SQLConnection.DbConnector();
        if (conn == null) {
            System.out.println("Connection failed\n");
        } else {
            System.out.println("Connection successful\n");
        }
    }

 */
    public User getMemberById(int id){
        String query = "Select * from member where idCode = ?";
        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) { //funkar
                currentUser = new User(resultSet.getInt("idCode"), resultSet.getInt("socialSecurityNumber"),
                        resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getInt("Title"));


            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return currentUser;
    }
    public ArrayList<User> getMembers()
    {
        //CheckConnection();

        ArrayList<User> tempList = new ArrayList<>();

        try
        {
            String query = "SELECT * FROM `dennis-1ik173vt21`.member";
            statement = conn.createStatement();

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) { //Print every existing row in artist table for all three columns
                //idCode, int ssn, String firstname, String lastname, int titel
                tempList.add(new User(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5)));
            }
        } catch (SQLException sqle) { //If connection fails
            sqle.printStackTrace();
        }
        return tempList;
    }

    public void creatNewMember(int id, int ssn, String fName, String lName,int title){
        //String query = "INSTER INTO member VALUES (?, ? ,? ,? ,? ,?)";
        try {
            conn = SQLConnection.DbConnector();
            preparedStatement = conn.prepareStatement("INSERT INTO member VALUES (?, ? ,? ,? ,? ,?)");

            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, ssn);
            preparedStatement.setString(3, fName);
            preparedStatement.setString(4, lName);
            preparedStatement.setInt(5, title);
            preparedStatement.setInt(6, 0);

            preparedStatement.executeUpdate();
            System.out.println("Lyckades!");

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println("Lyckades inte");
        }

    }

    public void removeMember(int id){
        try {
            conn = SQLConnection.DbConnector();
            preparedStatement = conn.prepareStatement("DELETE FROM member WHERE idCode = ?");

            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
            System.out.println("Medlem raderad");
        }
        catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
    }

}
