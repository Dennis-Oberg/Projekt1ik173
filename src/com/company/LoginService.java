package com.company;
import java.sql.*;
import java.util.ArrayList;


public class LoginService {

    String connUrl="jdbc:mysql://localhost/sys?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    User loggedInUser;

    RegisterService regService = new RegisterService();

    public LoginService()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Load database drivers
        } catch (ClassNotFoundException c) {
            System.out.println("Driver did not load");
        }
    }

     public void printUsers()
     {
         try (Connection conn = DriverManager.getConnection(connUrl, "root", "root")) //Set up connection to database
         {
             System.out.println("Connected" + "\n");

             Statement statement = conn.createStatement(); //Create object so queries can be sent to database


             System.out.println("Användare");
             System.out.println("=====");
             ResultSet users = statement.executeQuery("SELECT * FROM libraryuser");

             while (users.next()) { //Print every existing row in artist table for all three columns
                 System.out.println("ID: " + users.getInt(1) + "\n" + " SSN: " + users.getInt(2) + "\n" + " first name: " + users.getString(3) + "\n" + " last name: " + users.getString(4) + "\n" + " user type: " + users.getString(5));
             }

             System.out.println();
             System.out.println("Böcker");
             System.out.println("=====");
             ResultSet albums = statement.executeQuery("SELECT * FROM book");

             while (albums.next()) {  //Print every existing row in album table for all three columns
                 System.out.println(albums.getString(1));
             }
         } catch (SQLException sqle) { //If connection fails
             sqle.printStackTrace();
         }
     }

     public Boolean verifyLogin(int id, String password)
     {
         try (Connection conn = DriverManager.getConnection(connUrl, "root", "root")) //Set up connection to database
         {
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM libraryuser WHERE id = ? AND password = ?");
             statement.setInt(1,id);
             statement.setString(2,password);

             ResultSet rs = statement.executeQuery();

             while(rs.next())
             {
                 if (rs.getInt(1) == id && rs.getString(2).equals(password))
                 {
                     this.loggedInUser = new User(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6));
                     this.loggedInUser.setLoggedin(true);
                 }
                 else
                 {
                     this.loggedInUser.setLoggedin(false);
                 }
             }

         } catch (SQLException sqle) { //If connection fails
             sqle.printStackTrace();
         }

         if (!this.loggedInUser.isLoggedin())
         {
             System.out.println("Error, fel lösenord och/eller id");
         }
         return this.loggedInUser.isLoggedin();
     }

     public String getName()
     {
        if (this.loggedInUser.isLoggedin())
        {
            return (this.loggedInUser.getFirstName() + " " + this.loggedInUser.getLastName());
        }
        else
        {
            throw new RuntimeException("Error, no logged in user");
        }
     }

}
