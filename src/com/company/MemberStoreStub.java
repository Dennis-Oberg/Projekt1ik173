package com.company;

import java.util.ArrayList;

public class MemberStoreStub extends MemberStore{

    ArrayList<Member> memberList;

    public MemberStoreStub(){
        memberList = new ArrayList<>();


        //ska bort senare
        memberList.add(new Member(1234,1,"Tobias", "Wendel", 1));
        memberList.add(new Member(1235,1,"Tobias", "Wendel", 2));
        memberList.add(new Member(1236,1,"Tobias", "Wendel", 4));
        memberList.add(new Member(4321,1,"Tobias", "Wendel", 5));
    }

    public ArrayList<Member> getMembers()
    {
        return memberList; //databas kod
    }
    public Member getMemberById(int id){

        for (Member m: memberList){
            //Bytas ut mot databas??
            if (m.getIDCode() == id){
                return m;
            }
        }
        return null;
    }

}
