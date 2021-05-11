package com.company;

import java.util.ArrayList;

public class MemberStoreStub extends MemberStore{

    ArrayList<User> userList;

    public MemberStoreStub(){
        userList = new ArrayList<>();


        //ska bort senare
        userList.add(new User(1234,1,"Tobias", "Wendel", 1));
        userList.add(new User(1235,1,"Tobias", "Wendel", 2));
        userList.add(new User(1236,1,"Tobias", "Wendel", 4));
        userList.add(new User(4321,1,"Tobias", "Wendel", 5));
    }

    public ArrayList<User> getMembers()
    {
        return userList; //databas kod
    }
    public User getMemberById(int id){

        for (User m: userList){
            //Bytas ut mot databas??
            if (m.getIDCode() == id){
                return m;
            }
        }
        return null;
    }

}