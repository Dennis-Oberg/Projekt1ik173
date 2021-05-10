package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AuthService {

    MemberStore mStore;

    int loginId;
    User loggedInUser;
    public AuthService(){

    }


    public AuthService(MemberStore newMStore) {
        this.mStore = newMStore;
    }

    public void start(){
        System.out.println("Välkommen!\n");
        Scanner input = new Scanner(System.in);
        System.out.print("Ange ID för att logga in: ");
        int id = input.nextInt();
        mStore = new MemberStore();

        decideAuth(id);
    }

    public void decideAuth(int id)    {
        boolean authorisation = mStore.login(id);
        if (authorisation){
            loggedInUser = mStore.getMemberById(id);

            if (loggedInUser.getTitel() == 5)
            {
                User lib = new Librarian(loggedInUser);

            }
            else
            {
                User user = new Member(loggedInUser);

            }
        }
        else {
            System.out.println("\nError, fel lösenord och/eller användarnamn");
            System.out.println("Försök igen\n");
            start();
        }

    }



    public User getLoggedInMember() {
        return loggedInUser;
    }

    public User returnMember() {
        return loggedInUser;
    }
/*
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


        for (User m: mStore.userList){  //Bytas ut mot databas??
            if (m.getIDCode() == loginId){
                loggedInUser = m;
                return true;
            }
        }


        return false;
    }

 */


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
