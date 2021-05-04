package com.company;

import java.util.ArrayList;

public class AuthService {

    MemberStore mStore;
    Member loggedInMember;
    ArrayList<Member> members = new ArrayList<>();

    public AuthService(MemberStore newMStore)
    {
        this.mStore = newMStore;
    }

    public Member returnMember()
    {
        return loggedInMember;
    }

    public Boolean login(int newID) {
        members = mStore.getMembers();

        for (Member m: members
             ) {
            if(m.IDCode == newID)
            {
                loggedInMember = m;
                return true;
            }
        }
        return false;
    }

    public void displayMembers()
    {
        members = mStore.getMembers();

        for (Member m: members
             ) {
            System.out.println(m.firstName);
        }

    }

    public void logout()
    {
        this.loggedInMember = null;
    }


    void getCredentials() {

    }

}
