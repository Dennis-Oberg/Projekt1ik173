package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberManagerTest {

    @Test
    void test_addmember(){

        User d = new User(1, 123, "Dennis", "Öberg", 2, 0);
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        MemberManager memberManager = new MemberManager(d, memberStoreStub);

    }

    @Test
    void test_anal(){
        User d = new User(1, 123, "Dennis", "Öberg", 2, 0);

        MemberStoreStub memberStoreStub = new MemberStoreStub();
        MemberManager memberManager = new MemberManager(d,memberStoreStub);

        memberStoreStub.createNewMember(2, 1996, "Ost", "Ostsson", 1, 0);


    }
}