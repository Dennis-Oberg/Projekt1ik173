package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MemberManagerTest {

    User testUser = new User(123,20211224, "Tobias", "Wendel",1);

    @Test
    public void test_checkActiveSuspension(){
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        MemberManager cut = new MemberManager(testUser, memberStoreStub);

        Boolean expected = false;


        assertTrue(cut.checkActiveSuspension(testUser));

    }
    @Test
    void test_anal(){
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        MemberManager cut = new MemberManager(testUser, memberStoreStub);

        cut.addStrike();

    }
}

