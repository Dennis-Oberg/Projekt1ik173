package com.company;

public class User {

    private int id;
    private int ssn;
    private String firstName;
    private String lastName;
    private String userType;
    private int password;
    private boolean isLoggedin;

    public User(int id, int password, int ssn, String firstName, String lastName, String userType) {
        this.id = id;
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
        this.password = password;
    }

    public void printAll()
    {
        System.out.println(this.id);
        System.out.println(this.ssn);
        System.out.println(this.firstName);
        System.out.println(this.lastName);
        System.out.println(this.userType);
        System.out.println(this.password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSs() {
        return ssn;
    }

    public void setSs(int ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public boolean isLoggedin() {
        return isLoggedin;
    }

    public void setLoggedin(boolean loggedin) {
        isLoggedin = loggedin;
    }
}
