package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MemberManagerTest {

    User testUser = new User(123,20211224, "Tobias", "Wendel",1);

    @Test
    public void test_checkActiveSuspensionTrue(){
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        MemberManager cut = new MemberManager(testUser, memberStoreStub);

        assertTrue(cut.checkActiveSuspension(testUser));
    }

    @Test
    public void test_checkActiveSuspensionFalse(){
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        testUser.suspended = true;
        MemberManager cut = new MemberManager(testUser, memberStoreStub);

        assertFalse(cut.checkActiveSuspension(testUser));
    }

    @Test
    public void test_checkSuspensionTrue(){
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        MemberManager cut = new MemberManager(testUser, memberStoreStub);

        assertTrue(cut.checkSuspension(testUser));
    }

    @Test
    public void test_checkSuspensionFalse(){
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        testUser.strikes = 3;
        MemberManager cut = new MemberManager(testUser, memberStoreStub);

        assertFalse(cut.checkSuspension(testUser));
    }

    @Test
    public void test_checkBanSuspend(){
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        MemberManager cut = new MemberManager(testUser, memberStoreStub);

        assertTrue(cut.checkBan(testUser));

    }


    @Test
    public void test_checkBanSuspendCountTwo(){ //ska inte bli bannad med suspendCount = 2
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        testUser.suspendedCount = 2;
        MemberManager cut = new MemberManager(testUser, memberStoreStub);

        assertTrue(cut.checkBan(testUser));
    }
    @Test
    public void test_checkBanSuspendCountThree(){ //Ska bli bannand med suspendCount = 3
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        testUser.suspendedCount = 3;
        MemberManager cut = new MemberManager(testUser, memberStoreStub);

        assertFalse(cut.checkBan(testUser));
    }

    @Test
    public void test_removeMember(){
        
    }



}

