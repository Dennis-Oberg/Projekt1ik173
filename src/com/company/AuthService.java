package com.company;

import java.sql.*;
import java.util.ArrayList;

public class AuthService {

    MemberStore mStore;
    Connection conn = SQLConnection.DbConnector();
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;

    int loginId;
    Member loggedInMember;

    public AuthService(MemberStore newMStore) {
        this.mStore = newMStore;
    }


    public Member getLoggedInMember() {
        return loggedInMember;
    }

    public Member returnMember() {
        return loggedInMember;
    }

    public Boolean login(int loginId) {

        String query = "Select * from member where idCode = ?";
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, loginId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) { //funkar
                loggedInMember = new Member(resultSet.getInt("idCode"), resultSet.getInt("socialSecurityNumber"),
                        resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getInt("Title"));
                System.out.println(loggedInMember.firstName);
                return true;
            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

/*
        for (Member m: mStore.memberList){  //Bytas ut mot databas??
            if (m.getIDCode() == loginId){
                loggedInMember = m;
                return true;
            }
        }

 */
        return false;
    }

    public void displayMembers() {

        ArrayList<Member> members = mStore.getMembers();

        for (Member m : members
        ) {
            System.out.println(m.firstName);
        }

    }

    public void logout() {
        this.loggedInMember = null;
    }

    public Member getMemberById(int id) {

        for (Member m : mStore.memberList) {
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
