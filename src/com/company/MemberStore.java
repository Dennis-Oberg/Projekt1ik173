package com.company;

import java.util.ArrayList;

public class MemberStore {

    ArrayList<Member> memberList;

    public ArrayList<Member> getMembers()
    {
        this.memberList = new ArrayList<>();
        return memberList; //databas kod
    }

}
