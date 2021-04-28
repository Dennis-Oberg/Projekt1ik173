package com.company;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseInit {
        private static String url;
        private static String username;
        private static String password;

        public void init(String filename) {
            Properties props = new Properties();
            try(FileInputStream in = new FileInputStream(filename)) {
                props.load(in);
                String driver = props.getProperty("jdbc.driver");
                url = props.getProperty("jdbc.url");
                username = props.getProperty("jdbc.username");
                if(username == null){
                    username = "";
                }
                password = props.getProperty("jdbc.password");
                if(password == null){
                    password = "";
                }
                if(driver!=null){
                    Class.forName(driver);
                }
            } catch (IOException ex) {
                System.out.println("Something went wrong... " + ex.getMessage());
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Unable to load driver." + cnfe.getMessage());
            }
        }

        public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(url, username, password);
        }
}
