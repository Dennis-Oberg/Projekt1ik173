package com.company;

import java.sql.*;
import java.util.ArrayList;

public class MemberStore implements IMemberStore {

     Member currentMember;

    public MemberStore(){
        //memberList = new ArrayList<>();


        //ska bort senare
        /*
        memberList.add(new Member(1234,1,"Tobias", "Wendel", 1));
        memberList.add(new Member(1235,1,"Tobias", "Wendel", 2));
        memberList.add(new Member(1236,1,"Tobias", "Wendel", 4));
        memberList.add(new Member(4321,1,"Tobias", "Wendel", 5));

         */
    }

    Connection conn = SQLConnection.DbConnector();
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;
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
    public Member getMemberById(int id){
        String query = "Select * from member where idCode = ?";
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) { //funkar
                currentMember = new Member(resultSet.getInt("idCode"), resultSet.getInt("socialSecurityNumber"),
                        resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getInt("Title"));


            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return currentMember;
    }
    public ArrayList<Member> getMembers()
    {
        //CheckConnection();

        ArrayList<Member> tempList = new ArrayList<>();

        try
        {
            String query = "SELECT * FROM `dennis-1ik173vt21`.member";
            statement = conn.createStatement();

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) { //Print every existing row in artist table for all three columns
                //idCode, int ssn, String firstname, String lastname, int titel
                tempList.add(new Member(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5)));
            }
        } catch (SQLException sqle) { //If connection fails
            sqle.printStackTrace();
        }
        return tempList;
    }

}
