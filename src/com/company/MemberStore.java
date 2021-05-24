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

    public MemberStore() {


    }

    public Boolean login(int loginId) {
        conn = SQLConnection.DbConnector();

        String query = "Select * from member where idCode = ?";
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, loginId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) { //funkar
                User loggedInUser = new User(resultSet.getInt("idCode"),
                        resultSet.getInt("socialSecurityNumber"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("Title"),
                        resultSet.getInt("suspensions"),
                        resultSet.getInt("strikes"),
                        resultSet.getDate("suspensiondate"),
                        resultSet.getBoolean("suspended")
                        );
                System.out.println("Välkommen " + loggedInUser.firstName);
                return true;
            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return false;
    }


    public User getMemberById(int id) {
        String query = "Select * from member where idCode = ?";
        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) { //funkar
                currentUser = new User(
                        resultSet.getInt("idCode"),
                        resultSet.getInt("socialSecurityNumber"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("Title"),
                        resultSet.getInt("suspensions"),
                        resultSet.getInt("strikes"),
                        resultSet.getDate("suspensiondate"),
                        resultSet.getBoolean("suspended")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return currentUser;
    }

    public void creatNewMember(int ssn, String fName, String lName, int title) {
        //String query = "INSTER INTO member VALUES (?, ? ,? ,? ,? ,?)";
        try {
            ArrayList<Integer> tempArr = new ArrayList<>();
            conn = SQLConnection.DbConnector();
            preparedStatement = conn.prepareStatement("INSERT INTO member (idCode, socialSecurityNumber, firstName, lastName, title, strikes, suspensions, suspended) VALUES (?, ? ,? ,? ,? ,? ,? ,? )");

            String query = "SELECT idCode FROM `dennis-1ik173vt21`.member";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                tempArr.add(resultSet.getInt(1));
            }

            preparedStatement.setInt(1, GenerateID.returnID(tempArr));
            preparedStatement.setInt(2, ssn);
            preparedStatement.setString(3, fName);
            preparedStatement.setString(4, lName);
            preparedStatement.setInt(5, title);
            preparedStatement.setInt(6, 0);
            preparedStatement.setInt(7, 0);
            preparedStatement.setBoolean(8, false);

            preparedStatement.executeUpdate();
            System.out.println("Lyckades!");

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println("Lyckades inte");
        }

    }

    public void removeMember(User user) {
        try {
            conn = SQLConnection.DbConnector();
            preparedStatement = conn.prepareStatement("DELETE FROM member WHERE idCode = ?");

            preparedStatement.setInt(1, user.getIDCode());

            preparedStatement.executeUpdate();
            System.out.println("Medlem raderad");
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }

    public void moveToBannedMember(User user) {
        try {
            conn = SQLConnection.DbConnector();
            preparedStatement = conn.prepareStatement("INSERT INTO bannedmember VALUES (?)");

            preparedStatement.setLong(1, user.getSSN());

            preparedStatement.executeUpdate();
            System.out.println("Medlem suspenderad");
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }

    public void addSuspension(User user) {
        try {
            conn = SQLConnection.DbConnector();
            preparedStatement = conn.prepareStatement("UPDATE member SET strikes=0, suspensions = suspensions+1, suspended=true, suspensiondate = DATE_ADD(CURRENT_DATE, INTERVAL 15 DAY) WHERE idCode = ?");

            preparedStatement.setInt(1, user.getIDCode());

            preparedStatement.executeUpdate();
            System.out.println("Adderade en suspension, du har fått tre strikes!");
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }

    public void removeSuspension(User user) {
        try {
            conn = SQLConnection.DbConnector();
            preparedStatement = conn.prepareStatement("UPDATE member SET suspended=false, suspensiondate=null, strikes=0 WHERE idCode = ?");

            preparedStatement.setInt(1, user.getIDCode());

            preparedStatement.executeUpdate();
            System.out.println("tog bort suspension");

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }

    public void addStrike(User user) {
        try {
            conn = SQLConnection.DbConnector();
            preparedStatement = conn.prepareStatement("UPDATE member SET strikes = strikes+1 WHERE idCode = ?");

            preparedStatement.setInt(1, user.getIDCode());

            preparedStatement.executeUpdate();
            System.out.println("Adderade en strike, du har lämnat tillbaka boken för sent!");
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }
}
