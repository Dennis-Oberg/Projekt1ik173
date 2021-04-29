package com.company;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    public static Connection DbConnector(){ //Database initialization and connection
        try{
            DatabaseInit dbInit = new DatabaseInit();
            dbInit.init("C:\\Användare\\PC\\IdeaProjects\\testProjekt\\src\\dbcredentials.properties");
            return dbInit.getConnection();
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
}