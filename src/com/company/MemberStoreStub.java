package com.company;

import java.util.ArrayList;

public class MemberStoreStub extends MemberStore {

    ArrayList<Member> memberList;

    public void addMember(Member m) //Hjälpmetod för testning så att man enkelt kan lägga in användare
    {
        this.memberList.add(m);
    }

    public ArrayList<Member> getMembers()
    {
        return memberList;
    }

}
