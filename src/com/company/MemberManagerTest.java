package com.company;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


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
    public void test_checkActiveSuspension_shouldBeBanned() throws ParseException {
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        testUser.suspended = true;


        String inputString = "2021-06-15"; // Datum tills man är suspendad
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        testUser.suspensionDate = formatter.parse(inputString);

        MemberManager cut = new MemberManager(testUser, memberStoreStub);

        assertFalse(cut.checkActiveSuspension(testUser));
    }
    @Test
    public void test_checkActiveSuspension_shouldNotBeBanned() throws ParseException {
        MemberStoreStub memberStoreStub = new MemberStoreStub();

        String inputString = "2021-04-30"; //Suspension har gått ut
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        testUser.suspensionDate = formatter.parse(inputString);

        MemberManager cut = new MemberManager(testUser, memberStoreStub);

        assertTrue(cut.checkActiveSuspension(testUser));
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
        BookStoreStub bookStoreStub = new BookStoreStub();

        assertTrue(cut.checkBan(testUser, bookStoreStub));

    }
    
    @Test
    public void test_checkBanSuspendCountTwo(){ //ska inte bli bannad med suspendCount = 2
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        testUser.suspendedCount = 2;
        MemberManager cut = new MemberManager(testUser, memberStoreStub);
        BookStoreStub bookStoreStub = new BookStoreStub();

        assertTrue(cut.checkBan(testUser, bookStoreStub));
    }

    @Test
    public void test_checkBanSuspendCountThree(){ //Ska bli bannand med suspendCount = 3
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        testUser.suspendedCount = 3;
        MemberManager cut = new MemberManager(testUser, memberStoreStub);
        BookStoreStub bookStoreStub = new BookStoreStub();

        assertFalse(cut.checkBan(testUser, bookStoreStub));
    }

    @Test
    public void test_removeMember(){
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        MemberManager cut = new MemberManager(testUser, memberStoreStub);

        User user1 = new User(1,123,"Tobias", "Wendel",1);
        User user2 = new User(2,456,"Tobias", "Wendel",1);
        User user3 = new User(3,789,"Tobias", "Wendel",1);

        memberStoreStub.userList.add(user1);
        memberStoreStub.userList.add(user2);
        memberStoreStub.userList.add(user3);

        cut.removeMember(user2, new BookStoreStub());

        assertEquals(2,memberStoreStub.userList.size());
    }

    @Test
    public void test_removeMember2() {
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        MemberManager cut = new MemberManager(testUser, memberStoreStub);

        User user1 = new User(1,123,"Tobias", "Wendel",1);
        User user2 = new User(2,456,"Tobias", "Wendel",1);
        User user3 = new User(3,789,"Tobias", "Wendel",1);
        User user4 = new User(5,123,"Tobias", "Wendel",1);
        User user5 = new User(123,456,"Tobias", "Wendel",1);
        User user6 = new User(6,789,"Tobias", "Wendel",1);

        memberStoreStub.userList.add(user1);
        memberStoreStub.userList.add(user2);
        memberStoreStub.userList.add(user3);
        memberStoreStub.userList.add(user4);
        memberStoreStub.userList.add(user5);
        memberStoreStub.userList.add(user6);

        cut.removeMember(testUser, new BookStoreStub());

        assertEquals(5,memberStoreStub.userList.size());

    }
    @Test
    public void test_removeMemberWithBorrowedBooks() {
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        MemberManager cut = new MemberManager(testUser, memberStoreStub);
        BookManager mockManager = mock(BookManager.class);

        User user1 = new User(1,123,"Tobias", "Wendel",1);
        User user2 = new User(123,456,"Tobias", "Wendel",1);
        User user3 = new User(3,789,"Tobias", "Wendel",1);
        User user4 = new User(321,789,"Tobias", "Wendel",1);

        when(mockManager.numberOfBorrowedBooks()).thenReturn(1);
        when(mockManager.getMemberLoans()).thenReturn(new  Book[] {
                new Book(123,"1231")
        });

        memberStoreStub.userList.add(user1);
        memberStoreStub.userList.add(user2);
        memberStoreStub.userList.add(user3);
        memberStoreStub.userList.add(user4);

        cut.removeMember(testUser,mockManager);
        //ingen tas bort pga. bok kvar

       assertEquals(4,memberStoreStub.userList.size());
    }


    @Test
    public void mock_checkActiveSuspensionTrue(){
        MemberStore mockMemberStore = mock(MemberStore.class);
        MemberManager cut = new MemberManager(testUser, mockMemberStore);

    }
    @Test
    public void addUserTest(){
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        MemberManager cut = new MemberManager(testUser, memberStoreStub);
        cut.addUser(4,"Musti", "Musti",4);
        assertEquals(1,memberStoreStub.userList.size());
    }

    @Test
    public void searchMemeberTest(){
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        MemberManager cut = new MemberManager(testUser, memberStoreStub);
        User user5 = new User(5,500,"Musti", "Svensson",5);
        memberStoreStub.userList.add(user5);
        assertEquals(user5, cut.searchMember(5));
    }

    @Test
    public void addStrikeTest(){
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        MemberManager cut = new MemberManager(testUser, memberStoreStub);
        cut.addStrike();
        assertEquals(1, testUser.strikes);
    }
    @Test
    public void checkkSsnTestTrue(){
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        MemberManager cut = new MemberManager(testUser, memberStoreStub);
        assertTrue(cut.checkSsn(testUser.SSN));
    }

    @Test
    public void checkkSsnTestFalse(){
        MemberStoreStub memberStoreStub = new MemberStoreStub();
        MemberManager cut = new MemberManager(testUser, memberStoreStub);
        memberStoreStub.moveToBannedMember(testUser);
        assertFalse(cut.checkSsn(testUser.SSN));
    }




    public void fillStub(MemberStoreStub memberStoreStub) {

        memberStoreStub.bannedMember.add(123);
        memberStoreStub.bannedMember.add(1234);
        //ska bort senare
        memberStoreStub.userList.add(new User(1234, 11, "Tobias", "Wendel", 1));
        memberStoreStub.userList.add(new User(1235, 14, "Tobias", "Wendel", 2));
        memberStoreStub.userList.add(new User(1236, 17, "Tobias", "Wendel", 4));
        memberStoreStub.userList.add(new User(4321, 13, "Tobias", "Wendel", 5));
        memberStoreStub.userList.add(new User(5678, 12, "Tobias", "Wendel", 1));
        memberStoreStub.userList.add(new User(4123, 1787, "Tobias", "Wendel", 2));
        memberStoreStub.userList.add(new User(7823, 1324534, "Tobias", "Wendel", 4));
        memberStoreStub.userList.add(new User(1111, 134534, "Tobias", "Wendel", 5));
    }







}

