package com.company;

import java.util.ArrayList;

public class AuthService {


    int loginId;
    Member loggedInMember;

    MemberStore mStore = new MemberStore();


    public Member getLoggedInMember() {
        return loggedInMember;
    }
    public Member returnMember()
    {
        return loggedInMember;
    }

    public Boolean login() {


        for (Member m: mStore.memberList){  //Bytas ut mot databas??
            if (m.getIDCode() == loginId){
                loggedInMember = m;
                return true;
            }
        }
        return false;
    }

    public void logout()
    {
        this.loggedInMember = null;
    }
    public Member getMemberById(int id){

        for (Member m: mStore.memberList){
            //Bytas ut mot databas??
            if (m.getIDCode() == id){
                return m;
            }
        }
        return null;
    }


    void getCredentials() {

    }
    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

}
