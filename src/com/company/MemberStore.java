package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class MemberStore implements IMemberStore {

    User currentUser;
    Connection conn = SQLConnection.DbConnector();
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;

    public MemberStore(){

    }

    public Boolean login(int loginId) {
        conn = SQLConnection.DbConnector();

        String query = "Select * from member where idCode = ?";
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, loginId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) { //funkar
                User loggedInUser = new User(resultSet.getInt("idCode"), resultSet.getInt("socialSecurityNumber"),
                        resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getInt("Title"));
                System.out.println(loggedInUser.firstName);
                return true;
            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return false;
    }

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

    public void creatNewMember(int ssn, String fName, String lName,int title){
        //String query = "INSTER INTO member VALUES (?, ? ,? ,? ,? ,?)";
        try {
            ArrayList<Integer> tempArr = new ArrayList<>();
            conn = SQLConnection.DbConnector();
            preparedStatement = conn.prepareStatement("INSERT INTO member VALUES (?, ? ,? ,? ,? ,?)");

            String query = "SELECT idCode FROM `dennis-1ik173vt21`.member";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                tempArr.add(resultSet.getInt(1));
            }

            preparedStatement.setInt(1, GenerateID.returnID(tempArr));
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

    public void moveToBannedMember(User user)
    {
        try {
            conn = SQLConnection.DbConnector();
            preparedStatement = conn.prepareStatement("INSERT INTO bannedmember VALUES (?)");

            preparedStatement.setInt(1,user.getIDCode());

            preparedStatement.executeUpdate();
            System.out.println("Medlem suspenderad");
        }
        catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
    }
}
