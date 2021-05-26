package com.company;

import java.util.ArrayList;

public class MemberStoreStub extends MemberStore{

    ArrayList<User> userList;
    ArrayList<Integer> bannedMember;

    public MemberStoreStub(){
        userList = new ArrayList<>();
        bannedMember = new ArrayList<Integer>();

        bannedMember.add(123);
        bannedMember.add(1234);
        //ska bort senare
        userList.add(new User(1234,11,"Tobias", "Wendel", 1));
        userList.add(new User(1235,14,"Tobias", "Wendel", 2));
        userList.add(new User(1236,17,"Tobias", "Wendel", 4));
        userList.add(new User(4321,13,"Tobias", "Wendel", 5));
        userList.add(new User(5678,12,"Tobias", "Wendel", 1));
        userList.add(new User(4123,1787,"Tobias", "Wendel", 2));
        userList.add(new User(7823,1324534,"Tobias", "Wendel", 4));
        userList.add(new User(1111,134534,"Tobias", "Wendel", 5));
    }

    public Boolean login(int loginId) {

        for (User user: userList){
            if (user.getIDCode() == loginId){
                return true;
            }
        }
        return false;
    }

    public User getMemberById(int id){

        for (User m: userList){
            if (m.getIDCode() == id){
                return m;
            }
        }
        return null;
    }

    public void creatNewMember(int ssn, String fName, String lName, int title) {

        ArrayList<Integer> tempArr = new ArrayList<>();
        for (User user: userList){
            tempArr.add(user.getIDCode());
        }

        userList.add(new User(GenerateID.returnID(tempArr),ssn,fName,lName,title,
                0,0,null,false));
    }

    public void removeMember(User user) {

        for (User m: userList){
            if (m.getIDCode() == user.getIDCode()){
                userList.remove(m);
            }
        }
    }

    public void moveToBannedMember(User user) {
        bannedMember.add(user.getSSN());
    }

    public void addSuspension(User user) {
        user.suspended = true;
    }

    public void removeSuspension(User user) {
        user.suspended = false;
    }

    public void addStrike(User user) {
        user.strikes += 1;
    }

    public Integer[] checkSsn(int ssn) {

        Integer[] lista = new Integer[bannedMember.size()];
        return bannedMember.toArray(lista);
    }

}