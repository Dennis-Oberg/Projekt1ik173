package com.company;

import java.sql.*;
import java.util.ArrayList;

public class AuthService {

    MemberStore mStore;
    Connection conn;
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;

    int loginId;
    User loggedInUser;
    public AuthService(){

    }


    public AuthService(MemberStore newMStore) {
        this.mStore = newMStore;
    }


    public User getLoggedInMember() {
        return loggedInUser;
    }

    public User returnMember() {
        return loggedInUser;
    }

    public Boolean login(int loginId) {
        conn = SQLConnection.DbConnector();

        String query = "Select * from member where idCode = ?";
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, loginId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) { //funkar
                loggedInUser = new User(resultSet.getInt("idCode"), resultSet.getInt("socialSecurityNumber"),
                        resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getInt("Title"));
                System.out.println(loggedInUser.firstName);
                return true;
            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

/*
        for (User m: mStore.userList){  //Bytas ut mot databas??
            if (m.getIDCode() == loginId){
                loggedInUser = m;
                return true;
            }
        }

 */
        return false;
    }

    public void displayMembers() {

        ArrayList<User> users = mStore.getMembers();

        for (User m : users
        ) {
            System.out.println(m.firstName);
        }

    }

    public void logout() {
        this.loggedInUser = null;
    }

    public User getMemberById(int id) {

        for (User m : mStore.getMembers()) {
            //Bytas ut mot databas??
            if (m.getIDCode() == id) {
                return m;
            }
        }
        return null;
    }


    void getCredentials() {

    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

}
