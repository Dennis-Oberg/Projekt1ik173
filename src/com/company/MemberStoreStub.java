package com.company;

import java.util.ArrayList;

public class MemberStoreStub extends MemberStore {

    ArrayList<User> userList;
    User currentUser;


    public MemberStoreStub() {
        userList = new ArrayList<>();


    }

    public ArrayList<User> getMembers() {
        return userList; //databas kod
    }

    public void createNewMember(int idcode, int ssn, String fName, String lName, int title, int suspendedCount) {
        userList.add(new User(idcode, ssn, fName, lName, title, suspendedCount));
    }

    public void removeMember(User user) {
        for (int i = 0; i < userList.size(); i++) {
            if (user.IDCode == userList.get(i).getIDCode()) {
                userList.remove(user);
            }
        }
    }

    public void addSuspensions(User user) {
        for (User value : userList) {
            if (user.IDCode == value.getIDCode()) {
                user.suspendedCount = 3;
                removeMember(user);
            }
        }
    }

    public void addStrike(User user) {
        for (User u : userList) {
            if (user.getIDCode() == u.getIDCode()) {
                user.setStrikes(user.getStrikes() + 1);
            }
        }
    }

    public void removeSuspension(User user) {
        for (User u :userList) {
            if (user.getIDCode() == u.getIDCode()) {
                user.suspended = false;
                break;
            }
        }
    }

    public User getMemberById(int id) {

        for (User m : userList) {
            if (m.getIDCode() == id) {
                return m;
            }
        }
        return null;
    }
}