package com.company;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLConnection {

    public static Connection DbConnector(){ //Database initialization and connection
        try{
            DatabaseInit dbInit = new DatabaseInit();
            dbInit.init("C:\\Users\\46760\\Desktop\\1ik173\\Projekt1ik173\\src\\com\\company\\dbcredentials.properties");
            return dbInit.getConnection();
        } catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
}