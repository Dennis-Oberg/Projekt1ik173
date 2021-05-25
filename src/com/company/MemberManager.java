package com.company;

import java.sql.Date;

public class MemberManager {

    User user = null;
    BookManager bookManager;
    MemberStore mStore;

    public MemberManager(User user, MemberStore memberStore) {
        this.user = user;
        this.mStore = memberStore;
    }

    public boolean getMemberStatus(User user){
        boolean b1 = checkActiveSuspension(user);
        boolean b2 = checkSuspension(user);
        boolean b3 = checkBan(user);
        return b1 && b2 && b3;
    }

    public boolean checkActiveSuspension(User user) {

        long millis = System.currentTimeMillis();
        Date currentDate = new Date(millis);

        if (user.suspensionDate != null) {
            if (currentDate.after(user.suspensionDate))
            {
                mStore.removeSuspension(user);
                user.suspended = false;
            }
        }

        if (user.suspended) {
            return false;
        }
        return true;
    }

    public boolean checkSuspension(User user) {
        if (user.getStrikes() == 3) {
            mStore.addSuspension(user);
            user.suspended = true;
            return false;
        }
        return true;
    }

    public boolean checkBan(User user) {

        BookStore bStore = new BookStore();

        if (user.getSuspendedCount() == 3) {

            for (Book b: bStore.getBookByMember(user.getIDCode())
            ) {
                bStore.returnBook(b);
            }

            mStore.moveToBannedMember(user);
            mStore.removeMember(user);
            return false;
        }
        return true;
    }

    public void removeMember(User user) {
        //Måste kolla om man har böcker lånade innan man kan ta bort. Om inte blir det fel i databas
        BookStore bookStore = new BookStore();
        bookManager = new BookManager(user, bookStore);
        int numberOfBooks = bookManager.numberOfBorrowedBooks();

        if (numberOfBooks > 0) {
            System.out.println("Behöver lämna tillbaks följande böcker innan medlem kan tas bort:");
            Book[] books = bookManager.getMemberLoans();
            for (Book book : books) {
                System.out.println(book.getTitle() + " (ISBN: " + book.getIsbn() + ")");

            }
        } else {
            mStore.removeMember(user);
        }
    }

    public User searchMember(int id) {
        user = mStore.getMemberById(id);
        return user;
    }

    public void addUser(int ssn, String fName, String lName, int title) {

        //Ska senare kolla om man varit medlem tidigare eller är bannad
        mStore.creatNewMember(ssn, fName, lName, title);
    }

    public void addStrike() {
        mStore.addStrike(this.user);
    }

    public boolean checkSsn(int ssn) {

        Integer tempSsn = ssn;

        Integer[] bannandeMedlemar = mStore.checkSsn(ssn);

        if (bannandeMedlemar.length == 0){
            return true;
        }
        else {
            for (Integer integer: bannandeMedlemar){
                if (tempSsn.equals(integer)){
                    return false;
                }
            }
            return true;
        }
    }
}